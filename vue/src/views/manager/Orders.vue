<template>
  <div>
    <div class="search">
      <el-input placeholder="Please Enter Order ID" style="width: 200px" v-model="orderId"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">Query</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">Reset</el-button>
    </div>

    <div class="operation">
      <el-button type="danger" plain @click="delBatch">Batch Deletion</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="Serial ID" width="80" align="center" sortable></el-table-column>
        <el-table-column label="Picture">
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 40px; height: 40px; " v-if="scope.row.goodsImg"
                        :src="scope.row.goodsImg" :preview-src-list="[scope.row.goodsImg]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="orderId" label="Order ID"></el-table-column>
        <el-table-column prop="goodsName" label="Product" show-overflow-tooltip></el-table-column>
        <el-table-column prop="goodsPrice" label="Price" show-overflow-tooltip>
          <template v-slot="scope">
            {{scope.row.goodsPrice}} / {{scope.row.goodsUnit}}
          </template>
        </el-table-column>
        <el-table-column prop="num" label="Quantity" show-overflow-tooltip></el-table-column>
        <el-table-column prop="price" label="Total" show-overflow-tooltip></el-table-column>
        <el-table-column prop="businessName" label="Affiliated Store" show-overflow-tooltip></el-table-column>
        <el-table-column prop="username" label="To" show-overflow-tooltip></el-table-column>
        <el-table-column prop="useraddress" label="Address" show-overflow-tooltip></el-table-column>
        <el-table-column prop="phone" label="Phone" show-overflow-tooltip></el-table-column>
        <el-table-column prop="status" width="140" label="Status" show-overflow-tooltip></el-table-column>
        <el-table-column label="Operation" width="300" align="center">
          <template v-slot="scope">
            <el-button plain type="primary" size="mini" v-if="scope.row.status === 'Pending Shipment'" @click="updateStatus(scope.row, 'Pending Receipt')">Send</el-button>
            <el-button plain type="danger" size="mini" @click=del(scope.row.id)>Delete</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Notice",
  data() {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      orderId: null,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: []
    }
  },
  created() {
    this.load(1)
  },
  methods: {
    save() {   // Save the logic that the button triggers to add or update
      this.$request({
        url: this.form.id ? '/orders/update' : '/orders/add',
        method: this.form.id ? 'PUT' : 'POST',
        data: this.form
      }).then(res => {
        if (res.code === '200') {  // Indicates successful save
          this.$message.success('Successfully Saved')
          this.load(1)
          this.fromVisible = false
        } else {
          this.$message.error(res.msg)  // An error message is displayed
        }
      })
    },
    del(id) {   // Single delete
      this.$confirm('Are you sure about deleting it?', 'Confirm deletion', {type: "warning"}).then(response => {
        this.$request.delete('/orders/delete/' + id).then(res => {
          if (res.code === '200') {   //Indicates successful operation
            this.$message.success('Successful!')
            this.load(1)
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {   // All row data currently selected
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    delBatch() {
      if (!this.ids.length) {
        this.$message.warning('Please select data')
        return
      }
      this.$confirm('Are you sure to delete this data in bulk?', 'Confirm deletion', {type: "warning"}).then(response => {
        this.$request.delete('/orders/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // Indicates successful operation
            this.$message.success('Done')
            this.load(1)
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {  // Paging query
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/orders/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          orderId: this.orderId,
        }
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data?.list
          this.total = res.data?.total
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    reset() {
      this.orderId = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    handleAvatarSuccess(response, file, fileList) {
      this.form.img = response.data
    },
    updateStatus(row, status) {
      this.form = row;
      this.form.status = status
      this.save()
    }
  }
}
</script>

<style scoped>

</style>
