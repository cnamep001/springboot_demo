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
    
- org.apache.ibatis.exceptions.TooManyResultsException
    - 这个异常报了两次，对象转集合。
    
- jdbc连接的时区要设置
    serverTimezone=GMT%2B8    
    
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

//1、不删数据
//2、分表分库   ->   过亿
//3、数据仓库

//sql语句的优化

echo "# springboot_demo" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/cnamep001/springboot_demo.git
git push -u origin main