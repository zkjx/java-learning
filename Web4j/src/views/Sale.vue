<template>
  <div class="body">
    <div style="margin-bottom: 10px">
     今日卖出： <el-input v-model.number="saleNum" placeholder="请输入卖出数量" style="width: 150px;margin-right: 10px" />
      <el-button type="primary" @click="sale" >卖出</el-button>
    </div>
    <el-table
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column
          prop="id"
          label="id"
          width="180">
      </el-table-column>
      <el-table-column
          prop="name"
          label="status"
          width="180">
      </el-table-column>
      <el-table-column
          prop="num"
          label="num">
      </el-table-column>
    </el-table>
  </div>

</template>

<script>
import {getRequest, postRequest} from "@/utils/api";

export default {
  name: 'Sale',
  data() {
    return {
      saleNum: '',
      tableData: [{
        id: 1,
        name: '库存数',
        num: 0
      }, {
        id: 2,
        name: '已卖出',
        num: 0
      }]
    }
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
     getRequest('getBookNum','').then(res => {
       this.tableData[0].num = res.data.storeNum;
       this.tableData[1].num = res.data.soldNum;
     })
    },
    test(){
      // this.tableData.forEach(function (item) {
      //   item.num = item.num - 10;
      //   console.log(item.num)
      // })
      this.tableData[0].num -=10;
    },
    sale() {
      // 判断saleNum是否为数字,且大于0，且非空
      if (isNaN(this.saleNum) || this.saleNum <= 0 || this.saleNum === '') {
        this.$message.error('请输入正确的卖出数量');
        return;
      }
      // 判断库存是否足够
      if (this.tableData[0].num < this.saleNum) {
        this.$message.error('库存不足');
        return;
      }
      this.$confirm('确定卖出 '+this.saleNum+' 本?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // this.$message({
        //   type: 'success',
        //   message: '卖出成功!'
        // });
        postRequest('/sale', {saleNum: this.saleNum}).then(res => {
            this.tableData[0].num = res.data.storeNum;
            this.tableData[1].num = res.data.soldNum;
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消卖出'
          });
        });
      })
  }
}
}
</script>

<style>
.body {
 text-align: center;
  width: 500px;
  margin-top: 100px;
  margin-left: 500px;

}
</style>
