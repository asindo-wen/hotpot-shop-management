<template>
  <div class="whole">
    <br />
    <div class="avater_part">
      <div class="title">信息修改</div>
      <div class="setting_part">
        <el-form validate-on-rule-change ref="staff" 
                :model="staff" 
                :rules="rules"
                label-width="80px" size="mini">
          <div class="rulesCss">
            <el-form-item label="用户名" prop="staff_name">
              <el-input v-model="staff.staff_name"></el-input>
            </el-form-item>
          </div>
          
          <div class="rulesCss">
            <el-form-item label="年龄" prop="age" >
              <el-input v-model.number="staff.age"></el-input>
            </el-form-item>
          </div>

          <el-form-item label="性别" >
            <el-select v-model="staff.sex" placeholder="请选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>

          <div class="rulesCss">
            <el-form-item label="电话" prop="phone">
              <el-input v-model.number="staff.phone"></el-input>
            </el-form-item>
          </div>

          <el-form-item size="large">
            <el-button class="submit_button" @click="submitForm('staff')">提交</el-button>
             <el-button  @click="goBack()">返回</el-button>
          </el-form-item>

        </el-form>
      </div>
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
    var checkPhone = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('电话号码不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            if (value.toString().length != 11) {
              callback(new Error('必须输入11位数电话号码'));
            } else {
              callback();
            }
          }
        }, 500);
      };
    return {
      staff:{
        staff_name:"",
        age:"",
        sex:"",
        phone:""
      },

       rules: {
        staff_name: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 2, max: 8, message: '长度在 2 到 8个字符', trigger: 'blur' }
          ],
        phone:[{ validator: checkPhone, trigger: "blur" }],
        age:[{ validator: checkAge, trigger: "blur" }],

      }
     
    };
  },
  props:['staff_id'],

  mounted() {
      this.selectOneById()

  },

  methods: {     
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
          if (valid) {
             this.$axios
            .post("http://localhost:8080/staff/updateStaff",qs.stringify(this.staff))
            .then(res => {
              if(res.data){
              this.$message.success("修改成功");
              this.selectOneById();
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

    selectOneById(){
      // console.log(this.$route.params.staff_id);
        this.$axios.get("http://localhost:8080/staff/selectOneById?staff_id="+this.$route.params.staff_id)
        .then(res => {
          this.staff = res.data
        })
        .catch(err => {
          console.log(err);
        });
      },
    goBack(){
      
      this.$router.go(-1);
    }
    
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
</style>
