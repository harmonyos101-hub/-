# 台球厅管理系统

该项目提供一个基础可运行的台球厅管理系统示例，包含后端 API 与前端管理界面，覆盖顾客、桌位、预定、账单、优惠、反馈、维护与排班的核心流程。

## 项目结构

```
backend/   # FastAPI 后端服务
frontend/  # 静态前端页面
```

## 后端启动

```bash
cd backend
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
uvicorn app.main:app --reload
```

服务默认启动在 `http://localhost:8000`，访问 `http://localhost:8000/docs` 查看 API 文档。

## 前端启动

前端为静态页面，可使用任意静态服务器启动：

```bash
cd frontend
python -m http.server 5173
```

打开 `http://localhost:5173` 即可访问管理界面。

## 主要接口

- `GET /api/users` 顾客列表
- `POST /api/users` 新建顾客
- `GET /api/tables` 桌位列表
- `POST /api/reservations` 创建预定
- `POST /api/bills` 生成账单
- `GET /api/reports/summary` 经营概览
