# 台球厅管理系统部署文档（新手友好版）

本指南假设你是第一次接触 Java/Spring Boot 与数据库部署。按照步骤操作即可完成本地运行。

---

## 目录

1. 前置条件检查
2. 安装 JDK 17
3. 安装 Maven
4. 安装并启动 MySQL 8.0
5. 创建数据库与账号
6. 配置项目
7. 启动项目
8. 访问页面与接口
9. 常见问题（FAQ）

---

## 1. 前置条件检查

请在命令行中确认以下命令可用：

```bash
java -version
mvn -version
mysql --version
```

如果提示找不到命令，请继续后续安装步骤。

---

## 2. 安装 JDK 17

### Windows
1. 打开 [Oracle JDK 17](https://www.oracle.com/java/technologies/downloads/#java17) 或 [Eclipse Temurin 17](https://adoptium.net/temurin/releases/) 下载页面。
2. 下载 Windows 安装包并安装（一路下一步即可）。
3. 安装完成后打开命令行输入：
   ```bash
   java -version
   ```
   看到 `17` 版本即成功。

### macOS
1. 推荐使用 Homebrew：
   ```bash
   brew install --cask temurin@17
   ```
2. 验证：
   ```bash
   java -version
   ```

### Linux (Ubuntu)
```bash
sudo apt update
sudo apt install -y openjdk-17-jdk
java -version
```

---

## 3. 安装 Maven

### Windows
1. 访问 [Maven 官网](https://maven.apache.org/download.cgi) 下载 `Binary zip archive`。
2. 解压到任意目录，例如 `C:\tools\apache-maven-3.9.x`。
3. 配置环境变量：
   - 新增 `MAVEN_HOME`：指向 Maven 解压目录。
   - 在 `Path` 中加入 `%MAVEN_HOME%\bin`。
4. 验证：
   ```bash
   mvn -version
   ```

### macOS
```bash
brew install maven
mvn -version
```

### Linux (Ubuntu)
```bash
sudo apt update
sudo apt install -y maven
mvn -version
```

---

## 4. 安装并启动 MySQL 8.0

### Windows
1. 访问 [MySQL 下载页](https://dev.mysql.com/downloads/mysql/)，下载 MySQL 8.0 安装包。
2. 安装时记住设置的 root 密码。
3. 打开 MySQL 客户端或命令行：
   ```bash
   mysql -u root -p
   ```

### macOS
```bash
brew install mysql@8.0
brew services start mysql@8.0
mysql -u root -p
```

### Linux (Ubuntu)
```bash
sudo apt update
sudo apt install -y mysql-server
sudo systemctl start mysql
mysql -u root -p
```

---

## 5. 创建数据库与账号

登录 MySQL 后执行：

```sql
CREATE DATABASE billiards_hall DEFAULT CHARACTER SET utf8mb4;

-- 可选：创建专用账号
CREATE USER 'billiards'@'%' IDENTIFIED BY 'billiards123';
GRANT ALL PRIVILEGES ON billiards_hall.* TO 'billiards'@'%';
FLUSH PRIVILEGES;
```

---

## 6. 配置项目

打开项目中的配置文件：

```
src/main/resources/application.yml
```

修改数据库连接信息（示例）：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/billiards_hall?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: billiards
    password: billiards123
```

> 如果你使用 root 账号，请把 `username/password` 替换为 root 的账号密码。

---

## 7. 启动项目

在项目根目录执行：

```bash
mvn spring-boot:run
```

看到类似输出则表示成功：

```
Tomcat started on port(s): 8080
Started BilliardsHallApplication
```

---

## 8. 访问页面与接口

- 前端页面：
  ```
  http://localhost:8080/index.html
  ```

- 示例接口（浏览器可直接访问）：
  - 顾客列表：`http://localhost:8080/api/customers`
  - 桌台列表：`http://localhost:8080/api/tables`
  - 经营报表：`http://localhost:8080/api/reports/summary`

---

## 9. 常见问题（FAQ）

### Q1：运行时提示 `Access denied for user`
说明数据库账号密码不对，请检查 `application.yml`。

### Q2：端口被占用
默认端口是 8080，如果占用，请修改 `application.yml`：

```yaml
server:
  port: 9090
```

### Q3：Maven 下载依赖失败
可能是网络或仓库被限制，可在 `~/.m2/settings.xml` 配置国内镜像（例如阿里云 Maven 仓库）。

---

如果需要进一步部署到服务器（云主机），可在此文档基础上继续扩展部署方式（如 Docker、Nginx 反向代理等）。
