> spring
#### AOP原理(2-1)
    - AOP的意思是面向切面编程，简单的说就是在方法执行前执行中和执行后可以自定义一些操作。
    - 一般都是基于代理模式实现的，Spring支持两种代理模式，jdk动态代理和cglib代理
    - JDK动态代理利用拦截器(拦截器必须实现InvocationHanlder)加上反射机制生成目标类的代理对象，只能为接口创建代理实例
    - cglib代理利用ASM开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理
    - AOP可以给程序带良好的扩展性和封装性，可以实现业务代码与非业务代码的隔离
    - 比如：可以在不改变目标代码的前提下，实现目标方法的增强，比如做方法执行时间监控，
    记录方法访问日志，再比如：数据库的connection.close()默认是把连接关闭掉，
    但是数据库连接池的场景中，为了不改变用户的使用习惯，一般调用close的时候是把连接重新放回到池中，
    这是因为从数据库连接池中拿到的连接实际上是原生连接的一个代理类，所以内部把close给重写了。
    实际上代理模式的优点实际上也是AOP的优点。
    
#### AOP核心概念术语
    切面（Aspect）：切面就是对横切关注点的抽象
    横切关注点：对哪些方法进行拦截，拦截后怎么处理
    通知（Advice）：增强的内容，Advice:BeforeAdvice，AfterAdvice，ThrowAdvice，DynamicIntroducationAdvice
    连接点（Joinpoint）：被拦截到的方法，字段或构造器
    切入点（Pointcut）：一个通知将被引发的连接点集合，正则表达式，通配符来指定多个，而不是单单一个连接点。
    引入（Introduction）：引入方法或者字段到核心关注点
    目标对象（Target Object）：核心关注点
    AOP代理（AOP Proxy）：增强后的代理对象
    织入（Weaving）：增强后的AOP代理组装到系统
    AOP要做的事情就是：生成代理对象，然后通知织入切入点

    
    
### IOC
     - IOC是控制反转的意思，对Bean的管理交给IOC容器
     - 流程：1.spring启动时先读取Bean的配置信息，在spring容器中生成Bean定义注册表，
         2.然后根据这张注册表定义实例化Bean，装配好Bean之间的依赖关系DI
         3.将Bean实例放入到Spring容器的Bean缓存池中，为应用程序使用Bean提供准备就绪的环境
         
      DI：IOC通过DI进行依赖注入
        - 依赖注入包括set注入和构造器注入
      容器的两种形式
        - BeanFactory是一种低级容器，可以理解为就是一个HashMap，key是beanName，Value是Bean实例，
         最主要的方法是getBean，从容器中获得指定名字的Bean 
        - HierarchicalBeanFactory 父子级联 子容器可以访问父容器中的Bean，父容器不能访问子容器中的Bean spring springmvc
        - SingletonBeanRegistry 运行期间注册单例 Bean。BeanFactory 会缓存 Bean 实例，
        所以第二次使用 getBean() 获取 Bean 时将直接从IOC容器缓存中获得Bean实例
        - ApplicationContext是高级容器，具备更多功能。
            - ClassPathXmlApplicationContext
        - WebApplicationContext专门为Web应用准备的，从WebApplicationContext中可以获得ServletContext的引用，
        以便 Web 应用环境可以访问 Spring 应用上下文
#### Spring Bean 作用域
    - singleton（单例）：IOC容器中无论有多少个Bean引用，始终指向同一个对象，该模式在多线程下是不安全的
    - prototype（原型）:每次通过 Spring 容器获取 prototype 定义的 bean 时，容器都将创建一个新的Bean，每个bean都有自己的属性和状态
        - 对于有状态的Bean使用prototype作用域，对于无状态的bean使用singleton作用域
    - request：同一个http request会返回同一个Bean实例，不同的http请求会产生新的Bean实例
    - session ：同一个session请求会返回同一个Bean实例，不同的session请求会创建不同的实例，不同的Bean之间不共享属性，Bean只在本次session中有效
    - global session：在全局http session中，容器会返回该Bean的同一个实例
    
