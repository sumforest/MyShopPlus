<template>
  <div class="app-container">
    <el-form
      v-loading="formLoading"
      :data="form"
      element-loading-text="Loading"
      :model="form"
      labe-width="120px"
    >
      <el-input type="hidden" v-model="form.id"/>
      <el-form-item label="用户名">
        <el-input v-model="form.username" :disabled="true"/>
      </el-form-item>
      <el-form-item label="用户头像">
        <img :src="form.icon" width="60px" height="60px"/>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email"/>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickName"/>
      </el-form-item>
      <el-form-item label="备注信息">
        <el-input v-model="form.note"/>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input v-model="form.createTime" :disabled="true"/>
      </el-form-item>
      <el-form-item label="最后登录时间">
        <el-input v-model="form.loginTime" :disabled="true"/>
      </el-form-item>
      <el-form-item label="帐号状态">
        <el-radio-group v-model="form.status">
          <el-radio :label="0">禁用</el-radio>
          <el-radio :label="1">启用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {getProfile, updateProfile} from "../../api/profile";

  export default {
    name: "info",
    data() {
      return {
        form: {
          id: '',
          username: '',
          icon: '',
          email: '',
          nickName: '',
          note: '',
          createTime: '',
          loginTime: '',
          status: ''
        },
        formLoading: true
      }
    },

    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        getProfile(this.$store.getters.name).then(response => {
          this.form = response.data;
          this.formLoading = false;
        })
      },
      onSubmit() {
        //打开加载特效
        this.formLoading = true;
        updateProfile(this.form).then(response => {
          //关闭加载特效
          this.formLoading = false;
          //成功提示框
          this.$message({
            message: response.message,
            type: "success"
          });
        }).catch(() => {
          //失败时关闭加载特效
          this.formLoading = false;
        });
      },
    }
  };
</script>

<style scoped>

</style>
