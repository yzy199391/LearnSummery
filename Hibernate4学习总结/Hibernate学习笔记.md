# Hibernate学习记录
## Hibernate的概述与优势 ##
>+ 通过ORM(Object-Relational Mapping)映射，以Bean的形式操作数据库中记录  
>+ 一二级缓存机制结合连接池POOL是CURD操作效率大大得到提升
>+ 对sql的可维护性很好，用xml文件对系统中大量的SQL语句进行统一管理
>+ 减少大量重复的JDBC代码
>+ 面向对象的思考方式
>+ 更好的性能和移植性  
__总结__：以一个Bean来操作ResultSet并且带有很多附属功能的软件框架。

hebernate官方网站：[http://www.hibernate.org](http://www.hibernate.org)

## 持久层、持久化与ORM
>+ 持久化：将数据保存在可掉电的存储介质上
>+ 持久层：为整个项目提供一个衔接高低层、统一、安全和并发的持久机制，完成对各种数据库进行持久化的编程工作
>+ ORM：是数据持久层的一种子实现，通过映射机制，将数据库中的一条记录当成java的一个class（Bean）处理，在CURD处理上，真正实现了面向对象开发，也将软件的后期维护周期大大缩短

__例1__ :  数据库记录与javaBean映射方式
+ 数据库表字段如下：  
id name address school email
+ 通过Hebernate的ORM就可以将这个表中字段映射到一个java Bean中：  
![将数据库记录映射为javaBean]()  
__Ps__：实体类要实现java.io.Serializeable，且需要有一个参数为空的构造方法（java bean特性）

## 主要ORM结构 ##
>+ HibernateSessionFactory.java文件:用于创建Hibernate中的session对象，此session相当于JDBC中的statement对象，具有基本的CURD功能；Hibernate中使用session的目的就是保存数据、查询数据。
>+ IBaseHibernateDAO.java文件:该文件中只有主要代码：  
>`public interface IBaseHibernateDAO{`  
>`public Session getSession();`  
>`}`
>+ BaseHibernateDAO.java文件:实现上述接口
>+ 实体类文件(xxx.java)：将数据库中表结构用java类形式来表示，Hibernate通过操作这个类来间接地实现操作数据库表中的记录集。
>+ 实体类DAO文件(xxxDAO.java)：这是一个操作数据库的DAO数据访问对象类，功能即CURD操作的是上述实体类。
>+ 映射文件(xxx.hbm.xml):将O对象和R关系进行对照。通过这个配置文件可以将数据表中的字段与java中的属性进行有机的结合。
>主要结构如下(省略</>)：  
>`<hibernate-mapping>`  
>`<class name="" table="" schema="">`//name-当前映射的实体类；table-数据库中映射的表；schema-当前表所在的数据库  
>`<id name="" type="">`//主键映射关系及数据类型  
>`<column name="" precision="" scale="">`//表中主键字段名称  
>`<generator class="">`//主键生成策略  
>`<property name="" type="" >`//属性映射  
>`<column name="" length="">`  
>__Ps__主键生成策略如下：
>+ identity:适合主键可以自增型的数据库，比如mysql
>+ assigned:由程序负责生成
>+ sequence:适合支持序列对象的数据库，比如oracle
>+ increment:由Hibernate取得主键可以自增的主键值+1进行生成主键

## Session的操作解释
>+ Session是java和hibernate之间的主要运行时接口。它抽象了持久化服务概念的核心抽象API类。
>+ Session生命周期绑定在一个事物(Transaction)上。
>+ Session的主要功能是提供对映射的实体类实例的创建，读取和删除操作。实例可能有一下三种状态：  
>自由状态(transient)：不曾进行持久化，未与任何session相关联  
>持久化状态(persistent):仅与一个session相关联  
>游离状态(detached)：已经进行过持久化，但当前未与任何Session相关联
    