#### Spring Bean 的生命周期
    - 实例化Bean对象
    - 设置对象的属性
    - setBeanName：调用 BeanNameAware 的 setBeanName(String name) 设置 bean 的 id
    - BeanFactoryAware：调用 BeanFactoryAware 的 setBeanFactory(BeanFactory beanFactory) 设置bean工厂
    - ApplicationContextAware：设置applicationContext应用上下文
    - postProcessBeforeInitialization：初始化前的后置处理方法
    - init-method：初始化方法
    - postProcessAfterInitialization：初始化后的后置处理方法
    - 使用
    - Destroy： DisposableBean接口 destroy() 销毁bean
    - 自定义的destroy方法
    
#### spring依赖注入
    - 1.构造器注入
    - 2.setter方法注入
    - 3.接口注入
    
    @Autowired:可用于构造器、方法和接口注入，默认通过类型自动装配
    @Resource：可用于字段或者属性上，默认按照名称装配，找不到名称才会按照类型装配
    
    #### 自动装配的方式
       1.byName：Spring会自动寻找一个与该属性名称相同或id相同的Bean
       2.byType：Spring会自动寻找一个与该属性类型相同的Bean
       3.constructor：Spring会寻找与该Bean的构造函数各个参数类型相匹配的Bean
       4.autodetect：让容器自己决定，Spring容器会首先尝试构造器注入，然后尝试按类型注入
       默认情况下，Spring是不进行自动装配的


    
# spring mvc的controller默认是单例还是多例(2-1)
    - SpringMVC的controller默认使用了单例，
        在单例的bean中切记声明成员属性是线程不安全的，在并发时候会出现问题。
# SpringMVC请求处理流程(2-1)
    - 1.用户发起请求到前端控制器DispatcherServlet
    - 2.前端控制器请求处理映射器HandlerMapping查找Handler，处理映射器返回给前端控制器Hnadler
    - 3.前端控制器调用处理适配器HandlerAdapter去处理handler，handler执行完给适配器返回ModelAndView，适配器给前端控制器返回ModelAndView
    - 4.前端控制器请求视图解析器viewResolver，视图解析器返回给前端控制器view
    - 5.前端控制器将页面渲染，将model数据填充到request中
    - 6.前端控制器向用户响应结果

# Spring 动态代理(2-2)
    - JDK动态代理利用拦截器(拦截器必须实现InvocationHanlder)加上反射机制生成一个实现代理接口的匿名类
    - cglib代理利用ASM开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理

# 拦截器过滤器区别(2-2)
    - 1.拦截器是基于java反射的，过滤器是基于函数调用的
    - 2.拦截器依赖于web框架，过滤器依赖于servlet容器
    - 3.拦截器只能对action请求起作用，过滤器对几乎所有请求起作用
    - 4.拦截器可以访问action上下文，值栈里的对象，过滤器不可以
    - 5.在action的生命周期里，拦截器可以被多次调用，过滤器只能在容器初始化的时候调用一次
    - 6.拦截器可以获取IOC容器里面的各个bean，在拦截器里面注入servic，可以调用业务逻辑，过滤器不可以
# spring常用注解
       - @Controller:用于标注控制层组件
       - @RestController：相当与@Controller和@responseBody的组合效果
       - @Component：泛指组件
       - @Repository：用于注解DAO层
       - @Service：用于注解业务层组件
       
       - @responseBody：用于将Controller的方法返回的对象，转换成指定格式json，写入到response对象的body数据区
       - @RequestMapping：用来处理请求地址映射的注解
       - @Autowired：对成员变量，方法及构造函数进行自动装配
       - @PathVariable：将请求URL中的变量映射到方法参数中
       - @requestParam：用于从request中获取参数
       - @RequestHeader：把request请求header部分的值绑定到方法参数上
       
       - @ModelAttribute：controller的所有方法在调用前先调用该方法
       - @SessionAttribute：将值放在session作用域中，写在class上面
       - @Valid：实体数据校验
       - @CookieValue：获取cookie中的值


