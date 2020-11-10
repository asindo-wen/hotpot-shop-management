<template>
  <div class="whole">
    <br />
   <div class="title_part"> 
     <div class="title">菜品下单</div>
   </div>
    <br />

    <div class="dishType">
      <!-- 菜品种类 -->
      <el-menu  class="typeMenu" mode="horizontal" >
        <el-menu-item  @click="selectAll">全部</el-menu-item>
        <el-menu-item  @click="selectByType(1)">汤底</el-menu-item>
        <el-menu-item  @click="selectByType(2)">特色</el-menu-item>
        <el-menu-item  @click="selectByType(3)">荤菜</el-menu-item>
        <el-menu-item  @click="selectByType(4)">素菜</el-menu-item>
        <el-menu-item  @click="selectByType(5)">小吃</el-menu-item>
        <el-menu-item  @click="selectByType(6)">酒水</el-menu-item>
      </el-menu>

      <el-divider content-position="right" >
        <el-button type="add" size="mini" @click="cartDialogVisible=true">已下单菜品</el-button>
      </el-divider>

    <div style="margin-left:1%;margin-right:1%">
      <!-- 菜品信息 -->
      <el-row class="rowCss">            
        <el-col :span="2" v-for="(dish,index) in Dish.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                :key="index" :offset="1" >                
        <div style="margin-top:15px">                    
          <el-card class="dish-card" body-style="padding: 0px" shadow="hover" >                                       
            <div class="setUp" @click="deskDialogVisible=true">                    
              <span>{{dish.dish_name}}</span> <br />
              <span>{{dish.dish_price}}</span> 
            </div>
                              
            <div class="add_dish">                                
              <el-button type="order" @click="addToCart(dish.dish_id,dish.dish_name,dish.dish_price)">
                <i class="el-icon-circle-plus"></i>  
              </el-button>                            
            </div>                                      
          </el-card>                
        </div>            
        </el-col> 
      </el-row>
      <!-- 已点菜品 -->
      <el-dialog title="已点菜品" :visible.sync="cartDialogVisible"> 

        <el-table :data="cartDishs" style="width: 100%">
		    <el-table-column prop="dish_id" label="菜品ID" width="80"></el-table-column>
		    <el-table-column prop="dish_name" label="菜品名称" width="120"></el-table-column>
		    <el-table-column label="数量" width="180">
          <template slot-scope="scope">
					<el-input-number size="mini" :min="1" :value="scope.row.dish_amount" v-on:input="handleBlur" @change="handleChange( scope.row )"></el-input-number>
		    	</template>  
		    </el-table-column>
		    <el-table-column prop="dish_price" label="菜品价格" width="120"></el-table-column>
		    <el-table-column prop="total_price" label="总价" width="180"></el-table-column>
		    <el-table-column label="操作" width="120">
		    	<template slot-scope="scope">
			        <el-button type="danger" plain icon="el-icon-delete" size="mini" @click="deldialogVisibleTrue( scope.row )">删除</el-button>
			    </template>
		    </el-table-column>
        </el-table>

        <div class="info">
          <el-row :gutter="20" class="total">
              <el-col :span="6" :offset="6">总数：{{totalNum()}}</el-col>
              <el-col :span="6" :offset="2">合计价格：{{totalPrice()}}</el-col>
          </el-row>
          <el-row class="operate">
              <el-col :span="6" :offset="14">
                <el-button type="danger" size="mini" icon="el-icon-delete" @click="delCartDialog=true">清空待结算清单</el-button>
              </el-col>
              <el-col :span="4">
                <el-button type="add" size="middle" icon="el-icon-shopping-cart-2" @click="handleInsert">下单 </el-button>
              </el-col>
          </el-row>
          <!-- 清空所有菜品确认弹框 -->
          <el-dialog title="注意" 
              :visible.sync="delCartDialog" 
              width="20%"
              append-to-body>
              <span>要清空购物车吗?</span>
              <span slot="footer" class="dialog-footer">
                <el-button @click="delCartDialog = false">取 消</el-button>
                <el-button type="primary" @click="dialogDeleteAll">确 定</el-button>
              </span>
          </el-dialog>
        </div>
        <!-- 删除菜品确认弹框 -->
        <el-dialog title="注意" 
            :visible.sync="deldialogVisible" 
            width="20%"
            append-to-body>
            <span>确定要删除这个商品吗?</span>
            <span slot="footer" class="dialog-footer">
              <el-button @click="deldialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="dialogSure">确 定</el-button>
            </span>
        </el-dialog>
      </el-dialog>
      <!-- 分页条 -->
      <div style="width:100%" align="center">
        <el-pagination
          background
          :current-page.sync="currentPage"
          :page-size="pagesize"
          layout="prev, pager, next, jumper"
          :total="Dish.length"
        ></el-pagination>
      </div>
    </div>
      <br />
    </div>
  </div>
</template>
<script>
import qs from "qs";

