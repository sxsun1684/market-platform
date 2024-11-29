<template>
  <div>
    <el-card style="width: 50%">
      <el-form :model="user" label-width="100px" style="padding-right: 50px">
        <div style="margin: 15px; text-align: center">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
          >
            <img v-if="user.avatar" :src="user.avatar" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <el-form-item label="Username" prop="username">
          <el-input v-model="user.username" placeholder="Username" disabled></el-input>
        </el-form-item>
        <el-form-item label="Shop" prop="name">
          <el-input v-model="user.name" placeholder="Shop name"></el-input>
        </el-form-item>
        <el-form-item label="Phone" prop="phone">
          <el-input v-model="user.phone" placeholder="Phone"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="user.email" placeholder="Email"></el-input>
        </el-form-item>
        <el-form-item label="Intro" prop="description">
          <el-input type="textarea" v-model="user.description" placeholder="Introduction"></el-input>
        </el-form-item>
        <el-form-item label="Audit Status" prop="status">
          <el-input v-model="user.status" placeholder="Audit Status" disabled></el-input>
        </el-form-item>
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">Save</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "AdminPerson",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}')
    }
  },
  created() {

  },
  methods: {
    update() {
      // Save the current user information to the database
      this.$request.put('/business/update', this.user).then(res => {
        if (res.code === '200') {
          // Successfully updated
          this.$message.success('Done!')

          // Update the user information in the browser cache
          localStorage.setItem('xm-user', JSON.stringify(this.user))

          // Trigger data update in the parent component
          this.$emit('update:user')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAvatarSuccess(response, file, fileList) {
      // Update the user's avatar property to the uploaded image URL
      this.$set(this.user, 'avatar', response.data)
    },
  }
}
</script>

<style scoped>
/deep/.el-form-item__label {
  font-weight: bold;
}
/deep/.el-upload {
  border-radius: 50%;
}
/deep/.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
}
/deep/.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
  border-radius: 50%;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
  border-radius: 50%;
}
</style>