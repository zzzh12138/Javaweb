# Javaweb
本人是西安电子科技大学材料学院2018级本科毕业生，这是我的Javaweb毕设项目，是一个CRM管理系统。
---
项目采用前端Vue+ElementUI，后端是Servlet，使用Mybatis持久层框架。项目学习来源于黑马程序员B站课程。以下是本人笔记，大家可以按需学习。
# Javaweb

## 数据库

4. 注册mysql服务

   ```mysql
   mysqld -install
   ```

5. 启动MySQL服务

   ```MySQL
   net start mysql
   ```

6. 修改默认账户密码

   ```mysql
   mysqladmin -u root password 1234
   ```

7. 通过mysql.exe与mysql通信

   ```mysql
   mysql -uroot -p
   ```

8. 退出mysql

   ```mysql
   exit
   quit
   ```

9. 登录参数

   ``` mysql
    mysql -u用户名 -p密码 -h要连接的mysql的ip地址（默认127.0.0.1） -P端口号（默认3306）
   ```

10. 卸载

```mysql
net stop mysql
mysql -remove mysql
环境变量
```

### sql通用语法

1. sql以分号结尾，不区分大小写

2. 单行注释：-- 内容   #内容（mysql特有），多行注 释,/* 内容 */

3. 不等号！=也可以用<>

4. 注意，null值的比较不能用=或者！=，需要使用is或者is not

5. sql中等于是=，而不是==

6. 某个表的某个字段用·来连接

6. sql在xml中where id=#{id}，意思是参数占位符，相当于?，另一种参数占位符${}，会直接将其替换成值，前者能防止sql注入，后者不能防止sql注入

6. xml中对sql语句做特殊字符处理比如<,可以用转义字符或者<!CDATA[内容]>

   | DDL  |     数据定义语言，定义数据库对象     |
   | ---- | :----------------------------------: |
   | DML  |         数据操作语言，增删改         |
   | DCL  | 数据控制语言，定义访问权限和安全级别 |
   | DQL  | 数据查询语言，用来查询数据库中表记录 |
   
6. 分组查询group by
### 约束
1. 非空约束
保证列中所有数据不为null ，not null（关键字）
可以在建表时添加非空约束，也可以在建完表后添加非空约束，alter table 表名 modify 字段名 数据类型 not null（删除一样，只是不加not null）
2. 唯一约束
保证列中所有数据各不相同，unique
3. 主键约束
主键非空且唯一，primary key，可以设定自动增长，关键字auto_increment，只要数据类型设定，哪怕给null值都是123这种

4. 默认约束
未指定值给默认，Default xx，不写是默认，写null都有值
5. 检查约束
保证列中数据满足某一条件，check，MySQL不支持
6. 外键约束
连接两个表之间的数据，foreign，被关联的为主表，主动去关联的为从表，可以在创建表的时候constraint 
外键名称 foreign key（外键列名）reference 主表（主表列名），也可以在建完表后单独外键，alter table 从表 add constraint 外键名称 foreign key（从表列名 ）reference 主表（主表列名）
，删除外键语法alter table 从表  drop foreign key 外键名称
### 数据库设计
#### 表关系
1. 一对一
多用于表拆分，将经常使用的字段放一张表，不经常使用的放另一张表，提升查询性能。
在任意一方加入外键，关联另一方主键，并设置外键为唯一unique
2. 一对多（多对一）
在多的一方建立外键，指向一的一方的主键
3. 多对多
建立第三张中间表，中间表至少包含两个外键，分别关联两方主键
### 多表查询
如果select * from表1，表2会产生笛卡尔积（全组合），与where连用就是连接查询
1. 连接查询

  | 内连接         | ab交集              |      |
  | -------------- | ------------------- | ---- |
  | 外连接右外连接 | b表所有数据和ab交集 |      |
  | 外连接左外连接 | a表所有数据和ab交集 |      |

  ```mysql
  隐式内连接
  select 字段列表 from 表1，表2。。。 where 条件
  显式内连接
  select 字段列表 from 表1 [inner] join 表2 on 条件
  ```

  ```sql
  左外连接--表1在左，表2在右，查出了表1和交集
  select 字段列表 from 表1 left [outer] join 表2 on 条件
  右外连接--表1在左，表2在右，查出了表2和交集
  select 字段列表 from 表1 right [outer] join 表2 on 条件、
  ---
  左外连接和右外连接可以相互转化
  ```

  

2. 子查询（嵌套查询）

   根据查询结果不同，分为

   | 单行单列 | select 字段列表 from 表 where 字段名=（子查询）              | 用=、！=<> |
   | -------- | ------------------------------------------------------------ | ---------- |
   | 多行单列 | select 字段列表 from 表 where 字段名 in（子查询）            |            |
   | 多行多列 | select 字段列表 from (子查询)                                                                                                                                                                                                                                                                                                                        where 条件 | 作为虚拟表 |
### 事务

包含了一组sql语句，不可分割的逻辑单元，类似OS中的原子事务

#### 操作 

```mysql
start transaction或者 begin 开启事务
commit 提交事务
rollback 回滚事务
```

一旦开启了begin事务，只有当整个事务结束后才能实际性的改变数据，只在当前输入框中select *可以暂时看到改变了的数据，实际数据并没有改变，当前输入框中输入rollback又会回到原始数据，并没有真正改变数据

