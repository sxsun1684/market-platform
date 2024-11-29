<template>
  <div>
    <el-card style="width: 60%">
      <el-form ref="formRef" :model="user" :rules="rules" label-width="100px" style="padding-right: 50px;background-color: #0777c7">
        <el-form-item label="Current Password" prop="password">
          <el-input show-password v-model="user.password" placeholder="Enter current password"></el-input>
        </el-form-item>
        <el-form-item label="New Password" prop="newPassword">
          <el-input show-password v-model="user.newPassword" placeholder="Enter new password"></el-input>
        </el-form-item>
        <el-form-item label="Confirm New Password" prop="confirmPassword">
          <el-input show-password v-model="user.confirmPassword" placeholder="Confirm new password"></el-input>
        </el-form-item>
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">Confirm Changes</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Password",
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please confirm your password'))
      } else if (value !== this.user.newPassword) {
        callback(new Error('Passwords do not match'))
      } else {
        callback()
      }
    }

    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        password: [
          { required: true, message: 'Please enter your current password', trigger: 'blur' },
        ],
        newPassword: [
          { required: true, message: 'Please enter your new password', trigger: 'blur' },
        ],
        confirmPassword: [
          { validator: validatePassword, required: true, trigger: 'blur' },
        ],
      }
    }
  },
  created() {

  },
  methods: {
    update() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request.put('/updatePassword', this.user).then(res => {
            if (res.code === '200') {
              // Successful updated
              localStorage.removeItem('xm-user')   // Clear cached user information
              this.$message.success('Password changed successfully')
              this.$router.push('/login')
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
  }
}
</script>

<style scoped>
/deep/.el-form-item__label {
  font-weight: bold;
}
</style>