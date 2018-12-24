微服务+DDD代码结构例子
======================

1. 这是一个基本的微服务+DDD演示例子: 

    基于 Spring Boot 1.5.6 , Spring Cloud Edgware.SR4 Version
    
    微服务 + DDD，个人觉得应该是首先是从微服务的角度(如何划分微服务)考虑去划分大的业务模块，每一个微服务都应该是一个可以单独部署，各司其职的模块；

    微服务实际开发中，也结合DDD的思想去划分所有属于自己的领域。
    
    微服务的划分与落地，其实也应该是以DDD的思想做去指导的，所以无论我们代码结构如何规划，也并非一成不变，应该从实际出发，去思考划分结构的意义。
    
    此例子是对于微服务+DDD反应到实际开发，代码的结构设计上的一种初步的思考与探索,一个样板工程，不应该成为我们对实际DDD思考与设计的限制，本例仅供参考。
    
    本文博客链接 : [https://www.cnblogs.com/ealenxie/p/9559781.html]
    
2. DDD的结构图: 

    ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830125945668-1072959527.png)

3. 本项目是一个假设已经划分好了业务微服务，设计遵循DDD的架构与角色，代码设计上就分为Infrastructure，Domain，Application，Interfaces，项目结构图如下 : 

    ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830132619533-611437668.png)

4. 结构说明: 

    1. **Infrastructure层**:  
    
        基础实施层，向其他层提供通用的技术能力(比如工具类,第三方库类支持,常用基本配置,数据访问底层实现)，结构如图:
            
        ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830134304547-660094458.png)
        ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830134336916-1945132941.png)
        
    2. **Domain层**:
    
        主要负责表达业务概念,业务状态信息和业务规则；是整个系统的核心层,几乎全部的业务逻辑会在该层实现，结构如图:
        
        ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830134410240-623245752.png)
        ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830134515558-56966635.png)
        
    3. **Application层**: 
        
        相对于领域层,应用层是很薄的一层,应用层定义了软件要完成的任务,要尽量简单。
        
        注: 下图package-info里面所说的对内对外，对程序而言，事实上是从展现层调用应用层，应用层调用领域层，领域层或调用基础实施层。结构如图:
    
        ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830134844172-1295041747.png)
        ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830134819652-762502148.png)

    4.  **Interfaces层**:
    
        负责向用户显示信息和解释用户命令，请求应用层以获取用户所需要展现的数据(比如获取首页的商品数据)。结构如图:

        ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830135806554-1845171786.png)
        ![avatar](https://images2018.cnblogs.com/blog/994599/201808/994599-20180830135840092-1534652017.png)

5. 本文参考内容:
    
    [https://www.cnblogs.com/hafiz/p/9388334.html]
    
    [https://blog.csdn.net/k6T9Q8XKs6iIkZPPIFq/article/details/78909897]
    
    [https://www.cnblogs.com/netfocus/archive/2011/10/10/2204949.html]
    
    [https://blog.csdn.net/bluishglc/article/details/6681253] 

6. 声明:
    
    本文根据自身对微服务和DDD学习和理解，做了一个用SpringCloud搭建的最基本的结构例子。
    
    个人才疏学浅，如有雷同或是不当之处，望各位见谅和帮忙指正。
    
    感谢各位提出意见和支持。
    