#### 四大特征

ACID，分别为原子性，一致性，隔离性，持久性

## JDBC

JDBC是Java语言操作关系型数据库的api，可以操作所有关系型数据库，Java给了接口，数据库厂商提供数据库驱动jar包，

### 快速入门

```;
工程导入jar包
 注册驱动，Class.forName（"com.mysql.jdbc.Driver"）;
获取连接Connection conn=DriverMananer.getConnection(url,username,password);
---url语法为"jdbc:mysql://127.0.0.1:3306/数据库名称?参数键值对1&参数键值对2"（若为本机默认3306，则端口和ip可以省去）
定义sql语句String sql="update  ";
获取执行sql对象 Statement stmt=conn.createStatement();
执行sql stmt.executeUpdate(sql);
处理返回结果并释放资源
stmt.close();和conn.close()；

```

### Jdbc的API

1. driver manager作用是注册驱动和获取数据库连接

2. Connection作用是获取执行sql对象和管理事务

   ```SQL
   普通执行sql对象 Statement createStatement（）
   预编译sql执行sql对象，防止sql注入PreparedStatement prepareStatement（sql）
   执行存储过程的对象CallableStatement prepareCall（sql）
   ```

   ```java
   事务管理
   开启事务conn.setAutoCommit（boolean autocommit），true为自动提交，false为手动提交
   提交事务conn.commit（）
   回滚事务conn.rollback（）
   ```
   
3. Statement为执行sql语句

   ```java
   int executeUpdate（sql）执行DML、DDL语句，返回DML影响的行数
   ResultSet executeQuery（sql）执行DQL语句，返回Result结果集对象
   ```

4. ResultSet结果集对象

   封装了DQL查询语句，返回ResultSet对象(比如要遍历整个表，返回的就是ResultSet对象)

   ```Java
   Boolean next()，将光标从当前位置向前移动一行，判断当前行是否为有效行，默认在表头，true为有效行，false无效
   XXX getXXX（参数），获取数据，XXX为数据类型，参数为int：列的编号，从1开始，string：列的名称
   使用步骤：while(rs.next()){rs,getXXX()}
   ```
   
5. preparedStatement预编译sql语句，防止sql注入

   > sql注入：是通过操作输入来修改之前定义好的sq语句，攻击服务器、

   ```java
   String sql="select * from user where username=? and password =?"//参数值用？代替不是用'，实际上相当于转义敏感字符，但是默认是关闭的，一般useServerPrepStmts=true开启
   通过Connection对象获取，并传入对应的sql语句 preparedStatement pstmt =conn.preparedStatement（sql）;
   preparedStatement pstmt=conn.prepareStatemt(sql);//通过Connection对象获取，并传入sql
   设置参数值：PreparedStatement对象是setXXX（参数1，参数2），给？赋值，参数1是？1，参数2是？2 
   执行sql用executeUpdate()/ executeQuery(),不需要传递sql
   ```

### 数据库连接池

数据库连接池是个容器，负责分配，管理数据库连接，允许应用程序重复使用一个现有数据库连接，不开的话conn直接用一下释放，用的话使用conn后回到连接池，提升响应速度，资源重用，避免数据连接遗漏

```java 
标准数据库连接池接口：DataSource
    功能：获取连接Connection getconnection(),以后不用drivermanager了
```

## Maven

[尚硅谷关于Maven教程](./尚硅谷-常用的插件.pdf)

专门用于管理和构建java项目的工具,具体功能由插件进行调控，

> 提供了一套标准化的项目结构，构建流程，和依赖管理机制，去除了不同ide的差异

 默认本地仓库在user/.m2/repositoryr

坐标是Maven中资源的唯一标识，用坐标来定义项目或者引入项目中需要的依赖,坐标组成，POM是XML格式，<></>

> groupid：定义Maven项目隶属组织名称，通常是域名反写
>
> artifactid：定义当前Maven项目名称，通常是模块名称
>
> version：当前项目版本号

### 依赖管理

用坐标导入jar包

1. pom.xml中编写<dependencies>标签
2. 在<dependencies>中引入<dependency>引入坐标
3. 定义坐标的<groupid>，<artifactid>、<version>并刷新

通过<scope>设置坐标的依赖范围，设置jar包的作用范围，编译环境，测试环境，运行环境

## Mybatis

是持久层框架，用于简化JDBC开发
>持久层用于将数据到保存到数据库的那一层代码,
>
>javaee三层架构：表现层、业务层、持久层

> JDBC缺点：硬编码，不便于改动，操作繁琐，
>
> 可以将连接和sql语句写到配置文件中

调用Mybatis完成查询

1. 获取SqlSessionFactory对象，官网拷贝从XML构建。。。改路径
2. 获取SqlSession对象``SqlSession sqlSession=sqlSessionFactory.openSession()``
3. 通过2获取Mapper``UserMapper userMapper=sqlSession.getMapper(UserMapper.class)``
4. 调用Mapper方法并释放资源``User user=userMapper.select(username,password)``,sqlSession.close()

### Mapper代理开发

目的：解决原生方式中的硬编码，并简化后期执行sql

