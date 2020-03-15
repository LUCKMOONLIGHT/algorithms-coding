> JVM

## JVM的运行过程
    1.JAVA源文件-编译器->字节码文件
    2.字节码文件-JVM->机器码
    ### java为什么能够跨平台使用
        1.每一个平台的解释器是不同的，但是实现的虚拟机时一样的
## JAVA线程与操作系统的线程
    1.两者有直接映射关系。当java线程的本地存储，缓冲区分配，堆栈，程序计数器准备好，
    就会创建一个操作系统原生线程；java线程结束，原生线程随之被回收。
    操作系统负责调度所有的线程，并把他们分配到任何可用的CPU上。
    原生线程初始化完毕，就会调用java线程的run方法
    当线程结束时，会释放原生线程和java线程的所有资源

## Hotspot JVM后台运行的线程主要有
    1.虚拟机线程 VM thread
    2.周期性任务线程
    3.GC线程
    4.编译器线程
    5.信号分发器线程

## JVM内存区域
    - 分为线程私有、线程共享和直接内存
    - 线程私有：生命周期与线程相同，依赖用户线程的启动结束而创建销毁
        - 虚拟机栈：执行java方法，虚拟机栈和线程的生命周期相同，一个线程中，每调用一个方法
        创建一个栈帧，记录局部变量，操作数栈，动态连接和方法出口等
        - 本地方法栈：存放着虚拟机使用到的Native方法服务
        - 程序计数器：指向虚拟机字节码指令的位置,唯一一个没有OOM的地方
     - 线程共享：随虚拟机的启动\关闭而创建销毁
         - 方法区： 常量池，静态变量
         - 堆：    保存对象实例，垃圾收集器进行垃圾收集的主要区域
     - 直接内存：元数据取代永久代，保存在直接内存里
## JVM运行时内存
    - Java堆从GC的角度可以细分为，新生代1/3（Eden8/10，from1/10，to survivor1/10）和老年代2/3
    - 新生代：用来存放新生的对象，由于频繁创建，所以新生代会频繁触发MinorGC，
    默认年龄15的对象会被移到老年代，新生代包含Eden，servivor From， to三个区
        - Eden区：java新对象的出生地（如果新创建的对象占用内存很大，直接分配到老年代），内存不够进行MinorGC，对新生代进行一次垃圾回收
        - ServivorFrom：上一次GC的幸存者
        - ServivorTo：上一次GC的幸存者
          - MinorGC采用复制算法：（复制-清空-互换）把eden和from中存活的对象年龄加1保存到to中，同时把年龄+1，清空eden和from，将from和to互换
        - 老年代：存放生命周期长的对象，不会频繁GC。内存不足MajorGC
          - MajorGC：采用标记清除算法，扫描一次老年代，标记存活的对象，回收没有标记的对象。耗时长会产生内存碎片
          - Full GC 是清理整个堆空间—包括年轻代和老年代
        - JAVA8元空间：永久代已经被元空间取代，保存到java本地内存中，不受MaxPermSize控制，由系统实际可用空间控制；字符串常量池放入java堆中

#### Minor GC和Full GC触发条件
    从年轻代空间（包括 Eden 和 Survivor 区域）回收内存被称为 Minor GC；
    对老年代GC称为Major GC；
    而Full GC是对整个堆来说的；
     - Minor GC触发条件：
    当Eden区满时，触发Minor GC。
     - Full GC触发条件：
    （1）System.gc()方法的调用
    （2）老年代空间不足
    （3）方法区空间不足
# GC中Stop the world（STW）
    安全点：
        - 循环的末尾
        - 方法临返回前 / 调用方法的call指令后
        - 可能抛异常的位置
    在一些特定指令位置设置一些“安全点”，
    当程序运行到这些“安全点”的时候就会暂停所有当前运行的线程（Stop The World 
    所以叫STW），暂停后再找到“GC Roots”进行关系的组建，进而执行标记和清除
  
#### 如果一个固定的堆内存，当创建线程数很多的时候，你JVM参数怎么配置
    - 在java中每new一个线程，jvm都是向操作系统请求new一个本地线程，
       此时操作系统会使用剩余的内存空间来为线程分配内存，而不是使用jvm的内存,
       当操作系统的可用内存越少，则jvm可用创建的新线程也就越少
       减小最大堆内存 -Xmx
       减小单个线程的栈大小 -Xss
#### Xmx Xms 相等       
     - 设置-Xms、-Xmx 相等以避免在每次GC 后调整堆的大小  
## GC垃圾回收
    ### 如何确定是垃圾
        - 1.引用计数法：java中，引用和对象是有关联的。如果一个对象没有与之关联的引用，
        即引用计数为0，则该对象是可回收对象，但是会产生两个对象之间循环引用的问题，会产生内存泄漏
        - 2.可达性分析：为了解决引用计数的循环引用问题，通过GC ROOT作为起点搜索，
        如果对象和gc root之间没有可达路径，则称该对象是不可达的；不可达对象经过两次标记后是可回收对象，则面临回收
            - GC ROOT对象：1.JVM栈中的引用  2.方法区中的静态属性引用  3.方法区中的常量引用  4.本地方法栈JNI引用

    ### 垃圾收集算法
         - 标记清除：标记需要清除的对象，清除标记对象的内存，耗时长，内存碎片化严重
         - 复制算法：每次只使用一块内存，把存活的对象复制到另一块内存中去，把已经使用的内存清除掉，不易产生碎片，内存被压缩成原来的一半
         - 标记整理算法：标记后将存活对象向内存一端移动，清除端边界外的对象
         - 分代收集算法：目前大部分JVM采用的，根据对象存活的不同生命周期，将内存划分为老年代和新生代，
             新生代每次都有大量垃圾要回收，复制算法；老年代每次只有少量垃圾要回收，标记整理算法
    ### 垃圾收集器
         - Serial垃圾收集器(单线程复制算法)
         - ParNew垃圾收集器(Serial+多线程)
         - Parallel Scavenge收集器(高效多线程复制算法)
         - Serial Old 收集器（单线程标记整理算法）
         - Parallel Old收集器（多线程标记整理算法）
         - CMS：采用多线程标记清除算法
		     - Concurrent mark sweep 是一种老年代垃圾收集器，主要目的是获取最短垃圾回收停顿时间，
		     和标记整理算法不同，使用多线程的标记清除算法，最短的垃圾收集停顿时间，提供用户体验
			     - 初始标记：暂停所有工作线程：标记GC ROOT能直接关联的对象，速度很快
				 - 并发标记：不需要暂停工作线程：进行GC Root跟踪过程，和用户线程一起工作
				 - 重新标记：暂停所有工作线程：修正因程序继续运行导致标记变动的部分对象记录
				 - 并发清除：不需要暂停工作线程：清除GC Root不可达对象，和用户线程一起工作
		 - G1：Garbage first避免全区域垃圾收集，把堆内存划分为大小固定的几个独立区域，
		     同时在后台维护一个优先级列表，每次根据所允许的收集时间，优先回收垃圾最多的区域。
		     区域划分和优先级区域回收机制，确保G1垃圾收集器可以在有限的时间获得最高的垃圾回收效率
             1.基于标记整理算法，不产生内存碎片
			 2.可以非常精确的控制停顿时间，在不影响吞吐量的前提下，实现低停顿垃圾回收
			 3.G1不分新生代老年代

## 四种引用类型
    - 把对象的引用分为4种级别，更加灵活的控制对象的生命周期
    - 强引用：把一个对象赋给一个引用变量，不可以被垃圾收集器回收，当内存不足的时候，会抛出OOM，程序终止
    - 软引用：需要用softRefrence类实现，如果内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，
    就会回收这些对象的内存，常用在内存敏感的程序中
        - 场景：实现内存敏感的高速缓存；1.例如浏览器的后退按钮，将浏览过的网页存储到存入软引用，
        取数据时就可从内存里取数据，提高运行效率，内存不足的是时候会自动清除软引用，避免造成内存溢出
        - 2.假设我们的应用会用到大量的默认图片，比如应用中有默认的头像，默认游戏图标等等，这些图片很多地方会用到。
        如果每次都去读取图片，由于读取文件需要硬件操作，速度较慢，会导致性能较低。所以我们考虑将图片缓存起来，需要的时候直接从内存中读取。但是，由于图片占用内存空间比较大，缓存很多图片需要很多的内存，就可能比较容易发生OutOfMemory异常。这时，我们可以考虑使用软引用技术来避免这个问题发生
    - 弱引用：需要用WeakReference类实现，它比软引用的生命周期更短，只要垃圾回收机制一运行，
         不管当前内存空间是否足够，都会回收它的内存
        - 场景：等同于软引用，生命周期更短，不影响垃圾回收
    - 虚引用：phantomReference类实现，不能单独使用，必须和引用队列（ReferenceQueue）联合使用。任何时候都可以被垃圾回收。
        - 场景：主要用来跟踪对象被垃圾回收的状态，收到一个系统通知。2.监听gc的频率
        

