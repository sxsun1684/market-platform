<template>
  <div class="main-content">
    <div style="width: 80%; background-color: white; margin: 30px auto; border-radius: 20px">
      <div style="padding-bottom: 10px">
        <div style="font-size: 18px; color: #000000FF; line-height: 80px; border-bottom: #cccccc 1px solid;">
          <div style="margin-left: 20px">My Order: {{ ordersData.length }} {{ ordersData.length > 1 ? 'Pieces' : 'Piece' }}</div>
        </div>
        <div style="margin: 20px 0; padding: 0 40px">
          <div class="table">
            <el-table :data="ordersData" strip>
              <el-table-column label="Product Picture" width="120px">
                <template v-slot="scope">
                  <el-image style="width: 80px; height: 60px; border-radius: 3px" v-if="scope.row.goodsImg"
                            :src="scope.row.goodsImg" :preview-src-list="[scope.row.goodsImg]"></el-image>
                </template>
              </el-table-column>
              <el-table-column prop="orderId" label="Order ID"></el-table-column>
              <el-table-column prop="goodsName" label="Product" :show-overflow-tooltip="true">
                <template v-slot="scope">
                  <a :href="'/front/detail?id=' + scope.row.goodsId">{{scope.row.goodsName}}</a>
                </template>
              </el-table-column>
              <el-table-column prop="businessName" label="Shop">
                <template v-slot="scope">
                  <a :href="'/front/business?id=' + scope.row.businessId">{{scope.row.businessName}}</a>
                </template>
              </el-table-column>
              <el-table-column prop="goodsPrice" label="Price" width="60">
                <template v-slot="scope">
                  {{scope.row.goodsPrice}} / {{scope.row.goodsUnit}}
                </template>
              </el-table-column>
              <el-table-column prop="num" label="Quantity"></el-table-column>
              <el-table-column prop="price" label="Total"></el-table-column>
              <el-table-column prop="username" label="To"></el-table-column>
              <el-table-column prop="useraddress" label="Address"></el-table-column>
              <el-table-column prop="phone" label="Contact"></el-table-column>
              <el-table-column prop="status" width="100" label="Track"></el-table-column>
              <el-table-column label="Operation" align="center" width="180">
                <template v-slot="scope">
                  <el-button size="mini" type="primary" v-if="scope.row.status === 'Pending Receipt'" plain @click="updateStatus(scope.row, 'Completed')">Received</el-button>
                  <el-button size="mini" type="primary" v-if="scope.row.status === 'Completed'" plain @click="addComment(scope.row,'Reviewed')">Review</el-button>
                  <el-button size="mini" type="danger" plain @click="del(scope.row.id)">Delete</el-button>
                </template>
              </el-table-column>
            </el-table>

            <div class="pagination" style="margin-top: 20px">
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
      </div>
    </div>
    <el-dialog title="Please enter your comments" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="Reviews" prop="username">
          <el-input type="textarea" v-model="form.content" placeholder="Please enter your comments"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ordersData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      form: {},
      fromVisible: false
    }
  },
  mounted() {
    this.loadOrders(1)
  },
  // methods: All click events or other function definition areas on this page
  methods: {
    loadOrders(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/orders/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then(res => {
        if (res.code === '200') {
          this.ordersData = res.data?.list
          this.total = res.data?.total
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    navTo(url) {
      location.href = url
    },
    del(id) {
      this.$request.delete('/orders/delete/' + id).then(res => {
        if (res.code === '200') {
          this.$message.success('Successfully Deleted')
          this.loadOrders(1)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleCurrentChange(pageNum) {
      this.loadOrders(pageNum)
    },
    updateStatus(row, status) {
      this.form = row
      this.form.status = status
      this.$request.put('/orders/update', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('Done!')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    addComment(row) {
      this.fromVisible = true
      this.form = row
    },
    save() {
      let data = {
        userId: this.user.id,
        businessId: this.form.businessId,
        goodsId: this.form.goodsId,
        content: this.form.content,
      }
      this.$request.post('/comment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('Done!')
          this.fromVisible = false
          this.updateStatus(this.form, 'Reviewed')
          this.form = {}
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>
