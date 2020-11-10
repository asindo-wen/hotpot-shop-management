<template>
  <div class="whole">
    
     <br />
    <div class="title_part">
      <div class="title">薪资管理</div>
    </div>
    <br />

    <div style="background:white;">
      <div style="padding:20px 0 0 20px;">
        <span style="padding:7px 0 0 20px;">所有薪资</span>
        <div style="float:right;padding:0 60px 0 0">
          <el-button type="add" size="mini" @click="toSalaryAdd">新增</el-button>
          <el-button type="delete" size="mini" @click="deleteSalary">删除</el-button>
        </div>
      </div>
      <el-divider></el-divider>
      
      <el-table
        ref="multipleTable"
        :data="StaffSalary.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        :border="true"
        :header-cell-style="{textAlign: 'center'}"
        :cell-style="{textAlign: 'center'}"
      >
        <el-table-column name="id" type="selection"></el-table-column>
        <el-table-column prop="staff_id" label="员工编号"></el-table-column>
        <el-table-column prop="staff_name" label="员工姓名"></el-table-column>
        <el-table-column prop="basicSalary" label="基础工资"></el-table-column>
        <el-table-column prop="bonus" label="奖金"></el-table-column>
        <el-table-column prop="allSalary" label="总工资"></el-table-column>
        <el-table-column prop="creTime" label="创立时间"></el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="update" size="small"  @click="toSalaryEdit(scope.row.salary_id)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <br />
      <div style="width:100%" align="center">
        <el-pagination
          background
          :current-page.sync="currentPage"
          :page-size="pagesize"
          :total="StaffSalary.length"
          layout="prev, pager, next, jumper"         
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
      currentPage: 1, //当前页
      pagesize: 4, //每页数据条数
      StaffSalary: [],
      multipleSelection:[],
    };
  },

  mounted() {
    this.selectStaffSalary();
  },
  methods: {
    toSalaryAdd() {
      this.$router.push({ name: "salaryAdd" });
    },
    toSalaryEdit(id){
      console.log(id)
      this.$router.push({ name: "salaryEdit",
                          params: {salary_id:id}});
    },
    //获取员工薪水信息
    selectStaffSalary() {
      this.$axios
        .get("http://localhost:8080/salary/selectsalary")
        .then(res => {
          this.StaffSalary = res.data;
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
    deleteSalary(){
      for(var i=0;i<this.multipleSelection.length;i++){
        this.$axios.post("http://localhost:8080/salary/deletesalary?salary_id="+this.multipleSelection[i].salary_id)
          .then(res=>{
            this.$message.success("删除成功");
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