## JAVA IO\NIO
    - 程序读取数据分为两个步骤
        - 1.数据拷贝到内核
        - 2.内核数据拷贝到用户线程
    BIO：阻塞IO，面向流的。用户线程发起read操作，没有数据准备就一直阻塞状态。用户线程发起IO请求，内核会去查看数据是否就绪，如果没有就绪，用户线程就会阻塞，交出CPU。
        当数据就绪之后，内核将数据拷贝到用户线程，用户线程才解除block，一个线程管理一个连接
    NIO：非阻塞IO，面向缓冲的。用户线程发起read操作，并不需要等待，不会阻塞，不交出CPU。
        用户线程需要不断地询问内核数据是否就绪，只有当连接真正有读写事件时，才会调用函数来进行读写，
        大大减少了系统开销，不必为每一个连接都创建一个线程，避免多线程之间上下文切换开销
        一个线程管理多个连接
        selector用于监听多个channel的事件，当有读写事件时，数据从channel读取到Buffer中
    多路复用IO：NIO就是多路复用IO，会有一个线程不断去轮询多个socket状态，只有当socket真正有读写事件时，
        才真正调用实际的IO进行读写。大大减少了资源的占用。比JAVA NIO效率高
    java NIO与多路复用IO的区别：NIO不断轮询socket的状态是通过用户线程去进行的，
        多路复用IO中，轮询每个socket状态是在内核中进行，这个效率比用户线程高得多
    异步IO：用户线程发起read请求后，立即就可以去做其他的事情了，不会block。
        然后内核会等待数据准备完成，将数据拷贝到用户线程，当一切都完成后，
        内核会给用户线程一个信号，告诉他read操作完成。用户线程收到成功信号，直接去使用数据。

## JVM类加载机制：代码编译后，通过内存加载class文件，并进行验证、准备、解析、初始化为JVM直接使用的JAVA类型
    - 加载：加载class文件(jar\war包，动态代理)，在内存中生成一个class对象，作为方法区这个类各种数据的访问入口
    - 验证：验证被加载后的类是否有正确的结构，类数据是否会符合虚拟机的要求
    - 准备：为类的静态变量（static filed）在方法区分配内存，并赋默认初值（0值或null值）
        - 一般的成员变量是在类实例化时候，随对象一起分配在堆内存中
        - 静态常量会在准备阶段赋程序设定的初值666
        - 静态变量会在初始化阶段赋程序设定的初值
    - 解析：虚拟机将常量池中的符号引用替换为直接引用
    - 初始化：执行类构造器<client>方法，为静态变量赋程序设定的初值
    
    ### 类加载器
        JVM提供了3种类加载器：启动类加载器bootstrap ClassLoader、扩展类加载器Extension ClassLoader、应用类加载器Application ClassLoader
        - 启动类加载器bootstrap ClassLoader：负责加载JAVA_HOME/lib目录的类
        - 扩展类加载器Extension ClassLoader：加载JAVA_HOME/lib/ext目录的类
        - 应用类加载器Application ClassLoader：加载用户路径上的类
                 
        - 双亲委派模型（类加载机制）：当一个类收到类加载请求时，他首先不会尝试去自己加载这个类，而是先把这个请求委派给父类去完成，
            只有当父类加载器无法完成加载时，子类加载器才会自己尝试去加载
            - 好处：比如rt.jar中的Object类，不管是哪个类加载器加载这个类，最终都是委托启动类加载器进行加载。
                这样保证了使用不同的类加载器最终得到的都是同一个Object对象
            - 破坏双亲委派模型：父级加载器无法加载子级类加载器路径中的类，此时需要破坏双亲委派模型
                 启动类加载器通过线程上下文类加载器拿到应用类加载器，通过应用类加载器进行加载
                 - mysql的驱动JDBC加载过程
                    - 获取线程上下文类加载器，从而也就获得了应用程序\自定义加载器 Thread.currentThread().getContextClassLoader();
                    - 从自定义加载器的路径文件中获取类名“com.mysql.jdbc.Driver”，
                    - 通过线程上下文去加载这个Driver类
                    - 因为类加载器受到加载范围的限制，在某些情况下父类加载器无法加载到需要的文件，这时候就需要委托子类加载器去加载class文件
                - Spring破坏双亲委派模型
                    - spring是由common类加载器加载。而web程序一般放在WEB-INF目录下，由WebAppClassLoader类加载器加载
                    - Sprng要对WEB程序组织和管理，需要使用使用线程上下文类加载器，
               - 
        - 自定义类加载器
          - ClassLoader作用：Java程序在运行的时候,JVM通过类加载机制(ClassLoader)把class文件加载到内存中,只有class文件被载入内存,才能被其他class引用,使程序正确运行起来
          - 为什么要自定义ClassLoader：因为系统的ClassLoader只会加载指定目录下的class文件,如果你想加载自己的class文件,那么就可以自定义一个ClassLoader.
          - 如何自定义classLoader？
              - 1.新建一个类继承自java.lang.ClassLoader,重写它的findClass方法
              - 2.将class字节码数组转换为Class类的实例
              - 3.调用loadClass方法
        - 
    
> JAVA集合

