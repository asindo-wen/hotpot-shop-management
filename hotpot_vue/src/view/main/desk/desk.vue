<template>
  <div class="whole">

    <br />
    <div class="title_part">
      <div class="title"><span>桌台管理</span></div>
    </div>
    <br />

    <div style="background:white;">

      <div style="padding:20px 0 0 20px;">
        <span style="padding:7px 0 0 20px;">全部</span>
      </div>

      <el-divider></el-divider>

    <div style="margin-left:1%;margin-right:1%"> 
      <el-row  style="height:400px">            
        <el-col :span="4" v-for="(desk,index) in Desk.slice((currentPage-1)*pagesize,currentPage*pagesize)"
           :key="index" :offset="1" >                
        <div style="margin-top:15px">                    
          <el-card shadow="hover" class="dish-card" body-style="padding:0px"  >                                       
            <div class="setUp" @click="deskDialogVisible=true,deskID=desk.desk_id">                             
                <span>{{desk.desk_name}}</span>        
            </div>
                 
            <div class="status">                                
              <span v-if="desk.status == 0" >空闲</span>
              <span v-else>已开台</span>                                     
            </div>                                      
          </el-card>                
        </div>            
        </el-col>        
      </el-row>

      <el-dialog title="开台" :visible.sync="deskDialogVisible">       
        <span>确定开台?</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="deskDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="toDish()">确 定</el-button>
        </span>
      </el-dialog>

      <div style="width:100%" align="center">
        <el-pagination
          background
          :current-page.sync="currentPage"
          :page-size="pagesize"
          layout="prev, pager, next, jumper"
          :total="Desk.length"
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
      currentPage:1,
      pagesize:8,
      deskDialogVisible:false,
      Desk:[],
      deskID:0,
      
    };
  },
   
  mounted(){
    this.selectAllDesk();
    
  },
    methods: {
    handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    toDeskAdd(){

    },
    toDish(){
        this.$router.push(
        {name:'dish',
        params:{desk_id:this.deskID}});
    },

    selectAllDesk(){
        this.$axios.get("api/desk/selectAllDesk")
          .then(res => {
        this.Desk = res.data;
      })
      .catch(err => {
          console.log(err);
      });
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


    deleteDesk(){
      for(var i=0;i<this.multipleSelection.length;i++){
        this.$axios.post("http://localhost:8080/desk/deleteDesk?desk_id="+this.multipleSelection[i].id)
          .then(res=>{
            alert("删除成功");
            location.reload();  
          })
      }
    }

  }
};
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

.el-button--primary{
  background: tomato;
  border:2px solid ;
  color:whitesmoke;
}

.dish-card {
  width: 220px;
  padding:0px;
  text-align: center;
  color: #2d3436;
  background: #ecf0f1;
}

.setUp{
  display: flex;
  align-items: center;
  justify-content: center;
  height: 60px;
}
.status{
    display: flex;
    align-items: center;
    justify-content: center;
    height: 60px;
    border-top: 2px solid #dfe6e9;
}

</style>