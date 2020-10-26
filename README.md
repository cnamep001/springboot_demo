### 编程环境:IDEA2018.2

### 项目概述

首先来了解项目需求。

项目分为客户端和后台管理系统两个界面，客户端针对普通用户，功能包括用户登陆、用户退出、菜品订购、我的订单。

后台管理系统针对管理员，功能包括管理员登陆、管理员退出、添加菜品、查询菜品、修改菜品、删除菜品、订单处理、添加用户、查询用户、删除用户。

需求了解完之后，接下来设计系统，首先分配出4个服务提供者，account、menu、order、user。

- account 
    - 提供账户服务：用户和管理员登陆。

- menu 
    -  提供菜品服务：添加菜品、查询菜品、修改菜品、删除菜品。

- order 
    - 提供订单服务：添加订单、查询订单、删除订单、处理订单。

- user 
    - 提供用户服务：添加用户、查询用户、删除用户、用户修改。
    



## 异常    


- org.springframework.beans.factory.UnsatisfiedDependencyException
不满足依赖异常
- org.springframework.beans.factory.BeanCreationException
- org.springframework.beans.BeanInstantiationException
- org.springframework.core.NestedIOException
- org.apache.ibatis.builder.BuilderException
- java.lang.IllegalArgumentException
```
Unsatisfied dependency expressed through bean property 'sqlSessionFactory'; 

nested exception is org.springframework.beans.factory.BeanCreationException:
 Error creating bean with name 'sqlSessionFactory' defined in class path resource 
    
[org/mybatis/spring/boot/autoconfigure/MybatisAutoConfiguration.class]: 
Bean instantiation via factory method failed; 
nested exception is org.springframework.beans.BeanInstantiationException: 

Failed to instantiate [org.apache.ibatis.session.SqlSessionFactory]: 

Factory method 'sqlSessionFactory' threw exception; 
nested exception is org.springframework.core.NestedIOException: 
Failed to parse mapping resource: 
'file [C:\Users\GUSHI\IdeaProjects\springboot2\target\classes\mapping\UserRepository.xml]'; 

nested exception is org.apache.ibatis.builder.BuilderException: 
Error parsing Mapper XML. Cause: java.lang.IllegalArgumentException: 
Mapped Statements collection already contains value for com.m.dao.AdminDao.login
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireByType
	(AbstractAutowireCapableBeanFactory.java:1431)

```
- org.apache.ibatis.exceptions.TooManyResultsException
    - 这个异常报了两次，对象转集合。
    
- jdbc连接的时区要设置
    serverTimezone=GMT%2B8    

1、http://localhost:8080/account/redirect/index    status=500

- org.thymeleaf.exceptions.TemplateInputException
    

> 常见的错误状态码

404：资源找不到，你要检查 url 是否拼写正确。

405：请求类型不匹配，只能接收 POST，你发送的是 GET。

400：参数不匹配，检查 URL 的传参。

500：代码中抛出异常，逻辑代码的问题。    



## 小记录,优化思路

```    
    //index.html改成user_main.html
    //main.html 改成admin_main.html
    
        <select id="findOrderById" resultType="com.m.entity.Order">
            <!--*修改为id-->
            select id from t_order
            where uid = #{param1}
            and is_delete = 1
        </select>
        
        <select id="findOrderById" resultType="com.m.entity.Order">
            select id from t_order
            where mid = #{param1};
        </select>
            
        <select id="countByState" parameterType="int" resultType="int">
            select count(id) from t_order
            where state = #{state}
            and  is_delete = 1
        </select>
       
``` 

```

//1、不删数据
//2、分表分库   ->   过亿
//3、数据仓库

//sql语句的优化

```





## github

```
echo "# springboot_demo" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/cnamep001/springboot_demo.git
git push -u origin main

```