#### Spring如何解决循环依赖（三级缓存）（必考）
    -  Spring循环依赖指的是什么？
        - 简单来讲，就是有一个A对象，创建A的时候发现A对象依赖B，然后去创建B对象的时候，
            又发现B对象依赖C，然后去创建C对象的时候，又发现C对象依赖A。这就是所谓的循环依赖
        - 包括构造器循环依赖和field或者setter循环依赖
    - 三级缓存
        1，第一级缓存：单例缓存池singletonObjects。获取Bean实例
        2，第二级缓存：早期提前暴露的对象缓存earlySingletonObjects。只实例化了，未进行填充属性和初始化的对象
        3，第三级缓存：singletonFactories单例对象工厂缓存
        
        - 流程
           1.spring首先从一级缓存 singletonObjects 中获取Bean，（如何获取到则直接返回）
           2.如果一级缓存没有命中Bean，并且要获取的Bean是正在创建中的Bean，则尝试从二级缓存中获取（如果获取到则直接return）
           3.如果还是获取不到，且允许singletonFactories（allowEarlyReference=true）通过getObject()获取。就从三级缓存singletonFactory.getObject()获取。
        （如果获取到了就从singletonFactories中移除，并且放进earlySingletonObjects。其实也就是从三级缓存移动（是剪切、不是复制哦~）到了二级缓存）
        （如果Bean实例创建好了，就从二级缓存中移出到一级缓存）
    
    - Spring能解决哪种情况的循环依赖？不能解决哪种情况？
        - 能解决field或者setter循环依赖，不能解决构造器循环依赖
        - 因为加入singletonFactories三级缓存的前提是执行了构造器，所以构造器的循环依赖没法解决
    - Spring单例对象的初始化分为三步，实例化-填充属性-初始化
    
#### Spring的后置处理器
    - 在bean初始化的前后会通过BeanPostProcessor进行前后增强操作
    - 可以创建一个类继承BeanPostProcessor，实现postProcessBeforeInstantiation和postProcessAfterInstantiation方法
    
#### Spring的@Transactional如何实现的（必考）
    - Spring的@Transactional，跟Spring AOP一样，都是利用了动态代理
    - Spring在检查到@Transactional注解之后，给这个对象生成了一个代理对象proxy
    - 代理对象的methodB，会先开启事务（beginTransaction），然后再去执行原先对象target的methodB，
    - 最后注入Spring容器的，也正是这个带有事务逻辑的代理对象。所以我们调用methodB时会产生事务。
        如果抛异常，则回滚（rollBack），如果一切顺利，则提交（commit）
    - 一个没有加@Transactional注解的方法，去调用一个加了@Transactional的方法，会不会产生事务？
        - 不会产生事务，因为事务存在于代理对象的方法中

#### Spring的事务传播级别
    - 当事务方法被另一个事务方法调用时,必须指定事务应该如何传播
    - 支持当前事务的情况：
        - PROPAGATION_REQUIRED：当前存在事务，就加入该事务；没有事务，就创建事务
        - PROPAGATION_SUPPORTS：当前存在事务，就加入该事务；没有事务，就以非事务方式运行
        - PROPAGATION_MANDATORY：当前存在事务，就加入事务；没有事务，抛出异常
    - 不支持当前事务的情况
        - PROPAGATION_REQUIRES_NEW: 创建一个新的事务,如果当前存在事务,则把当前事务挂起
        - PROPAGATION_NOT_SUPPORTED: 以非事务方式运行,如果当前存在事务,则把当前事务挂起
        - PROPAGATION_NEVER: 以非事务方式运行,如果当前存在事务,则抛出异常
    - 其他情况
        - PROPAGATION_NESTED: 如果当前存在事务,则创建一个事务作为当前事务的嵌套事务来运行
            当前没有事务,则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED
            
    - TransactionDefinition 
        - ISOLATION_READ_UNCOMMITTED 读未提交 select 脏读、幻读或不可重复读
        - ISOLATION_READ_COMMITTED   读已提交 update 幻读或不可重复读
        - ISOLATION_REPEATABLE_READ  可重复读 insert 幻读
        - ISOLATION_SERIALIZABLE     串行化
