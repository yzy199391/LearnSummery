# oracle impdp/expdp 使用详解

今天在项目中遇到了如下情况：备份恢复一张数据量比较大（大约200w条记录，约119M大小）的表时，使用惯用的exp/imp工具进行操作时，工具直接卡死（或是操作时间过长）。于是想起之前使用过的impdp/expdp数据泵工具，其优点在于大数据量导入导出时高效快捷。使用数据泵后，漫长的大表导出过程变得快速高效，想到自己好久没有进行技术总结，故在此将impdp/expdp的使用总结一下，以便之后查阅。

##相关参数
+ DIRECTORY
>创建语句:  
create or replace directory dmp as 'd:/dmp'  
其中dmp为directory名称，as后接directory路径，需要在指定路径下自行建立指定文件夹。  
建立directory所需权限：create any directory

+ CONTENT
>该选项用于指定要导出的内容.默认值为ALL  
CONTENT={ALL | DATA_ONLY | METADATA_ONLY}  
当设置CONTENT为ALL时,将导出对象定义及其所有数据.为DATA_ONLY时,只导出对象数据,为METADATA_ONLY时,只导出对象定义

+ DUMPFILE
>用于指定转储文件的名称,默认名称为expdat.dmp  
DUMPFILE=[directory_object:]file_name [,….]  
Directory_object用于指定目录对象名,file_name用于指定转储文件名.需要注意,如果不指定directory_object,导出工具会自动使用DIRECTORY选项指定的目录对象

## 导出步骤
> 1. 创建DIRECTORY  
>>create directory dir_dp as 'D:/Oracle/dir_dp';
>   
> 2. 授权  
>>Grant read,write on directory dir_name to user_name;
>
> _查看目录及权限_
>>SELECT privilege, directory_name, DIRECTORY_PATH FROM user_tab_privs t, all_directories d
 WHERE t.table_name(+) = d.directory_name ORDER BY 2, 1;
>
> 3. 执行导出
>>expdp zftang/zftang@fgisdb schemas=zftang directory=dir_dp dumpfile =expdp_test1.dmp logfile=expdp_test1.log;
>
> ___备注：___  
> + directory=dir_dp必须放在前面，如果将其放置最后，会提示 ORA-39002: 操作无效  
>ORA-39070: 无法打开日志文件。  
>ORA-39087: 目录名 DATA_PUMP_DIR无效
> + 在导出过程中，DATA DUMP 创建并使用了一个名为SYS_EXPORT_SCHEMA_01的对象，此对象就是DATA DUMP导出过程中所用的JOB名字，如果在执行这个命令时如果没有指定导出的JOB名字那么就会产生一个默认的JOB名字，如果在导出过程中指定JOB名字就为以指定
>名字出现
>如下改成：
>>expdp zftang/zftang@fgisdb schemas=schema_name directory=dir_name dumpfile =dmp_name.dmp
>>logfile=log_name.log,job_name=myjob_name;
>
> + 导出语句后面不要有分号，否则如上的导出语句中的job表名为‘my_job1;’，而不是my_job1。因此导致expdp zftang/zftang attach=zftang.my_job1执行该命令时一直提示找不到job表

## 数据泵导出模式
1. 按表模式导出：  
>expdp zftang/zftang@fgisdb  tables=zftang.b$i_exch_info,zftang.b$i_manhole_info dumpfile =expdp_test2.dmp logfile=expdp_test2.log directory=dir_dp job_name=my_job

2. 按查询条件导出：
>expdp zftang/zftang@fgisdb  tables=zftang.b$i_exch_info dumpfile =expdp_test3.dmp logfile=expdp_test3.log directory=dir_dp job_name=my_job query='"where rownum<11"'

3. 按表空间导出：  
>Expdp zftang/zftang@fgisdb dumpfile=expdp_tablespace.dmp tablespaces=GCOMM.DBF logfile=expdp_tablespace.log directory=dir_dp job_name=my_job

4. 导出方案：  
>Expdp zftang/zftang DIRECTORY=dir_dp DUMPFILE=schema.dmp SCHEMAS=zftang,gwm

5. 导出整个数据库：
>expdp zftang/zftang@fgisdb dumpfile =full.dmp full=y logfile=full.log directory=dir_dp job_name=my_job

## 数据泵导入模式

1. 按表导入：
>p_street_area.dmp文件中的表，此文件是以gwm用户按schemas=gwm导出的：  
>>impdp gwm/gwm@fgisdb  dumpfile =p_street_area.dmp logfile=imp_p_street_area.log directory=dir_dp tables=p_street_area job_name=my_job

2. 按用户导入（可以将用户信息直接导入，即如果用户信息不存在的情况下也可以直接导入）:
>impdp gwm/gwm@fgisdb schemas=gwm dumpfile =expdp_test.dmp logfile=expdp_test.log directory=dir_dp job_name=my_job

3. 不通过expdp的步骤生成dmp文件而直接导入的方法：
>--从源数据库中向目标数据库导入表p_street_area
>>impdp gwm/gwm directory=dir_dp NETWORK_LINK=igisdb tables=p_street_area logfile=p_street_area.log  job_name=my_job
>
>igisdb是目的数据库与源数据的链接名，dir_dp是目的数据库上的目录

4. 更换表空间
>采用remap_tablespace参数  
>--导出gwm用户下的所有数据
>>expdp system/orcl directory=data_pump_dir dumpfile=gwm.dmp SCHEMAS=gwm  
>
> ___注:___  
>>如果是用sys用户导出的用户数据，包括用户创建、授权部分，用自身用户导出则不含这些内容
>
>--以下是将gwm用户下的数据全部导入到表空间gcomm（原来为gmapdata表空间下）下:
>>impdp system/orcl directory=data_pump_dir dumpfile=gwm.dmp remap_tablespace=gmapdata:gcomm






