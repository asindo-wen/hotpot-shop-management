<template>
  <div class="whole">
    <br />
    <div class="avater_part">
      <div class="title">添加员工薪资</div>
      <div class="setting_part">
        <el-form validate-on-rule-change ref="salary" 
                :model="salary" 
                :rules="rules"
                label-width="80px" size="mini">

          <div class="rulesCss">
            <el-form-item label="员工编号" prop="staff_id">
              <el-input v-model.number="salary.staff_id"></el-input>
            </el-form-item>
          </div>

          <div class="rulesCss">
            <el-form-item label="员工姓名" prop="staff_name">
              <el-input v-model="salary.staff_name"></el-input>
            </el-form-item>
          </div>
          
          <div class="rulesCss">
            <el-form-item label="基础工资" prop="basicSalary" >
              <el-input v-model.number="salary.basicSalary"></el-input>
            </el-form-item>
          </div>

          <div class="rulesCss">
            <el-form-item label="奖金" prop="bonus" >
              <el-input v-model.number="salary.bonus"></el-input>
            </el-form-item>
          </div>          

          <el-form-item size="large">
            <el-button type="add" @click="submitForm('salary')">提交</el-button>
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
    return {
      salary:{
        staff_id:"",
        staff_name:"",
        basicSalary:"",
        bonus:"",
      },
      rules: {
        staff_id:[{ required: true, message: '请输入员工编号', trigger: 'blur' },],
        staff_name: [
            { required: true, message: '请输入员工姓名', trigger: 'blur' },
            { min: 2, max: 8, message: '长度在 2 到 8个字符'} ],
        basicSalary:[
            { required: true, message: '请输入基本工资', trigger: 'blur' },
            { type: 'number', message: '基本工资必须为数字值'}
        ],
        bonus:{ type: 'number', message: '奖金必须为数字值'}

      }
     
    };
  },
  methods: {
      submitForm(formName) {
      this.$refs[formName].validate((valid) => {
          if (valid) {
             this.$axios
              .post("http://localhost:8080/salary/insertsalary",qs.stringify(this.salary))
            .then(res => {
              if(res.data){
                this.$message.success("添加成功");
                setTimeout(() => {
                  this.toSalary()
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
    toSalary(){
        this.$router.push('/salary')
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
.title_part{
  color: white; 
  background-color:#fab1a0;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;

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
.el-button--add{
  background:#ff7f50;
  color:white;
}
.el-button--update{
  background:#ff9f43;
  color:white;
}
.el-button--delete{
  background:#ff6348;
  color:white;
}

</style>
