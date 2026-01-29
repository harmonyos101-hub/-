<template>
  <el-card>
    <div class="header">
      <div>
        <h2>社区互动与知识分享</h2>
        <p>匿名倾诉、经验分享、心理健康活动报名。</p>
      </div>
      <el-button type="success" @click="addPost">发布话题</el-button>
    </div>
    <el-table :data="posts" style="width: 100%">
      <el-table-column prop="topic" label="话题" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="status" label="状态" />
    </el-table>
  </el-card>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const posts = ref([
  { topic: '如何缓解焦虑', author: '匿名用户', status: '讨论中' },
  { topic: '学习压力调节', author: '小林', status: '已结帖' },
]);

const addPost = () => {
  posts.value = [
    { topic: '情绪日记打卡', author: '匿名用户', status: '讨论中' },
    ...posts.value,
  ];
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

p {
  color: #6a7a90;
}
</style>
