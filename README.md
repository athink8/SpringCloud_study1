## 微服务SpringCloud基础学习及案例

> 我的博客：[https://blog.onfree.cn](https://blog.onfree.cn/)

> 博客原文：[https://blog.onfree.cn/posts/933a0c1f.html](https://blog.onfree.cn/posts/933a0c1f.html)

### 一、常见问题

1. 什么是微服务，微服务架构？

   - 微服务：强调的是服务的大小，它关注的是某一个点，是具体解决某一个问题/提供落地对应服务的一个服务应用，狭意的看，可以看作Ecipse里面的一个个微服务工程/或者Module

   - 微服务架构：是一种架构模式，它提倡将单一应用程序划分成一组小的服务，服务之间互相协调、互相配合，为用户提供最终价值。每个服务运行在其独立的进程中，服务与服务间采用轻量级的通信机制互相协作（通常是基于HTTP协议的RESTfulAPl）。每个服务都围绕着具体业务进行构建，并且能够被独立的部署到生产环境、类生产环境等。另外，应当尽量避免统一的、集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文，选择合适的语言、工具对其进行构建。

2. 微服务的优缺点？

   - `优点`
     每个服务足够内聚，足够小，代码容易理解这样能聚焦一个指定的业务功能或业务需求开发简单、开发效率提高，一个服务可能就是专一的只干一件事。

     微服务能够被小团队单独开发，这个小团队是2到5人的开发人员组成。

     微服务是松耦合的，是有功能意义的服务，无论是在开发阶段或部署阶段都是独立的。

     微服务能使用不同的语言开发。

     易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如Jenkins，Hudson，bamboo。

     微服务易于被一个开发人员理解，修改和维护，这样小团队能够更关注自己的工作成果。无需通过合作才能体现价值。

     微服务允许你利用融合最新技术。

     微服务只是业务逻辑的代码，不会和HTML，CSS或其他界面组件混合。每个微服务都有自己的存储能力，可以有自己的数据库。也可以有统一数据库。

   - `缺点`
     开发人员要处理分布式系统的复杂性

     多服务运维难度，随着服务的增加，运维的压力也在增大系统部署依赖

     服务间通信成

     数据一致性

     系统集成测试

     性能监控..

3. SpringCloud和Dobbo的区别?
   SpringClou的通信机制是基于HTTP的RESTful Api，而Doboo是使用RPC;

<img src="https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/SpringCloud%E5%AF%B9%E4%B8%8Ddobbo.png" alt="image" style="zoom: 67%;" />

3. SpringCloud是什么？

   SpringCloud，基于SpringBoot提供了一套微服务解决方案，括服务注册与发现，配置中心，全链路监控，服务网关，负载均衡，熔断器等组件，除了基于NetFlix的开源组件做高度抽象封装之外，还有一些选型中立的开源组件。

   也就是分布式微服务架构下的一站式解决方案，是各个微服务架构落地技术的集合体，俗称微服务全家桶。

4. SpringCloud和SpringBoot是什么关系？

   SpringBoot专注于快速、方便的开单个微服务个体，SpringCloud关注全局的服务治理框架。

   SpringBoot专注于快速方便的开发单个个体微服务。

   SpringCloud是关注全局的微服务协调整理治理框架，它将SpringBoot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供，配置管理、服务发现、断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等集成服SpringBoot可以离开SpringCloud独立使用开发项目，但是SpringCloud离不开SpringBoot，属于依赖的关系。

5. 微服务技术栈

   ![image](https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%8A%80%E6%9C%AF%E6%A0%881.png)

   ![image](https://cdn.jsdelivr.net/gh/athink8/cdn/imgs/arctle/%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%8A%80%E6%9C%AF%E6%A0%882.png)

### 二、SpringCloud

SpringCloud架构：

![image](https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/SpringCloud%E6%9E%B6%E6%9E%84.png)

> API文档：https://springcloud.cc/spring-cloud-dalston.html

添加依赖：

```xml
 <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.5.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
           <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR3</version>
                <type>pom</type>
                <scope>runtime</scope>
            </dependency>
```

版本选择：

> [https://start.spring.io/actuator/info](https://start.spring.io/actuator/info)

> spring-boot  	2.2.5.RELEASE
>
> spring-cloud 	Hoxton.SR3
>
> netflix-eureka-server	2.2.1.RELEASE
>
> netflix-eureka-client	2.2.1.RELEASE
>
> netflix-ribbon	2.2.2.RELEASE
>
> config		2.2.2.RELEASE
>
> config-server  2.2.2.RELEASE
>
> openfeign 	2.2.2.RELEASE
>
> netflix-hystrix	2.2.2.RELEASE
>
> netflix-zuul		2.2.2.RELEASE		

#### 1. Eureka 服务注册与发现

##### 1.1是什么？

Eureka是Netflix的一个子模块，也是核心模块之一。Eureka是一个基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移。服务注册与发现对于微服务架构来说是非常重要的，有了服务发现与注册，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了。

> Netflix在设计Eureka时遵守的就是AP原则
>
> CAP原则又称CAP定理，指的是在一个分布式系统中，Consistency（一致性）、 Availability（可用性）、Partition tolerance（分区容错性），三者不可兼得

##### 1.2基本架构

Eureka 采用了 C-S 的设计架构。Eureka Server 作为服务注册功能的服务器，它是服务注册中心。

而系统中的其他微服务，使用 Eureka 的客户端连接到 Eureka Server并维持心跳连接。这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。SpringCloud 的一些其他模块（比如Zuul）就可以通过 Eureka Server 来发现系统中的其他微服务，并执行相关的逻辑。

<img src="https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/Eureka%E6%9E%B6%E6%9E%84.png" alt="image" style="zoom:67%;" />

> Eureka Server 提供服务注册和发现
>
> Service Provider服务提供方将自身服务注册到Eureka，从而使服务消费方能够找到
>
> Service Consumer服务消费方从Eureka获取注册服务列表，从而能够消费服务

##### 1.3使用

###### 服务中心

- pom:

  ```xml
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
          </dependency>
  ```

- yaml:

  ```yml
  server:
    port: 7001
  
  eureka:
    instance:
      hostname: localhost
    client:
      register-with-eureka: false #false表示不向注册中心注册自己。
      fetch-registry: false #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
      service-url:
        defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
  ```

- 启动类

  ```
  @EnableEurekaServer
  ```

  

###### 客户端

- pom:

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-config</artifactId>
  </dependency>
  ```

- yml:

  ```yaml
  eureka:
    client:
      service-url:
        defaultZone: http://localhost:7001/eureka/
    instance:
      instance-id: 服务1 #服务名
      prefer-ip-address: true #显示ip
  info: #信息
    app.name: 服务1-8081
    company.name: Athink
    build.artifactId: ${project.artifactId}
    build.version: ${project.version}
  ```

- 父pom:

  ```xml
  #为了显示服务info信息    
  	<build>
          <finalName>${project.artifactId}</finalName>
          <resources>
              <resource>
                  <directory>src/main/resources</directory>
                  <filtering>true</filtering>
              </resource>
          </resources>
          <plugins>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-resources-plugin</artifactId>
                  <configuration>
                      <delimiters>
                          <delimit>$</delimit>
                      </delimiters>
                  </configuration>
              </plugin>
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
              </plugin>
          </plugins>
      </build>
  
  ```

- 启动类

  ```java
  //其实这里测试不用写也通过  真奇怪
  @EnableEurekaClient
  ```

  ##### 1.4服务发现

对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息

- controller：

  ```java
  /*服务发现*/
      @GetMapping("/discovery")
      public Object discovery() {
          List list = eurekaDiscoveryClient.getServices();
          System.out.println("**********" + list);
          List<ServiceInstance> srvList = eurekaDiscoveryClient.getInstances("demo1_provider_8081");
          for (ServiceInstance element : srvList) {
              System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                      + element.getUri());
          }
          return this.eurekaDiscoveryClient;
      }
  ```

- 启动类

  ```java
  @EnableDiscoveryClient
  ```

##### 1.5集群

![image](https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/Eureka%E9%9B%86%E7%BE%A4.png)

###### 基本原理

上图是来自eureka的官方架构图，这是基于集群配置的eureka； 

\- 处于不同节点的eureka通过Replicate进行数据同步 

\- Application Service为服务提供者 

\- Application Client为服务消费者 

\- Make Remote Call完成一次服务调用

服务启动后向Eureka注册，Eureka Server会将注册信息向其他Eureka Server进行同步，当服务消费者要调用服务提供者，则向服务注册中心获取服务提供者地址，然后会将服务提供者地址缓存在本地，下次再调用时，则直接从本地缓存中取，完成一次调用。

当服务注册中心Eureka Server检测到服务提供者因为宕机、网络原因不可用时，则在服务注册中心将服务置为DOWN状态，并把当前服务提供者状态向订阅者发布，订阅过的服务消费者更新本地缓存。

服务提供者在启动后，周期性（默认30秒）向Eureka Server发送心跳，以证明当前服务是可用状态。Eureka Server在一定的时间（默认90秒）未收到客户端的心跳，则认为服务宕机，注销该实例。

###### 搭建使用

- 服务中心pom:

  ```yml
  server:
    port: 7001
  
  eureka:
    instance:
      hostname: eureka1.cn
    client:
      register-with-eureka: false
      fetch-registry: false
      service-url:
        defaultZone: http://eureka2.cn:7002/eureka/,http://eureka3.cn:7003/eureka/
  ```

- 客户端：

  ```yaml
  eureka:
    client:
     service-url:
       defaultZone: http://eureka1.cn:7001/eureka/,http://eureka2.cn:7002/eureka/,http://eureka3.cn:7003/eureka/
  instance:
      instance-id: 服务1
      prefer-ip-address: true #显示ip
  
  info:
    app.name: 服务1-8081
    company.name: Athink
    build.artifactId: ${project.artifactId}
    build.version: ${project.version}
  ```
  

> `注意 ： 配置里需要加/eureka/，浏览器不加才可以访问！`

#### 2.Ribbon 负载均衡

##### 2.1是什么?

Spring Cloud Ribbon是基于Netflix Ribbon实现的一套  **客户端** 、**负载均衡**  的工具。

简单的说，Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将Netflix的中间层服务连接在一起。Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等。简单的说，就是在配置文件中列出Load Balancer（简称LB）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等）去连接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法。

##### 2.2负载均衡LB介绍

> [官网资料](https://github.com/Netflix/ribbon/wiki/Getting-Started)

LB，即负载均衡(Load Balance)，在微服务或分布式集群中经常用的一种应用。

负载均衡简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA。

常见的负载均衡有软件Nginx，LVS，硬件 F5等。

相应的在中间件，例如：dubbo和SpringCloud中均给我们提供了负载均衡，SpringCloud的负载均衡算法可以自定义。

---

- 集中式LB

  即在服务的消费方和提供方之间使用独立的LB设施(可以是硬件，如F5, 也可以是软件，如nginx), 由该设施负责把访问请求通过某种策略转发至服务的提供方；

- 进程内LB

  将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。

  Ribbon就属于进程内LB，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址。

##### 2.3初步配置

- 客户端80

  - pom:

    ```xml
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-config</artifactId>
            </dependency>
    ```

  - yml:

    ```yml
    server:
      port: 80
    
    eureka:
      client:
        register-with-eureka: false
        service-url:
          defaultZone: http://eureka1.cn:7001/eureka/,http://eureka2.cn:7002/eureka/,http://eureka3.cn:7003/eureka/
    
    ```

  - beanConfig:

    ```java
    @Configuration
    public class beanConfig {
    
        @LoadBalanced //添加
        @Bean
        public RestTemplate getRestTemplate()
        {
            return new RestTemplate();
        }
    
    }
    ```

  - 启动类：

    ```java
    @EnableEurekaClient //添加
    ```

  - controller

    ```java
     //地址修改为 微服务名 
    //注意 微服务名 为spring: application：name: xxx
    //微服务名 都为大写  小写会出错
    //微服务名 不可包含 _
     private static final String REST_URL_PREFIX = "http://DEMO1PROVIDER8081"+"/user";
    ```

##### 2.4 Ribbon 负载均衡

<img src="https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/Ribbon%E8%B4%9F%E8%BD%BD%E5%9D%87%E8%A1%A1%E6%9E%B6%E6%9E%84.png" alt="image" style="zoom: 67%;" />

> 同个微服务可以写多个，只要spring: application：name: xxx 相同就行，每个服务都可单独的连接自己的数据库
>
> 客户端调用的时候，只会通过服务名调用，由客户端的Ribbon负责按照的设定的负载规则去执行，默认轮询。

> 总结：Ribbon其实就是一个软负载均衡的客户端组件，
>
> 他可以和其他所需请求的客户端结合使用，和eureka结合只是其中的一个实例。

##### 2.5Ribbon核心组件IRule

![image](https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/Ribbon%E7%9A%84IRule.jpg)

> IRule：根据特定算法规则从服务列表中选取一个要访问的服务

```yml
#轮询
RoundRobinRule

#随机
RandomRule

#会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务,还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问
AvailabilityFilteringRule	

#根据平均响应时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高。
刚启动时如果统计信息不足，则使用RoundRobinRule策略，等统计信息足够，
会切换到WeightedResponseTimeRule
WeightedResponseTimeRule

#先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务
RetryRule

#会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
BestAvailableRule

#默认规则,复合判断server所在区域的性能和server的可用性选择服务器
ZoneAvoidanceRule
```

##### 2.6自定义负载规则

> 以下均在客户端80上执行

> 官方文档明确给出了警告：
>
> 这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
>
> 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是说
>
> 我们达不到特殊化定制的目的了。

###### 简单定义

- 添加类MyRibbonRule

  ```java
  @Configuration
  public class MyRibbonRule {
      @Bean
      public IRule myRule() {
          return new RandomRule();//Ribbon默认是轮询，我自定义为随机
      }
  }
  ```

- 启动类

  ```java
  @SpringBootApplication
  @EnableEurekaClient
  //只指定服务生效 name为服务名 configuration为自定义规则类
  @RibbonClient(name = "DEMO1PROVIDER1",configuration = MyRibbonRule.class) 
  
  //所有服务生效
  //@RibbonClients(defaultConfiguration = MyRibbonRule.class)
  public class Demo1Consumer80Application {
  
      public static void main(String[] args) {
          SpringApplication.run(Demo1Consumer80Application.class, args);
      }
  
  }
  ```

###### 自定义规则算法

> 需要继承 AbstractLoadBalancerRule 并实现方法

- 添加类MyRule1

  ```JAVA
  /**
   * author: jz
   * Time: 2020/3/17 23:12
   * 每个服务访问4次，轮询下一个服务
   **/
  public class MyRule1 extends AbstractLoadBalancerRule {
      private int sum = 0;    //总共被调用的次数，目前要求每台被调用3次
      private int currentIndex = 0;//当前提供服务的机器号
  
      public MyRule1() {
      }
  
      @Override
      public void initWithNiwsConfig(IClientConfig iClientConfig) {
  
      }
  
      @Override
      public Server choose(Object key) {
          return this.choose(this.getLoadBalancer(), key);
      }
  
      public Server choose(ILoadBalancer lb, Object key) {
          if (lb == null) {
              return null;
          } else {
              Server server = null;
  
              while (server == null) {
                  if (Thread.interrupted()) {
                      return null;
                  }
  
                  List<Server> upList = lb.getReachableServers();
                  List<Server> allList = lb.getAllServers();
                  int serverCount = allList.size();
                  if (serverCount == 0) {
                      return null;
                  }
  
                  int index = this.chooseInt(serverCount);
                  server = (Server) upList.get(index);
                  if (server == null) {
                      Thread.yield();
                  } else {
                      if (server.isAlive()) {
                          return server;
                      }
  
                      server = null;
                      Thread.yield();
                  }
              }
  
              return server;
          }
      }
  
      /*选择算法*/
      public int chooseInt(int serverCount) {
          if(currentIndex>=0){
              sum++;
          }
          if(sum>=4){
              sum=0;
              currentIndex++;
          }
          if(currentIndex>=serverCount){
              sum=0;
              currentIndex=0;
          }
          return currentIndex;
      }
  }
  ```

- 添加类MyRibbonRule

  ```JAVA
  @Configuration
  public class MyRibbonRule {
      @Bean
      public IRule myRuleConfig() {
          return new MyRule1();
      }
  }
  ```

- 启动类

  ```JAVA
  @SpringBootApplication
  @EnableEurekaClient
  //只指定服务生效 name为服务名 configuration为自定义规则类
  @RibbonClient(name = "DEMO1PROVIDER1",configuration = MyRibbonRule.class) 
  
  //所有服务生效
  //@RibbonClients(defaultConfiguration = MyRibbonRule.class)
  public class Demo1Consumer80Application {
  
      public static void main(String[] args) {
          SpringApplication.run(Demo1Consumer80Application.class, args);
      }
  
  }
  ```

#### 3.openFeign负载均衡

##### 3.1是什么？

> [官网](https://projects.spring.io/spring-cloud/spring-cloud.html#spring-cloud-feign)

Feign是一个声明式WebService客户端。使用Feign能让编写Web Service客户端更加简单, 它的使用方法是定义一个接口，然后在上面添加注解，同时也支持JAX-RS标准的注解。Feign也支持可拔插式的编码器和解码器。Spring Cloud对Feign进行了封装，使其支持了Spring MVC标准注解和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡。

Feign是一个声明式的Web服务客户端，使得编写Web服务客户端变得非常容易，只需要创建一个接口，然后在上面添加注解即可。

##### 3.2能干什么

Feign旨在使编写Java Http客户端变得更容易。

前面在使用Ribbon+RestTemplate时，利用RestTemplate对http请求的封装处理，形成了一套模版化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。在Feign的实现下，我们只需创建一个接口并使用注解的方式来配置它(以前是Dao接口上面标注Mapper注解,现在是一个微服务接口上面标注一个Feign注解即可)，即可完成对服务提供方的接口绑定，简化了使用Spring cloud Ribbon时，自动封装服务调用客户端的开发量。

**Feign集成了Ribbon**

利用Ribbon维护了MicroServiceCloud-Dept的服务列表信息，并且通过轮询实现了客户端的负载均衡。而与Ribbon不同的是，通过feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用

##### 3.2配置使用

- pom

  ```xml
          <!--feign-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-openfeign</artifactId>
          </dependency>
         
  		<!--ribbon-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-config</artifactId>
          </dependency>
  ```

- yml

  ```yml
  server:
    port: 80
  
  eureka:
    client:
     register-with-eureka: false
     service-url:
      defaultZone: http://eureka1.cn:7001/eureka/,http://eureka2.cn:7002/eureka/,http://eureka3.cn:7003/eureka/
  #   enabled: true
  
  ```

- 创建接口

  ```java
  /**
   * author: jz
   * Time: 2020/3/18 13:30
   **/
  //注意这里绑定的是微服务实例
  //@RequestMapping("/user") 注意前面带链接不能这么写，应写 path = "/user"
  @FeignClient(value = "DEMO1PROVIDER1"，path = "/user") //请求的服务名
  public interface UserClientService {
  
      @PostMapping("/")
      public boolean addUser(@RequestBody User user);
  
      @GetMapping("/id/{id}")
      public User findById(@PathVariable("id") Integer id);
  
      @GetMapping("/username/{username}")
      public User findByUsername(@PathVariable("username") String username);
  
      @GetMapping("/email/{email}")
      public User findByEmail(@PathVariable("email") String email);
  
      @GetMapping("/phone/{phone}")
      public User findByPhone(@PathVariable("phone") String phone);
  
      @GetMapping("/all")
      public List<User> findAll();
  
      /*服务发现*/
      @GetMapping("/discovery")
      public Object discovery();
  }
  ```

- controller

  ```java
      @Autowired(required = false)
      UserClientService userClientService;
  ```

- 启动类

  ```java
  @EnableEurekaClient
  @EnableDiscoveryClient
  //扫描的是标记有@FeignClient(value = "xx")的类包
  //如果是同一个服务模块这可以省略参数
  @EnableFeignClients(basePackages = {"demo1_api.FeignClientService"})
  ```

##### 3.3总结

 Feign通过接口的方法调用Rest服务（之前是Ribbon+RestTemplate），

该请求发送给Eureka服务器（http://DEMO1PROVIDER1/user/all）,

通过Feign直接找到服务接口，由于在进行服务调用的时候融合了Ribbon技术，所以也支持负载均衡作用。

#### 4.Hystrix断路器

##### 4.1是什么？

Hystrix也是Netflix套件的一部分

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

“断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

<img src="https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/HystrixFallback.png" alt="image" style="zoom:80%;" />

---

##### 4.2能干嘛

- 服务熔断
- 服务降级
- 服务限流
- 接近实时的监控

##### 4.3服务熔断

- pom

  ```xml
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
          </dependency>
  ```

- controller

  ```java
      @HystrixCommand(fallbackMethod = "fallbackMethod")
      @GetMapping("/id/{id}")
      public User findById(@PathVariable("id") Integer id) {
         
          return userService.findById(id);
      }
  
      /*服务熔断返回方法*/
      public User fallbackMethod(@PathVariable("id") Integer id) {
          return new User().setName("服务错误！");
      }
  ```

  > 注意服务熔断返回方法必须和监控的方法的参数和返回值一致
  >
  > 否则会抛出 `fallback method wasn't found: defaultFallback`

- yml

  ```yaml
  hystrix: 
    command: 
      HelloClient	#toHello()://这个不配默认是全局
        execution: 
          isolation:
            thread:
              timeoutInMilliseconds: 500 #线程超时,调用Fallback方法
        circuitBreaker:
          requestVolumeThreshold: 3  #10秒内出现3个以上请求(已临近阀值),并且出错率在50%以上,开启断路器.
  ```

  

- 启动类

  ```java
  @EnableCircuitBreaker //开启服务熔点
  ```

---

附录：@HystrixCommand**

```java
     public @interface HystrixCommand {

            // HystrixCommand 命令所属的组的名称：默认注解方法类的名称
            String groupKey() default "";

            // HystrixCommand 命令的key值，默认值为注解方法的名称
            String commandKey() default "";

            // 线程池名称，默认定义为groupKey
            String threadPoolKey() default "";
            // 定义回退方法的名称, 此方法必须和hystrix的执行方法在相同类中
            String fallbackMethod() default "";
            // 配置hystrix命令的参数
            HystrixProperty[] commandProperties() default {};
            // 配置hystrix依赖的线程池的参数
             HystrixProperty[] threadPoolProperties() default {};

            // 如果hystrix方法抛出的异常包括RUNTIME_EXCEPTION，则会被封装HystrixRuntimeException异常。我们也可以通过此方法定义哪些需要忽略的异常
            Class<? extends Throwable>[] ignoreExceptions() default {};

            // 定义执行hystrix observable的命令的模式，类型详细见ObservableExecutionMode
            ObservableExecutionMode observableExecutionMode() default ObservableExecutionMode.EAGER;

            // 如果hystrix方法抛出的异常包括RUNTIME_EXCEPTION，则会被封装HystrixRuntimeException异常。此方法定义需要抛出的异常
            HystrixException[] raiseHystrixExceptions() default {};

            // 定义回调方法：但是defaultFallback不能传入参数，返回参数和hystrix的命令兼容
            String defaultFallback() default "";
        }
```



##### 4.4服务降级(配合Feign)

> @EnableFeignClients中已经默认打开了断路器功能，所以这里的启动类上不需要再加@EnableCircuitBreaker注解
>
> 只需要在@FeignClient中为fallback参数指定fallback方法

- pom

  ```xml
          <!--feign-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-openfeign</artifactId>
          </dependency>
          
          <!--ribbon-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-config</artifactId>
          </dependency>
          
          <!--hystrix-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
          </dependency>
  ```

- yml

  ```yaml
  #开启feign对hystrix的支持
  feign:
    hystrix:
      enabled: true
  ```

- service

  ```java
  //接口类型1
  @FeignClient(value = "DEMO1PROVIDER1",path = "/user",fallback = Hystrixfallback.class)
  
  //接口类型2
  //@FeignClient(value = "DEMO1PROVIDER1",path = "/user",fallbackFactory = Hystrixfallback.class)
  
  //注意这里绑定的是微服务实例
  public interface UserClientService {
  ```

- 创建接口Hystrixfallback

  - 第一种：继承服务的接口

    ```java
    @Component //必须  添加到容器
    public class Hystrixfallback implements UserClientService {
    ```

  - 第二种：继承FallbackFactory<xx>的接口

    ```java
    @Component
    public class Hystrixfallback2 implements FallbackFactory<UserClientService> {
    
        @Override
        public UserClientService create(Throwable throwable) {
            return new UserClientService() {}
     	}
     }
    ```

##### 4.5服务监控Hystrix Dashboard

###### - 服务方

- pom

  ```xml
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-actuator</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
          </dependency>
  ```

- controller

  > 注意  必须添加熔断 才能被监控 
  >
  > 熔断方法太多时很麻烦 这问题 还没理解如何解决?

  ```java
      @HystrixCommand(fallbackMethod = "fallbackAll")
      @GetMapping("/all")
      public List<User> findAll() {
          return userService.findAll();
      }
      
      public List<User> fallbackAll() {
          List<User> users = new ArrayList<>();
          users.add(new User().setName("LIST 好烦"));
          return users;
      }
  ```

- 启动类

  ```JAVA
  @EnableCircuitBreaker
  ```

- 注入bean

  ```Java
  @Configuration
  public class beanConfig {
  
      //这个bean主要是解决/hystrix.stream视图无法打开的问题；
      @Bean
      public ServletRegistrationBean getServlet() {
          HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
          ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
          registrationBean.setLoadOnStartup(1);
          registrationBean.addUrlMappings("/hystrix.stream");
          registrationBean.setName("HystrixMetricsStreamServlet");
          return registrationBean;
      }
  }
  ```

###### - Dashboard面板

- pom

  ```xml
           <!--hystrix -->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
          </dependency>
  ```

- 启动类

  ```java
  @EnableHystrixDashboard
  ```

###### - 面板解释

<img src="https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/HystrixDashboard.png" alt="image" style="zoom:67%;" />

实心圆：共有两种含义。它通过颜色的变化代表了实例的健康程度，它的健康度从绿色，该实心圆除了颜色的变化之外，它的大小也会根据实例的请求流量发生变化，流量越大该实心圆就越大。所以通过该实心圆的展示，就可以在大量的实例中快速的发现故障实例和高压力实例。

曲线：用来记录x分钟内流量的相对变化，可以通过它来观察到流量的上升和下降趋势。

#### 5.zuul路由网关

##### 5.1是什么？

Zuul作为微服务系统的网关组件，是从设备和网站到Netflix流应用程序后端的所有请求的前门。作为边缘服务应用程序，Zuul旨在实现动态路由，监控，弹性和安全性。

> 注意：Zuul服务最终还是会注册进Eureka

##### 5.2能干什么?

Zuul主要功能时API Gateway(api网关)

- 身份认证
- 审查与监控
- 压力测试
- 金丝雀测试
- 动态路由
- 服务迁移
- 负载分配
- 安全
- 静态响应处理
- 主动/主动流量管理

##### 5.3作用

`zuul`是一个网关和负载均衡器,在通过`ribbon`或者`feign`实现了客户端负载均衡之后,`zuul`在服务端实现负载均衡。`zuul`支持用任何`JVM`语言来编写规则和过滤条件。

服务网关是微服务架构中不可或缺的部分。通过服务网关统一向外系统提供`REST API`的过程中，除了具备服务路由、均衡负载功能之外，它还具备了权限控制等功能。

 `Spring Cloud Netflix`中的`Zuul`就担任了这样的一个角色，为微服务架构提供了前门保护的作用，同时将权限控制这些较重的非业务逻辑内容迁移到服务路由层面，使得服务集群主体能够具备更高的可复用性和可测试性。

![image](https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/zuul%E4%BD%9C%E7%94%A8.jpg)

##### 5.3动态路由

- pom

  ```xml
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
          </dependency>
  ```

- yml

  ```yaml
  server:
    port: 9999
  
  spring:
    application:
      name: springcloud-zuul
  
  eureka:
    client:
      service-url:
        defaultZone: http://eureka1.cn:7001/eureka/,http://eureka2.cn:7002/eureka/,http://eureka3.cn:7003/eureka/
  
  zuul:
    ignored-services: "*"
    #ribbonIsolationStrategy: THREAD
    strip-prefix: false
    sensitive-headers: Access-Control-Allow-Origin
    #ignored-headers: Access-Control-Allow-Origin,H-APP-Id,APPToken
    #strip-prefix: true #全局去掉前缀
    #sensitive-headers: true
    #MyZuulFilter: #过滤类名
      #pre: #过滤类型
        #disable: true
    routes:
      demo1provider1: # 标识 随意写
        path: /p1/**  #访问路径
        serviceId: demo1provider1 #服务名
      demo1_consumer_feign:
        serviceId: consumerfeign
        path: /c1/**
      a:
        path: /a/**
        #stripPrefix: true  #当前去掉前缀
      host:
        connect-timeout-millis: 60000
        socket-timeout-millis: 60000
  
  
  hystrix:
    command:
      default:
        execution:
          isolation:
            thread:
              timeoutInMilliseconds: 6000
  #负载均衡
  ribbon:
    ConnectionTimeout: 500
    ReadTimeout: 2000
  ```

- 主类

  ```JAVA
  @EnableEurekaClient
  @EnableZuulProxy
  ```

##### 5.4自定义过滤器

> 在实现自定义过滤器时，过滤生效

- 添加类MyZuulFilter

  ```java
  package demo1_zuul.config;
        
  /**
   * 网关认证过滤器（Demo演示，实际根据自身业务考虑实现）
   *
   * @author jz
   * @date 2020-03-20.
   */
  @Component
  public class MyZuulFilter extends ZuulFilter {
  
      /**
       * per：路由之前，如实现认证、记录调试信息等
       * routing：路由时
       * post：路由后，比如添加HTTP Header
       * error：发生错误时调用
       */
      @Override
      public String filterType() {
          return "pre";
      }
  
      /**
       * 过滤器顺序，类似@Filter中的order
       */
      @Override
      public int filterOrder() {
          return 20;
      }
  
      /**
       * 这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
       */
      @Override
      public boolean shouldFilter() {
          return true;
      }
  
      /**
       * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
       */
      @Override
      public Object run() {
          RequestContext requestContext = RequestContext.getCurrentContext();
          HttpServletRequest request = requestContext.getRequest();
  
          System.out.println("getServerName "+request.getServerName()); //localhost
          System.out.println("getRequestURL "+request.getRequestURL());
          //http://localhost:9999/p1/user/all
          System.out.println("getRequestURI "+request.getRequestURI()); //p1/user/all
          System.out.println("getServerPort "+request.getServerPort()); //9999
          System.out.println("authToken "+request.getHeader("authToken")); //获取请求头信息
  
          String token = request.getParameter("token");
          if (token == null) {
              requestContext.setSendZuulResponse(false);
              requestContext.setResponseStatusCode(404);
              requestContext.setResponseBody("token cannot be empty");
          }
          requestContext.setSendZuulResponse(true);
          return null;
      }
  }
  ```

##### 5.5添加熔断

> 在实现添加熔断时，熔断生效 

- 添加类MyFallbackProvider

  ```java
  package demo1_zuul.config;
  
  @Component
  public class MyFallbackProvider implements FallbackProvider {
  
      /**
       * getRoute方法的返回值就是要监听的挂掉的微服务的名字
       * 如果需要所有服务都走这个熔断回退，则return "*" 或 return null
       *
       * @return
       */
      @Override
      public String getRoute() {
          return "*";
      }
  
      /**
       * 当服务无法执行的时候，返回托底信息
       *
       * @param route
       * @param cause
       * @return
       */
      @Override
      public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
          return new ClientHttpResponse() {
  
              /**
               * ClientHttpResponse的fallback的状态码
               * @return
               * @throws IOException
               */
              @Override
              public HttpStatus getStatusCode() throws IOException {
                  return HttpStatus.OK;
              }
  
              /**
               * ClientHttpResponse的fallback的状态码
               * @return
               * @throws IOException
               */
              @Override
              public int getRawStatusCode() throws IOException {
                  return this.getStatusCode().value();
              }
  
              /**
               * ClientHttpResponse的fallback的状态码
               * @return
               * @throws IOException
               */
              @Override
              public String getStatusText() throws IOException {
                  return this.getStatusCode().getReasonPhrase();
              }
  
              /**
               * Close this response, freeing any resources created.
               */
              @Override
              public void close() {
  
              }
  
              /**
               * 设置响应体
               * @return
               * @throws IOException
               */
              @Override
              public InputStream getBody() throws IOException {
                  String content = "该服务不可用!!";
                  return new ByteArrayInputStream(content.getBytes());
              }
  
              /**
               * 设置响应头信息
               * @return
               */
              @Override
              public HttpHeaders getHeaders() {
                  HttpHeaders headers = new HttpHeaders();
                  MediaType mt = new MediaType("application", "json", Charset.forName("utf-8"));
                  headers.setContentType(mt);
  
                  return headers;
              }
          };
      }
  }
  ```

#### 6.SpringCloud Config分布式配置中心

##### 6.1是什么？

SpringCloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。

##### 6.2怎么玩

SpringCloud Config分为服务端和客户端两部分。

服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密/解密信息等访问接口

客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。

由于SpringCloud Config默认使用Git来存储配置文件(也有其它方式,比如支持SVN和本地文件)，

但最推荐的还是Git，而且使用的是http/https访问的形式

##### 6.3使用

###### -服务端

- pom

  ```xml
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-actuator</artifactId>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-config-server</artifactId>
          </dependency>
  ```

- yml

  ```yaml
  spring:
    #profiles:
      #active: native #开启本地配置文件
    application:
      name: demo1config
    cloud:
      config:
        label: master # 配置仓库的分支
        server:
          git:
            # 配置git仓库地址
            #uri: https://github.com/athink8/SpringCloud-Config.git
            uri: https://e.coding.net/athink8/SpringCloud-Config-coding.git
            # 配置仓库路下的相对路径.可以,配置多个
            #search-paths: /
            username: athink8@163.com # 访问git仓库的用户名
            password:  # 访问git仓库的密码
          #native:  #本地配置文件
            #search-locations: file:///E:\yuyan\java\spring\other\SpringCloud\SpringCloud-Config\SpringCloud-Config
  
  server:
    port: 9200
  
  eureka:
    client:
      service-url:
        defaultZone: http://eureka1.cn:7001/eureka/,http://eureka2.cn:7002/eureka/,http://eureka3.cn:7003/eureka/
    instance:
      instance-id: config9200
      prefer-ip-address: true #显示ip
  ```

- 主类

  ```java
  @EnableConfigServer
  @EnableEurekaClient
  @EnableDiscoveryClient
  ```

- 测试

  ```yaml
  http://localhost:9200/application-dev.yml #前提时你的git仓库有这个文件profile
  ```

  > 访问测试：
  >
  > / {application} / {profile} [/ {label}]
  > /{application}-{profile}.yml
  > /{label}/{application}-{profile}.yml
  > /{application}-{profile}.properties
  > /{label}/{application}-{profile}.properties

###### -客户端

- pom

  ```xml
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-actuator</artifactId>
          </dependency>
  
          <dependency><!--config客户端-->
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-config</artifactId>
          </dependency>
          <dependency><!--eureka客户端-->
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          </dependency>
          <dependency><!--hystrix熔断器-->
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
          </dependency>
  ```

- git上传文件demo1_provider_1.yml

  ```yaml
  spring:
    profiles:
      active:
      - dev
      - demo1provider1_mysql_1
  ---
  #server:
    #port: 8081
  spring:
    profiles: dev
    application:
      name: demo1provider1
    #数据源基本配置
    datasource:
      driver-class-name: com.mysql.jdbc.Driver
      type: com.alibaba.druid.pool.DruidDataSource
      #数据源其他配置
      initialSize: 5
      minIdle: 5
      maxActive: 20
      maxWait: 60000
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      poolPreparedStatements: true
      #配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
      filters: stat,wall
      maxPoolPreparedStatementPerConnectionSize: 20
      useGlobalDataSourceStat: true
      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
  
  mybatis:
    type-aliases-package: demo1_api.entities    # 所有Entity别名类所在包
    config-location: classpath:mybatis/mybatis-config.xml #指定全局配置文件的位置
    mapper-locations: classpath:mybatis/mapper/*.xml  #指定sql映射文件的位置
  
  #实现服务集群能够连接不同的数据库
  ---
  spring:
    profiles: demo1provider1_mysql_1
    datasource:
      username: root
      password: 123456
      url: jdbc:mysql:///athink_all?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
  ---
  spring:
    profiles: demo1provider1_mysql_2
    datasource:
      username: root
      password: 123456
      url: jdbc:mysql:///test1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
  ---
  spring:
    profiles: demo1provider1_mysql_3
    datasource:
      username: root
      password: 123456
      url: jdbc:mysql:///test2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
  ```

- bootstrap.yml配置

  > 注意，需要把config调用的信息保存到bootstrap.yml,而不是application.yml
  >
  > bootstrap.yml 优先级> application.yml，会启用bootstrap.yml

  ```yaml
  server:
    port: 8081
  spring:
    application:
      name: demo1provider1
    cloud:
      config:
        label: master # 配置仓库的分支
        #uri: http://localhost:9200 #直接域名访问config中心
        name: demo1_provider_1 #需要从github上读取的资源名称，注意没有yml后缀名
        profile: dev,demo1provider1_mysql_1 #配置类型
        discovery:
          enabled: true #开启服务发现
          service-id: demo1config #从eureka中寻找出config中心服务
        #fail-fast: true
  
  #eureka配置应放置在bootstrap.yml里
  eureka:
    client:
      service-url:
        defaultZone: http://eureka1.cn:7001/eureka/,http://eureka2.cn:7002/eureka/,http://eureka3.cn:7003/eureka/
    instance:
      instance-id: 服务1-8081
      prefer-ip-address: true #显示ip
  info:
    app.name: demo1provider1
    company.name: Athink
    build.artifactId: ${project.artifactId}
    build.version: ${project.version}
  ```

- 主类

  ```java
  @EnableEurekaClient
  @EnableDiscoveryClient
  @EnableCircuitBreaker
  ```

#### 7.SpringCloud Sleuth和Zipkin链路跟踪

##### 7.1各是什么？

- Spring Cloud Sleuth

  Spring Cloud Sleuth为服务之间调用提供链路追踪。通过Sleuth可以很清楚的了解到一个服务请求经过了哪些服务，每个服务处理花费了多长。从而让我们可以很方便的理清各微服务间的调用关系。此外Sleuth可以帮助我们：

  - 耗时分析: 通过Sleuth可以很方便的了解到每个采样请求的耗时，从而分析出哪些服务调用比较耗时;
  - 可视化错误: 对于程序未捕捉的异常，可以通过集成Zipkin服务界面上看到;
  - 链路优化: 对于调用比较频繁的服务，可以针对这些服务实施一些优化措施。

  spring cloud sleuth可以结合zipkin，将信息发送到zipkin，利用zipkin的存储来存储信息，利用zipkin ui来展示数据。

![Spring Cloud Sleuth概念图](https://cdn.jsdelivr.net/gh/athink8/cdn@master/imgs/arctle/spring%20cloud%20sleuth.png)

- Zipkin 

  Zipkin是一个开放源代码分布式的跟踪系统，由Twitter公司开源，它致力于收集服务的定时数据，以解决微服务架构中的延迟问题，包括数据的收集、存储、查找和展现。

  每个服务向zipkin报告计时数据，zipkin会根据调用关系通过Zipkin UI生成依赖关系图，显示了多少跟踪请求通过每个服务，该系统让开发者可通过一个 Web 前端轻松的收集和分析数据，例如用户每次请求服务的处理时间等，可方便的监测系统中存在的瓶颈。

  Zipkin提供了可插拔数据存储方式：In-Memory、MySql、Cassandra以及Elasticsearch。接下来的测试为方便直接采用In-Memory方式进行存储，生产推荐Elasticsearch。

##### 7.2使用

> 好像已启用，那也好像没啥学习的必要了..

---

未完待续..

