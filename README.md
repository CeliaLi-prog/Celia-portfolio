# Celia-portfolio (Spring Boot + REST + PostgreSQL + JWT + CMS)

## 项目简介
一个可视化 CMS + 作品集/简历主页的全栈项目。后台使用 Spring Boot + JPA + JWT，前端为静态页面（Bootstrap 模板改造），支持内容编辑、媒体上传、服务/作品集管理、联系表单提交等。

## 主要功能
- 首页内容可编辑（Hero / About / Contact / Footer / Skills & Tools / Pet Gallery）
- Services / Portfolio 列表与详情可编辑（含富文本编辑器）
- 媒体上传（支持拖拽、多文件、进度条）
- 登录/登出 + JWT 权限控制
- 联系表单提交保存到数据库

## 技术栈
- Java 17, Spring Boot, Spring Security, JPA (Hibernate)
- PostgreSQL
- 静态前端：HTML/CSS/JS + Bootstrap
- 富文本编辑器：Quill

## 本地运行
### 1) 准备数据库
```sql
CREATE DATABASE resume_db;
```

### 2) 环境变量（推荐）
```bash
export APP_JWT_SECRET=your_long_random_secret
export SPRING_DATASOURCE_PASSWORD=your_db_password
export APP_INIT_ADMIN_ENABLED=true
export APP_INIT_ADMIN_PASSWORD=your_admin_password
```

如需自定义上传目录：
```bash
export APP_UPLOAD_DIR=/Users/kathrynlee/Desktop/resume-site/uploads
```

### 3) 启动
```bash
mvn spring-boot:run
```

## 访问入口
- 主页：`http://localhost:8080/`
- CMS：`http://localhost:8080/cms.html`
- 登录页：`http://localhost:8080/auth.html`

## CMS 使用说明（HR 快速查看）
1. 访问 `auth.html` 登录（首次启动会通过 `APP_INIT_ADMIN_*` 初始化管理员）
2. 进入 `cms.html` 编辑内容并保存
3. 刷新主页查看更新结果

## 核心接口
### 认证
- `POST /api/auth/register`
- `POST /api/auth/login`
- `POST /api/auth/logout`
- `POST /api/auth/init-admin`

### 首页配置
- `GET /api/homepage`
- `PUT /api/homepage` (ADMIN)

### Services / Portfolio
- `GET /api/services` (public)
- `GET /api/services/admin` (ADMIN)
- `POST /api/services` / `PUT /api/services/{id}` / `DELETE /api/services/{id}` (ADMIN)

- `GET /api/portfolio` (public)
- `GET /api/portfolio/admin` (ADMIN)
- `POST /api/portfolio` / `PUT /api/portfolio/{id}` / `DELETE /api/portfolio/{id}` (ADMIN)

### 媒体上传
- `POST /api/media/upload` (ADMIN)
- `GET /uploads/{filename}` (public)

### 联系表单
- `POST /api/contact` (public)

## 备注
- 上传文件默认保存到 `uploads/`，访问路径为 `/uploads/<filename>`
- JWT 登出使用内存黑名单（演示用途）
