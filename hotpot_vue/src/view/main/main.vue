<template>
  <div class="whole">   
    <div class="title">
      <div class="left_title">
        <img src="../../assets/hotpot.png" class="image1" alt="火锅logo" />
        <span class="title1">辣上瘾</span>
        <span class="title2">老火锅</span>
      </div>

      <div class="right_title">
        <span class="right_part">
            <i class="el-icon-user">欢迎登录:{{user.user_name}}</i>
        </span>
        <span class="right_part">
          <el-button class="logoutBT" type="text" @click="logout()">
            <i class="el-icon-switch-button">退出登录</i>
          </el-button>
        </span>
      </div>

    </div>

    <div class="center_part">
      <div class="menu">
        <el-menu :default-active="this.$route.path" 
                  router mode="horizontal"          
                   >
          <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
            <i class="el-icon-star-on"></i>
            <span slot="title">{{ item.navItem }}</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div class="main_part">
        <router-view></router-view>
      </div>
    </div> <!-- center_part -->
  </div>
</template>
<script>
import qs from "qs";
export default {
  data() { 
    return {
      user_navList: [
        {name : "/home", navItem: "主页" }, 
        { name: "/desk", navItem: "桌台管理" },
        { name: "/bill", navItem: "订单管理" },
        { name: "/staff", navItem: "员工管理" },
        { name: "/salary", navItem: "薪资管理" },
      ],
      manage_navList: [
        {name : "/home", navItem: "主页" },       
        {name : "/desk", navItem: "桌台管理" },
        { name: "/bill", navItem: "订单管理" },
        { name: "/staff", navItem: "员工管理" },
        { name: "/salary", navItem: "薪资管理" },
        { name: "/personalOption", navItem: "个人设置" },
      ],
      navList:[],
      user:{},
   };
  },

  methods: {
    logout(){
    this.$router.push("/login");//返回上一层
  },
    //权限验证
    generateList() {
      if (this.user.user_type == 1) {
        this.navList=this.manage_navList;
      }else{
        this.navList=this.user_navList;
      }
    }
  },
  mounted() {
    this.user=JSON.parse(window.localStorage.getItem("user"))
    this.generateList(); //调用上面那个赋值的方法
    this.id=this.user.user_id;
    localStorage.setItem("id",this.id);  
  }
};
</script>
<style scoped>
.title {
  background-color: #f7f1e3;
  height: 60px;
  border-width: 1px;
  border-color:#34495e;
}

.left_title {
  background-color: white;
  height: 60px;
  width: 250px;
  font-size: 20px;
  font-weight: bolder;
}
.title1 {
  color: #c0392b;
  position: relative;
  font-size: 30px;
  bottom: 20px;
}
.title2 {
  color: #e74c3c;
  position: relative;
  bottom: 15px;
}
.right_title {
  font-size: 15px;
  color: #34495e;
  width: 300px;
  position: relative;
  top: -50px;
  float: right;
}
.right_part {
  margin-left: 20px;
}
.center_part{
  height: 650px;
}
  .menu {
  width: 250px;
  height: 100%;
  color: #ecf0f1;
  float: left;
  }
  .el-menu {
    height: 650px;
    background-color:#f7f1e3;

  }
  .main_part {
  height: 650px;
  width: 100%;
  background-color: rgb(241, 242, 247);
 }

.el-menu--horizontal>.el-menu-item{
  border-bottom: 0px;
}
.changePFVisble {
  font: 20px;
}

.el-menu-item.is-active {
  background-color: #e74c3c !important;
  color: white !important;
}
.el-menu-item {
  width: 250px;
}



.image1{
  
  height: 60px;
  width: 60px;
}


.el-icon-setting {
  font-size: 15px;
  color: #34495e;
}
.el-icon-switch-button {
  font-size: 15px;
  color: #34495e;
}
</style>