> 比如sqlsession.selectList（参数）中参数还是用的xml中的mapper标签中的select标签
>
> 之后会变成SQLSession.getMapper(Usermapper.class),获取接口代理对象，返回了接口，原本的select标签变成了方法，还能IDE提示，不依赖字符串字面值更安全

规则：

1. 定义sql映射文件同名的Mapper接口，并且将mapper接口和sql映射文件放在同一目录下
2. 设置sql映射文件的那么搜啊侧属性为mapper接口全限定名
3. 在mapper接口中定义方法，方法名就是sql映射文件中sql语句的id，并保持参数类型和返回值类型一致
4. 在resource下新建目录结构用"com/itheima/mappper",将UserMapper.xml放进其中当配置文件，其中namespace是接口的全路径

### 配置文件增删改查

#### 条件查询三种参数方式

1. list<Brand> selectByCondition(@Param("status")int status, @Param("companyName")String companyName,@Param("brandName")String brandName)，散装参数用@Param("SQL参数占位符名称")
2. list<Brand> selectByCondition(Brand brand)，获取对象
3. list<Brand> selectByCondition(Map map)，map集合

> ／<select id="selectByCOndition" resultMap="brandResultMap"> sql语句</select>

sql语句随着用户的输入或者外部条件的变化而变化，为**动态sql**，Mybatis对动态sql有强大支撑，标签if（里面的test属性）、choose(when=case,otherwise=default)从多个条件中选择一个类似于Java中的Switch造句、trim(where（标签代替where关键字自动判断去掉and）,set)、foreach

> 返回添加数据的主键<insert useGeneratedKeys="true" keyProperty="id">

### 注解开发

注解完成简单功能，配置文件完成复杂功能

> 查询@Select，添加@Insert，修改@Update，删除@Delete
>
> 例：@select("select * from 表 where id =#{id}")
>
> public user selectById(int id)



## HTML

![image-20220311214508862](C:\Users\zhanghh\AppData\Roaming\Typora\typora-user-images\image-20220311214508862.png)

一般不输入资源就会自己导向index,html

### 表格标签

~~~html
<table>定义表格，border定义表格边框宽度、width定义表格宽度、cellspaceing规定单元格之间的空白
    <tr>定义行，align定义表格行的内容对齐方式
        <td>定义单元格，rowspan规定单元格可以横跨的行数，colspan规定单元格可横跨的列数
        <th>定义表头单元格
~~~

### 布局标签

~~~html
<div>块级标签，一占占一行，一行是一块
<span>组合行内元素，只占包裹的内容
~~~

### 表单标签

表单负责数据采集功能，使用<form>标签定义表单，

表单项（元素）:不同类型的input元素，下拉列表，文本域等

1. ~~~HTML
   <form>定义表单，action规定向何处url发送表单，action=”#“，是提交到当前页面的，但是要想被真正提交，需要在<input>填写name属性，method规定提交表单发送的方式是get还是post
   <input>定义表单项，通过type属性控制输入形式，type="text"或者”submit“
   <label>为表单项定义标注，一般用作关联<label>内容和id所指，如<label for ="username">关联<id="username">
   <select>定义下拉列表
   <option>定义下拉列表的列表项
   <textarea>定义文本域，用col和row改变行列大小值
   
2. get方法是直接将数据附在表单的Action URL之后，url大小限制4kb

   Post是将数据放到http消息体中，参数大小无限制，url不会出现变化

3. type取值

   | text     | 默认值，定义单行输入字段                                     |
   | -------- | ------------------------------------------------------------ |
   | password | 密码                                                         |
   | radio    | 定义单选按钮,要想多个选项，只需要有共同的username，若不加value属性，提交的username等于无用，用value属性重新定义要提交的数据，同理，input标签填入name属性后会提交输入进来值，想要更改用value属性 |
   | checkbox | 定义多选框，                                                 |
   | file     | 文件上传按钮                                                 |
   | hidden   | 定义隐藏输入字段，比如提交数据时隐藏id                       |
   | submit   | 定义提交按钮，表单数据发送到服务器                           |
   | reset    | 定义重置按钮                                                 |
   | button   | 可以点击按钮                                                 |

## CSS

### 导入方式

1. 内联样式：在样式内使用style属性，属性值是css属性键值对,不利于维护

   ~~~css
   <div style=XX></div>
   ~~~

2. 内部样式：定义<style>标签，在标签内部定义css

   ~~~css
   <style>
   div{XXX}
   </style>
   ~~~

3. 外部样式：定义link标签

   ~~~css
   <link rel=stylesheet href=XXX>
   /*rel描述了当前页面与href所指定文档的关系,关联的是一个样式表(stylesheet)文档，它表示这个link在文档初始化时将被使用*/
   ~~~

### 选择器

同样针对一个文字的样式选择，那种方式涉及的范围越小，谁优先生效

1. 元素选择器

   ~~~css
   标签名称如div{color:red;}
   ~~~

2. id选择器，只能针对一个元素

   ~~~css
   #id属性值如#name{}
   <div id="name"></div>
   ~~~

3. 类选择器，可以选择多个元素

   ~~~css
   .class属性值如.cls{}
   <div class="cls"></div>
   ~~~

## Javascript（JS）

### 引入和语法、常用对象

引入分内部脚本和外部脚本

