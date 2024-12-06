<template>
  <div>
    <div class="search">
      <!-- Input field for entering the product name to query -->
      <el-input placeholder="Enter product name to query" style="width: 200px" v-model="name"></el-input>
      <!-- Query button -->
      <el-button type="info" plain class="custom-query-button" style="margin-left: 10px" @click="load(1)">Query
      </el-button>
      <!-- Reset button -->
      <el-button type="warning" plain class="custom-reset-button" style="margin-left: 10px" @click="reset">Reset
      </el-button>
    </div>

    <div class="operation">
      <!-- Button to publish a product -->
      <el-button type="primary" plain @click="handleAdd">Post Product</el-button>
      <!-- Button to delete products in bulk -->
      <el-button type="danger" plain @click="delBatch">Batch Deletion</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="Serial ID" width="80" align="center" sortable></el-table-column>
        <el-table-column label="Picture">

          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 40px; height: 40px; " v-if="scope.row.img"
                        :src="scope.row.img" :preview-src-list="[scope.row.img]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="Name" show-overflow-tooltip></el-table-column>
        <el-table-column prop="description" label="Description">
          <template slot-scope="scope">
            <!-- Button to view the product description -->
            <el-button type="success" @click="viewEditor(scope.row.description)">Click to View</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="Price" show-overflow-tooltip></el-table-column>
        <el-table-column prop="unit" label="Quantity" show-overflow-tooltip></el-table-column>
        <el-table-column prop="typeName" label="Category" show-overflow-tooltip></el-table-column>
        <el-table-column prop="businessName" label="Business" show-overflow-tooltip></el-table-column>
        <el-table-column prop="count" label="Sales" show-overflow-tooltip></el-table-column>
        <el-table-column label="Operation" width="180" align="center">
          <template v-slot="scope">
            <!-- Button to edit the product -->
            <el-button plain type="primary" @click="handleEdit(scope.row)" size="mini">Edit</el-button>
            <!-- Button to delete the product -->
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


    <el-dialog title="Information" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false"
               destroy-on-close @close="cancel">
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item label="Picture">
          <!-- Upload component for uploading product main image -->
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :headers="{ token: user.token }"
              list-type="picture"
              :on-success="handleAvatarSuccess"
          >
            <!-- Button to upload image -->
            <el-button type="primary">Upload Image</el-button>
          </el-upload>
        </el-form-item>

        <el-form-item prop="name" label="Product">
          <!-- Input field for entering product name -->
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="price" label="Price">
          <!-- Input field for entering product price -->
          <el-input v-model="form.price" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="typeId" label="Type">
          <!-- Dropdown for selecting product category -->
          <el-select v-model="form.typeId" placeholder="Please select a category" style="width: 100%">
            <!-- Dropdown options for categories -->
            <el-option v-for="item in typeData" :label="item.name" :value="item.id" :key="item.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="unit" label="Quantity">
          <!-- Input field for entering unit of measurement -->
          <el-input v-model="form.unit" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="description" label="Description">
          <!-- Editor for entering product description -->
          <div id="editor" style="width: 100%"></div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">Cancel</el-button>
        <el-button type="primary" @click="save">Confirm</el-button>
      </div>
    </el-dialog>

    <el-dialog title="Introduction" :visible.sync="editorVisible" width="50%">
      <div v-html="this.viewData" class="w-e-text"></div>
    </el-dialog>
  </div>
</template>

<script>
import E from 'wangeditor'

let editor

function initWangEditor(content) {
  setTimeout(() => {
    if (!editor) {
      editor = new E('#editor')
      editor.config.placeholder = 'Post Content'
      editor.config.uploadFileName = 'file'
      editor.config.uploadImgServer = 'http://localhost:9090/files/wang/upload'
      editor.create()
    }
    editor.txt.html(content)
  }, 0)
}

export default {
  name: "Notice",
  data() {
    return {
      tableData: [],
      pageNum: 1,  // Current page number
      pageSize: 10,  // Number of items per page
      total: 0,  // Total number of items
      name: null,
      fromVisible: false, // Visibility for the form modal
      editorVisible: false, // Visibility for the editor modal
      form: {}, // Form data
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'), // Current user data from local storage
      rules: { // Form validation rules
        title: [
          {required: true, name: 'Please enter the product name', trigger: 'blur'},
        ],
        content: [
          {required: true, img: 'Please upload the product main image', trigger: 'blur'},
        ]
      },
      ids: [], // Selected item IDs for batch operations
      typeData: [], // Dropdown data for product types
      viewData: null // Data for viewing detailed content
    }
  },
  created() {
    this.load(1); // Load data for the first page
    this.loadType(); // Load product type data
  },
  methods: {
    loadType() { // Load product type data
      this.$request.get('/type/selectAll').then(res => {
        if (res.code === '200') {
          this.typeData = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleAdd() {   // Add new data
      if ('Reviewed' !== this.user.status) {
        this.$message.warning('Your store information has not been approved yet, so you are temporarily not allowed to publish products.')
        return
      }
      this.form = {}  // Clear the form data when adding new data
      initWangEditor('')
      this.fromVisible = true   // Open the modal
    },
    handleEdit(row) {   // Edit data
      this.form = JSON.parse(JSON.stringify(row))  // Assign values to the form object, making sure to deep copy the data
      initWangEditor(this.form.description || '')
      this.fromVisible = true   // Open the modal
    },
    viewEditor(content) {   // View the editor content
      this.viewData = content
      this.editorVisible = true
    },
    cancel() {   // Cancel the action
      this.fromVisible = false
      location.href = '/goods'
    },
    save() {   // Logic triggered by the save button, handles both adding and updating
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.form.description = editor.txt.html()
          this.$request({
            url: this.form.id ? '/goods/update' : '/goods/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {  // Indicates successful save
              this.$message.success('Saved successfully')
              // this.load(1)
              this.fromVisible = false
              location.href = '/goods'
            } else {
              this.$message.error(res.msg)  // Display an error message
            }
          })
        }
      })
    },

    del(id) {   // Delete a single item
      this.$confirm('Are you sure you want to delete?', 'Confirm Deletion', {type: "warning"}).then(response => {
        this.$request.delete('/goods/delete/' + id).then(res => {
          if (res.code === '200') {   // Indicates the operation was successful
            this.$message.success('Operation successful')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // Display an error message
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {   // All currently selected row data
      this.ids = rows.map(v => v.id)   // Example: [1, 2]
    },
    delBatch() {   // Batch delete
      if (!this.ids.length) {
        this.$message.warning('Please select data')
        return
      }
      this.$confirm('Are you sure you want to delete these items in bulk?', 'Confirm Deletion', {type: "warning"}).then(response => {
        this.$request.delete('/goods/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // Indicates the operation was successful
            this.$message.success('Operation successful')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // Display an error message
          }
        })
      }).catch(() => {
      })
    },

    load(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/goods/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    reset() {
      this.name = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    handleAvatarSuccess(response, file, fileList) {
      this.form.img = response.data
    },
  }
}
</script>

<style scoped>
.custom-query-button {
  background-color: #0777c7;
  color: white;
  border-color: #cde9ff;
}

.custom-query-button:hover {
  background-color: #bbe0ff;
  border-color: #0777c7;
}

.custom-reset-button {
  background-color: #86b600;
  color: white;
  border-color: rgba(149, 173, 112, 0.93);
}

.custom-reset-button:hover {
  background-color: rgba(149, 173, 112, 0.93);;
  border-color: #86b600;
}
</style>