<template>
  <div class="main-content">
    <div style="width: 70%; background-color: white; margin: 30px auto; border-radius: 20px">
      <div style="padding-bottom: 10px">
        <div style="font-size: 18px; color: #000000FF; line-height: 80px; border-bottom: #cccccc 1px solid;">
          <div style="margin-left: 20px">
            All Favourites ({{ collectData.length }} {{ collectData.length > 1 ? 'Pieces' : 'Piece' }})
          </div>
        </div>
        <div style="margin: 20px 0; padding: 0 50px">
          <div class="table">
            <el-table :data="collectData" strip>
              <el-table-column label="Picture" width="120px">
                <template v-slot="scope">
                  <el-image style="width: 80px; height: 60px; border-radius: 3px" v-if="scope.row.goodsImg"
                            :src="scope.row.goodsImg" :preview-src-list="[scope.row.goodsImg]"></el-image>
                </template>
              </el-table-column>
              <el-table-column prop="goodsName" label="Product" width="350px"></el-table-column>
              <el-table-column prop="businessName" label="Shop Name"></el-table-column>
              <el-table-column prop="goodsPrice" label="Price"></el-table-column>
              <el-table-column label="Operation" align="center" width="180">
                <template v-slot="scope">
                  <el-button size="mini" type="danger" plain @click="del(scope.row.id)">Remove</el-button>
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
  </div>
</template>

<script>

export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      collectData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
    }
  },
  mounted() {
    this.loadCollect(1)
  },
  // methods: All click events or other function definition areas on this page
  methods: {
    loadCollect(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/collect/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then(res => {
        if (res.code === '200') {
          this.collectData = res.data?.list
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
      this.$request.delete('/collect/delete/' + id).then(res => {
        if (res.code === '200') {
          this.$message.success('Successfully Removed')
          this.loadCollect(1)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleCurrentChange(pageNum) {
      this.loadCollect(pageNum)
    }
  }
}
</script>
