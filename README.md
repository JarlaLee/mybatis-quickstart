学习地址: [02-MyBatis快速入门_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1MT4y1k7wZ?spm_id_from=333.788.videopod.episodes&vd_source=2f39b7a71778e6e5f3139416ebc6a414&p=2)





# MyBatis

MyBatis 是一款优秀的持久层框架, 用于简化JDBC开发。

官网：

持久层就是将数据保存到数据库的那一层代码。

JavaEE 三层结构

- 表现层 - Controller
-  业务层 - Service 
- 持久层 - Mapper / Repository



框架: 框架就是一个半成品的软件，是一套可重复使用，通用的，软件基础代码模型。在框架(其实也就是某种代码工具) 基础之上编写代码会更加高效，更加规范，更加通用和可扩展。

任何的一个企业级别的软件，不仅仅是前端，不仅仅是Java都需要各种框架。这些框架都可以极大改变编程的效率使得编程更加具有效率。



## 为什么 MyBatis 会流行呢？

MyBatis 其实是解决了 JDBC 开发过程中出现的开发效率低下，过程繁琐的缺点。

```Java
// 1.注册驱动
Class.forName("com.mysql.jdbc.Driver").
    
// 2.获取Connection连接String url = "jdbc:mysql:///db1?useSSL=false";
String uname = “root";
String pwd =“1234";
Connection conn = DriverManager.gefConnection(url, uname, pwd);
// 接收输入的查询条件
String gender = “男”;


// 定义sql
String sql = "select *from tb user where gender = ?";
// 获取pstmt对象
PreparedStatement pstmt = conn.prepareStatement(sql);


// 设置?的值
pstmt.setString(1,gender);


// 执行sql
ResultSet rs = pstmt.executeQuery();
// 追历Result，获取数据
User user = null;
ArrayList<User> users = new ArrayList<>();
while(rs.next()){
    // 获取数据
    int id = rs.getlnt("id”);
	String username = rs.getString("username");
	String password = rs.getString("password”);
    // 创建对象，设置属性值
    user = new User();
	user.setld(id);
	user.setUsername(usemame),
	user.setPassword(password);
	user.setGender(gender);
	// 装入集合
	users.add(user);
}
```





存在几个问题

1. 数据库注册的 硬编码
2. SQL 语句的硬编码 （但是我觉得不是）
3. 操作比较繁琐，手动封装结果比较麻烦。



硬编码和操作繁琐是基础框架普遍存在的问题。高级框架的解决方案就是将硬编码的内容写在对应的配置文件种。就比如数据库的相关编码信息就写入properties文件种。SQL语句可以写入对应Mapper的xml文件中去。对于操作繁琐的步骤，框架会自动完成这部分的任务。



在企业级别的项目种，持久层的框架技术使用占比图表:

- Mybatis 69
- Mybatis plus 36.5
- Spring Data JPA



学习流程

- Mybatis 快速入门
- Mapper 代理开发
- MyBatis 核心配置文件
- 配置文件完成增删改查
- 注解完成增删改查
- 动态SQL



# 快速入门

**查询user表种的所有数据**

1. 创建user表，添加一些数据
2. 导入mybatis的依赖，导入maven坐标
3. 编写mybatis 核心配置文件
4. 编写SQL mapper 文件 - 统一管理sql语句，解决硬编码问题
5. 编码
   1. 创建POJO类 / Model 类
   2. 加载核心配置文件，获取SqlSessionFactory对象
   3. 获取SqlSession 对象，执行SQL 语句
   4. 释放资源



第一次使用mybatis 或者其他的框架如何添加依赖呢。去官网查找maven 的依赖，然后粘贴在项目中。

如果使用的不是spring, 需要配置mybatis-config.xml 配置文件的

```java
driver:com.mysql.cj.jdbc.Driver
url: jdbc:mysql://localhost:3306/mybatisdemo?useSSL=false   // 到数据库，数据库名字mybatisdemo

?useSSL=false&serverTimezone=UTC
// 连接数据库时 不适用SSL，
//    时区为UTC    
    
username: root
password: gtsi2024.
```



如果使用spring 或者 spring boot，MyBatis 的配置通常会通过Spring的配置文件例如application.properties 或者 application.yml 来完成的。不需要单独的mybatis-config.xml 文件。

```java
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: password

mybatis:
  mapper-locations: classpath:mapper/*.xml # Mapper 文件路径
  configuration:
    map-underscore-to-camel-case: true # 开启下划线转驼峰
```



SQL 映射文件，如果你要操纵的是User表，对应的SQL映射文件就是 UserMapper.xml



logback 日志 和 slf4j 日志，Junit 日志

slf4j 是一款可以打印日志的工具。基于logback



学习Mybatis 入门之后，基本掌握使用Mybatis这个框架开发数据库的最基本的方式。



# Mapper 代理开发

这是企业中最常见的开发模式。Mapper 代理开发和基本的开发方式有什么区别呢？

基本的开发方式:

- 添加maven 依赖
- 添加静态 mybatis-config.xml 配置文件
- 添加对应的MapperUser.xml 文件
- 添加POJO 类
- 在代码中，通过SqlSessionFactory 来获取SqlSession 来获取对于数据库的访问
- 通过SQL中定义的 namespace + id 来执行特定的sql



Mapper 代理开发的模式