## 接口继承关系和实现
    - 集合类存放于 Java.util 包中， 主要有 3 种： set(集和）、 list(列表包含 Queue）和 map(映射)
    - Collection： Collection 是集合 List、 Set、 Queue 的最基本的接口
    - Iterator：迭代器，可以通过迭代器遍历集合中的数据
    - Map：是映射表的基础接口
    
### 常见集合类
    - ArrayList：有序可重复；底层数组实现；查询速度快，增删慢；线程不安全；扩容10*1.5+1
      - 空间扩展方法：动态增长的数组，grow（）计算出新的扩容数组的size后实例化，并将原有数组内容Arrays.copyOf复制到新数组中去
    - Vector：有序可重复；底层数组实现；速度快，增删慢；线程安全，效率低；扩容*2
    - LinkedList：有序可重复；底层双向循环链表实现；查询慢，增删快；线程不安全
    
    - HashSet：无序不重复；底层Hash表实现，存取速度快；内部是HashMap，元素保存到hashmap的key上，定义一个虚拟的Object对象作为HashMap的value
      - HashSet 首先判断两个元素的哈希值，如果哈希值一样，接着会比较
        equals 方法 如果 equls 结果为 true ， HashSet 就视为同一个元素。
        如果 equals 为 false 就不是同一个元素。一个hashcode位置上可以存放多个元素
      - hashset存储自定义对象，为什么要重写hashcode和equals？
          - 自定义对象的hashcode和equals方法不重写的话，继承的时Object，根据对象的引用来计算，
             new新建对象的引用都不一样，因此hashset中可能会保存重复的元素
    - TreeSet：排序存储不可重复；底层使用二叉树实现；内部是TreeMap的SortSet
      - 每增加一个对象就会进行排序，将对象插入二叉树指定的位置，自定义对象必须实现Comparatble接口，覆写compareTo方法才可以正常使用
      
    - LinkedHashSet：采用hash表存储，双向链表记录插入顺序；内部是LinkedHashMap
      - 它继承与 HashSet、又基于 LinkedHashMap 
    - Queue：在两端出入的List，可以用数组或者链表实现
    
    - HashMap：键不可重复，值可重复；底层hash表；线程不安全；允许key为nul，value为null
      - 线程安全：Collections 的 synchronizedMap
      - 线程安全：concurrentHashMap
      - 扩容属性：数组容量16，扩容*2，始终保持2*n，负载因子0.75
      - 底层实现：数组+链表+红黑树；当表中结点数大于64，链表中元素超过8，使用红黑树，查找时间复杂度O(logN)
      
    - HashTable：键不可重复，值可重复；底层hash表；线程安全；key和value都不能为null
    
    - TreeMap：键不可重复，值可重复；底层二叉树
      - 把保存的记录根据键值排序，默认升序
    - LinkedHashMap：键不可重复，值可重复；底层hash表+链表；key和value都不能为null
      - 保存记录的插入顺序，按照访问次数排序，LRU
### Hashmap成环的原因
    - hashmap是一个线程不安全的集合，并发情况下可能会出现链表成环的问题
    - 加入两个线程同时执行到transfer方法，由于next指针不是volatile的，导致并发操作的时候产生了环
### hashmap与hashtable的区别
     1.线程安全：Hashtable是线程安全，而HashMap则非线程安全
     2.针对null：HashMap可以使用null作为key，而Hashtable则不允许null作为key
     3.继承结构：HashMap是对Map接口的实现，HashTable实现了Map接口和Dictionary抽象类
     4.扩容大小：HashMap的初始容量为16，Hashtable初始容量为11，两者的填充因子默认都是0.75
                 - HashMap扩容时是当前容量翻倍即:capacity*2，Hashtable扩容时是容量翻倍+1即:capacity*2+1
     5.两者计算hash的方法不同：Hashtable计算hash是直接使用key的hashcode对table数组的长度直接进行取模
                               HashMap计算hash对key的hashcode进行了二次hash，以获得更好的散列值，然后对table数组长度取摸

    - HashMap是jdk1.7数组和链表的结构；1.8数组+链表+红黑树
        put：添加键值对时，首先进行table是否初始化的判断，如果没有进行初始化（分配空间，Entry[]数组的长度16）。
        然后进行key是否为null的判断，如果key==null ,放置在Entry[]的0号位置。
        计算在Entry[]数组的存储位置，判断该位置上是否已有元素，如果已经有元素存在，则遍历该Entry[]数组位置上的单链表。
        判断key是否存在，如果key已经存在，则用新的value值，替换点旧的value值，并将旧的value值返回。
        如果key不存在于HashMap中，程序继续向下执行。将key-vlaue, 生成Entry实体，添加到HashMap中的Entry[]数组链表的尾部jdk1.8
    冲突解决：
        - 线性（平方\随机）探测再散列法
        - 再散列法
        - 链地址法：将链表的头结点存在hash数组中，适用于经常插入和删除
       		     								   
### ConcurrentHashMap
       - jdk1.7中是采用Segment + HashEntry + ReentrantLock
       - jdk1.8中是采用Node + CAS + Synchronized
       - 变化  
         - 降低锁的粒度:JDK1.7版本锁的粒度是基于Segment的，包含多个HashEntry，而JDK1.8锁的粒度就是HashEntry（首节点）
         - 结构简单：synchronized来进行同步，不需要Segment这种数据结构
         - 红黑树：JDK1.8使用红黑树来优化链表，基于长度很长的链表的遍历是一个很漫长的过程，而红黑树的遍历效率是很快的，代替一定阈值的链表，这样形成一个最佳拍档
    
         - get：
                 - 1.首先计算hash值与数组长度，定位到该table索引位置，如果是首节点是空，直接返回，否则判断首结点的key是否相同，符合就返回
                 - 2.如果遇到扩容的时候，会调用标志正在扩容节点ForwardingNode的find方法，查找该节点，匹配就返回
                 - 3.以上都不符合的话，就往下next遍历节点，匹配就返回，否则最后就返回null
         - get没有加锁的话，ConcurrentHashMap是如何保证读到的数据不是脏数据？
            - get操作可以无锁是由于Node的元素val和指针next是用volatile修饰的，
               在多线程环境下线程A修改结点的val或者新增节点的时候是对线程B可见的。                   
         - volatile加在数组上的目的是？
             - 为了使得Node数组在扩容的时候对其他线程具有可见性而加的volatile
         - containsKey(key)：根据key的hash值与数组长度，找到数组中的位置，然后再逐个比较链表中的key是否相等
         - 链表转换成红黑树的情况：1.数组长度大于等于64  2.单链表长度大于8
             - 红黑树是一颗弱平衡的二叉搜索树
             - 每个节点要么是黑色，要么是红色。
               根节点是黑色。
               每个叶子节点（NIL）是黑色。
               每个红色结点的两个子结点一定都是黑色。
               任意一结点到每个叶子结点的路径都包含数量相同的黑结点。
          - sizeCtl: -1表示初始化   -(1+n) n:表示活动的扩张线程  在实例化对象的时候指定了容量，则初始化sizeCtl=len*0.75 控制表初始化
          - 初始化数组table
              - 第一次put的时候，table还没被初始化，进入while
              - sizeCtl初始值为0，当小于0的时候表示在别的线程在初始化表或扩展表，让出yield
              - 否则cas修改sizeCtl的值
              - 指定了大小的时候就创建指定大小的Node数组，否则创建指定大小(16)的Node数组
              - 初始化后，sizeCtl长度为数组长度的3/4
          - putVal
              - 初始化：当添加一对键值对的时候，首先会去判断保存这些键值对的数组是不是初始化了，如果没有的话就初始化数组
              - 空：然后通过计算hash值来确定放在数组的哪个位置，如果这个位置为空则直接添加，如果不为空的话，则取出这个节点来
              - 扩容：如果取出来的节点的hash值是MOVED(-1)的话，则表示当前正在对这个数组进行扩容，复制到新的数组，则当前线程也去帮助复制
              - 加锁遍历：最后一种情况就是，如果这个节点，不为空，也不在扩容，则通过synchronized来加锁，进行添加操作，然后判断当前取出的节点位置存放的是链表还是树
              - 链表：如果是链表的话，则遍历整个链表，直到取出来的节点的key来个要放的key进行比较，如果key相等，并且key的hash值也相等的话，则说明是同一个key，则覆盖掉value，否则的话则添加到链表的末尾
              - 树：如果是树的话，则调用putTreeVal方法把这个元素添加到树中去
              - 扩容：最后在添加完成之后，会判断在该节点处共有多少个节点，不加当前节点大于等于8个的话
                则调用treeifyBin方法来尝试将处的链表转为树，或者扩容数组
              
           - treeifyBin
               - 当数组长度小于64的时候，扩张数组长度一倍，否则的话使用synchronized同步器，将该节点出的链表转为树
           - tryPresize:扩容表为可以容纳指定个数的大小（总是2的N次方），并没有加锁，允许多个线程进入，如果数组正在扩张，则当前线程也去帮助扩容
               - transfer
                   - 1.将表拆分，让每个线程处理自己的区间，最小区间为16
                   - 2.扩容的时候每处理一个节点，会在链表的头部设置一个fwd节点，这样其他线程就会跳过他
                   - 3.根据节点的hash值判断Node在新表中的位置，0：新表的原来位置  n：新表的n+原来位置
                   - 顺序大部分和原来是反的，分成两部分分别放到了原来的位置和新增加的长度的相同位置

#### stringbuffer与stringbuilder的区别 
    - 字符串变量，是可改变的对象
    - 1.线程安全：stringbuffer是线程安全的，stringbuilder是线程不安全的
    - 2.效率：stringbuilder速度比stringbuffer快
               
> JAVA多线程并发

## JAVA线程的创建方式
    - 1.继承Thread：Thread本质上是实现了Runnable接口的一个实例。启动线程的唯一方法是通过Thread类的start()实例方法。
        start()方法是一个native方法，它将启动一个新线程，并执行run方法
    - 2.实现Runnable接口

## Runnable Callable
     - 方法：Runnable执行方法是run(),Callable是call()
     - 返回值：实现Runnable接口的任务线程无返回值；实现Callable接口的任务线程能返回执行结果
     - 异常：run方法若有异常只能在内部消化；call方法可以抛出异常
     ### callable如何获取结果
       - Callalble接口支持返回结果，submit提交Callable任务返回Future对象，并通过Future.get()方法来获取执行结果，
           此方法会阻塞主线程，直到获取结果
     
## 线程池
### 什么是线程池
      - 什么是线程池：  java.util.concurrent.Executors提供了一个 java.util.concurrent.Executor接口的实现用于创建线程池，线程池里面定期维护活跃线程，减少线程的创建和销毁时间
### 何时使用线程池
      - 多线程技术显著减少处理器单元的闲置时间，增加处理器单元的吞吐能力
      - 一个服务器完成一项任务所需时间为：T1 创建线程时间，T2 在线程中执行任务的时间，T3 销毁线程时间
          如果：T1 + T3 远大于 T2，则可以采用线程池，以提高服务器性能
### 线程池的作用
       - 1.缩短线程创建销毁时间
       - 2.根据系统的承受能力，调整线程池中工作线线程的数目，防止消耗过多的内存
       - 多线程程序并不能提高程序的运行速度，但能够提高程序运行效率，让CPU的使用率更高
    
### 线程池包括哪些部分
    一个线程池包括以下四个基本组成部分：
    1、线程池管理器（ThreadPool）：用于创建并管理线程池，包括 创建线程池，销毁线程池，添加新任务；
    2、工作线程（PoolWorker）：线程池中线程，在没有任务时处于等待状态，可以循环的执行任务；
    3、任务接口（Task）：每个任务必须实现的接口，以供工作线程调度任务的执行，它主要规定了任务的入口，任务执行完后的收尾工作，任务的执行状态等；
    4、任务队列（taskQueue）：用于存放没有处理的任务。提供一种缓冲机制。
    
### 有哪几种线程池
    newSingleThreadExecutor：单个线程的线程池，这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。    
    newFixedThreadPool：固定数量的线程池，每提交一个任务就是一个线程，直到到达线程池的最大核心数量，然后后面的任务进入等待队列，直到前面的任务完成才可以执行 （无界队列：LinkedBlockingQueue）
    newCachedThreadPool：可缓存线程池，当线程池大小超过了处理任务所需的线程，那么会回收部分空闲（默认60s无执行）线程，当有任务来时，添加新的线程来执行 （无界线程池-核心线程数Integer.MAX_VALUE   SynchronousQueue-BlockQueue-当队列空或者满时，每个插入操作必须等待另一个线程的对应移除操）
    newScheduledThreadPool：创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
     
### ThreadPoolExecutor详解
        是ExecutorService的默认实现
        public ThreadPoolExecutor(intcorePoolSize,   //线程池中保存的线程数
        intmaximumPoolSize,    //最大线程数
        longkeepAliveTime,   //当线程数大于核心线程数时，空闲线程等待新任务的最长时间
        TimeUnitunit,  //时间单位
        BlockingQueue<Runnable>workQueue,  //保存超过核心线程数的任务的队列
        ThreadFactorythreadFactory,                  //创建新线程时使用的工厂
        RejectedExecutionHandlerhandler)     //超出线程范围和队列容量而执行的策略

### 线程池的处理流程  
        corePoolSize -> 任务队列 -> maximumPoolSize -> 拒绝策略
        - 提交任务：1.先判断线程池中的核心线程是否空闲，如果空闲，就把新的任务指派给某一个空闲线程去执行。如果没有空闲，并且当前线程池中的线程数小于corePoolSize，那就再创建一个线程
        - 2.如果没有空闲，并且线程池中的线程数都达到和核心线程，那就把新来的任务加入到等待队列中。如果等待队列也满了，那么查看当前线程数是否达到了最大线程数maxPoolSize，如果还未达到，就继续创建线程
        - 3.如果以及达到了最大线程数，就交给RejectedExecutionHandler拒绝策略

### 如何控制线程池线程的优先级
    - 线程在创建的时候可以设置优先级0-9，数字越小优先级越高
    - 可以使用优先队列来为队列中的每一个任务增加优先级

### queue的三种类型
    - 1.无缓存的队列：SynchronousQueue是内部只能包含一个元素的阻塞队列。必须等待队列中的元素被消费后才能继续添加元素。一般要求maximumPoolSizes为无界，避免线程执行拒绝操作   （并发不高、任务执行时间长）
    - 2.无界队列：LinkedBlockingQueue，当任务耗时较长时可能会导致大量新任务在队列中堆积最终导致OOM。  （高并发，任务执行时间不长）
    - 3.有界队列：ArrayBlockingQueue，可以防止资源耗尽的情况发生   （高并发，任务执行时间长）

    阻塞队列的方法：
    1.抛出异常：add,remove
    2.特殊值：offer,poll
    3.阻塞：put，take
    
    #### Java中的阻塞队列
        - 1.ArrayBlockingQueue：由数组结构组成的有界阻塞队列，默认不保证公平访问，公平访问为true，效率低
        - 2.LinkedBlockingQueue：由链表结构组成的阻塞队列，默认无限大小容量，
            生产者消费者端分别使用了独立的锁（写锁和读锁）来控制同步，高效的处理并发数据，性能比1好
        - 3.PriorityBlockingQueue：支持优先级排序的无界阻塞队列，compareTo()方法来指定元素进行排序规则，
        - 4.DelayQueue：支持延迟获取元素的无阻塞队列
        - 5.SynchronousQueue：不存储元素的阻塞队列，每一个 put 操作必须等待一个 take 操作，否则不能继续添加元素
        - 6.LinkedTransferQueue：由链表结构组成的无界阻塞队列，transfer如果当前有消费者正在等待接收元素，
            可以把生产者传入的元素立刻 transfer（传输）给消费者，否则放入阻塞队列
        - 7.LinkedBlockingDeque：由链表结构组成的双向阻塞队列，可以从队列的两端插入和移出元素

### 4种拒绝策略
    - 1.AbortPolicy  直接抛出异常阻止系统正常运行
    - 2.DiscardPolicy  直接丢弃任务
    - 3.DiscardOldestPolicy 抛弃队列中等待最久的线程，把当前任务提交到队列中
    - 4.CallerRunsPolicy  阻塞主线程，直接运行当前任务，别的任务只有当被拒绝的任务执行完才能提交到线程池执行
     
### submit方法与execute方法的区别     
     1.execute方法是Executor接口定义的，它只能用于执行Runable任务，并且无返回值。
     2.submit方法是ExecutorService中定义的，它既能用于执行Runable任务，也可以用于执行Callable任务，
     并且必有返回值，且返回值为Future<T>，利用返回值Future<T>，我们可以获取任务执行的结果，以及中断任务的执行等操作
     
### 如何正确使用线程池
      - 1.避免使用无界队列，防止OOM   ArrayBlockingQueue<>(512)
      - 2.设定拒绝策略 DiscardPolicy  (AboardPolicy 非受检异常，很容易忘记捕获)
     
### 创建线程池
      - 1.避免使用Executors构造线程池，直接使用ThreadPoolExecutor的构造方法，明确线程池的运行规则，规避资源耗尽的风险
      - 2.避免无界队列可能导致的OOM以及线程个数限制不当导致的线程数耗尽等问题

### 线程池状态
     - RUNNING：线程池一旦被创建，就处于 RUNNING 状态，任务数为 0，能够接收新任务，对已排队的任务进行处理
     - SHUTDOWN：不接收新任务，但能处理已排队的任务
     - STOP(shutdownNow)：不接收新任务，不处理已排队的任务，并且会中断正在处理的任务
     - TIDYING：线程池中执行中任务为空时，就会由 STOP 转变为 TIDYING 状态
     - TERMINATED：线程池彻底终止
    
### 如何合理配置核心线程数和最大线程数
    1.先看下机器的CPU核数，然后在设定具体参数：   
    2.分析下线程池处理的程序是CPU密集型，还是IO密集型 
        - CPU密集型：业务时间长集中在计算操作，线程池中的线程数设置得少一些，减少线程上下文的切换 ，核心线程数 = CPU核数 + 1   保证CPU不会空闲，减少线程上下文的切换  
        - IO密集型：因为IO操作并不占用CPU，所以不要让所有的CPU闲下来，可以适当加大线程池中的线程数目，让CPU处理更多的业务核心线程数 = CPU核数 * 2     保证IO不会空闲，io不占用cpu的资源
    
    
    • corePoolSize = 每秒需要多少个线程处理？ 
    	○ threadcount = tasks/(1/taskcost) =tasks*taskcout =  (500~1000)*0.1 = 50~100 个线程。corePoolSize设置应该大于50
    	○ 根据8020原则，如果80%的每秒任务数小于800，那么corePoolSize设置为80即可
    • queueCapacity = (coreSizePool/taskcost)*responsetime
    	○ 计算可得 queueCapacity = 80/0.1*1 = 80。意思是队列里的线程可以等待1s，超过了的需要新开线程来执行
    	○ 切记不能设置为Integer.MAX_VALUE，这样队列会很大，线程数只会保持在corePoolSize大小，当任务陡增时，不能新开线程来执行，响应时间会随之陡增。
    • maxPoolSize = (max(tasks)- queueCapacity)/(1/taskcost)
    	○ 计算可得 maxPoolSize = (1000-80)/10 = 92
    	○ （最大任务数-队列容量）/每个线程每秒处理能力 = 最大线程数
    • rejectedExecutionHandler：根据具体情况来决定，任务不重要可丢弃，任务重要则要利用一些缓冲机制来处理
    • keepAliveTime和allowCoreThreadTimeout采用默认通常能满足
    
### Future、FutureTask、CompletionService、CompletableFuture
    想要获取线程池的执行结果，可以使用一下几种结果类
     1.Future
     Future 表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
     使用线程池提交Callable接口任务，返回Future接口，添加进list,最后遍历FutureList且内部使用while轮询,并发使用future.get()获取结果（阻塞线程）十分耗时，按照提交顺序获取结果
     2.FutureTask类
     FutureTask类实现了RunnableFuture接口，而RunnableFuture接口又继承了Runnable接口和Future接口，所以FutureTask类本质上是Runnable接口的实现类，且兼具Future接口的特性，我们知道线程池的execute方法和submit方法都可以执行Runable任务，所以同样可以执行FutureTask任务。
     FutureTask类有一个done方法，该方法在任务执行完毕时自动回调，我们可以重写该方法并在该方法中调用get()方法获取任务执行的结果，并且因为任务已经执行完毕，此时调用get()方法可以直接得到结果，所以并不会阻塞线程。顺序未知
     
     3.CompletionService
     内部通过阻塞队列+FutureTask，实现了任务先完成可优先获取到，即结果按照完成先后顺序排序，
     使用率也挺高，而且能按照完成先后排序，建议如果有排序需求的优先使用。只是多线程并发执行任务结果归集，也可以使用。
     
     4.CompletableFuture
     JDK8实现了Future, CompletionStage两个接口
     - 满足并发执行
     - 支持任务完成的先后顺序
     - 流式处理速度很快
     - API极端丰富，配合流式编程，推荐使用！
     CompletionStage代表异步计算过程中的某一个阶段，一个阶段完成以后可能会触发另外一个阶段
  
### 线程池里一个线程OOM，其他两个线程还能继续执行吗    
    还能继续执行
    在多线程环境下，每个线程拥有一个栈和一个程序计数器，栈和程序计数器用来保存线程执行历史和线程执行状态，
    是线程私有的，堆是由用一个进程内多个线程共享的
    当一个线程抛出OOM异常后，它占用的内存空间会全部被释放掉，从而不会影响其他线程的运行
  
     
## 线程
#### 线程的五种生命周期
      - new :使用 new 关键字创建了一个线程之后，该线程就处于新建状态，此时仅由 JVM 为其分配内存，并初始化其成员变量的值
      - Runnable: 调用了 start()方法之后，该线程处于就绪状态。 Java 虚拟机会为其创建方法调用栈和程序计数器
      - Running : 就绪状态的线程获得了 CPU，开始执行 run()方法的线程执行体
      - Blocked : 线程因为某种原因放弃了 cpu 使用权，暂时停止运行。直到线程进入就绪状态
          - 等待阻塞wait：放入等待队列，等待唤醒
          - 同步阻塞lock：如果同步锁被其他线程占用，该线程入锁池
          - 其他阻塞：调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。
             当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态
      - Dead：线程执行完了或者因异常退出了run()方法，该线程结束生命周期     
 
#### 实现多线程的两种方式
        1.Thread 和 Runnable 的相同点：都是“多线程的实现方式”。
        2.Thread 和 Runnable 的不同点：
            - Thread 是类，而Runnable是接口；Thread本身是实现了Runnable接口的类。我们知道“一个类只能有一个父类，但是却能实现多个接口”，因此Runnable具有更好的扩展性。
            - Runnable还可以用于“资源的共享”。即，多个线程都是基于某一个Runnable对象建立的，它们会共享Runnable对象上的资源。
                  通常，建议通过“Runnable”实现多线程！

#### Thread中start()和run()的区别     
     - start() : 它的作用是启动一个新线程，新线程会执行相应的run()方法。start()不能被重复调用
     - run(): run()就和普通的成员方法一样，可以被重复调用。单独调用run()的话，会在当前线程中执行run()，而并不会启动新线程！

#### 线程之间如何通信
    - wait notify notifyAll join sleep ThreadLocal
#### sleep wait 区别  notify   notifyAll
    - sleep是属于Thread类的方法，wait是属于Object的方法
    - sleep让出CPU，不释放锁，指定时间到了会自动恢复运行状态，wait释放锁,进入等待状态，等待调用notify，才能进去runnable状态
    - notify唤醒在此对象监视器上等待的单个线程
    - notifyAll唤醒在此对象监视器上等待的所有线程
#### 为什么notify(), wait()等函数定义在Object中，而不是Thread中
    - notify(), wait()依赖于“同步锁”，而“同步锁”是对象锁持有，并且每个对象有且仅有一个
    
#### yield
    - 让出cpu执行片段，让当前线程由running进入runnable状态，从而让其他具有相同优先级的等待线程获取执行权，
        并不能保证拥有相同优先级的线程能够获取执行权，有可能当前线程又获取了执行权
### join
    - 执行t1.join后，主线程会进行阻塞状态，等待子线程执行完，会唤醒主线程重新获取CPU执行权
        - 底层：主线程执行t1.join(),while(isAlive)判断子线程是活的，主线程就不停的wait
#### 线程终止的4种方式
    1.正常运行结束：程序运行结束，线程自动结束
    2.使用volatile关键字变量做为while循环退出标志，同一时间只能由一个线程来修改exit
    3.Interrupt方法结束线程
        - 线程处于阻塞状态(sleep,wait)，当调用interrupt方法时，会抛出InterruptException异常，
            阻塞中的方法抛出异常，通过代码捕获异常，然后break跳出循环状态
        - 线程未处于阻塞状态：使用isInterrupted()判断线程的中断标志来退出循环
    4.stop方法退出循环。线程不安全，调用stop后，导致了该线程所持有的所有锁突然释放，有可能出现数据不一致

#### interrupt、interrupted 、isInterrupted
    - interrupt()设置中断状态。线程中断仅仅是改变线程的中断状态位，不会停止线程。当线程调用sleep，wait等
        方法处于阻塞状态时，一旦线程的中断状态设置为true，就会抛出中断异常
    - interrupted()返回当前线程的中断状态，并清除当前线程中断状态，如果当前线程中断状态为true，
        第一次interrupted会返回true，，第二次调用interrrupted会返回false
    - isInterrupted()仅仅返回线程中断状态，不会清除中断状态 
     
#### synchronized关键字
    - 同一个对象锁，调用对象的同步方法都会被阻塞。非同步方法不会阻塞
    - 同一个类锁（锁在静态方法上），不同对象不同方法共享一个全局锁，也会被阻塞
    - 对象锁和类锁不互相阻塞
    
#### 线程上下文切换
        利用时间片轮转方式，CPU给每个任务都服务一段时间，把当前任务的状态保存下来，加载下一任务的状态，
        继续服务下一任务，任务的状态保存再加载，成为上下文切换
     
#### 线程和进程
    1、最小单位：进程是资源分配的最小单位，线程是程序执行的最小单位（资源调度的最小单位）
    2、独立空间：进程有自己的独立地址空间，每启动一个进程，系统就会为它分配地址空间，建立数据表，这种操作非常昂贵。
    而线程是共享进程中的数据的，使用相同的地址空间，因此CPU创建和切换一个线程的花费远比进程要小很多
    3、通信：线程之间的通信比进程更方便，同一进程下的线程共享全局变量、静态变量等数据
    4、健壮：但是多进程程序更健壮，多线程程序有一个线程死掉可能会导致整个进程也死掉了，而一个进程死掉并不会对另外一个进程造成影响
     
####  threadlocal(2-2)
     - 线程局部变量
         - 每个线程提供一个独立的变量副本解决了变量并发访问的冲突问题，
         ThreadLocal比直接使用synchronized同步机制解决线程安全问题更简单，更方便，拥有更高的并发性。
         - ThreadLocal相当于维护了一个ThreadLocalMap，key就是当前的线程，value就是需要存储的对象
         
         - 场景：用来解决 数据库连接、 Session 管理
         ```
         private static final ThreadLocal threadSession = new ThreadLocal();
             public static Session getSession() throws InfrastructureException {
                 Session s = (Session) threadSession.get();
                 try {
                    if (s == null) {
                    s = getSessionFactory().openSession();
                    threadSession.set(s);
                    }
                 } catch (HibernateException ex) {
                    throw new InfrastructureException(ex);
                 }
             return s;
         }
         ```
         - 内存泄漏
             - ThreadLocal引用被设置为null，且后面没有set，get,remove操作。
             - 线程池一直运行，不停止。（线程池）
             - 触发了垃圾回收不能回收value对象。（Minor GC或Full GC）     
     
#### 守护线程
    - 守护线程：为用户线程提供公共服务，再没有用户线程可服务时会自动离开
    - 优先级比较低，通过设置setDaemon(true)将用户线程设置为守护线程
    - example：垃圾回收线程时守护线程，当没有用户线程产生垃圾后，垃圾回收线程就会自动离开
    - 生命周期：守护线程的生命周期与JVM相同    
   
> JAVA 锁

### 乐观锁与悲观锁
    - 乐观锁：认为读多写少，遇到并发的可能性低，每次读数据时不会加锁；写数据时通过cas操作判断是否被修改
       - cas：比较当前工作内存中的值与内核中的值是否一样，一样则更新，否则失败
       - 乐观锁一般会使用版本号机制或CAS算法实现
    - 悲观锁：认为写多，遇到并发的可能性比较高，每次读写数据的时候都会加锁，Synchronized，ReentrantLock
    - 自旋锁：假设持有锁的线程能在很短时间内释放锁，它们只需要等一等（自旋一段时间），等待有锁的线程释放锁后即可立即获得锁，
        避免了用户线程和内核切换的消耗
    - 适应性自旋锁：自旋是需要消耗cpu的，为了避免一直获取不到锁，设定一个自旋的时间，
        自旋时间由前一次再同一个锁上的自旋时间以及锁的拥有者的状态来决定
    - 独占锁：读写都不能多个线程执行
    - 共享锁：读读可以多个线程同时操作，读写，写写都只能一个线程访问
    - 重量级锁：Synchronized 是通过对象内部的一个叫做监视器锁（monitor）来实现的，
        依赖于底层的操作系统的 Mutex Lock 来实现的，线程之间的切换需要从用户态转换为核心态，成本非常高，称之为重量级锁
    - 轻量级锁：为了减少重量级锁产生的性能消耗，场景：线程交替执行同步块时
        - 锁的状态：无锁、偏向锁、轻量级锁、重量级锁
        - 偏向锁：只有一个线程多次执行同步块时，只需要置换threadID时依赖一次CAS，避免多次获取释放CAS指令
        - 轻量级锁：没有多线程竞争的情况下，多线程交替执行同步块，使用CAS指令；如果多线程同一时间访问同一锁，就会变成重量级锁
        
    #### 锁优化
     - 1.减少持有时间，只有在要求线程安全时，加锁
     - 2.减少锁粒度：ConcurrentHashMap
     - 3.锁分离：ReadWriteLock
     - 4.锁粗化：
#### 偏向锁的获取与撤销
    - 当一个线程访问同步块并获取锁时，
        会在对象头和栈帧中的锁记录里存储锁偏向的线程ID，
        以后在该线程进入和退出同步块时不需要CAS操作来加锁和解锁，
        只需要简单的测试一下对象头的Mark Word里是否存储着指向当前线程的偏向锁。
        如果测试成功，表示线程已经获得了锁。如果测试失败，
        则需要再测试一下Mark Word中偏向锁的标志是否设置成1，
        如果没有设置，使用CAS竞争锁，如果设置了，
        则尝试使用CAS将对象头的偏向锁指向当前线程。
        
     - 偏向锁的撤销过程：偏向锁使用了一种等到竞争出现才释放锁的机制，所以当其他线程尝试竞争偏向锁时，持有偏向锁的线程才会释放锁
           - 1.假设线程1持有偏向锁，此时线程2竞争锁
           - 2.暂停线程1，等到全局安全点
           - 3.判断线程1是否存活，不存活，设置为无锁
           - 4.否则判断线程1是否继续竞争，如果继续竞争，升级为轻量级锁，否则线程2获得锁
### Synchronized 同步锁
    - synchronized同步锁，可以把任意一个非空的对象当作锁，独占式的悲观锁，可重入锁，非公平锁
    ##### 作用范围
        - 作用与方法：锁住的是对象的实例，对当前对象的所有线程起作用
        - 作用与静态方法：锁住的是Class，相当于全局锁，对该类的所有对象的所有线程起作用
        - 作用与对象实例时，锁住的是以该对象为锁的代码块
        (01) x.isSyncA()与x.isSyncB()  不能被同时访问，同一个对象锁
        (02) x.isSyncA()与y.isSyncA()  可以同时访问，不同的对象锁
        (03) x.cSyncA()与y.cSyncB()    不能被同时访问。全局锁，不同实例之间仍然会被限制
        (04) x.isSyncA()与Something.cSyncA()  可以同时访问。实例锁与类锁相互之间无约束，独立的监视块
    #### Synchronized的竞争过程 ContentionList EntryList OnDeck Owner WaitSet
        - 自旋：线程进入ContentionList时，首先会先尝试自旋获取锁或者直接抢占OnDeck资源，获取不到进入ContentionList
        - 迁移：Owner线程在unlock时，将ContentionList中的部分线程迁移到EntryList中，并指定EntryList中的某个线程为OnDeck线程
        - owner：OnDeck线程需要竞争锁，获取到锁资源后会变成Owner线程
        - 阻塞：Owner线程被wait方法阻塞，则转移到WaitSet队列中，通过notify唤醒，重新进去EntryList
        - 位于ContentionList、EntryList、WaitSet中的线程都处于阻塞状态，阻塞是由操作系统完成的
    #### 对象头    
        - 同步的时候是获取对象的monitor，即获取到对象的锁，对象的锁就是对对象的一个标志，这个标志就是存在Java对象的对象头中
        - Java对象头里的Mark Word里默认存放是的对象的Hashcode，分代年龄和锁标记位，表示无锁、偏向锁、轻量级锁、重量级锁
        - 重量级锁的指针指向的是一个monitor对象，加锁就是在竞争monitor对象，
          代码块加锁是在前后分别加上monitorenter和monitorexit指令实现的，加锁成功计数器加1，否则减1
#### ReentrantLock底层原理
    - ReentrantLock主要利用CAS+AQS队列来实现。它支持公平锁和非公平锁
    - lock-acquire(1)
        - （非公平锁）直接 CAS 设置 state 变量，如果设置成功，表明加锁成功。否则，再调用 acquire 方法将线程置于队列尾部排队
        - （公平锁）没有前驱节点并且CAS设置成功（设置当前线程为独占线程）- 如果当前线程已经持有锁，表示重入，更新同步状态值netxc
    - 先尝试释放锁，若释放成功，那么查看头结点的状态是否为SIGNAL，如果是则唤醒头结点的下个节点关联的线程，如果释放失败那么返回false表示解锁失败
#### ReentrantLock 与 Synchronized
    1.锁：ReentrantLock 显示的获得、释放锁， synchronized 隐式获得释放
    2.新特性：ReentrantLock 等待可响应中断、公平与非公平锁
    3.级别：ReentrantLock 是 API 级别的， synchronized 是 JVM 级别的
    4.异常：synchronized 在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生;
        Lock 在发生异常时，如果没有主动通过 unLock()去释放锁，则很可能造成死锁现象,
        因此使用 Lock 时需要在 finally 块中释放锁
        
      - condition.await() 通过创建 Condition 对象来使线程 wait，必须先执行 lock.lock 方法获得锁
      - condition.signal() condition 对象的 signal 方法可以唤醒 wait 线程
      
#### Condition类与Object类锁方法区别
    1. Condition 类的 awiat 方法和 Object 类的 wait 方法等效
    2. Condition 类的 signal 方法和 Object 类的 notify 方法等效
    3. Condition 类的 signalAll 方法和 Object 类的 notifyAll 方法等效
    4. ReentrantLock 类可以唤醒指定条件的线程，而 object 的唤醒是随机的
    
#### 不可重入锁与可重入锁
     不可重入锁：当需要获取同步锁时，只判断这个锁有没有被占用，如果被占用，线程都会被要求等待。实现简单
     可重入锁：不仅判断锁有没有被锁上，还会判断锁是谁锁上的，当就是自己锁上的时候，那么他依旧可以再次访问临界资源，并把加锁次数加一。
         1.同一线程外层函数获得对象锁之后，内层递归函数仍然可以获取该对象锁的代码，而不会出现死锁  synchronized  ReentrantLock
     作用及使用场景：
         1.最大的作用就是可以避免死锁
         2.当一个线程执行一个带锁的代码块或方法，代码块或方法里也获取同一个锁访问同步资源时。为了避免死锁，可以用可重入锁。
    	 

#### 公平锁与非公平锁
    - 非公平锁：在进去等待队列之前会自动尝试获取锁，获取不到，进入队列阻塞，实际执行效率高与公平锁
    - 公平锁：先对锁提出获取请求的线程会先分配到锁
 
 
#### ReadWriteLock 读写锁
    - 1.共享锁，读/读不冲突，读锁允许并发读，写锁只允许一个线程写 
#### tryLock 和 lock 和 lockInterruptibly
    1. tryLock 能获得锁就返回 true，不能就立即返回 false， tryLock(long timeout,TimeUnit
    unit)，可以增加时间限制，如果超过该时间段还没获得锁，返回 false
    2. lock 能获得锁就返回 true，不能的话一直等待获得锁
    3. lock 和 lockInterruptibly，如果两个线程分别执行这两个方法，但此时中断这两个线程，
    lock 不会抛出异常，而 lockInterruptibly 会抛出异常
 
### 死锁
    - 两个或者两个以上的线程在运行过程中因争夺资源而造成的僵局
   #### 死锁产生的原因
      - 1.竞争资源不足导致死锁；不能满足所有先线程资源请求，打印机
      - 2.进程推进顺序不当引起死锁；所有线程都等待对方释放锁，或者等待对方发送消息
   #### 产生线程的4个必要条件
       - 1.互斥条件：一个资源每次只能被一个进程使用
       - 2.不可剥夺条件：进程已获得的资源，在未主动释放前，不能强行剥夺
       - 3.请求与保持：一个进程因请求资源而阻塞时，对已获得的资源保持不放
       - 4.循环等待：若干进程之间形成一种头尾相接的循环等待资源关系
   #### 死锁的例子
   ```java
/**
 * 一个简单的死锁类
 * 当DeadLock类的对象flag==1时（td1），先锁定o1,睡眠500毫秒
 * 而td1在睡眠的时候另一个flag==0的对象（td2）线程启动，先锁定o2,睡眠500毫秒
 * td1睡眠结束后需要锁定o2才能继续执行，而此时o2已被td2锁定；
 * td2睡眠结束后需要锁定o1才能继续执行，而此时o1已被td1锁定；
 * td1、td2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。
 */
public class DeadLock implements Runnable {
    public int flag = 1;  
    //静态对象是类的所有对象共享的  
    private static Object o1 = new Object(), o2 = new Object();  
    @Override  
    public void run() {  
        System.out.println("flag=" + flag);  
        if (flag == 1) {  
            synchronized (o1) {  
                try {  
                    Thread.sleep(500);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                synchronized (o2) {  
                    System.out.println("1");  
                }  
            }  
        }  
        if (flag == 0) {  
            synchronized (o2) {  
                try {  
                    Thread.sleep(500);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                synchronized (o1) {  
                    System.out.println("0");  
                }  
            }  
        }  
    }  

    public static void main(String[] args) {
        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();
        td1.flag = 1;
        td2.flag = 0;
        //td1,td2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的。  
        //td2的run()可能在td1的run()之前运行  
        new Thread(td1).start();  
        new Thread(td2).start();
    }  
} 
```
     
   #### 处理死锁的办法
     - 1.预防死锁：破坏死锁的4个必要条件之一
     - 2.避免死锁：加锁顺序，加锁时限

### CountDownLatch 线程计数器
    - CountDownLatch 的作用是允许1或者多个线程，等待另外N个线程完成某件事情之后，这1个或者多个线程才能执行
      2.实现类似计数器的功能，一个任务 A，它要等待其他 4 个任务执行完毕之后才能执行，
       主latch.await()后面的代码需要等到n个线程执行完才能继续向下执行
    - 是一次性的，无法被重置的  
    
    - 底层实现：创建一个Sync对象，而Sync继承AQS
      - CountDownLatch使用state来计数，最终调用的是AQS的getState()
      - countDown()源码：该方法其实调用AQS中的releaseShared(1)释放共享锁方法
```java
final CountDownLatch latch = new CountDownLatch(2);
    new Thread(){public void run() {
        System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
        Thread.sleep(3000);
        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
        latch.countDown();
    };}.start();
    new Thread(){ public void run() {
        System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
        Thread.sleep(3000);
        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
        latch.countDown();
    };}.start();
    System.out.println("等待 2 个子线程执行完毕...");
    latch.await();
    System.out.println("2 个子线程已经执行完毕");
    System.out.println("继续执行主线程");
}
```

### CyclicBarrier 回环栅栏
    - 1.让N线程等待await至某个状态barrier之后再全部同时执行
    子cyclicBarrier.await()：挂起当前线程，直至所有线程都到达 barrier 状态再同时执行后续任务
    - 在调用reset方法之后，还可以重新使用
    
    - 底层实现：CyclicBarrier是由ReentrantLock可重入锁和Condition共同实现的
```java
public static void main(String[] args) {
    int N = 4;
    CyclicBarrier barrier = new CyclicBarrier(N);
        for(int i=0;i<N;i++)
        new Writer(barrier).start();
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(5000); //以睡眠来模拟线程需要预定写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完
                毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务，比如数据操作");
        }
}
```
### Semaphore
    - 1.Semaphore（信号量）是用来控制同时访问特定资源的线程数量
    acquire() 获取一个许可，如果没有可用的许可就阻塞等待，而 release() 释放一个许可
    - 可以用来构建一些对象池，资源池之类的， 比如数据库连接池
    - 设置阈值为2，也可以用来构建锁
    
    - Semaphore有两个内部类：NonfairSync FairSync
    #### "公平信号量"和"非公平信号量"的区别
     - "公平信号量"和"非公平信号量"的释放信号量的机制是一样的！
     不同的是它们获取信号量的机制：线程在尝试获取信号量许可时，
     对于公平信号量而言，如果当前线程不在CLH队列的头部，则排队等候；
     而对于非公平信号量而言，无论当前线程是不是在CLH队列的头部，它都会直接获取信号量。
     该差异具体的体现在，它们的tryAcquireShared()函数的实现不同。
```java
int N = 8; //工人数
Semaphore semaphore = new Semaphore(5); //机器数目
for(int i=0;i<N;i++)
    new Worker(i,semaphore).start();
}
static class Worker extends Thread{
    private int num;
    private Semaphore semaphore;
    public Worker(int num,Semaphore semaphore){
        this.num = num;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("工人"+this.num+"占用一个机器在生产...");
            Thread.sleep(2000);
            System.out.println("工人"+this.num+"释放出机器");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
    }
}
```
         
#### JAVA volatile关键字以及线程安全;(2-1)  
    
    1.解释为什么会产生线程安全问题
        两个或者两个以上的线程对象操作同一资源，线程之间工作内存不共享
        
    2.volatile的原理
        - volatile只保证可见性和有序性，不保证原子性
        - volatile关键字对于基本类型的修改可以在随后对多个线程的读保持一致，但是对于引用类型如数组，
           实体bean，仅仅保证引用的可见性，但并不保证引用内容的可见性
        可见性
        - 缓存一致性协议：当某个CPU在写数据时，如果发现操作的变量是共享变量，则总线会通知其他CPU告知该变量的缓存行是无效的，
            因此其他CPU在读取该变量时，发现其无效会重新从主存中加载数据。
    
        有序性
    	  - 当我们把代码写好之后，虚拟机不一定会按照我们写的代码的顺序来执行
    	    虚拟机在进行代码编译优化的时候，对于那些改变顺序之后不会对最终变量的值造成影响的代码，是有可能将他们进行重排序的
    	    对于有些代码进行重排序之后，虽然对变量的值没有造成影响，但有可能会出现线程安全问题的
    	    如果一个变量被声明volatile的话，那么这个变量不会被进行重排序，也就是说，虚拟机会保证这个变量之前的代码一定会比它先执行，而之后的代码一定会比它慢执行
    
        - 什么情况下volatile能够保证线程安全
    	   - 单纯的变量赋值，对变量的写操作不依赖于当前值（比如 i++）。
    	   - 该变量没有包含在具有其他变量的不变式中
    	   
    	   
   #### volatile为什么不能保证线程安全
       - 线程想要保证线程安全必须保证原子性，可见性和有序性；volatile只能保证可见性和有序性
       
       
#### Java 中用到的线程调度
    - 抢占式调度：系统控制每条线程的执行时间、线程的切换。在这种机制下，一个线程的阻塞不会导致整个进程的阻塞
    - 协同式调度：线程本身控制线程的执行时间。一个线程的阻塞可能会导致整个系统的崩溃
    - JVM的线程调度实现是抢占式调度，Java线程按照优先级分配CPU时间片段，且优先级越高越优先执行
    
    #### 进程调度算法 ：优先调度算法：高优先权调度算法 ： 基于时间片的轮询调度算法
     - 1.先来先服务调度算法
     - 2.估计短作业优先调度算法
     
      - 1.非抢占式优先权算法：系统调度，进程执行完
      - 2.抢占式优先权调度算法：只要优先级更高，立即分配执行权
      - 3.高响应比优先调度算法：
      
      
#### 什么是 CAS
    - 概念：CAS（Compare And Swap/Set）比较并交换，包含三个参数，内存偏移，预期值，新值
    只有当内存值等于预期值的时候，才会将内存值更新为新值
    - 原子类中自增使用了CAS，CAS是一种非阻塞的算法，getAndIncrement 采用了 CAS 操作，
      每次从内存中读取数据然后将此数据和+1 后的结果进行CAS操作，如果成功返回，否则重试
      compareAndSet 利用 JNI 来完成CPU 指令的操作      
    #### CAS 会导致“ABA 问题”
        - 一个线程通过CAS操作将a变为b，然后又变为a，另一个线程CAS操作成功，但是不代表过程中没有问题
        - 解决：使用AtomicReference原子类，在每次修改时，带上一个版本号，每次对比数字和版本号，
        因为每次操作版本号都会随之增加，所以不会出现ABA问题
      
#### 什么是 AQS（抽象的队列同步器）
    - AbstractQueuedSynchronizer抽象的队列式的同步器， AQS 定义了一套多线程访问共享资源的同步器框架
    - 独占式：ReentrantLock
    - 共享式： Semaphore/CountDownLatch/ReentrantReadWriteLock 
    
> Java 异常
#### 异常分类
    - Throwable 是 Java 语言中所有错误或异常的超类。下一层分为 Error 和 Exception
        - Error： java 运行时系统的内部错误和资源耗尽错误
        - Exception 有两个分支，
           1.RuntimeException：NullPointerException、ClassCastException，
           2.CheckedException：IOException、SQLException 
               一般是外部错误，这种异常都发生在编译阶段， Java 编译器会强制程序去捕获此类异常，
               即会出现要求你把这段可能出现异常的程序进行 try catch
#### throw,throws
    1.位置不同：throws 用在函数上，后面跟的是异常类throws Exception，可以跟多个；
        而throw 用在函数内，后面跟的是异常对象throw new NumberFormatException()
    2.功能：throws 用来声明异常，表示出现异常的可能性；throw 抛出具体的问题对象，表示一定发生
    
#### JAVA反射
    - Java 中的反射机制是指在运行状态中，对于任意一个类都能够知道这个类所有的属性和方法；
      并且对于任意一个对象，都能够调用它的任意一个方法
    #### 编译时类型和运行时类型 Person p=new Student();
    - 编译时类型：由声明对象时使用的类型决定。Person
    - 运行时类型：由实际赋值给对象的类型决定。Student
    - 如果编译时根本无法预知该对象和类属于哪些类，程序只能依靠运行时信息来发现该对象
      和类的真实信息，此时就必须使用到反射了
      
    #### API
    #### 获取 Class 对象的 3 种方法
        - 调用某个对象的 getClass()方法 p.getClass();
        - 调用某个类的 class 属性来获取该类对应的 Class 对象  Class clazz=Person.class
        - 使用 Class 类中的 forName()静态方法  Class.forName("类的全路径");
    #### 创建对象的两种方法
        - Class 对象的 newInstance(),要求类有空构造方法
        - 调用 Constructor 对象的 newInstance()
        
#### JAVA 注解
    - Annotation(注解)是一个接口，程序可以通过反射来获取Annotation对象，和注解中的元信息
    - @Target 修饰的对象范围（类,方法，变量）
    - @Retention 定义 被保留的时间长短 SOURCE CLASS RUNTIME
    - @Documented 描述-javadoc
    - @Inherited 阐述了某个被标注的类型是被继承的
    
    - 1.定义注解
    - 2.注解使用
    - 3.注解处理：通过反射获取注解的元信息
    
#### JAVA 内部类
    - 静态内部类：定义在类内部的静态类，就是静态内部类
        - 静态内部类可以访问外部类所有的静态变量和方法，即使是 private 的也一样
        - Java集合类HashMap内部就有一个静态内部类Entry，HashMap 内部维护 Entry 数组用了存放元素，但是 Entry 对使用者是透明的
    - 成员内部类：定义在类内部的非静态类，就是成员内部类。成员内部类不能定义静态方法和变量
    - 局部内部类：定义在方法中的类，就是局部类。如果一个类只在某个方法中使用，则可以考虑使用局部类，不能有static，可以有static final
        - Java非静态内部类中为什么不能有静态变量却可以有常量？
            - java类加载顺序，局部内部类必须在实例外部类后完成，但是静态变量必须在对象创建之前完成，矛盾
    - 匿名内部类：没有名字的内部类，必须继承一个父类或实现一个接口。直接使用 new 来生成一个对象的引用，没有class关键字
        - 1、匿名内部类不能定义任何静态成员、方法
          2、匿名内部类中的方法不能是抽象的
          3、匿名内部类必须实现接口或抽象父类的所有抽象方法
          4、匿名内部类不能定义构造器
          6、内部类可以访问外部类私有变量和方法
          5、匿名内部类访问的外部类成员变量或成员方法必须用static修饰；
          - 局部内部类和匿名内部类只能访问 final 的局部变量？
              - 作用域中变量的生命周期，当外部类的方法结束时，局部变量就会被销毁了，但是内部类对象可能还存在，这里就出现了一个矛盾：内部类对象访问了一个不存在的变量
              - 局部变量实际上会生成副本为内部类的成员变量使用，为了保证数据同步
              - 将局部变量设置为final，保证了内部类的成员变量和外部方法的局部变量的一致性
    
#### JAVA 泛型
    - 泛型提供了编译时类型安全检测机制，该机制允许程序员在编译时检测到非法的类型
    - <? extends T>表示该通配符所代表的类型是 T 类型的子类
    - <? super T>表示该通配符所代表的类型是 T 类型的父类
    
    - 类型擦除：使用泛型的时候加上的类型参数，会被编译器在编译的时候去掉类型信息，生成的字节码中无泛型类型
    
#### JAVA 序列化
    -  保存(持久化)对象及其状态到内存或者磁盘
    - 序列化:将java序列化对象以字节数组保持-静态成员不保存
    
    序列化场景：
       - 持久化
       - 远程方法调用，网络传输
    - 序列化 ID：虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，还有类的序列化 ID 是否一致    
    - Transient ：在变量声明前加上 Transient 关键字，可以阻止该变量被序列化到文件中
    
#### JAVA 复制
    - 直接复制复制：A a1 = a2，实际上复制的是引用，a1 a2指向同一个对象，当a1变化时，a2成员变量也会跟着变化
    - 浅复制（复制引用但不复制引用的对象）：如果字段是值类型的，对字段进行复制，如果是引用类型的，复制引用但是不复制引用对象
    - 深复制（复制对象和其应用对象）：深拷贝不仅复制对象本身，而且复制对象包含的引用指向的所有对象    
 
#### final、finally与finalize的区别
    - final可以用来修饰类（不可继承），方法（不可重写）和变量（不可修改）
    - finally作为异常处理的一部分，try/catch语句执行完后，最终执行，释放资源
        - 当try、catch中有return时，将把返回结果放置进函数栈中，finally中的代码依然会继续执行，但不会影响return的值
        - 当finally中有return时，会直接返回finally中的return结果，并终止程序
    - finalize：当对象被回收的时候调用的方法，例如释放一些资源
#### Object的方法
    - clone
    - hashcode
    - toString
    - equals
    - getclass
    - wait
    - notify
    - notifyAll
    - finalize
#### 基本数据类型
    - byte    1  -2^7～2^7-1
    - short   2  -2^15～2^15-1
    - int     4  -2^31～2^31-1
    - long    8  -2^63～2^63-1
    - float   4
    - double   8
    - boolean  1  true或false
    - char     2  0~2^16-1
    
#### 面向对象
    - 面向对象有三大特性，封装、继承和多态    
    - 封装就是将一类事物的属性和行为抽象成一个类，使其属性私有化，行为公开化，
        提高了数据的隐秘性的同时，使代码模块化。这样做使得代码的复用性更高
    - 继承则是进一步将一类事物共有的属性和行为抽象成一个父类，
        而每一个子类有父类的行为和属性，也有自己特有的行为和属性。
        这样做扩展了已存在的代码块，进一步提高了代码的复用性
    - 多态是指引用对象所指的类型在编译时并不确定，只有在运行时才确定，从而可以将引用绑定到不同实现上，
        提高了接口的扩展性,多态的表现：重写和重载
        - 重写：参数列表和返回值不能改变
        - 重载：参数列表必须改变，返回值可以改变
    
> OOP设计模式    
# 单例 模式 线程安全,延迟初始化(2-1)
    #### 单例模式VS静态类
        1.单例可以继承和被继承，方法可以被override，而静态方法不可以
        2.静态类会在第一次运行时初始化，单例模式可以有其他的选择，即可以延迟加载
        
    - 1. 懒汉模式（线程不安全）(懒加载)
    - 2. 线程安全的懒汉模式（线程安全）同步锁方法 效率低
    - 3. 饿汉模式（线程安全）（无懒加载）
    - 4. 静态类内部加载（线程安全）静态内部类不会在单例加载时就加载，而是在调用getInstance()方法时才进行加载，达到了懒加载的效果，而这种方法又是线程安全的
```java
public class SingletonDemo {
    private static class SingletonHolder{
        private static SingletonDemo instance=new SingletonDemo();
    }
    private SingletonDemo(){
        System.out.println("Singleton has loaded");
    }
    public static SingletonDemo getInstance(){
        return SingletonHolder.instance;
    }
}
```
    - 5. 枚举方法（线程安全）(自由串行化)（懒加载）需要继承的场景不适用
      - 枚举单例可以有效防御两种破坏单例（即使单例产生多个实例）的行为：反射攻击与序列化攻击
      - JDK反射机制内部完全禁止了用反射创建枚举实例的可能性
      
      - 反射攻击：通过反射侵入单例类的私有构造方法并强制执行，使之产生多个不同的实例，这样单例就被破坏了
      - 序列化攻击：如果一个实现了Serializable/Externalizable接口的类可以在运行时实例化，那么就调用newInstance()方法，使用其默认构造方法反射创建新的对象实例，自然也就破坏了单例性
```java
public class User {
    //私有化构造函数
    private User(){ }
 
    //定义一个静态枚举类
    static enum SingletonEnum{
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private User user;
        //私有化枚举的构造函数
        private SingletonEnum(){
            user=new User();
        }
        public User getInstnce(){
            return user;
        }
    }
 
    //对外暴露一个获取User对象的静态方法
    public static User getInstance(){
        return SingletonEnum.INSTANCE.getInstnce();
    }
}

public class Test {
    public static void main(String [] args){
        System.out.println(User.getInstance());
        System.out.println(User.getInstance());
        System.out.println(User.getInstance()==User.getInstance());
    }
}
```
    - 6.双重校验锁法+volatile   线程安全 懒加载
        - 双重校验：第一次是为了提高效率，避免每次都要执行同步代码块，第二次判空，是为了避免多线程带来的不安全
        - 1.为uniqueInstance分配内存空间
          2.初始化uniqueInstance
          3.将uniqueInstance指向分配的内存地址
          t1执行了13，此时t2调用了getInstance()后发现uniqueInstance已经不为空了，
          因此返回uniqueInstance，但是这时uniqueInstance还没有被初始化
          volatile就可以禁止jvm的指令重排，保证在多线程环境下也能正常运行
        - 无法防御反射攻击
```java
public class Singleton{
    private volatile static Singleton singleton = null;
    private Singleton()  {    }
    public static Singleton getInstance()   {
        if (singleton== null)  {
            synchronized (Singleton.class) {
                if (singleton== null)  {
                    singleton= new Singleton();
                }
            }
        }
        return singleton;
    }
}
```
    - 7.使用ThreadLocal实现单例模式（线程安全）
        - ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突,用空间换时间
```java
public class Singleton {
    private static final ThreadLocal<Singleton> tlSingleton =
            new ThreadLocal<Singleton>() {
                @Override
                protected Singleton initialValue() {
                    return new Singleton();
                }
            };
    /**
     * Get the focus finder for this thread.
     */
    public static Singleton getInstance() {
        return tlSingleton.get();
    }
    // enforce thread local access
    private Singleton() {}
}
```
    - 8.使用CAS锁实现（线程安全）
# 工厂抽象工厂(2-2)
      - 简单工厂方法：一个工厂生产所有产品
      - 工厂方法：每个产品都有一个专属工厂，避免工厂类变成超级类（一个工厂生成所有对象）
      - 抽象工厂：同类工厂中抽象出工厂接口，具体工厂实现继承抽象工厂；只能横向扩展同类工厂
# 发布订阅模式(2-2)
      - 在被观察者Subject中注册添加观察者Observer
      - 发送通知时：被观察者调用观察者的update方法，更新通知
# 模板方法(2-1)
    - 定义一个抽象基类，创建一个final类型的算法框架和多个抽象方法
    - 子类继承抽象基类，实现抽象方法；使用的时候只要调用算法框架即可
# 装饰器模式(2-2)
    - 对已经存在的某些类进行装饰，扩展一些功能
    - 包装类和被包装类实现同一个接口，包装类在被包装类的基础上扩展功能，不影响被包装类
    - InputStream作为一个普通类，有多个具体装饰器比如BufferedInputStream、DataInputStream等
# 桥接方法(2-2)
    - 将抽象部分与它的实现部分分离，使抽象部分和具体部分都可以独立地扩展
    - JDBC驱动器；JDBC为所有的关系型数据库提供一个通用的界面。一个应用系统动态地选择一个合适的驱动器，然后通过驱动器向数据库引擎发出指令。这个过程就是将抽象角色的行为委派给实现角色的过程。

#### 生产者消费者模型 (2-1)
    # 阻塞队列
        - 异常：add remove element
        - 返回值：offer poll peek
        - 阻塞：put take
    - 线程池+阻塞队列实现
```java
//生产线程
public class ProductThread extends Thread {
    private int taskNum;
    private ArrayBlockingQueue queue;
    public ProductThread(int taskNum,ArrayBlockingQueue queue) {
        this.taskNum = taskNum;
        this.queue = queue;
    }
    public void run() {
        try {
            //模拟生产
            Thread.currentThread().sleep(5000);
            System.out.println("开始生产");
            queue.add(taskNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//消费者线程
public class ConsumerThread extends Thread {
    private ArrayBlockingQueue queue;
    public ConsumerThread(ArrayBlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        System.out.println("准备消费");
            int taskNum;
            try {
                taskNum = (int) queue.take();
                System.out.println("消费了"+taskNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
    }
}
//主线程
public class ProductAndConsumer {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(20);
        //为多生产者和多消费者分别开创的线程池
        ThreadPoolExecutor productPool = 
                new ThreadPoolExecutor(10,20,60,TimeUnit.MILLISECONDS,new ArrayBlockingQueue(5),new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor consumerPool = 
                new ThreadPoolExecutor(10,20,60,TimeUnit.MILLISECONDS,new ArrayBlockingQueue(5),new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("start");
        for(int i = 0;i<100;i++) {

            productPool.execute(new ProductThread(i,queue));
            consumerPool.execute(new ConsumerThread(queue));
        }
        productPool.shutdown();
        consumerPool.shutdown();
    }
}
```


