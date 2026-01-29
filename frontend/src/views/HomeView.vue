<template>
  <div class="grid">
    <el-card shadow="hover" class="card">
      <h3>用户端核心功能</h3>
      <ul>
        <li>注册登录、身份验证、账号管理</li>
        <li>心理测评、风险预警、趋势分析</li>
        <li>咨询预约、在线沟通、反馈评价</li>
        <li>知识学习、内容推荐、社区互动</li>
      </ul>
    </el-card>
    <el-card shadow="hover" class="card">
      <h3>咨询师端核心功能</h3>
      <ul>
        <li>资质认证与资料管理</li>
        <li>预约管理与日程提醒</li>
        <li>在线咨询与案例管理</li>
        <li>专业成长与同行交流</li>
      </ul>
    </el-card>
    <el-card shadow="hover" class="card">
      <h3>管理员端核心功能</h3>
      <ul>
        <li>用户与咨询师管理</li>
        <li>内容审核与数据分析</li>
        <li>系统配置与安全管理</li>
        <li>运营监控与告警支持</li>
      </ul>
    </el-card>
  </div>
</template>

<style scoped>
.grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
  gap: 20px;
}

.card {
  min-height: 220px;
}

h3 {
  margin-bottom: 12px;
}

ul {
  padding-left: 18px;
  color: #5a6b85;
}
</style>
