<template>
  <div class="whole">
    <div class="enrollPart">
      <div class="enroll">
        <div class="title">
          <span class="title1">用户</span>
          <span class="title2">注册</span>
        </div>
        <el-form :model="user" :rules="rules" ref="user" label-width="100px" class="enroll_form">
          <el-form-item label="用户名" prop="username">
            <el-input
              class="enroll_input"
              v-model="user.username"
              auto-complete="off"
              placeholder="请输入您的用户名"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              class="enroll_input"
              type="password"
              v-model="user.password"
              auto-complete="off"
              placeholder="请输入您的密码"
            ></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="password2">
            <el-input
              class="enroll_input"
              type="password"
              v-model="password2"
              auto-complete="off"
              placeholder="请再次输入您的密码"
            ></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="user.sex" placeholder="请选择" class="enroll_input">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input
              class="enroll_input"
              v-model.number="user.age"
              placeholder="请输入您的年龄"
            ></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input
              class="enroll_input"
              v-model="user.email"
              auto-complete="off"
              placeholder="请输入您的邮箱地址"
            ></el-input>
          </el-form-item>
          <el-form-item label="验证码">
            <el-input class="code_input" v-model="code" auto-complete="off" placeholder="验证码"></el-input>
            <div class="code" @click="refreshCode">
              <Sidentify :identifyCode="identifyCode"></Sidentify>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button class="enroll_bt" type="primary" @click="enroll('user')">注册</el-button>
            <el-button class="button" @click="toLogin()">返回</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
import Sidentify from "@/components/identify";
import qs from "qs";
export default {
  components: {
    Sidentify
  },
  data() {
    return {
      options: [{
          value: '男',
          label: '男'
        }, {
          value: '女',
          label: '女'
        }],
        value: '',
      user: {
        username: "",
        password: "",
        sex: "",
        age: "",
        email: ""
      },
      password2: "",
      code: "",
      identifyCodes: "1234567890",
      identifyCode: "",
      rules: {
        username: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        sex: [{ required: true, message: "请输入性别", trigger: "blur" }],
        age: [{ required: true, message: '年龄不能为空'},
      { type: 'number', message: '年龄必须为数字值'}],
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }]
      }
    };
  },
  methods: {
    toLogin() {
      this.$router.push({ path: "/login" });
    },
    enroll(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log(this.user);
          console.log(this.user.password);
          if(this.user.password==this.password2){
          if (this.identifyCode == this.code) {
            this.$axios
              .post("api/user/insertUser", qs.stringify({userName:this.user.username,password:this.user.password,
              regSex: this.user.sex,
        regAge: this.user.age,
        regEmail: this.user.email}))
              .then(res => {
                if(res.data){
                  this.$message.success("注册成功");
                  this.$router.push("login");
                }else{
                  this.$message.error("账号已存在！");
                  this.refreshCode();
                }
              });
          } else {
            this.$message.error("验证码错误");
            this.refreshCode();
          }
          }else{
             this.$message.error("两次密码输入错误");
          }
        }
      });
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },
    refreshCode() {
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
          this.randomNum(0, this.identifyCodes.length)
        ];
      }
    }
  },
  mounted() {
    this.identifyCode = "";
    this.makeCode(this.identifyCodes, 4);
  }
};
</script>
<style scoped>
.code_input {
  width: 50%;
  float: left;
  height: 60px;
}
.enroll_input {
  width: 80%;
  height: 60px;
}
.el-input >>> .el-input__inner {
  height: 50px;
}
.el-select>>>.el-input__inner{
  height: 50px;
}
.enrollPart {
  border-radius: 10px;
  width: 580px;
  height: 604px;
  position: absolute;
  left: 50%;
  top: 60%;
  transform: translate(-50%, -65%);
  color: #2d2d2d;
  background: rgb(163, 161, 161, 0.2);
}
.el-icon-back {
  font-size: 50px;
}

.title {
  font-size: 30px;
  padding-top: 20px;
  padding-left: 220px;
  margin-bottom: 30px;
}
.title1 {
  color: #00dada;
}
.title2 {
  color: #ff9102;
}
.bottom {
  margin-top: 60px;
  float: right;
  margin-right: 20px;
}
.bottom1 {
  float: right;
  margin-right: 20px;
}
.enroll_bt {
  width: 180px;
  background-color: #00dada;
  border-color: #00dada;
}
.button {
  width: 185px;
  background-color: #00dada;
  border-color: #00dada;
  color: white;
}
.tp {
  margin-left: 50px;
  margin-top: 50px;
}
.sp {
  margin-left: 50px;
  margin-top: 50px;
}
.whole {
  top: 0;
  left: 0;
  background-color: rgb(34, 37, 56);
  background-size: cover;
  width: 100%;
  height: 100%;
  position: absolute;
  overflow: auto;
}
.code {
  width: 35%;
  height: 50px;
  float: left;
  padding-left: 5%;
}
.el-form-item {
  margin-bottom: 0px;
}
</style>