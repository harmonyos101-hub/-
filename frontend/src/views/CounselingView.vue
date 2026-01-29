<template>
  <el-row :gutter="20">
    <el-col :span="12">
      <el-card>
        <h2>咨询预约</h2>
        <el-form :model="form" label-width="90px" class="form">
          <el-form-item label="咨询师">
            <el-select v-model="form.counselor" placeholder="选择咨询师">
              <el-option label="王老师" value="王老师" />
              <el-option label="刘老师" value="刘老师" />
            </el-select>
          </el-form-item>
          <el-form-item label="时间">
            <el-date-picker v-model="form.date" type="datetime" placeholder="选择时间" />
          </el-form-item>
          <el-form-item label="方式">
            <el-radio-group v-model="form.channel">
              <el-radio label="文字" />
              <el-radio label="语音" />
              <el-radio label="视频" />
            </el-radio-group>
          </el-form-item>
          <el-button type="primary" @click="submit">提交预约</el-button>
        </el-form>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card>
        <h2>咨询记录</h2>
        <el-timeline>
          <el-timeline-item timestamp="2024-01-20" placement="top">
            睡眠与焦虑咨询 - 已完成
          </el-timeline-item>
          <el-timeline-item timestamp="2024-01-28" placement="top">
            压力管理咨询 - 待开始
          </el-timeline-item>
        </el-timeline>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import { reactive } from 'vue';

const form = reactive({
  counselor: '',
  date: '',
  channel: '文字',
});

const submit = () => {
  form.counselor = '';
  form.date = '';
  form.channel = '文字';
};
</script>

<style scoped>
.form {
  margin-top: 12px;
}
</style>
