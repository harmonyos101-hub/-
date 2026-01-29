# 智慧心理健康平台：环境配置与部署指南

本文档提供本项目的**本地开发环境配置**、**数据库初始化**、**IDEA 导入运行**以及**生产环境部署**的详细方案，适用于当前仓库的前后端结构与 MySQL 8.0 + MyBatis Plus 技术栈。

## 1. 本地开发环境准备

### 1.1 必需软件版本

**后端**
- JDK 17（Spring Boot 3.x 推荐）
- Maven 3.8+
- MySQL 8.0

**前端**
- Node.js 18+
- npm 9+

### 1.2 目录结构概览

```
/workspace/-
├── backend/      # Spring Boot 后端
├── frontend/     # Vue 3 + Vite 前端
└── docs/         # 项目文档
```

---

## 2. 数据库初始化方案（MySQL 8.0）

本项目已提供完整的建表与初始化数据脚本：

- 建表脚本：`backend/src/main/resources/schema.sql`
- 初始化数据：`backend/src/main/resources/data.sql`

### 2.1 创建数据库

```sql
CREATE DATABASE IF NOT EXISTS mental_health
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;
```

### 2.2 执行建表脚本

```bash
mysql -u root -p mental_health < backend/src/main/resources/schema.sql
```

### 2.3 执行初始化数据

```bash
mysql -u root -p mental_health < backend/src/main/resources/data.sql
```

---

## 3. 后端环境配置与运行

### 3.1 修改数据库连接配置

配置文件：`backend/src/main/resources/application.yml`

根据本地 MySQL 的用户名/密码调整以下字段：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mental_health?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
    username: root
    password: password
```

### 3.2 后端运行方式

在 `backend/` 目录下执行：

```bash
mvn spring-boot:run
```

运行成功后，默认端口为 `8080`。

---

## 4. 前端环境配置与运行

### 4.1 安装依赖

在 `frontend/` 目录下执行：

```bash
npm install
```

### 4.2 启动开发服务器

```bash
npm run dev
```

默认地址为：`http://localhost:5173`。

### 4.3 前后端联调

前端已在 `frontend/src/api/client.ts` 中配置后端 API 基础地址：

```
http://localhost:8080/api
```

如需修改端口或域名，请在该文件中调整。

---

## 5. IDEA 导入与运行（后端）

### 5.1 导入步骤

1. 打开 **IntelliJ IDEA**
2. 选择 **Open**
3. 指定目录：`/workspace/-/backend`
4. IDEA 将自动识别为 Maven 项目并下载依赖

### 5.2 运行方式

- 打开 `MentalHealthApplication.java`
- 右键选择 **Run 'MentalHealthApplication'**

运行成功后，后端服务将在 `8080` 端口启动。

### 5.3 常见问题排查

- **依赖下载慢/失败**：可配置国内 Maven 镜像（如阿里云 Maven）
- **数据库连接失败**：确认 MySQL 已启动、账号密码正确、`mental_health` 库存在

---

## 6. 生产环境部署方案

### 6.1 后端部署（Spring Boot）

1. 打包应用：

```bash
mvn clean package -DskipTests
```

2. 将生成的 `backend/target/*.jar` 上传到服务器
3. 启动应用：

```bash
java -jar mental-health-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

4. 建议使用 `systemd` 或 Docker 进行守护式运行

### 6.2 前端部署（Vite）

1. 构建前端：

```bash
npm run build
```

2. 将 `frontend/dist/` 部署到 Nginx 或 CDN

### 6.3 Nginx 示例配置（反向代理）

```nginx
server {
    listen 80;
    server_name your-domain.com;

    location / {
        root /var/www/mental-health-frontend;
        try_files $uri /index.html;
    }

    location /api/ {
        proxy_pass http://127.0.0.1:8080/api/;
    }
}
```

---

## 7. 补充建议

- **数据库自动化迁移**：可引入 Flyway 或 Liquibase
- **生产配置隔离**：建议新增 `application-prod.yml`
- **安全加固**：建议结合 Spring Security + JWT

---

如果你需要：
- Docker Compose 一键部署
- IDEA 运行截图
- 数据库 ER 图

可继续告诉我，我将补充对应文档与文件。
