# global_names与global_name

>使用场景:  
最近在做项目时，在客户现场遇到这样的问题：在数据库中建立database link后总会自动在db_link name后面自动添上一串ip字符串，上网查询了相关资料定位问题出现在global_name的相关设置上，故有如下学习过程。

## global_names
>参数查询方式:  
>sql>show parameter global_name;  
>参数修改方式:
>alter system set global_names=true;  
>oracle官方文档中对此参数说明:  
>If the value of the GLOBAL_NAMES initialization parameter is TRUE, then the database link must have the same name as the database to which it connects. If the value of GLOBAL_NAMES is FALSE, and if you have changed the global name of the database, then you can specify the global name.  
>从上述说明可以看出：
>+ global_names为true时,本地数据库连接名称必须与远程数据库的global_name相同;
>+ global_names为false时,本地数据库连接名称可以任意指定;

## global_name
>参数查询方式:  
>select * from global_name;  
>参数修改方式:   
>+ alter database rename GLOBAL_NAME to new NEW_NAME;（无法去掉domain）   
>+ update props$ set value$='__orcl__' where name='GLOBAL_DB_NAME'; （可以去掉domain）   
>
>>注：不要直接用update global_name set global_name=”将global_name设置为空，否则数据库不能启动，会报ORA-00600[18061] 或 ORA-00600[18062]这样的错误。 只有用备份进行恢复后才能打开。（参见metalink note 743676.1）
>
>对象类型:  
>![global_name对象类型图片](https://github.com/yzy199391/LearnSummery/blob/master/oracle学习/image/global_name对象类型.png)  
>由上图可以看出，global_name实际上是一个视图，来源于sys.props$表，而这个表是非常重要的一个关于数据库属性的表，不要随意更改此表的内容，否则将引起无可预料的、无法挽回的损失。  
>参数组成:  
>Db_name.Db_domain  
>应用场景中的问题出现就是在设置了domain后，db_link名后自动添加domain值。
>___在用alert database rename global_name时，如果设置了global_name类似于”dmdb.com”这样的名称，则以后更改名称，则没有常规的办法去掉"."分隔符了，但是"."后面的值可以修改，要去掉点分隔符只能用update。___  
>+ 郑重提示：除非万不得已，不要去更新global_name视图，即时更新，也不要去更新global_name的基表props$，更不要将global_name更新为空。
