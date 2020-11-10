<template>
  <div class="whole">
    <br />
    <div class="title_part">
      <div class="title">员工管理</div>
    </div>
    <div class="search_part">
        <span class="search_condition">员工姓名:</span>
        <el-input class="search_input" size="max" v-model="search_result" placeholder="请输入员工姓名"></el-input>
        <el-button class="search_button" size="medium" @click="selectResultLike()">查询</el-button>
    </div>
    <br />

    <div style="background:white;">
      <div style="padding:20px 0 0 20px;">
        <span style="padding:7px 0 0 20px;">全部</span>
        <div style="float:right;padding:0 60px 0 0">
          <el-button type="add" size="large" @click="toStaffAdd">新增</el-button>
          <el-button type="delete" size="large" icon="el-icon-delete" 
                        @click="deleteStaff">删除</el-button>  
        </div>
      </div>
      
      <!-- <el-dialog title="删除" :visible.sync="deleteVisible">       
        <span>确定删除?</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="deleteVisible = false">取 消</el-button>
          <el-button type="add" size="mini" @click="deleteStaff()">确 定</el-button>
        </span>
      </el-dialog> -->
      
      <el-divider></el-divider>

      <el-table
        ref="multipleTable"
        :data="Staff.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        tooltip-effect="dark"
        style="width: 100%" 
        @selection-change="handleSelectionChange"      
        :border="true"
        :header-cell-style="{textAlign: 'center'}" 
        :cell-style="{textAlign: 'center'}"
      >
        <el-table-column name="id" type="selection" align="center"></el-table-column>
        <el-table-column prop="staff_id" label="编号"> </el-table-column>
        <el-table-column prop="staff_name" label="姓名"> </el-table-column>
        <el-table-column prop="age" label="年龄"></el-table-column>
        <el-table-column prop="sex" label="性别"></el-table-column>
        <el-table-column prop="phone" label="电话" ></el-table-column>
        <el-table-column prop="regTime" label="注册时间"></el-table-column>

         <el-table-column width="230px" label="操作">
          	<template slot-scope="scope">
              <el-button type="update" size="small"  @click="toStaffEdit(scope.row.staff_id)">编辑</el-button>
              
          	</template>
        </el-table-column>
      </el-table>

      <br />

      <div style="width:100%" align="center">
        <el-pagination
          background
          :current-page.sync="currentPage"
          :page-size="pagesize"
          layout="prev, pager, next, jumper"
          :total="Staff.length"
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
      search_result:'',
      deleteVisible: false,
      currentPage: 1, //当前页
      pagesize: 4, //每页数据条数
      Staff:[],
      multipleSelection:[],

    };
  },
   
  mounted(){
    this.selectAllStaff();
  },
    methods: {
    //更改员工信息
    toStaffEdit(id){
      this.$router.push(
        {name:'staffEdit',
         params: {staff_id:id}});
    },
    //增加员工
    toStaffAdd(){
      this.$router.push({name:'staffAdd'});
    },
    //获取员工信息表
    selectAllStaff(){
      this.$axios.get("/api/staff/selectAllStaff")
        .then(res => {this.Staff = res.data})
        .catch(err => {
          console.log(err);
          });

    },
    //删除员工
    //   deleteStaff(id){
    //     this.$axios.post("http://localhost:8080/staff/deleteStaff?staff_id="+id)
    //       .then(res=>{
    //         if(res.data){
    //           this.$message.success("删除成功");

    //         }else{
    //           this.$message.error("删除失败");
    //        }           
    //         location.reload();  
    //       }) 
    // },
    
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
    deleteStaff(){
      for(var i=0;i<this.multipleSelection.length;i++){
        this.$axios.post("http://localhost:8080/staff/deleteStaff?staff_id="+this.multipleSelection[i].staff_id)
          .then(res=>{
            if(res.data){
              this.$message.success("删除成功");

            }else{
              this.$message.error("删除失败");
           }            
            location.reload();  
          })
      }
    },

    selectResultLike(){
      this.$axios.get("/api/staff/selectResultLike?search_result="+this.search_result)
        .then(res => {this.Staff = res.data})
        .catch(err => {
          console.log(err);
          });
    },

  }
};
</script>

<style scoped>
.whole {
  padding-left: 280px;
  padding-right: 30px;
}

.search_condition {
  padding-left: 50px;
  font-size: 15px;
  font-weight: bolder;
}
.search_button {
  margin-left: 120px;
  color: white;
  background-color:#e74c3c;
}
.search_part {
  display: flex;
  align-items: center;
  justify-content:start;
  padding: 10px 0 0 30px;
  height: 80px;
  border-radius: 10px 0;
  background-color: white;
}
.search_input {
  width: 30%;
  padding-left: 30px;
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
.avatar{
  height: 60px;
  width: 60px;
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