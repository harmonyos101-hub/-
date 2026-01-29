<template>
  <el-container class="app-shell">
    <el-header class="header">
      <div class="title">智慧心理健康平台</div>
      <el-menu mode="horizontal" :default-active="activeMenu" class="menu">
        <el-menu-item index="/">首页</el-menu-item>
        <el-menu-item index="/assessment">心理测评</el-menu-item>
        <el-menu-item index="/counseling">咨询服务</el-menu-item>
        <el-menu-item index="/community">社区互动</el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const activeMenu = computed(() => route.path);
</script>

<style scoped>
.app-shell {
  min-height: 100vh;
  background: #f5f7fb;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #ffffff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.title {
  font-size: 20px;
  font-weight: 600;
  color: #1f2f46;
}

.menu {
  flex: 1;
  justify-content: flex-end;
}
</style>