1. 在HTML文档任意地方，可以放置任意数量的<scirpt>
2. 一般放在<body>元素的底部，可以改善显示的速度，脚本执行会拖慢显示
3. 外部引入用<script src=XX></script>
4. alert（）弹框显示

**语法**

1. 与Java一样区分大小写，每行末尾分号可有可无

2. 单行注释//,多行注释/**/

3. 输出语句有三种，Window.alert()写入警告框，document.write()写入HTML输出，console.log写入浏览器控制台

4. 用var声明变量，可以存放不同类型的值，全局变量，建议用驼峰命名，新增的let关键字定义变量类似于var，所声明的变量只在let关键字所在的代码块内有效且不允许重复声明，新增了const只读关键字，一旦声明不能改变

5. 数据类型分为原始类型和引用类型，用typeof获取数据类型

   | 原始类型：number | 整数、小数、NaN                          |
   | ---------------- | ---------------------------------------- |
   | string           | 字符、字符串、单双引号                   |
   | Boolean          | 布尔                                     |
   | null             | 对象为空，typefof null为Object，意外之错 |
   | undefined        | 变量未初始化                             |

6. 运算符基本等同于Java，但$if("20"==20)$，因为会先进行类型转换再比较，===全等于独有,如果类型不一样就直接不等

7. 类型转换,$var \quad str=+"abc"$,string类型认为是正号会将“abc”转为数字，而不是字符连接符号，一般使用parseInt（）方法

   Boolean类型的true转为1，false转为0，$var \quad flag=+true$

   number转Boolean，0和NaN转为false，其他转为true
   string：空字符串转为false，其他字符转为true

   null和undefined转boolean都是false

**函数**：用function关键词进行定义，语法为

~~~javascript
function functionName(参数1、2){return 参数}
~~~

参数和返回值不需要返回类型，因为Javascript是弱类型，调用直接在<script>里用函数名调用

传参时多一个参数无所谓，只按顺序接受前面的，少一个参数，缺的参数若是数字按照NaN计算，NaN与数字还是NaN

**对象**

1.array用于定义数组，类似Java集合，长度和类型都可以变，未赋值为undefined，若只给后面的赋值了，前面的就变成空了

~~~javascript
定义 var 变量名=new Array(元素列表如1,2,3)
	或 var 变量名 =[元素列表]//java用的是{}
访问 变量名[索引]=值
方法：push()在array后添加元素
	splice(从几删，删几个)在array后删除元素
~~~

2. string

   ~~~javascript
   定义：var 变量名=new String(str)；
   	或者 var 变量名 =str；
   属性：length
   方法：chatAt()返回在指定位置的字符
   	indexOf()检索字符串
       trim()去除字符串两端的空白字符
   ~~~

   属性和方法的区别：属性没有括号，用.来访问，方法有括号；方法的本质就是函数的调用

3. 自定义对象

   ~~~javascript
   var 对象名称{
       属性名称1：属性值1，
       ……
       函数名称：function(形参列表){//方法
           
       }
   }
   ~~~

### BOM浏览器组成对象	

1. 对象Windows，代表打开的窗口

   ~~~javascript
   获取：window.alert()=alert()
   属性：获取其他bom对象
   方法:alert()显示带消息和一个确认按钮的警告框
   	confirm()显示带消息和确认/取消的对话框，返回值可以直接给变量
   	setInterval(function(){},毫秒值)定时器，周期时间执行代码，
       setTimeout(function(){},毫秒值),定时器，指定时间后执行代码
   ~~~

1. 对象Navigator，代表整个浏览器对象
2. screen,显示记录
3. history对象，历史记录，用window.history()获取，其中window,可以省略

~~~javasrcipt
方法：back()，history列表中的上一个url
	forward(),history列表中的下一个url
~~~

4. location，地址url框，用window.location()获取，其中window,可以省略

~~~js
属性：href(),跳到完整的url
~~~

### DOM文档对象模型

将标记语言各个组成部分组成对象，按照html标签组装成DOM树，js可以通过DOM对HTML操作，改变元素内容样式，增删HTML元素，DOM事件做出反应，

1. 核心DOM，是任何结构化文档的标准模型

   - Document整个文档对象
   - Element元素对象
   - Attribute属性对象
   - Text文本对象
   - Comment注释对象

2. XML文档标准模型

3. HTML文档标准模型，将HTML元素封装成对象

   - Image:<img>

   - Button:<input type="button">

#### 获取对象

~~~Js
getElementById()方法，根据id属性返回Element对象,value属性是其值
getElementsByTagName()方法，根据标签名称，返回Element对象数组
getElementsByName()方法，根据name属性，返回Element对象数组
getElementsByClassName()方法，根据class属性，返回Element对象数组
~~~

### 事件监听

事件是发生在HTML上的，监听是指Js可以在事情被侦测到的执行代码

#### 事件绑定

1. 通过HTML事件属性进行绑定

~~~javascript
<input type="button" onclick="on()">
    function on(){
}
~~~

2. 通过DOM元素属性绑定

