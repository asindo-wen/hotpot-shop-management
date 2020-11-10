<template>
  <div class="whole">
    <div class="loginPart">
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="loginForm"
      >
        <span class="title">
          <span class="title1">辣上瘾</span>
          <span class="title2">火锅店</span>
        </span>
        <br />
        <br />

          <el-form-item label="账号" prop="username">
            <el-input v-model="ruleForm.username" auto-complete="off" placeholder="请输入您的用户名"></el-input>
          </el-form-item>
          <el-form-item label="密码"  prop="password">
            <el-input
              type="password" v-model="ruleForm.password" auto-complete="off" placeholder="请输入您的密码"
            ></el-input>
          </el-form-item>

        
        <div class="bottom">
          <el-form-item>
            <el-button type="primary" @click="login()">登陆</el-button>
            <!-- <el-button type="button" @click="toEnroll()">注册</el-button> -->
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script>
import qs from "qs";
export default {
  data() {
    return {
      ruleForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      }
    };
  },

  methods: {
    toEnroll(){
        this.$router.push({path:'/enroll'})
      },
    login() {
      this.$axios
        .post("/api/Login/login", qs.stringify({user_name:this.ruleForm.username,password:this.ruleForm.password}), {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          }
        })
        .then(res => {
          console.log(res.data)
          window.localStorage.setItem("user",JSON.stringify(res.data))
          this.$router.push({name:'home' });
        })
        .catch(err => {
          this.$message.error("账号或密码错误");
        });
    },
  }
};
</script>

<style scoped>
.bottom{
  position: absolute;
  left: -30px;
}
.el-button--primary{
  width: 340px;
  background-color:  #e66767;
  border-color:#e15f41;
  color:white;

}
.el-button--button{
  width: 170px;
  background-color:  #e66767;
  border-color: #e15f41;
  color:white;
}


.whole {
  top: 0;
  left: 0;
  background-color: #f7f1e3;
  background-size: cover;
  width: 100%;
  height: 100%;
  position: absolute;
  overflow: auto;
}
.loginPart {
  border-radius: 10px;
  width: 480px;
  height: 350px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -65%);
  background:#f8c291;

}

.loginForm {
  padding-top: 10%;
  margin-right: 60px;
}

.title {
  font-size: 30px;
  padding-left: 140px;
  margin-bottom: 30px;
}
.title1 {
  color: #c0392b
}
.title2 {
  color: #e74c3c
}
.loginForm >>> .el-form-item__label {
  line-height: 50px;
  color: #535c68
}
.el-input >>> .el-input__inner {
  height: 50px;
}
</style>