#### BeanFactory和ApplicationContext的联系和区别
    - BeanFactory：Spring里面最低层的接口，只提供了实例化对象和拿对象的功能
    - ApplicationContext：应用上下文，继承了BeanFactory接口，高级容器；功能：国际化，访问资源，消息发送，AOP
    - BeanFactory默认延迟实例化，启动占用资源少；ApplicationContext默认实例化全部Bean，系统运行速度快





> Mybatis

#### Mybatis 两级缓存缓存
    Mybatis 中有一级缓存和二级缓存，默认情况下一级缓存是开启的，而且是不能关闭的。
    一级缓存是指 SqlSession 级别的缓存，当在同一个 SqlSession 中进行相同的 SQL 语句查询时，第二次以
    后的查询不会从数据库查询，而是直接从缓存中获取，一级缓存最多缓存 1024 条 SQL。
    二级缓存是指可以跨 SqlSession 的缓存。 是 mapper 级别的缓存，对于 mapper 级别的缓存不同的
    sqlsession 是可以共享的

    ##### Mybatis 的一级缓存原理
        - 第一次发出一个查询 sql， sql 查询结果写入 sqlsession 的一级缓存中，缓存使用的数据结构是一个Map
        key： MapperID+offset+limit+Sql+所有的入参
        value：用户信息
        - 同一个 sqlsession 再次发出相同的 sql，就从缓存中取出数据
        - 如果两次中间出现 commit 操作（修改、添加、删除），本 sqlsession 中的一级缓存区域全部清空，
            下次再去缓存中查询不到所以要从数据库查询， 从数据库查询到再写入缓存


    ##### 二级缓存原理
        - 二级缓存的范围是 mapper 级别，mapper 以命名空间为单位创建缓存数据结构，结构是 map。
          mybatis 的二级缓存是通过 CacheExecutor（缓存池） 实现的，所有的查询操作，在 CacheExecutor 中都会先匹配缓存中是否存在
        key： MapperID+offset+limit+Sql+所有的入参




# Servlet生命周期
    - Servlet 通过调用 init () 方法进行初始化
    - Servlet 调用 service() 方法来处理客户端的请求
    - Servlet 通过调用 destroy() 方法终止（结束）
    
# MyBatis中#{}和${}的区别
    1.#字符串：#将传入的数据都当成一个字符串，会对自动传入的数据加一个双引号。如：order by #user_id#，如果传入的值是111,那么解析成sql时的值为order by "111", 如果传入的值是id，则解析成的sql为order by "id".
    2.$直接显示：$将传入的数据直接显示生成在sql中。如：orderbyuser_id$，如果传入的值是111,那么解析成sql时的值为order by user_id, 如果传入的值是id，则解析成的sql为order by id.
    3.#方式能够很大程度防止sql注入;$方式无法防止Sql注入。
    5.$方式一般用于传入数据库对象，例如传入表名.
    6.一般能用#的就别用$.
    MyBatis排序时使用order by 动态参数时需要注意，用$而不是#


> SpringBoot

#### SpringBoot-自动装配原理    
    - 1.SpringBoot的主配置类上有@SpringBootApplication注解
    - 2.@SpringBootApplication是一个组合注解，有@EnableAutoConfiguration开启自动配置的功能
    - 3.@Import注解就是给Spring容器中导入组件的选择器:AutoConfigurationImportSelector
    - 4.AutoConfigurationImportSelector类的方法，
        调用了loadFactoryNames方法会从META-INF/spring.factories中获取指定的值，
        将这些值作为自动配置类导入到容器中，自动配置类就生效
  
