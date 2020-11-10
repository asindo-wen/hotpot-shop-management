<template>
  <div class="whole">

    <br />
    <div class="title_part">
      <div class="title"><span>主页</span></div>
    </div>
    <br />

    <div class="center_part">
      <div class="shop">
          <div class="shop_img">
              <img src="../../assets/hotpot.png" class="image" alt="火锅logo" />
          </div>
          <div class="shop_info">
            <span style="  font-size: 40px;">辣上瘾老火锅总店</span><br />
            <i class="el-icon-phone">15998979173</i><br />
            <i class="el-icon-location-information">重庆市永川区渝西大道159号</i>
          </div>
      </div>
      <br />
      <div class="data_part">
        <div class="data">
          <div class="left_data">
            <span>今日营业额(元)</span>
          </div>
          <div class="right_data">
            <span>{{TotalPrice}}</span>
          </div>
        </div>
        <div class="data">
          <div class="left_data">
            <span>今日订单数</span>
          </div>
          <div class="right_data">
            <span>{{this.Bill.length}}</span>
          </div>
          <br />
          
        </div>
      </div>
      
    </div>
    
 </div>

</template>
<script>
export default {
  data() { 
    return {
      Bill: [],
      totalPrice:0,
    }
  },
  mounted() {
    this.selectByDay();
  },

computed:{
    TotalPrice(){
      return this.Bill.reduce((totalprice, item) => {
              return totalprice + item.bill_price},0)
    }
},
  methods:{
    selectByDay(){
      this.$axios
        .get("api/bills/selectByDay")
        .then(res => {
          this.Bill = res.data;
          // this.totalPrice = this.getTotalPrice(res.data)
        })
        .catch(err => {
          console.log(err);
        });

    },
  }
}
</script>

<style scoped>
.whole {
  padding-left: 280px;
  padding-right: 30px;
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

.center_part{
  background-color:#dfe6e9;
  padding: 0 0 40px 0;
}
.shop{
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  border:2px solid #636e72;
  border-radius: 5px;
  background-color:white;
  height: 180px;
  padding: 0 500px 0 10px;
}
.shop_info{
  flex-grow: 0.5;
  background-color:#dfe6e9;
  border:2px solid #636e72;
  font-size: 22px;
  border-radius: 10px;
  padding:0 0 0 30px;
  height: 120px;
  width: 180px;
}
.shop_img>.image{
  border:2px solid #636e72;
  border-radius: 90px;
  height: 120px;
  width: 120px;
}
.data_part{
  display: flex;
  justify-content: space-evenly;
  height: 250px;

  margin: 50px 0 0 0;
}
.data{
  background-color: white;
  border:2px solid #636e72;

  display: flex;
  justify-content: center;
  border-radius: 5px;
  width: 400px;
}
.left_data{
  display: flex;
  align-items: center; 
  justify-content: center;

  border-radius: 5px;
  width: 200px;
}
.left_data span{
  font-size: 25px;

}
.right_data{
  display: flex;
  align-items: center; 
  justify-content: center;
  border-radius: 5px;
  width: 200px;
}
.right_data span{
  font-size: 60px;
  color:red;
  border-radius: 5px;

}


</style>