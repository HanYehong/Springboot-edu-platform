# Springboot-edu-platform
基于Springboot成绩可视化平台，内含sql文件，在Navicat中运行sql文件即可得到项目的数据库（注意你本地的数据库名称与项目中的是否吻合，在application.yml中查看mysql的配置）

总体基于Springboot搭建；

持久层采用的Spring Data Jpa的方式和Mysql数据库进行通信；

采用了安全框架Shiro对登录模块进行控制，并在数据库中对登录密码进行了MD5加密；

在短信发送的部分利用了Redis存储验证码；

在展示层利用Echarts技术实现成绩可视化；

前端技术主要为BootStrap、Ajax、Json、JQuery、JavaSript、CSS。
