import request from './request';

// 定义返回的数据类型
export interface LoginResponse {
  token: string;
}

export const login = (data: { username: string; password: string }) => {
  return request.post<LoginResponse>('/login', data); // 使用泛型指定返回类型
};
