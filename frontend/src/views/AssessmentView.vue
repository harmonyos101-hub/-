<template>
  <el-card shadow="never">
    <div class="header">
      <div>
        <h2>心理测评与评估</h2>
        <p>支持焦虑、抑郁、压力等多维度测评，自动生成评估报告。</p>
      </div>
      <el-button type="primary" @click="runAssessment">生成评估报告</el-button>
    </div>
    <el-table :data="reportData" style="width: 100%">
      <el-table-column prop="scale" label="量表" />
      <el-table-column prop="score" label="得分" />
      <el-table-column prop="level" label="等级" />
      <el-table-column prop="suggestion" label="建议" />
    </el-table>
  </el-card>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const reportData = ref([
  { scale: 'GAD-7', score: 8, level: '轻度', suggestion: '保持运动习惯' },
  { scale: 'PHQ-9', score: 12, level: '中度', suggestion: '尝试规律作息' },
]);

const runAssessment = () => {
  reportData.value = [
    { scale: '压力指数', score: 35, level: '正常', suggestion: '继续保持' },
    { scale: '情绪稳定度', score: 78, level: '良好', suggestion: '可进行正念训练' },
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

h2 {
  margin-bottom: 4px;
}

p {
  color: #6a7a90;
}
</style>