~~~javascript
   <input type="button" id="btn">
       document.getElementById("btn").onclick=funtion(){
~~~

### 正则表达式

定义：

- 直接量，不加引号因为不是字符串，``var reg=XXX``,常用

- 创建RegExp对象，加引号，因为是正则表达式字符串，``var reg =new RegExp("XXX")``

  方法：test（str）判断 指定字符串是否符合规则，返回true或者false

语法：

- /^表示开始

- $/表示结束

- []某个范围内的单个字符

- . 代表任意单个字符，除了换行和行结束符

- \w 代表单词字符，包括字母数字下划线，=[A-Za-z0-9]

- \d代表数字字符,=[0-9]

  量词：

  - +至少一个，\w+表示一个字符串至少以一个字符组成

  - *0/多个

  - ?0个/1个

  - {x}x个

  - {m,}至少m个

  - {m,n}至少m个最多n个
 ## web核心

​    HTTP基于TCP协议，每次请求响应都是独立的，多次请求间不能共享数据，用会话解决

**请求数据格式**

1. 请求行：请求数据第一行，有请求方式，请求资源路径，协议版本
2. 请求头：key：value格式
3. 请求体：POST请求的最后一部分，存放请求参数

GET请求参数在请求行中，没有请求体，POST请求参数在请求体中

**响应数据格式**

1. 响应行：响应数据第一行，有协议版本，状态码，状态码描述

2. 响应头：key：value格式

3. 响应体：最后一部分，存放响应参数

**Tomacat**

是轻量级的Web服务器，支持Servelt、Jsp、少量JavaEE规范，是web容器、servlet容器，Servlet需要Tomcat才能运行

**Servlet**

是java提供的动态Web资源开发技术，是JavaEE的规范之一，是一个接口，定义Servlet类实现Servlet接口，并由web服务运行Servlet

- servlet要想访问，必须配置访问路径，所以加个web注解@WebServlet("访问路径")，然后写个implement接口，再重写父类方法

- 创建web项目，导入依赖

~~~xml
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
            <!--scope很重要-->
        </dependency>
~~~
- servlet对象是由Tomcat服务器创建的，调用service方法，完成响应

- 因为自定义的servlet必须实现servlet接口并且复写其方法，而servlet接口中有service方法，所以服务器一定知道servlet中有service方法

- **生命周期**由容器来管理，分为四个阶段

  | 加载和实例化 | 默认servlet第一次被访问时，由容器创建servlet对象，在@WebService(loadOnStartup) |
  | ------------ | ------------------------------------------------------------ |
  | 初始化       | 在实例化之后，容器调用servlet的init()方法初始化对象，完成加载配置文件，创建连接等初始化工作，只调用一次 |
  | 请求处理     | 每次请求sevlet后，容器都会调用servlet的service()方法对请求进行处理，每次servlet被访问时调用 |
  | 服务终止     | 当需要释放内存或者容器关闭时，都会调用destroy方法完成资源的释放，回收垃圾处理 |
  
- **体系结构**，servlet是体系根接口，GenericServlet抽象实现类，HttpServlet是对HTTP协议封装的Servlet实现类，HttpServlet使用步骤是先继承HttpServlet，然后重写doGet和doPost方法，原理是根据不同的请求方式调用不同的doXXX方法、

- Servlet urlPattern访问路径配置，一个Servlet可以配置多个urlPattern，整个路径是自己涉及给文件管理的，完了访问网站需要加入在最后，越精确的越优先，访问对应的.java文件，优先级依次降低配置规则有

  1. 精确匹配@Webservlet("/user/select")

  2. 目录规则@Webservlet("/user/*")

  3. 扩展名匹配@Webservlet("*.do")//如果是``/*.do``,会直接报错

  4. 自由匹配@Webservlet("/")

     当servlet项目配置了"/",会覆盖掉tomcat中的DefaultServlet，这个管静态资源的访问，当其他的urlpattern都匹配不上的时候会走这个Servlet，实际应用中减少用/或者/*,因为会覆盖掉访问静态资源比如HTML文件
  
- 3.0版本后支持使用注解配置，3.0版本前只支持XML配置文件，格式见课件web-demo代码

### request和response

- **request体系结构**：ServletRequest是Java提供的请求对象根接口

​		HttpServletRequest是Java提供的对Http协议封装的请求对象接口，继承自ServletRequest

​		RequestFaced是Tomcat定义的实现类，封装为request对象，并且创建request对象传递到service方法中，		不是Java实现类

- **response体系结构**：ServletResponse是Java提供的请求对象根接口

  HttpServletResponse是Java提供的对Http协议封装的请求对象接口，继承自ServletResponse

  ResponseFaced是Tomcat定义的实现类，封装为Response对象，并且创建Response对象传递到service方法中，不是Java实现类

- **request获取请求数据**：

  ~~~JAVA
  请求行    String getMethod()获取请求方式
          String getContextPath()获取动态虚拟项目目录
          StringBuffer getRequestURL()获取URL(统一资源定位符)，http...req1，url分为URL和?之后的参数
          String getRequestURI()获取短的URI(统一资源标识符),/request-demo/req1
          String getQueryString()获取请求参数(GET方式)
      	BufferedReader getReader()(Post方式)
  请求头   String getHeader(String name)根据请求头名称获取值
  请求体	  BufferedReader getReader()获取字符输入流，比如纯文本，后序要读取用readline()读取
      	ServletInputStream getInputStream()获取字节输入流，比如文件
  ~~~

  - GET和POSt的区别主要在于获取请求参数的方式不一样，底层通过if判断，post多一步从流中realine，统一doget和dopost方法的方式 

  - request通过Map<String,String[]>存放获取的参数成为统一post和get的方式，底层封装了

    ~~~java
    Map<Sting,String[]> getParameterMap()获取所有参数Map键值集合
    String[] getParameterValues(String name)根据名称获取参数数组
    String getParameter(String name)根据名称获得单个参数
    doPost内只有this.doGet()转到doGet减少代码量
    ~~~
    
  - request中文乱码：若是POST设置输入流的编码``req.setCharacterEncoding("UTF-8")``,因为调用的是getReader
  
  - Servlet可以把数据封装起来放到request域中，Jsp获取域中数据，展现数据
  
- **request请求转发**，请求转发是在服务器内部的资源跳转方式，实现方式从转发``getRequestDispatcher(路径).forward(req,resp)``方法

  ~~~java
  请求转发资源间共享资源：使用request对象
      void setAttribute(String name,Object o)，前面是键，后面是值，存入request域中
      Object getAttribute(String name)根据键获取值
      void removeAttribute(String name),根据键删除键值对
  ~~~

  - 请求转发特点：url不发生变化，只能转发到当前服务器内部的资源，一次请求可以在转发的资源间使用request共享数据
  
- **response**响应数据都属于response对象

  ~~~java
  响应行：void setStatus(int sc)设置响应状态码
  响应头：void setHeader(String name,String value)设置响应头键值对
  响应体：PrintWriter getWriter()获取字符输出流，给对象后可以用write("<h1></h1>")方法写HTML页面，当然要在前面设置content-type调用HTML解析引擎response.setHeader("content-type","text/html")，更简便的是response.setContentType("text/html","charset=utf-8")放在getWriter前，这样中文不会乱码，该流不需要关闭，随着响应结束response对象销毁
     	  ServletOutputStream getOutputStream()获取字节输出流
  ~~~

  - 重定向状态码302，响应返回其他资源的位置即响应头``setHeader(location,虚拟目录)``或者简化形式``sendRedirect(虚拟目录)``，相比于请求转发，特点是地址栏发生变化，可以重定向到任意位置资源，发生了两次请求，不能在多个资源使用request共享数据
- **路径问题是否加虚拟目录**，若是浏览器使用，则需要加虚拟目录也就是项目访问路径，若是服务端使用，则不需要加虚拟目录，比如请求转发是服务器端使用，重定向是浏览器使用

### JSP

Java服务端页面，一种动态网页技术=HTML+Java，可以定义静态内容，也可以定义Java代码的动态内容，简化开发，避免了Servlet中直接输出HTML标签。引入依赖后可以创建JSP文件

- 原理

  本质上就是一个Servlet，在收到请求后JSP容器(Tomcat)将jsp转为servlet（.java文件），然后编译成.class文件

- 脚本

  jsp脚本用于在Jsp页面内定义java代码，脚本分类

  > <% %>内容直接放到_jspService()方法中
  >
  > <%=  %>内容放在out.print()中，作为out.print()的参数
  >
  > <%! %>内容直接放到_jspService()方法外，被类直接包含

- EL表达式

  即表达式语言，替换获取数据的Java代码，主要功能是获取数据的，语法是``${表达式}``，意思是获取域中存储的key是表达式的数据，提前在Servlet中request存储数据后，转发到Jsp页面中去

  - JavaWeb中四大域对象：page，当前页面有效；request，当前请求有效；Session，当前会话有效；Application，当前应用有效……EL表达式会依次在这四个域中获取数据，直到找到为止

- JSTL标签即JSP标准标签库，使用标签取代JSP页面上的Java代码，导入依赖，在JSP页面上引入JSTL标签库<%@ taglib prefix="c" uri =""http://java.sun.com/jsp/jstl/core %>,这些是指令，

  - 使用时``<c:if>要显示的</c:if>``相当于Java中的if ，但是不提供else，else也用``<c:if>``实现，其有test属性，值为逻辑表达式，可以与EL表达式连用
  - ``<c:forEach><c:forEach``相当于for循环，替换循环遍历的代码，有items属性（被遍历的容器）和var属性（每次遍历产生的临时变量）和varStatus(遍历状态对象)，被遍历的代码是EL表达式化并每个对象``.``属性值（这个属性值不是成员变量而实际用的是方法）的HTML，特别的对于varStatus是status.count。
    - 开始步长形式，可以完成分页工具
  
- MVC模式和三层架构

  **MVC**:业务模型视图模型控制器模型，业务模型也即获取数据，一般由Servlet获取数据，JSP当作视图，JavaBean当作模型

  **三层架构**：

  | 表现层     | 接受请求封装数据，调用业务逻辑层响应数据，包括Servlet控制器和JSP视图 | 包名一般为com.web/controller | 三大框架用SpringMVC/Structs2代替 |
  | ---------- | ------------------------------------------------------------ | ---------------------------- | -------------------------------- |
  | 业务逻辑层 | 封装业务逻辑，组合数据访问层中基本功能，形成复杂的业务逻辑功能, | 包名一般为com.service        | Spring                           |
  | 数据访问层 | 对数据库进行CRUD操作，持久层dao(data access object)          | com.dao/mapper               | Mybatis/Hibername                |

  (三大框架即ssm)

  **关系**：MVC中JSP视图和Servlet控制器充当表现层，JavaBean充当业务逻辑层和数据访问层

### 会话跟踪技术

会话：打开浏览器开一个窗口建立会话，直到一方断开连接会话结束，一次会话包含多次请求和响应

会话跟踪：一种维护浏览器状态的方法，服务器识别多次请求是否来自于同一个浏览器，以便在同一次会话的多次请求间共享数据。需要识别请求的原因是HTTP协议是无状态的，每次请求服务都被认为是新的请求，需要这样的原因是后面的数据请求会变得越来越大

实现方式：客户端会话跟踪技术是Cookie；服务端会话跟踪技术是Session

- Cookie，数据保存在客户端，以后每次请求都携带Cookie访问，大小限制3KB，可以长期存储

  ~~~java
  创建Cookie对象：Cookie cookie =new Cookie("key","value")
  发送Cookie到客户端：response.addCookie(cookie),发送写在doGet中
  获取客户端的所有Cookie，使用request对象：Cookie[] cookies=request.getCookies();遍历数组获取每一个Cookie对象
  使用Cookie对象获取数据：cookie.getName()和getValue(),接收也写在doGet中              
  
  ~~~

  原理：基于HTTP，响应头：set-cookie，在response header中，请求头：cookie，在request header中

  存活时间：默认存储在浏览器内存中，当浏览器关闭则cookie销毁，

  ```java
  setMaxAge(int seconds)设置Cookie存活时间，若为正数则Cookie写入硬盘，持久化存储，到时间自动删除
   										若为负数，默认情况，若为0，直接删除对应Cookie
  ```

  存储中文：不能直接存储中文，需要存储则需要进行URL转码

  ~~~java
  String value="汉字"
  value=URLEncoder.encode(value,"UTF-8"),完了再一摸一样进行一次就ok
  ~~~

- Session，数据存在服务端，JavaEE提供HttpSession接口，实现一次会话多次请求间数据共享功能，相较于Cookie更安全一些，大小无限制

  ~~~java
  获取Session对象：HttpSession session=request.getSession();
  Session对象功能：void setAttribute(String name,Object o)存储数据到session域中，
  				Object getAttribute(String name)根据key获取值
      			void removeAttribute(String name)根据key删除键值对
  ~~~

  原理：基于Cookie，每个Session都有唯一的id，服务器在response header中填入``set-cookie：JSESSIONID=XX``，下次请求在request header中填入``cookie：JSESSIONID=XX``

  Session钝化活化：服务器即使关闭，数据依然存在，

  > 钝化：在服务器正常关闭后，Tomcat会自动将Session数据写入硬盘的文件
  >
  > 活化：再次启动服务器后，从文件中加载数据到Session，并删除之前的临时文件

  Session销毁：默认情况下无操作30分钟自动销毁，

  ~~~java
  自我销毁：Session对象的invalidate()方法
  ~~~

### 验证码

- 展示验证码并点击切换：验证码是Java代码生成的一张图片，代码是写好的，CheckCodeServlet写response输出流返回给jsp图片，提供的文字用outputVerifyImage方法，在CheckCOdeUtil主函数中写图片大小设置

- 校验验证码：验证码图片访问和提交注册表单是两次请求，要将程序生成的验证码存入Session中。在CheckCodeServlet中存入Session给request，

### Filter

  过滤器是JavaWeb三大组件（Servlet、Filter、Listener）之一。可以把资源的请求拦截，在访问Web资源前首先都要进到Filter中，比如进行权限操作

- 快速入门

  1. 定义类，实现Filter接口并重写其所有方法

     ~~~java
     Public class FilterDemo implements Filter{
         public void init(FilterConfig filterConfig)
         public void doFilter(ServletRequest request)
         public void destroy()
     }
     ~~~

  2. 配置Filter拦截资源的路径，而不是访问路径，在类上定义@WebFilter注解,在doFilter中输出
  
     ~~~java
     @WebFilter("/*")
     Public class FilterDemo implements Filter{
     ---
     chain.doFilter(request,response)//chain是doFilter的方法，可以放行request和response
     ~~~
  
- 执行流程

  资源访问完成后还是会回到Filter中，执行放行后的逻辑，放行前对request数据处理，放行后对response数据处理，判断用户是否登录是Session中是否有user对象

- 拦截路径配置和过滤器链

  > 拦截具体的资源：/index.jsp，只有访问这个资源才会被拦截
  >
  > 目录拦截：/user/*
  >
  > 后缀名拦截：/*.jsp
  >
  > 拦截所有：/*

过滤器链是一个Web应用可以配置多个过滤器，过滤器的顺序是按照过滤器的类名的字符串自然排序进行的

### Listener(不常用)

监听就是在Application、Session、Request三个对象创建、销毁或者往其中添加修改删除属性时自动执行代码的功能组件——（类比Js中的监听功能）

| ServletContext监听 | ServletContextListener          | 用于对ServletContext对象进行监听（创建销毁） |
| ------------------ | ------------------------------- | -------------------------------------------- |
|                    | ServletContextAttributeListener | 对ServletContext对象属性的监听（增删改属性） |
| Session监听        | HttpSessionListener             | 对Session对象的整体状态的监听（创建销毁）    |
|                    | HttpSessionAttributeListener    | 对Session对象的监听属性（增删改属性）        |
|                    | HttpSessionBindingListener      | 监听对象于Session的绑定和解除                |
|                    | HttpSessionActivationListener   | 对Session对象的钝化和活化的监听              |
| Request监听        | ServletRequestListener          | 对Request对象进行监听（创建销毁）            |
|                    | ServletRequestAttributeListener | 对Request对象的监听属性（增删改属性）        |

使用：定义类，**实现**第二列的接口，在类上添加@WebListener注解

### Ajax

异步的Js和XMl，Ajax写在HTML中获取数据，用以代替JSP页面

> 异步：可以在不重新加载整个页面的情况下，与服务器进行交换数据并更新部分网页技术，客户端不会等待服务器，可以继续执行其他操作，页面也不会转圈圈，更新部分网页

- 快速入门：

  1. 编写AjaxServlet，并使用response输出字符串

  2. 创建XMLHttpRequest对象，用于和服务器交换数据

     ~~~java
     var xmlhttp
       if(window.XMLHttpRequest){
           xmlhttp=new xmlHttpRequest();
       }else{
           xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");//对IE5、IE6
       }
     ~~~

  3. 向服务器发送请求
  
     ~~~java
     xmlhttp.opemn("GET","url");
     xmlhttp.send()
     ~~~
  
  4. 获取服务器响应数据
  
     ~~~java
     xmlhttp.onreadystatechange=function(){
         if(xmlhttp.readyState==4&&xmlhttp.status==200){
             alert(xmlhttp.responseText);
         }
     }
     ~~~
  
- Axios异步框架，对原生的Ajax框架进行封装，引入axios的js文件，使用axios发送请求并获得响应结果

  ~~~java
  <script src="XX"></script>
  axios({
      method:XX
      url:"XX"
      data://如果是post的话是参数
  }).then(function(resp){
      
  });
  ~~~

- json即Javascript对象表示法，数据的载体，通过异步的请求传输数据，写在<script>标签中，用.访问其属性，

  > 例如{
  >
  > ​	"name":"zhangsan",
  >
  > }
  >
  > 定义：var 变量名={
  >
  > "key1":value1,
  >
  > "key2":value2
  >
  > };
  >
  > value:数字
  >
  > ​        字符串
  >
  > ​		 逻辑值
  >
  > ​		 数组（类似python字典）
  >
  > ​		 对象（在花括号中）
  >
  > ​		 null
  >
  > 获取数据: 变量名.key  

  - Json字符串互转ava对象，用Fastjson库实现转换，导入依赖

    ~~~java
    String jsonStr =JSON.toJSONString(obj);//Java对象转Json
    User user = JSON.parseObject(jsonstr, User.class)//JSON字符串转java对象
    ~~~
  
### Vue

  免除了原生Javascript的DOM书写，基于**MVVM**（Model-view-viewModel（由vue提供））思想，实现数据的双向绑定，将编程的关注点放在数据上……MVC只能实现模型到视图的单向展示

1. ~~~java
   引入vue：<script src="js/vue.js"></script>
   Js区域创建Vue核心对象，数据绑定：
       new Vue({
           el:"#app",//属性el作用范围id为app
           data(){//数据域
               return{
                   username:""//和v-model对应
               }
           }
       });
   编写视图：<div id="app">
       		<input name="username" v-model="username">
   			{{username}}//插值表达式,实现效果：往input输入框内输入信息在旁边就同步出现，因为模型发生变动
       	</div>
   ~~~

2. 常用指令：带有v-前缀的

   ~~~JavaScript
   v-bind:为HTML标签绑定属性值，如设置href：<a v-bind:href="url"></a><!--这个url在Vue核心对象中绑定-->
   								简化格式：<a :href="url"></a>
   v-model：表单元素创建双向数据绑定
           <a v-model="url"></a>
           <!--一旦哪里出现url都会同步变化-->
   v-on:为HTML标签绑定事件<a v-on:click="show()"></a>，
   			简化形式把v-on:换成@
   			绑定Vue核心对象中method：{
               show(){
                   XX
               }
           }
   v-if、v-else、v-else-if、v-show：条件性的渲染某元素，判定为true时渲染，否则不渲染,用return返回
   				<a v-if="count==3"></a>
   v-show：条件展示某元素，区别是切换的是display属性的值<a v-show="count==3"></a>
   v-for:列表渲染，遍历容器元素，<div v-for="addr in addrs">{{addr}}</div>
   			加索引：<div v-for="(addr,i) in addrs">
                   {{i+1}}:{{addr}}
   					</div>
   ~~~

3. 生命周期每次出发一个生命周期事件，会自动执行一个生命周期方法（钩子），不同位置绑定不同生命周期让自动执行

   1. 创建前后
   2. 载入前挂载完成----Vue初始化完成，HTML渲染完成，发送异步请求mounted()，与data()写法一样
   3. 更新前后
   4. 销毁前后
   
   ### ELMENT
   
   基于Vue的网站组件库，用于快速构建网页，先把vue.js引入，再把Element的css和js引入，再创建vue核心对象
   
   - **布局**：包括两种布局方式
     1. layout布局，基础24分栏，迅速简便的创建布局
     2. Container布局，用于布局的容器组件，方便快速搭建页面的基本结构，带有左侧导航栏
   - **组件**：
