# 总体认知 #

## IOC和AOP介绍 ##
>+ Spring框架是实现了IOC和AOP的容器
>+ Spring推崇模块化设计，其每一个模块都可以单独使用或与其他模块联合使用
>### 主要模块 ###
>1. Core：bean管理及context核心上下文创建与维护。
>2. Web：提供spring MVC的一个框架模块
>3. IOC/DI（控制反转/依赖注入）：为管理Bean创建的一个Bean的内存区，将接口与实现分离
>4. AOP：面向一小段代码编程，在不改变原始代码段的基础上实现功能性增强，例如：在不改变servlet代码的基础上加入日志的功能；在不改变Structs的action代码的基础上加入数据库事物的功能。

[例1：IOC实现接口与实现类分离:Spring_IOC_test](https://github.com/yzy199391/LearnSummery/tree/master/Spring%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93/Spring_IOC_test)  
解析：
1. 通过maven管理添加引入spring所需依赖，使用myeclipse自动添加spring框架结构；
2. 使用新建applicationContext.xml配置文件添加bean实现接口与实现类的解耦；




[例2：业务变更为将数据保存进xml文件中:Spring_IOC_test]()  
解析：
1. 在练习1的基础上新增加一个将数据保存进xml的实现类；
2. 在配置文件中声明这个实现类；
3. 将对象实例注入进Run类的属性中,实现Bean的重用。

__Ps__：
>ApplicationContext接口的类层次结构：
>![]()


## IOC ##
> ### BeanFactory和ApplicationContext  ###
> + Spring的IOC容器为BeanFactory接口的一个实现类，通过工厂模式来取得对应的BEAN对象引用。
> + ApplicationContext是BeanFactory的子接口，相较于BeanFactory的基本对象管理功能新增了更多附加功能，如：与web整合、支持国际化、事件发布和通知等功能。
> + 类创建时间上：BeanFactory延迟取得，在getBean时才创建指定类的对象ApplicationContext在自身容器初始化时就创建全部类对象。   
>### ApplicationContext常用实现类  
> + FileSystemXmlApplicationContext
> + ClassPathXmlApplicationContext
> + XmlWebApplicationContext

__Ps__：

>### 工厂模式：
>+ 轻松方便地构造对象实例而无需关注构造对象实例的细节和复杂过程。
>+ 通过建立一个工厂来创建对象，为创建对象提供过渡接口，将创建对象的具体过程屏蔽隔离起来。
>### 逐级抽象：
>+ 简单工厂模式（Simple Factory）建立一个工厂类方法来制造新的对象   
>+ 工厂方法模式（Factory Method）定义一个用于创建对象的接口。（子工厂出现）   
>+ 抽象工厂模式（Abstract Factory）工厂方法模式的升级版本，用来创建一组相关或者相互依赖的对象。（多个接口）   

## Spring的IOC容器的注入类型
+ __基本数据类型__

    [例3：通过IOC容器注入常用数据类型（dataType_test.test/run.test.runit）]()

    解析：
    1. 注入数据类型为int、short、long、char、double、boolean   
    2. 通过配置文件制定初始化方法

+ __引用数据类型__

    在例3基础上进行
        使用ref属性

   > `<property name = "string_var" value = "yzy"></property>`

+ __null类型__

    [例4：通过IOC容器注入null类型（dataType_test.testNull/run.test.runit_null）]()

    解析：
    1. null值的注入方式：property标签中只有name属性，注入的null值由<null/>标签提供

        >`<property  name = "null_string">`  
        `<null/>`  
        `</property>`

+ __Properties类型__

    [例5：通过IOC容器注入Properties类型（dataType_test.testProperties/run.test.runit_properties）]()

    解析：
    1. properties注入方式：property标签中只有name属性，注入的properties值由<props/>标签提供

        >`<property name = "properties">`  
	        `<props>`  
		        `<prop key="1">11</prop>`  
		        `<prop key="2">22</prop>`  
		        `<prop key="3">33</prop>`  
	        `</props>`  
        `</property>`  

+ __构造方法注入__

    [例6：通过IOC容器注入构造方法（）]()  
    解析：
    1. 通过标<constructor-arg/>标签指定构造方法形参类型和值
        >`<bean id = "test_ref" class = "init_test.test">`  
	    `<constructor-arg type = "java.lang.String" value = "hello"></constructor-arg>`  
		`<constructor-arg type = "java.lang.String" value = "world"></constructor-arg>`  
	    `</bean>`

## Bean的作用域 ##
+ __Singleton__  
>在IOC容器中只有一个Bean的实现   
使用方法  
`<bean id = "my_date" class = "java.util.Date" scope = "singleton"/>`

+ __Prototype__  
>当getBean()方法取得一个Bean时，IOC容器新建一个指定Bean的实例并返回给程序员  
使用方法  
`<bean id = "my_date" class = "java.util.Date" scope = "prototype"/>`

+ __Request__
+ __Session__
+ __GlobalSession__

[例7：测试Bean的作用域（Runit_ScopeTest/date）]()  
解析：  
通过注入bean设置不同scope，在主方法中间隔3秒打印一次时间（创建bean的时间）显示结果如下：
Singleton：  
![]()
Prototype：  
![]()

## Spring中注入外部属性文件的属性值 ##
>Spring提供了PropertyPlaceholderConfigure类来操作属性文件。
>+ 配置方法：  
>>`<bean id = "property_configer" class = "org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer">`  
>		`<property name = "locations">`  
>			`<list>`  
>				`<value>db_connection_info.properties</value>`  
>			`</list>`  
>		`</property>`  
>	`</bean>`  
>+ 取用方法：  
>>`<bean id = "show_db_info" class = "run.runit_propertiesBean">`  
		`<property name = "username" value = "${test.username}"/>`  
		`<property name = "password" value = "${test.password}"/>`  
	`</bean>`  

[例8：使用PropertyPlaceholderConfigure注入外部属性文件的属性值（RunPropertyTest/PropertyTest）]()  
解析：  
1. 通过Spring中类PropertyPlaceholderConfigure将.properties文件从classpath路径中加载进内存，通过EL表达式${}的形式指定文件中的key来将对应的value注入runit_propertiesBean的属性中。

## Spring使用多个applicationcontext.xml配置文件　##
>在将不同类型的注入分解到不同功能的配置文件的应用场景中，需要使用到多个spring配置文件。  
>+ 配置方法：  
> `<import resource="other.xml">`

[例9：在同一个项目中使用两个ApplicationContext配置文件（RunMultiConf/my_interface）]()  
解析：  
1. 通过使用import标签，在主配置文件中引用辅助配置文件other.xml中的配置。

## AOP ##

>相当于一个代理，若想对某一个类实现功能性增强，那么只需要将这个类让代理类处理。增强什么，在哪方面增强由代理类决定。

### __静态代理的实现__ ###

>在静态代理中，代理对象与被代理对象必须实现同一个接口，完整保留被代理对象的接口样式，并一直保持接口不变的原则。  
>>步骤：
>>1. 声明一个与被代理实现类实现相同接口的代理类；
>>2. 在代理类中声明要代理的接口；
>>3. 代理类中创建参数为被代理接口的构造方法；
>>4. 重写实现类中需要增强的方法；
>>5. 调用时，在被代理接口实现类外包装一层代理类，则此时调用的方法就为增强后方法。

[例10：静态代理的实现实例，通过使用代理类将原有类进行包装实现功能增强（StaticProxyInterface /StaticProxyImpl /RunStaticProxy）]()  
解析：  
>接口--StaticProxyInterface  实现类--StaticProxyImpl  代理类--RunStaticProxy

>在代理类中声明接口，并添加一个传入参数为接口的构造函数，从而实现接口与代理类的绑定；在代理类中同时对实现类中的方法进行重写，其内容为对原有实现类的功能增强。
在实际使用中，用代理类将实现类进行包装实现静态代理完成功能增强  
`StaticProxyInterface runit = new RunStaticProxy(new StaticProxyImpl());`

### __动态代理的实现__ ###

>动态代理可以实现对任何接口的实现类进行增强。在java中动态代理类由InvocationHandler来实现。
>>步骤：
>>1. 创建动态代理类，实现java.lang.reflect.InvocationHandler接口；
>>2. 声明一个Object对象，用于接收需要代理的对象；
>>3. 声明bind()方法，传入被代理接口的实现类，使用Proxy.newProxyInstance方法实现
>>动态代理，此方法参数分别为classloader、interface、invocationHandler；
>>4. 重写invoke方法实现被代理方法的增强；
>>5. 调用时，先声明一个代理类，再在声明被代理接口时调用代理类中的bind()方法将被代理的实现类传入，从而将被代理对象与代理类对象进行绑定；从而实现动态代理的实现类功能增强。

[例11：动态代理的实现 （my_interface/my_interface_imple/RunDynamicProxy）]()  
解析：  
>1. 动态代理实现代码：   
>`Proxy.newProxyInstance(any_object.getClass().getClassLoader(),any_object.getClass().getInterface(),this);`
>将传递进来的my_object对象的类与接口进行动态关联
>2. Bind()绑定方法来将一个实现类与一个动态代理进行绑定
>3. 调用被代理类my_interface_imple的实例方法时，系统将转到包含代理类(RunDynamicProxy)的InvocationHandler的invoke()方法中执行相应的代码。

__Ps__：
>Spring只支持方法的advice增强，不支持字段级的增强；
静态代理是面向接口的开发方式，而动态代理是面向实现类的开发方式。
动态代理的设计出发点不是直接操作接口文件，而是动态地取得接口，软件的灵活性大大增强。

### 方法执行前增强（实现MethodBeforeAdvice接口） ###

[例12：通过实现接口MethodBeforeAdvice来实现方法执行前增强（my_interface/my_interface_imple/RunBeforeAdvice）]()

解析：  
>增强方法实现MethodBeforeAdvice接口，重写before方法，此方法中实现在advice前对其进行增强。
>>Spring配置：  
>><bean id = "my_proxy" class = "org.springframework.aop.framework.ProxyFactoryBean">
>>        `<property name = "proxyInterfaces">`
>>            `<value>test.interfacepackage.my_interface</value>`
>>        `</property>`
>>        `<property name="target" ref = "yzy_bean1"></property>`
>>        `<property name = "interceptorNames">`
>>            `<list>`
>>                `<value>my_beforemethod_advice</value>`
>>            `</list>`
>>        `</property>`
>>`</bean>`
>>>其中：
>>> + proxyInterfaces属性指定了要代理接口的名称
>>> + target属性指定了接口实现类的对象
>>> + interceptorNames属性指定了advice类对象的名称

### 方法执行后增强（实现AfterReturningAdvice接口） ###

[例13：通过实现接口AfterReturningAdvice来实现方法执行前增强（my_interface/my_interface_imple/AfterReturningAdvice）]()

>大致流程与方法执行前增强相同，区别仅在于AfterReturningAdvice的实现和afterReturning方法的重写。

### 方法执行前后环绕增强（实现MethodInterceptor接口） ###
>大致流程与方法执行前增强相同，区别仅在于MethodInterceptor的实现和invoke方法的重写。

















