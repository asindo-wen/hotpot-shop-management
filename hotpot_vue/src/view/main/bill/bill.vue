<template>
  <div class="whole">
    <br />
    <div class="title_part">
      <div class="title">查询订单</div>
    </div>
    <div class="search_part">
        <span>时间日期查询</span>
            <el-date-picker
              class="search_item"
              v-model="value"
              type="daterange"
              align="right"
              unlink-panels
              format="yyyy-MM-dd" value-format="yyyy-MM-dd HH:mm:ss"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :picker-options="pickerOptions">
            </el-date-picker>
         <el-button class="search_button" size="medium" @click="selectByTime()">查询</el-button>

      </div>
    <br />
    <div style="background:white;">
      <div style="padding:20px 0 0 20px;">
        <span style="padding:7px 0 0 20px;">当前：所有订单</span>
        <div style="float:right;padding:0 60px 0 0">
          <el-button type="danger" size="mini" @click="deleteBill()">删除</el-button>
        </div>
      </div>
      <el-divider></el-divider>
      <!-- 订单信息展示 -->
      <el-table
        ref="multipleTable"
        :data="Bill.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        :header-cell-style="{textAlign: 'center'}"
        :cell-style="{textAlign: 'center'}"        
      >
     
        <el-table-column prop="bill_id" label="ID"> </el-table-column>
        <el-table-column prop="desk_id" label="桌台"></el-table-column>
        <el-table-column prop="bill_number" label="订单编号"></el-table-column>
        <el-table-column prop="bill_price" label="订单总价"></el-table-column>
        <el-table-column prop="bill_status" label="订单状态">
            <span v-if="Bill.bill_status == 0" >未结账</span>
            <span v-else>已结账</span> 
        </el-table-column>
        <el-table-column prop="bill_date" label="创立时间"></el-table-column>

         <el-table-column label="操作">
          	<template slot-scope="scope">
              <el-button type="sucess" size="small"  @click="selectBillDetail(scope.row.bill_id),billDetailDialog=true">查看明细</el-button>
          	</template>
        </el-table-column>
      </el-table>
      <!-- 订单明细Dialog -->
      <el-dialog title="订单明细" :visible.sync="billDetailDialog">
        <el-table
          :data="BillDetail"
         >
          <el-table-column prop="dish_id" label="菜品编号" > </el-table-column>
          <el-table-column prop="dish_name" label="菜品名称" > </el-table-column>
          <el-table-column prop="dish_price" label="菜品价格" > </el-table-column>
          <el-table-column prop="dish_amount" label="菜品数量" > </el-table-column>
          <el-table-column label="小计" > 
              <template slot-scope="scope">
                  {{dishPrice(scope.row.dish_price,scope.row.dish_amount)}}
              </template>
          </el-table-column>
        </el-table>
      </el-dialog>       
      <br />
      <div style="width:100%" align="center">
        <el-pagination
          background
          :current-page.sync="currentPage"
          :page-size="pagesize"
          layout="prev, pager, next, jumper"
          :total="Bill.length"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import qs from "qs";
export default {
  data() {
    return {
      search_number: "",
      currentPage: 1, //当前页
      pagesize: 4, //每页数据条数
      Bill: [],
      billId:"",
      BillDetail:[],
      billDetailDialog:false,
      pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value: '',

      }
  },
  mounted() {
    this.selectBill();
  },

  computed:{
    
  },
  methods: {
    dishPrice(price,amount){
      return price*amount;  
    },
    selectBillDetail(bill_id) {
      this.$axios
        .get("api/billdetail/selectOne?bill_id=" + bill_id)
        .then(res => {
          this.BillDetail = res.data;

        })
        .catch(err => {
          console.log(err);
        });
    },
    selectBill() {
      this.$axios
        .get("api/bills/selectAll")
        .then(res => {
          this.Bill = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },

    selectByTime() {
      if(this.value != null){
        this.$axios
        .get("http://localhost:8080/bills/selectByTime?starTime=" + this.value[0]+"&endTime="+ this.value[1])
        .then(res => {
          this.Bill = res.data;
        })
        .catch(err => {
          console.log(err);
        });
      }else{
        this.selectBill()
      }

    },

    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

     deleteBill(){
      for(var i=0;i<this.multipleSelection.length;i++){
        this.$axios.post("http://localhost:8080/bills/deleteBills?bill_id="+this.multipleSelection[i].bill_id)
          .then(res=>{
            alert("删除成功");
            location.reload();  
          })
      }
    },
  }
};
</script>

<style scoped>
.whole {
  padding-left: 280px;
  padding-right: 30px;
}

.search_part {
  display: flex;
  align-items: center;
  justify-content:flex-start;
  padding: 10px 0 0 30px;
  height: 80px;
  border-radius: 10px 0;
  background-color: white;
}
.search_item{
  margin: 0 0 0 50px;
}
.search_button {
  margin: 0 0 0 50px;
  color: white;
  background-color:	#FF4500;
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