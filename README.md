# 台球厅管理系统（Java / Spring Boot）

该项目提供一个完整的台球厅管理系统后端与轻量前端控制台，涵盖顾客、桌台、预定、账单、员工、优惠、资源、排班、反馈与审计日志等模块。

## 技术栈

- Spring Boot 3.2
- Spring Data JPA
- MySQL 8.0
- Maven

## 运行方式

1. 启动 MySQL 并创建数据库：
   ```sql
   CREATE DATABASE billiards_hall DEFAULT CHARACTER SET utf8mb4;
   ```
2. 修改 `src/main/resources/application.yml` 中的数据库账号密码。
3. 启动服务：
   ```bash
   mvn spring-boot:run
   ```
4. 访问前端控制台：`http://localhost:8080/index.html`

如果需要更完整的新手部署教程，请查看 [DEPLOYMENT.md](DEPLOYMENT.md)。

## 模块概览

- 顾客管理：`/api/customers`
- 桌台管理：`/api/tables`
- 预定管理：`/api/reservations`
- 账单管理：`/api/bills`
- 员工管理：`/api/staff`
- 促销活动：`/api/promotions`
- 优惠券：`/api/coupons`
- 反馈与客服：`/api/feedback`
- 资源管理：`/api/resources`
- 排班管理：`/api/shifts`
- 审计日志：`/api/audit`
- 经营报表：`/api/reports/summary`

## 前端说明

前端为简单的静态页面，可快速查看系统数据与经营概览，适合演示和对接。
