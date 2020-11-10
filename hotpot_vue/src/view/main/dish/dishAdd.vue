<template>
  <div class="whole">
    <br />
    <div class="avater_part">
      <div class="title">添加菜品</div>
 <div class="setting_part">
        <el-form validate-on-rule-change ref="dish" 
                :model="dish" 
                :rules="rules"
                label-width="80px" size="mini">
          <div class="rulesCss">
            <el-form-item label="菜品名称" prop="dish_name">
              <el-input v-model="dish.dish_name"></el-input>
            </el-form-item>
          </div>
          
          <div class="rulesCss">
            <el-form-item label="菜品价格" prop="dish_price" >
              <el-input v-model.number="dish_price"></el-input>
            </el-form-item>
          </div>
          <div class="rulesCss">
            <el-form-item label="菜品种类" >
            <el-select v-model="dish_type" prop="dish_type" placeholder="请选择菜品种类">
                <el-option label="汤底" value=1></el-option>
                <el-option label="特色" value=2></el-option>
                <el-option label="荤菜" value=3></el-option>
                <el-option label="素菜" value=4></el-option>
                <el-option label="小吃" value=5></el-option>
                <el-option label="酒水" value=6></el-option>     
            </el-select>
            </el-form-item>
          </div>
 
          <el-form-item size="large">
            <el-button class="submit_button" @click="submitForm('dish')">添加</el-button>
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
    var checkPrice = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('价格不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            
              callback();
            
          }
        }, 500);
      };
    
    return {
       dish:{
           dish_name:"",
           dish_price:"",
           dish_type:0,
       },

       rules: {
        dish_name: [  { required: true, message: '请输入菜品名称', trigger: 'blur' } ],
        dish_price:[{ validator: checkPrice, trigger: "blur" }],
        dish_type: [  { required: true, message: '请选择菜品种类', trigger: 'blur' } ],

      }
     
    };
  },
  methods: {
      submitForm(formName) {
      this.$refs[formName].validate((valid) => {
          if (valid) {
             this.$axios
              .post("http://localhost:8080/dish/insertDish",qs.stringify(this.staff))
            .then(res => {
              if(res.data){
                this.$message.success("添加成功");
                setTimeout(() => {
                  this.toDish()
                },2000)

              }
              else{
                  this.$message.error("添加失败");
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

    goBack(){    
      this.$router.go(-1);
    },
    toDish(){
        this.$router.push('/dish')
    }

  },

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
.option_part {
  padding-top: 10px;
  height: 250px;
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
  background-color: rgb(23, 162, 184);
}
.avater_part {
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: white;
}
.title {
  background-color: rgb(23, 162, 184);
  color: white;
  font-size: 15px;
  height: 30px;
  padding-top: 10px;
  padding-left: 30px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}
</style>
