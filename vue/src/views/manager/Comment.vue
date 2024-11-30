<template>
  <div>
    <div class="search">
      <el-input placeholder="Please enter a comment" style="width: 200px" v-model="content"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">Inquire</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">Reset</el-button>
    </div>

    <div class="operation">
      <el-button type="danger" plain @click="delBatch">Batch Deletion</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="Serial ID" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="goodsName" label="Product" show-overflow-tooltip></el-table-column>
        <el-table-column prop="content" label="Comment" show-overflow-tooltip></el-table-column>
        <el-table-column prop="userName" label="User" show-overflow-tooltip></el-table-column>
        <el-table-column prop="time" label="Time" show-overflow-tooltip></el-table-column>
        <el-table-column prop="businessName" label="Shop" show-overflow-tooltip></el-table-column>
        <el-table-column prop="goodsName" label="Product" show-overflow-tooltip></el-table-column>
        <el-table-column label="Operation" width="180" align="center">
          <template v-slot="scope">
            <el-button plain type="primary" size="mini" v-if="scope.row.status === 'Pending Shipment'" @click="updateStatus(scope.row, 'Waiting Receiving')">Deliver</el-button>
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
      content: null,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: []
    }
  },
  created() {
    this.load(1)
  },
  methods: {
    save() {   // Logic triggered by the save button; it handles both create and update operations
      this.$request({
        url: this.form.id ? '/comment/update' : '/comment/add',
        method: this.form.id ? 'PUT' : 'POST',
        data: this.form
      }).then(res => {
        if (res.code === '200') {  // Indicates a successful save
          this.$message.success('Save successful')
          this.load(1)
          this.fromVisible = false
        } else {
          this.$message.error(res.msg)  // Displays an error message
        }
      })
    },
    del(id) {   // Single deletion
      this.$confirm('Are you sure you want to delete?', 'Confirm Deletion', {type: "warning"}).then(response => {
        this.$request.delete('/comment/delete/' + id).then(res => {
          if (res.code === '200') {   // Indicates the operation was successful
            this.$message.success('Operation successful')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // Displays an error message
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {   // Currently selected row data
      this.ids = rows.map(v => v.id)   // Example: [1, 2]
    },
    delBatch() {   // Batch deletion
      if (!this.ids.length) {
        this.$message.warning('Please select data')
        return
      }
      this.$confirm('Are you sure you want to delete these records in bulk?', 'Confirm Deletion', {type: "warning"}).then(response => {
        this.$request.delete('/comment/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // Indicates the operation was successful
            this.$message.success('Operation successful')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // Displays an error message
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/comment/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          content: this.content,
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
      this.content = null
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