- 以上的方法基本不变
- 通过Java 反射的方式
  - UserMapper userMapper = sqlSession.getMapper(UserMapper.class)
  - List<User> users = userMapper.selectAll();



Mapper 开发的步骤

- 定义SQL映射文件同名的Mapper接口，并将Mapper接口和SQL映射文件放在同一目录下

  - 在resources 文件下定义对应的mapper包
  - 注意使用/来分级

- 设置SQL映射文件的namespace 属性为Mapper接口全限定名

  - ```
    com.yushenglll.quickstart.mybatisdemo.mapper.UserMapper
    ```

- 在Mapper 接口中定义方法，方法名就是SQL映射文件中sql语句的id，保持参数类型和返回值类型一致。

- 编码

  - 通过SqlSession 的getMapper方法 获取Mapper 接口的代理对象
  - 调用对应的方法完成SQL的执行

注意：如果Mapper接口名称和SQL映射文件名称相同，并在同一目录下，可以通过使用包扫描的方式简化SQL映射文件的加载



# Mybatis 核心细节配置

mybatis的核心配置文件, mybatis-config.xml

mybatis 的配置文件包含了深深影响MyBatis行为的设置和属性信息

mybatis 的配置文件中包含很多内容。具体内容可以参考官网：[配置_MyBatis中文网](https://mybatis.net.cn/configuration.html)

Configuration:

- properties
- settings
- typeAliases
- typeHandlers
- objectFactory
- plugins
- environments
  - environment
    - transactionManager
    - dataSource
- databaseldProvider
- mappers



# 增删改查

## 配置文件 完成增删改查

将sql语句写入 Mapper.xml 文件中去

一张表通常基础的功能:

- 查询
  - 查询所有数据
  - 查询某条数据的详细信息
  - 条件查询
- 添加
  - 添加一条数据
  - 批量添加数据
- 修改
  - 修改全部字段
  - 动态修改字段（删除字段不确定）
- 删除
  - 删除一个数据
  - 删除一批数据



项目的配置（这些都是开发的一般步骤）:

- 数据库表
- 实体类 Brand
- 测试用例
- 安装MyBatisX 插件

MyBatisX 插件：基于IDEA的快速开发插件。

主要功能：

- XML 和 接口方法互相跳转
- 根据接口方法生成statement



任何CURD的功能分为三个步骤:

- 编写接口方法 - 编写 Mapper 接口中定义的方法
  - 编写方法注意输入值，返回值
- 编写SQL语句 -- 在SQL Mapper 文件中 （可以通过MybatisX插件快速定位）
- 执行方法，测试。



MyBatis 完成操作需要几个步骤呢?

三个步骤: 编写接口方法 --> 编写SQL --> 写一个测试, 测试方法



发现问题:

数据库表的attribute 名称和 Java 类中名称不一样，不能自动封装数据 (查询的时候)

手动设置

- 给对应的字段取别名

  - selset brand_name brandName

  - 定义一个sql标签

    - ```java
      <sql id="brand_column"> id, brand_name as brandName, company_name as companyName, ordered, description, status
      </sql>    
      ```

- 定义一个标签 resultMap

  - ```java
    <resultMap id="brandResultMap" type="brand">
        <result column="" property=""> </result>
        
    	column 就是列名， property 就是属性名    
    </resultMap> 
        
    然后再对应的Select 标签中，使用 resultMap 属性    
    ```

ResultMap 的使用：

- 1. 定义ResultMap 的标签 （标签中两个属性, id 和 type）
     1. 定义result （有两个属性 column, property）
  2. 在对应的SQL 语句标签地方，通过 resultMap标签 通过id绑定定义好的ResultMap

这样才叫完整的使用。



**查看详情**

就是查看某一条数据的信息。根据id查询这条记录的详细信息。

1. 编写接口方法：Mapper 接口
2. 参数：id
3. 结果：Brand
4. 编写SQL 语句
5. 执行方法



参数占位符号:

- #{ }, 替换为 ？，这是为了防止SQL注入的方式
- ${ } --> 可能存在SQL 注入的问题

传递参数的时候，一定需要使用#{ }，一定要避免SQL 注入



${ } 用于动态查询数据的时候使用的。将来可以将table及时的更换

指定参数类型:

- parameterType 属性指定对应sql的传入参数的数据类型
  - ParameterType 这个值可以省略
- 对于特殊字符的处理



按照id 搜索特定brand的方式有以下几点

- #{} 执行sql的时候，将占位符替换为？
- ${} 拼接 sql，会存在SQL 注入问题
- 传递参数的时候，都使用#{}
- 如果对于表名，列名进行动态设置，只能使用${} 进行SQL 拼接

SQL 语句中特殊字符处理

- 转义字符
- CDATA区



**条件查询**

- 多条件查询
- 多条件的动态查询
- 单条件的动态条件查询



普通多条件查询

```java
List<Brand> selectByCondition(@Param("status") int status, @Param("compnyName") String companyName, @Param("brandName") String brandName);


// 将多个查询条件封装成一个对象
List<Brand> selectByCondition(Brand brand);

List<Brand> selectByCondition(Map map);


// SQL 部分
<select id="selectByCondition" resultMap="brandResultMap">
    select *
    from tb_brand
    where 
    	status = #{status}
		and company_name like #{companyName}
		and brand_name like #{brandName}
</select>
```



















## 注解完成增删改查

将sql 语句写在方法的注解上面