export default {
  data() {
    return {
      cartDialogVisible:false,
      deldialogVisible:false,
      delCartDialog:false,
      Dish:[],   
      currentPage:1,
      pagesize:24,
      cartDishs:[],
      result : null,
      input_number_value:1,
      desk_id:this.$route.params.desk_id,
      billNumber:0,
      billId:0,
      bill:{
        bill_id:0,
      }
    };
  },
  mounted(){
    this.selectAll();
    // console.log(this.desk_id);
  },

    methods: {
      // 加入点菜栏
    addToCart(id,name,price){
      this.cartDishs.push({
        dish_id : id,
        dish_name : name,
        dish_price : price,
        dish_amount:1,
        total_price:price
      })
    },
    insertBills(){
        this.billNumber  = (new Date()).valueOf();
        this.$axios.post("http://localhost:8080/bills/insertBills?desk_id="+this.desk_id
                          +"&bill_number="+this.billNumber+"&bill_price="+this.totalPrice()+"&bill_status="+1)
          .then(res => {
          if(res.data){
            this.$message.success("订单添加成功")
            this.selectByNumber(this.billNumber)
          }
          else{
              this.$message.error("添加失败");
          }
        })
        .then(res => {

        }) 
        .then(res =>{
            setTimeout(() => {
              this.toDesk()
            },2000)
        })
        .catch(err => {
          console.log(err);
        });
    },
    insertBillDetail(id){
      for(var i=0;i<this.cartDishs.length;i++){
        this.$axios.post("http://localhost:8080/billdetail/insertBillDetail?bill_id="+id
                          +"&dish_id="+this.cartDishs[i].dish_id+"&dish_name="+this.cartDishs[i].dish_name
                          +"&dish_price="+this.cartDishs[i].dish_price+"&dish_amount="+this.cartDishs[i].dish_amount)
          .then(res=>{
            if(res.data){
            console.log(id )         

            }else{
              this.$message.error("添加失败");
          }
          })
          .catch(err => {
            console.log(err);
        });
      }
           this.$message.success("订单详情添加成功"); 
    },
    selectByNumber(number){
        this.$axios.get("http://localhost:8080/bills/selectByNumber?bill_number="+number)
          .then(res => {
            console.log(number) ;           
            this.bill = res.data;
            this.billId = this.bill.bill_id;
            console.log(this.billId );
            this.insertBillDetail(this.bill.bill_id);
      })
      .catch(err => {
          console.log(err);
      });
    },
    handleInsert(){
      this.insertBills();
      this.cartDialogVisible = false
    },

    selectAll(){
        this.$axios.get("api/dish/selectAll")
          .then(res => {
        this.Dish = res.data;
      })
      .catch(err => {
          console.log(err);
      });
    },

    selectByType(id){
        this.$axios.get("api/dish/selectByType?type_id="+id)
          .then(res => {
        this.Dish = res.data;
      })
      .catch(err => {
          console.log(err);
      });
    },

    toDesk(){
        this.$router.push({name:'desk'})
    },
    //计算总数量
    totalNum (){
    let total = 0;
    this.cartDishs.forEach( item => {
        total += item.dish_amount
    })
    return total;
    },
    // 计算总价格
    totalPrice(){
    let total = 0;
    this.cartDishs.forEach( item => {
        total += item.dish_price * item.dish_amount
    })
    return total.toFixed(2);
},

    deldialogVisibleTrue( data ){
			this.deldialogVisible = true;
			this.result = data;
		},
		dialogSure(){      
      this.delCartDish( this.result );
      this.deldialogVisible = false;
      this.$message.success("删除成功");
    },
    delCartDish(data){
      this.cartDishs.forEach( ( item, index ) => {
            if( item.dish_id == data.dish_id ){
                //找到index的下标值
                this.cartDishs.splice( index, 1 );
            }
        })
    },
    handleBlur(value){
      this.input_number_value = value
    },
    handleChange( data ) {
      data.dish_amount = this.input_number_value;
      this.priceChange( data );
    },
    priceChange( data ){
      data.total_price = data.dish_amount*data.dish_price
    },
    dialogDeleteAll(){
      this.cartDishs= []
      this.delCartDialog = false
      this.cartDialogVisible = false
      this.$message.success("清空成功");
    }
}
};
</script>

<style scoped>
.whole {
  padding-left: 280px;
  padding-right: 30px;
}
.dishType{
  background: white;
}
.typeMenu{
  background: tomato;
  width:100%;
  height: 60px;
}
.typeMenu>.el-menu-item{
    color:#f5f5f5;
}

.el-button--add{
  background: #ff7675;
  color:whitesmoke;
  width: 100px;
  height: 40px;
}
.el-button--primary{
  background: tomato;
  color:whitesmoke;
}
.el-button--order{
  display: flex;
  align-items: center;
  justify-content: center;
  height: 30px;
  width: 30px;
  background: #ecf0f1;
  color:#fab1a0;
  border: 1px solid black;
  margin: 0px;
}
.el-button--order:hover{
  background: #636e72;
}
.rowCss{
  width:100%;
  height: 400px;
}
.dish-card {
  width: 100px;
  height: 100px;
  padding:0px;
  border-radius: 10px;
  text-align: center;
  color: black;
  background: #ecf0f1;
  position: relative;
}

.setUp{
    margin: 0px;
    background: #fab1a0;
}
.add_dish{
  width: 50px;
  height: 50px;
  position: absolute;
  margin-top: 10px;
  margin-left:30px;
  border-top:2px solid #dfe6e9 ;
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
.info>.total{
  height: 50px;
  font-size: 18px;
  color: red;
}
.info>.operate{
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
}


</style>