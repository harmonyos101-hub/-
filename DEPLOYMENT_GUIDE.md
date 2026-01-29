# 智慧心理健康平台环境配置与部署方案（IDEA 运行指南）

本文档给出从本地开发到生产部署的完整规划，包含环境准备、数据库初始化、IDEA 导入与运行、部署建议与常见问题排查，适用于本仓库当前的前后端与 MySQL 8.0 + MyBatis Plus 技术栈。

## 1. 环境准备

### 1.1 必备软件版本

| 组件 | 版本要求 | 说明 |
| --- | --- | --- |
| JDK | 17 | Spring Boot 3.x 推荐 |
| Maven | 3.8+ | 后端构建与依赖管理 |
| MySQL | 8.0 | 主数据库 |
| Node.js | 18+ | 前端构建与运行（Vite） |
| Git | 最新稳定版 | 拉取与协作 |

### 1.2 项目结构说明

```
/workspace/-
├── backend                 # Spring Boot + MyBatis Plus
│   ├── src/main/resources
│   │   ├── application.yml # 数据库连接配置
│   │   ├── schema.sql      # MySQL 建表脚本
│   │   └── data.sql        # 初始化数据
│   └── ...
└── frontend                # Vue 3 + Element Plus + Vite
```

## 2. 数据库初始化

### 2.1 创建数据库

建议创建名为 `mental_health` 的数据库：

```sql
CREATE DATABASE IF NOT EXISTS mental_health DEFAULT CHARACTER SET utf8mb4;
```

### 2.2 导入建表与初始化数据

执行顺序：
1. 运行 `backend/src/main/resources/schema.sql`
2. 运行 `backend/src/main/resources/data.sql`

> 建表脚本会创建用户、咨询师、预约、测评、文章、社区、AI 会话与情绪趋势记录等核心表。

### 2.3 检查数据库连接配置

在 `backend/src/main/resources/application.yml` 中修改如下配置（如你的数据库用户名或密码不同）：

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mental_health?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
    username: root
    password: password
```

## 3. IDEA 导入与运行

### 3.1 后端导入步骤

1. 打开 IntelliJ IDEA。
2. 选择 **File → Open**。
3. 选择项目目录 `/workspace/-/backend`。
4. IDEA 会自动识别 Maven 项目，点击 **Load Maven Project**。
5. 等待依赖下载完成。

### 3.2 运行后端服务

1. 在项目中找到启动类：
   `backend/src/main/java/com/example/mentalhealth/MentalHealthApplication.java`
2. 右键该类 → **Run**。
3. 控制台出现 `Started MentalHealthApplication` 表示启动成功。
4. 默认端口：`http://localhost:8080`

### 3.3 验证后端接口（示例）

可通过浏览器或 Postman 访问：

```
GET http://localhost:8080/api/user/articles
GET http://localhost:8080/api/admin/users
```

> 接口数据返回是示例内容，可在后续与数据库实体与 Mapper 结合时进行持久化改造。

## 4. 前端运行（可选）

### 4.1 安装依赖

进入 `frontend` 目录并安装依赖：

```bash
cd frontend
npm install
```

### 4.2 启动开发服务器

```bash
npm run dev
```

浏览器访问：

```
http://localhost:5173
```

## 5. 生产部署方案

### 5.1 后端部署（Linux）

推荐流程：
1. 配置生产数据库。
2. 修改 `application.yml` 为生产配置。
3. 打包应用：
   ```bash
   mvn clean package -DskipTests
   ```
4. 运行：
   ```bash
   java -jar target/mental-health-0.0.1-SNAPSHOT.jar
   ```

> 可配合 systemd 或 Docker 进行守护与重启管理。

### 5.2 前端部署（Nginx）

1. 构建前端资源：
   ```bash
   npm run build
   ```
2. 将 `dist/` 部署到 Nginx 静态目录。
3. 使用 Nginx 反向代理后端接口 `/api`。

## 6. 常见问题排查

### 6.1 数据库无法连接

- 检查 MySQL 是否启动。
- 确认 `application.yml` 用户名、密码与端口是否正确。
- 确认数据库已创建并存在表结构。

### 6.2 IDEA 无法识别 Maven 依赖

- 检查 IDEA 是否启用 Maven 插件。
- 尝试右侧 Maven 面板 → **Reload**。

### 6.3 前端依赖安装失败

- 检查网络与 npm registry 是否可用。
- 可设置镜像源：
  ```bash
  npm config set registry https://registry.npmmirror.com
  ```

## 7. 推荐下一步（可选）

- 引入 Flyway 或 Liquibase 做数据库版本管理。
- 在 Controller 中通过 Mapper 接入真实数据库。
- 引入 `application-dev.yml` 与 `application-prod.yml` 区分环境配置。
- 提供 Docker Compose 一键启动 MySQL + 后端 + 前端。

---

如果你希望我继续补充：
- Docker Compose 实际部署文件
- 生产级 Nginx 配置样例
- 后端接口与数据库持久化改造方案
请直接告诉我你的需求。
