<template>
  <div class="whole">
    <br />
    <div class="title_part">
      <div class="title">个人设置</div>
    </div>
    <br />
    <div class="avater_part">
      <div class="setting_part">
        <el-form  :validate-on-rule-change="false" ref="user" :model="user"
                  :rules="rules" 
                  label-width="80px" size="mini">
          <div class="rulesCss">       
            <el-form-item label="用户名" prop="user_name">
              <el-input v-model="user.user_name"></el-input>
            </el-form-item>
          </div>

          <el-form-item label="性别" prop="sex">
            <el-select v-model="user.sex" placeholder="请选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>

          <div class="rulesCss">
            <el-form-item label="年龄" prop="age">
              <el-input type="age" v-model.number="user.age"></el-input>
            </el-form-item>
          </div>

           <el-form-item >
            <el-button @click="passwordFormVisible = true">修改密码</el-button>
          </el-form-item>

          <el-form-item size="large">
            <el-button class="submit_button" @click="submitForm('user')">提交</el-button>
          </el-form-item>


        </el-form>

      </div>

    <el-dialog title="修改密码" :visible.sync="passwordFormVisible">
      <el-form
        :model="passwordForm"
        status-icon
        :rules="rules"
        ref="passwordForm"
        label-width="80px"
        class="demo-ruleForm"
      >
        <el-form-item label="旧的密码"  prop="oldpassword">
          <el-input type="password" v-model="passwordForm.oldPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="passwordForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="passwordForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="passwordFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updatePassword('passwordForm')">确 定</el-button>
      </div>
    </el-dialog>

    </div>
  </div>
</template>
<script>
import qs from "qs";
export default {
  data() {
      var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('年龄不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            if (value < 18) {
              callback(new Error('必须年满18岁'));
            } else {
              callback();
            }
          }
        }, 500);
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.passwordForm.checkPass !== '') {
            this.$refs.passwordForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.passwordForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
    return {
      user:{
        user_name:"",    
        sex:"",
        age:0,
      },
      passwordFormVisible:false,
      passwordForm: {
        oldPass: "",
        pass: "",
        checkPass: ""
      },
      rules: {
        user_name: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 2, max: 8, message: '长度在 2 到 8个字符', trigger: 'blur' }
          ],
        age:[{ validator: checkAge, trigger: "blur" }],
        pass: [{ validator: validatePass, trigger: "blur" }],
        checkPass: [{ validator: validatePass2, trigger: "blur" }]
      }
    };
  },
  mounted() {
        this.user=JSON.parse(window.localStorage.getItem("user"))
  },
  methods: {
    submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
             this.$axios.post("api/user/updateuser",qs.stringify(this.user))
        .then(res => {
          if(res.data){
           this.$message.success("修改成功");          
           this.selectonebyid();
           }
           else{
            this.$message.error("修改失败");
           }
        })
        .catch(err => {
          console.log(err);
        });


          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },

    selectonebyid(){
      this.$axios.get("http://localhost:8080/user/selectonebyid?user_id="+this.user.user_id)
      .then(res => {
        window.localStorage.setItem("user",JSON.stringify(res.data))
        this.user = res.data

      })
      .catch(err => {
        console.log(err);
      });
    },

    updatePassword(formName){
      this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios
              .post("api/user/updatepassword", qs.stringify({oldpassword:this.passwordForm.oldPass,
                password:this.passwordForm.pass,user_id:this.user.user_id}), {
                headers: {
                  "Content-Type": "application/x-www-form-urlencoded"
                }
              })
              .then(res => {
                if(res.data){
                  this.$message.success("修改成功");
                  this.passwordFormVisible=false;
                  }else
                  this.$message.error("旧密码输入错误");
              })
              .catch(err => {
                  this.$message.error("密码错误");
              });

              } else {
        console.log('error submit!!');
        return false;
      }
    });
  },
  
  }
};
</script>
<style scoped>
.whole {
  padding-left: 280px;
  padding-right: 30px;
}
.el-form-item--mini.el-form-item,
.el-form-item--small.el-form-item{
  height: 29px;
}
.rulesCss /deep/ .el-form-item--mini .el-form-item__content, .el-form-item--mini .el-form-item__label{
  height: 32px;
}
.el-input--mini {
  width: 90%;
}
.submit_button .change_button {
  float: right;
  margin-right: 20px;
  margin-top: 3px;
  color: white;
  background-color: rgb(23, 162, 184);
}
.setting_part {
  padding-top: 10px;
  height: 250px;
}
.submit_button{
  margin-right: 20px;
  margin-top: 3px;
  color: white;
  background-color:#ff7675;
}
.avater_part {
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: white;
}
.option_part {
  margin: 10px 0 20px 100px;
  height: 100px;
  width: 200px;
  background: aqua;
}
.title_part{
  color: white; 
  background-color:#fab1a0;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;

}
.title{
  font-size: 22px;
  display: flex;
  align-items: center;
  padding: 0 0 0 30px;
  vertical-align:middle;
  height: 60px;
  width: 300px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader {
  margin-left: 41%;
  margin-top: 10px;
}
.avatar-uploader-icon {
  margin-top: 10px;
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  border: 1px solid rgb(182, 182, 182);
  margin-left: 10px;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
} 
</style>
