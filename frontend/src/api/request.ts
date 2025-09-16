import axios from 'axios';

// 创建一个 axios 实例
const request = axios.create({
  baseURL: 'http://your-api-url.com',  // 替换成实际 API 地址
  timeout: 5000,                      // 请求超时
});

// 请求拦截器：可以用来处理 token 等认证信息
request.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`; // 将 token 添加到请求头
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// 响应拦截器：可以处理响应错误，统一处理
request.interceptors.response.use(
  response => response.data,
  error => {
    // 错误处理逻辑（如弹出提示）
    return Promise.reject(error);
  }
);

export default request;
