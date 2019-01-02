# SpringCloud-scaffolding
## SpringCloud通用整合基本框架
该项目是一个典型的由springcloud管理的微服务项目，主要包括如下模块

|     服务名      | 父级依赖 |         模块说明         |
| :-------------: | :------: | :----------------------: |
|   api_gateway   |    无    |        zuul版网关        |
|  cloud-gatewat  |    无    | spring-cloud-gateway网关 |
|  eureka_server  |    无    |         注册中心         |
|  user_service   |    无    |       用户服务模块       |
| comment_service |    无    |       评论服务模块       |
| weather_service |    无    |       天气服务模块       |

1. 用户服务
   - [x] 登录注册
   - [x] 单点登录
   - [x] 找回 / 修改 密码
   - [x] 每天推送天气信息
   - [ ] 根据注册的IP地址智能推荐附近的信息（已完成天气推送）
2. 评论服务
   - [x] 保存 / 缓存取拿
   - [x] 父评论自评论组装显示
   - [ ] 热评分析智能推荐
   - [ ] 评论点赞
3. 天气服务
   - [x] 根据城市ID / 名称 取天气信息