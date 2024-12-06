<template>
  <div>
    <div class="search">
      <el-input placeholder="Please enter the account" style="width: 200px" v-model="username"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">Query</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">Reset</el-button>
    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">Add</el-button>
      <el-button type="danger" plain @click="delBatch">Batch Deletion</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" strip @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="Serial ID" width="70" align="center" sortable></el-table-column>
        <el-table-column prop="username" label="Account"></el-table-column>
        <el-table-column prop="name" label="Name"></el-table-column>
        <el-table-column prop="phone" label="Phone"></el-table-column>
        <el-table-column prop="email" label="Email"></el-table-column>
        <el-table-column label="Avatar">
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 40px; height: 40px; border-radius: 50%" v-if="scope.row.avatar"
                        :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="role" label="Role"></el-table-column>
        <el-table-column prop="description" label="Shop Intro" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="status" label="Audit Status"></el-table-column>
        <el-table-column label="Operation" align="center" width="180">
          <template v-slot="scope">
            <el-button size="mini" type="primary" plain @click="handleEdit(scope.row)">Edit</el-button>
            <el-button size="mini" type="danger" plain @click="del(scope.row.id)">Delete</el-button>
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


    <el-dialog title="Admin" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="Username" prop="username">
          <el-input v-model="form.username" placeholder="Username"></el-input>
        </el-form-item>
        <el-form-item label="Store" prop="name">
          <el-input v-model="form.name" placeholder="Shop name"></el-input>
        </el-form-item>
        <el-form-item label="Phone" prop="phone">
          <el-input v-model="form.phone" placeholder="Phone"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="birth">
          <el-input v-model="form.email" placeholder="Email"></el-input>
        </el-form-item>
        <el-form-item label="Avatar">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :headers="{ token: user.token }"
              list-type="picture"
              :on-success="handleAvatarSuccess"
          >
            <el-button type="primary">Upload Avatar</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="Store" prop="description">
          <el-input v-model="form.description" placeholder="Shop Intro"></el-input>
        </el-form-item>
        <el-form-item label="Status" prop="status">
          <el-select v-model="form.status" placeholder="Please select..." style="width: 100%">
            <el-option label="Under Review" value="Under Review"></el-option>
            <el-option label="Reviewed" value="Reviewed"></el-option>
            <el-option label="Review Failed" value="Review Failed"></el-option>
          </el-select>
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
  name: "Admin",
  data() {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      username: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        username: [
          {required: true, message: 'Please enter account...', trigger: 'blur'},
        ]
      },
      ids: []
    }
  },
  created() {
    this.load(1)
  },
  methods: {
    handleAdd() {   // ADD data
      this.form = {}  // Adding then clear cache
      this.fromVisible = true   // popup
    },
    handleEdit(row) {  // Edit the data
      this.form = JSON.parse(JSON.stringify(row))
      // Assign a value to the form object.
      // TODO:Note that deep copy the data
      this.fromVisible = true
    },
    save() {   // Save the logic triggered by the button it will trigger a new or update
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/business/update' : '/business/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {
              this.$message.success('Saved')
              this.load(1)
              this.fromVisible = false
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    del(id) {   // Single delete
      this.$confirm('Are you sure you want to delete?', 'Confirm Deletion', { type: "warning" })
          .then(response => {
            this.$request.delete('/business/delete/' + id).then(res => {
              if (res.code === '200') {   // Indicates successful operation
                this.$message.success('Operation successful');
                this.load(1);
              } else {
                this.$message.error(res.msg);
              }
            });
          })
          .catch(() => {
            // Handle cancelation or error
          });
    },

    handleSelectionChange(rows) {
      this.ids = rows.map(v => v.id)
    },
    delBatch() {   // Batch deletion
      if (!this.ids.length) {
        this.$message.warning('Please select data')
        return
      }
      this.$confirm('Are you sure you want to delete these data in bulk?', 'Confirm Deletion', {type: "warning"}).then(response => {
        this.$request.delete('/business/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // Indicates the operation was successful
            this.$message.success('Done')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // Display error message
          }
        })
      }).catch(() => {
      })
    },

    load(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/business/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    reset() {
      this.username = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    handleAvatarSuccess(response, file, fileList) {
      // Replace the profile picture attribute with a link to the uploaded image
      this.form.avatar = response.data
    },
  }
}
</script>

<style scoped>

</style>