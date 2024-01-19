## 项目介绍

用户在线做题的系统，能够根据用户提交的代码、管理员预先设置的题目输入和输出用例，进行编译代码、运行代码、判断代码运行结果是否正确。

**项目地址：** [BU OJ 判题系统](http://oj.bc2996.com/)

### 项目结构

#### 1. buoj-backend-micorservice

- buoj-backend-common: 公共模块，公共配置、常量、异常处理、工具类等
- buoj-backend-model: 实体类模块，公共实体类
- buoj-backend-service-client: 服务客户端模块，服务之间使用OpenFeign相互调用
- buoj-backend-gateway: 网关服务，后端统一入口
- buoj-backend-user-service: 用户服务，负责用户管理，登录，注册等
- buoj-backend-question-service: 题目服务，负责题目增删改查，以及用户的代码提交等
- buoj-backend-judge-service：判题服务，负责调用代码沙箱，比较运行返回结果

#### 2. buoj-code-sandbox

[代码沙箱](https://github.com/byc996/buoj-code-sandbox)

## 核心业务流程

![img](https://cdn.nlark.com/yuque/0/2024/jpeg/25961647/1705695949066-586d710d-1be7-4735-80c2-dfbc3c7c3b9f.jpeg)

## 技术选项

### 后端

- Java 8
- MySQL 数据库
- Spring Boot 2.6.13
- Spring Cloud Alibaba 2021.0.5.0
- Spring Session Redis 分布式登录
- Hutool 工具类
- RabbitMQ 消息队列

### 前端

- Vue3, Vue Router, Vuex
- Arco Design UI框架
- ByteMD Markdown编辑器
- Monaco Editor 代码编辑器
- openapi-typescript-codegen 快速生成axios 调用后端代码
- Eslint +Prettier 代码格式化和规范代码



## 功能展示

### 首页



![img](https://cdn.nlark.com/yuque/0/2024/png/25961647/1705681826795-59b921ae-4f45-4acc-80fd-449eb07283b1.png)

### 题目管理

![img](https://cdn.nlark.com/yuque/0/2024/png/25961647/1705681877388-f96d4a11-4d19-4aa9-b866-15353355371f.png)

### 题目创建

![img](https://cdn.nlark.com/yuque/0/2024/png/25961647/1705681895872-82832cc7-5b21-4ee1-8871-eeb495e2b56f.png)

### 在线做题

![img](https://cdn.nlark.com/yuque/0/2024/png/25961647/1705681931508-5bc15457-458a-4bd5-9276-e27e8874e455.png)



### 题目提交

![img](https://cdn.nlark.com/yuque/0/2024/png/25961647/1705681969415-bbc46d53-bb70-4ed6-9bc7-281ce8ef4476.png)

#### 题目提交记录

![img](https://cdn.nlark.com/yuque/0/2024/png/25961647/1705681984643-db6ea1a3-cbc7-41ec-9003-c9f599f26497.png)