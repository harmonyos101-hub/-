import { defineStore } from 'pinia';

interface UserState {
  userId: string;
  name: string;
  role: 'USER' | 'COUNSELOR' | 'ADMIN';
}

export const useUserStore = defineStore('user', {
  state: (): UserState => ({
    userId: 'u-001',
    name: '张三',
    role: 'USER',
  }),
});
