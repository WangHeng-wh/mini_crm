<template>
  <el-form :model="form" ref="formRef" label-width="120px" class="login-form">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="form.username" placeholder="请输入用户名" />
    </el-form-item>

    <el-form-item label="密码" prop="password">
      <el-input
        type="password"
        v-model="form.password"
        placeholder="请输入密码"
      />
    </el-form-item>

    <el-form-item>
      <el-button
        type="primary"
        :loading="loading"
        @click="handleLogin"
        style="width: 100%"
      >
        登录
      </el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts">
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { login } from '@/api/auth';  // 引入登录 API
import type { LoginResponse } from '@/api/auth'; // 引入 LoginResponse 类型

export default {
  name: 'Login',
  setup() {
    const form = ref({
      username: '',
      password: ''
    });
    const loading = ref(false);

    const handleLogin = async () => {
      if (form.value.username === '' || form.value.password === '') {
        ElMessage.error('用户名或密码不能为空');
        return;
      }

      loading.value = true;
      try {
        const response = await login(form.value); // 自动推导类型为 AxiosResponse<LoginResponse>
        const token = response.data.token; // 从 data 中获取 token
        localStorage.setItem('token', token); // 现在可以安全地访问 token
        ElMessage.success('登录成功');
        // 登录成功后的操作，如跳转到主页
        window.location.href = '/dashboard';
      } catch (error) {
        ElMessage.error('登录失败，请检查用户名和密码');
      } finally {
        loading.value = false;
      }
    };

    return {
      form,
      loading,
      handleLogin
    };
  }
};

</script>

<style scoped>
.login-form {
  width: 400px;
  margin: 0 auto;
  padding-top: 100px;
}
</style>
