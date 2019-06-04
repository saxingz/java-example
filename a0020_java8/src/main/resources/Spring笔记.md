## 1. Spring Boot pom文件里引入spring boot两个方法
```bash
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.2.0.M3</version>
	<relativePath/> <!-- lookup parent from repository -->
</parent>

<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
	</plugins>
</build>
```
或
```bash
<modelVersion>4.0.0</modelVersion>

<groupId>org.saxing</groupId>
<artifactId>test</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>test</name>
<description>Demo project for Spring Boot</description>

<properties>
	<java.version>1.8</java.version>
</properties>

<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-parent</artifactId>
			<version>2.2.0.M3</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>

<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
			<version>2.2.0.M3</version>
			<executions>
				<execution>
					<goals>
						<goal>repackage</goal>
					</goals>
				</execution>
			</executions>
		</plugin>
	</plugins>
</build>
```

## 2. 数据源方面

DataSourceAutoConfiguration 配置 DataSource;

DataSourceTransactionManagerAutoConfiguration 配置 DataSourceTransactionManager;

JdbcTemplateAutoConfiguration 配置 JdbcTemplate;

符合条件时才进行配置。

多数据源时，可以配置Spring Boot自身的@Primary注解，
也可以排除Spring Boot的自动配置。
```java
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class})
@Slf4j
public class MultiDataSourceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDataSourceDemoApplication.class, args);
	}

	@Bean
	@ConfigurationProperties("foo.datasource")
	public DataSourceProperties fooDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource fooDataSource() {
		DataSourceProperties dataSourceProperties = fooDataSourceProperties();
		log.info("foo datasource: {}", dataSourceProperties.getUrl());
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
		return new DataSourceTransactionManager(fooDataSource);
	}

	@Bean
	@ConfigurationProperties("bar.datasource")
	public DataSourceProperties barDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource barDataSource() {
		DataSourceProperties dataSourceProperties = barDataSourceProperties();
		log.info("bar datasource: {}", dataSourceProperties.getUrl());
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager barTxManager(DataSource barDataSource) {
		return new DataSourceTransactionManager(barDataSource);
	}
}
```
	
### HikariCP为什么这么快？
- 字节码级别优化，很多方法用JavaAssist生成
- 大量小改进
	- 用FastStatementList代替ArrayList
	- 无锁集合ConcurrentBag(.net概念)
	- 代理类的优化（如，用invokestatic 代替了invokevirtual）
Spring Boot2.x默认使用HikariCP，Spring Boot1.x默认使用Tomcat连接池

日志打印依赖三方：p6spy

### Druid(druid-spring-boot-starter):
经过阿里巴巴各大系统考验
- 详细全面监控
- ExceptionSorter 针对主流数据库的返回码都有支持
- SQL防注入，如防止删除，防止drop table
- 内置加密配置
- 众多扩展点，方便定制，如语句执行前后的操作，继承FilterEventAdapter， 修改META-INF/druid-filter.properties增加filtera配置

一些注意点：
- 没有特殊情况，不要在生产环境打开监控的Servlet，不安全，负载均衡导致看到的也不一定及时
- 没有连接泄露可能的情况下，不开启removeAbandoned，影响性能较大
- testXxx 的使用要注意
- 务必配置合理的超时时间

选择连接池考量点：可靠性，性能，功能，可运维性，可扩展性，其他（社区，作者等）
	
### 淘宝TDDL， ShardingSphere等数据库中间件
功能如 对应用程序来说，一个datasource，内部处理为多个数据库。
## 3. Spring 事务抽象
	
### 一致的事务模型，核心接口：
- PlatformTransactionManager
	- DataSourceTransactionManager
	- HibernateTransactionManager
	- JtaTransactionManager
- TransactionDefinition
	- Propagation
	- Isolation
	- Timeout
	- Read-only status

### 事务的传播特性7个，文档里有4个，代码里实际上有7个
```bash
propagation_requierd：0 默认。 如果当前没有事务，就新建一个事务，如果已存在一个事务中，加入到这个事务中，这是最常见的选择。
propagation_supports：1 支持当前事务，如果没有当前事务，就以非事务方法执行。
propagation_mandatory：2 使用当前事务，如果没有当前事务，就抛出异常。
propagation_required_new：3新建事务，如果当前存在事务，把当前事务挂起。
propagation_not_supported：4以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
propagation_never：5 以非事务方式执行操作，如果当前事务存在则抛出异常。
propagation_nested：6 如果当前存在事务，则在嵌套事务内执行，里面的事务回滚不影响外面的事务。如果当前没有事务，则执行与propagation_required类似的操作
```

### 事务隔离级别, Spring默认值为-1， 即完全取决于数据库。
![file](http://www.saxing.cn/upload/2019/4/saxing_2019-05-21_23-13-3820190521231437332.png)

### 编程式事务
- TransactionTemplate（建议）
	- TransactionCallback
	- TransactionCallbackWithoutResult
- PlatformTransactionManager
	- 可以传入TransactionDefinition进行定义

	```bash
		@Override
		public void run(String... args) throws Exception {
			log.info("COUNT BEFORE TRANSACTION: {}", getCount());
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
					jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'aaa')");
					log.info("COUNT IN TRANSACTION: {}", getCount());
					transactionStatus.setRollbackOnly();
				}
			});
			log.info("COUNT AFTER TRANSACTION: {}", getCount());
		}

		private long getCount() {
			return (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM FOO").get(0).get("CNT");
		}
	```

### 声明式事务
![pic](http://www.saxing.cn/upload/2019/4/saxing_2019-05-21_23-23-0320190521232434134.png)
所以如果走不到代理层，就无法调用事务。

开启方式：
- @EnableTransactionManagement
- <tx:annotation-driven/>
一些配置
- proxyTargetClass 
- mode
- order
使用
@Transactional
	- transactionManager
	- propagation
	- isolation
	- timeout
	- readOnly
	- 怎么判断回滚

```bash
@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FooService fooService;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();// 无事务
        fooService.insertThenRollback(); // 有事务
    }
}
```

## 4. Spring JDBC异常
Spring 会将数据操作的异常转换为`DataAccessException`，无论使用何种数据访问方式，都能使用一样的异常
如何做到的： 通过SQLErrorCodeSQLExceptionTranslator 解析错误码
ErrorCode定义：
	- org/springframework/jdbc/support/sql-error-codes.xml
	- Classpath 下的sql-error-codes.xml

## 5. Spring Data Jpa
对象与关系不匹配
![file](http://www.saxing.cn/upload/2019/4/image-155845645628220190522003416208.png)

@Entity、 @MappedSuperclass、 @Table(name)
@Id
	- @GeneratedValue(strategy,generator)
	- @SequenceGenerator(name, sequenceName)
@Column(name, nullable, length, insertable, updatable)
@JoinTable(name)、@JoinColumn(name)

@OnetoOne、@OneToMany、@ManyToOne、@ManyToMany、@OrderBy

Repository Bean 是如何创建的
- JpaRepositoriesRegistrar
	- 激活了@EnableJpaRepositories
	- 返回了JpaRepositoryConfigExtension
- RepositoryBeanDefinitionRegistrarSupport.registerBeanDefinitions
	- 注册 Repository Bean （类型是 JpaRepositoryFactoryBean）
- RepositoryConfigurationExtensionSupport.getRepositoryConfigurations
	- 取得Repository配置
- JpaRepositoryFactory.getTargetRepository
	- 创建了Repository

接口中的方法是如何被解释的
- RepositoryFactorySupport.getRepository 添加了 Advice
	- DefaultMethodInvokingMethodInterceptor
	- QueryExecutorMethodInterceptor
- AbstractJpaQuery.execute执行具体的查询
- 语法解析在Part中

## 6. MyBatis 
- 优秀的持久层框架
- 支持定制化SQL、存储过程、高级映射

工具：
MyBatis Generator
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <context id="H2Tables" targetRuntime="MyBatis3">
        <plugin type="org.mybatis.generator.plugins.FluentBuilderMethodsPlugin" />
        <plugin type="org.mybatis.generator.plugins.ToStringPlugin" />
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin" />
        <plugin type="org.mybatis.generator.plugins.RowBoundsPlugin" />

        <jdbcConnection driverClass="org.h2.Driver"
                        connectionURL="jdbc:h2:mem:testdb"
                        userId="sa"
                        password="">
        </jdbcConnection>

        <javaModelGenerator targetPackage="example.spring.data.mybatis.model"
                            targetProject="./src/main/java">
            <property name="enableSubPackages" value="true" />
            <property name="trimStrings" value="true" />
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="example.spring.data.mybatis.mapper"
                         targetProject="./src/main/resources/mapper">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>

        <javaClientGenerator type="MIXEDMAPPER"
                             targetPackage="example.spring.data.mybatis.mapper"
                             targetProject="./src/main/java">
            <property name="enableSubPackages" value="true" />
        </javaClientGenerator>

        <table tableName="t_coffee" domainObjectName="Coffee" >
            <generatedKey column="id" sqlStatement="CALL IDENTITY()" identity="true" />
            <columnOverride column="price" javaType="org.joda.money.Money" jdbcType="BIGINT"
                            typeHandler="example.spring.data.mybatis.handler.MoneyTypeHandler"/>
        </table>
    </context>
</generatorConfiguration>
```
```java
	private void generateArtifacts() throws Exception {
		List<String> warnings = new ArrayList<>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(
				this.getClass().getResourceAsStream("/generatorConfig.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}

	private void playWithArtifacts() {
		Coffee espresso = new Coffee()
				.withName("espresso")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date());
		coffeeMapper.insert(espresso);

		Coffee latte = new Coffee()
				.withName("latte")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date());
		coffeeMapper.insert(latte);

		Coffee s = coffeeMapper.selectByPrimaryKey(1L);
		log.info("Coffee {}", s);

		CoffeeExample example = new CoffeeExample();
		example.createCriteria().andNameEqualTo("latte");
		List<Coffee> list = coffeeMapper.selectByExample(example);
		list.forEach(e -> log.info("selectByExample: {}", e));
	}
```

如果有自己的微调SQL或者其他逻辑，建议分开存放，方便下次生成直接覆盖之前的代码。

MyBatis PageHelper.

```properties
pagehelper.offset-as-page-num=true
pagehelper.reasonable=true
pagehelper.page-size-zero=true
pagehelper.support-methods-arguments=true
```

```java
	public void run(ApplicationArguments args) throws Exception {
		coffeeMapper.findAllWithRowBounds(new RowBounds(1, 3))
				.forEach(c -> log.info("Page(1) Coffee {}", c));
		coffeeMapper.findAllWithRowBounds(new RowBounds(2, 3))
				.forEach(c -> log.info("Page(2) Coffee {}", c));

		log.info("===================");

		coffeeMapper.findAllWithRowBounds(new RowBounds(1, 0))
				.forEach(c -> log.info("Page(1) Coffee {}", c));

		log.info("===================");

		coffeeMapper.findAllWithParam(1, 3)
				.forEach(c -> log.info("Page(1) Coffee {}", c));
		List<Coffee> list = coffeeMapper.findAllWithParam(2, 3);
		PageInfo page = new PageInfo(list);
		log.info("PageInfo: {}", page);
	}
```

## 7. MongoDB，开源文档数据库
NoSQL四大块
- KV：redis, memcached
- 文档型：MongoDB, CouchDB
- 列存储：HBase, Cassandra
- 图数据：Neo4j

## 8. Redis 
- Jedis/Lettuce
- RedisTemplate
- Repository

### Jedis
- 非线程安全
- 通过JedisPool获得Jedis实例

```yaml
redis.host=localhost
redis.maxTotal=5
redis.maxIdle=5
redis.testOnBorrow=true
#redis.max-total=3
#redis.max-idle=3
#redis.test-on-borrow=true
```
```java
@Autowired
private JedisPool jedisPool;
@Autowired
private JedisPoolConfig jedisPoolConfig;

@Bean
@ConfigurationProperties("redis")
public JedisPoolConfig jedisPoolConfig() {
	return new JedisPoolConfig();
}

@Bean(destroyMethod = "close")
public JedisPool jedisPool(@Value("${redis.host}") String host) {
	return new JedisPool(jedisPoolConfig(), host);
}

public void run(ApplicationArguments args) throws Exception {
	log.info(jedisPoolConfig.toString());

	try (Jedis jedis = jedisPool.getResource()) {
		coffeeService.findAllCoffee().forEach(c -> {
			jedis.hset("springbucks-menu",
					c.getName(),
					Long.toString(c.getPrice().getAmountMinorLong()));
		});

		Map<String, String> menu = jedis.hgetAll("springbucks-menu");
		log.info("Menu: {}", menu);

		String price = jedis.hget("springbucks-menu", "espresso");
		log.info("espresso - {}",
				Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
	}
}
```

### 哨兵模式
- Redis Sentinel 是一种高可用方案
	- 监控，通知，自动故障转移，服务发现
- JedisSentinelPool

![file](http://www.saxing.cn/upload/2019/4/image-155853150374120190522212503801.png)

### 集群模式
- Redis Cluster 
	- 数据自动分片（分成16384个Hash Slot）无法批量操作，可能这些key在不同master上
	- 在部分节点失效时有一定可用性
- JedisCluster
	- Jedis 只从Master读数据，如果想要自动读写分离，可以定制



## 9. Spring 缓存
- 为不同的缓存提供一层抽象
	- 为Java方法增加缓存，缓存执行结果
	- 支持ConcurrentMap, EhCache, Caffeine, JCache
	- 接口
		- org.springframework.cache.Cache
		- org.springframework.cache.CacheManager

- @EnableCaching
	- @Cacheable
	- @CacheEvict
	- @CachePut
	- @Caching
	- @CacheConfig

## 10. Reactor
- Operators - Publisher /Subscriber
	- Nothing Happens Until You subscribe()
	- Flux [0...N] - onNext()、onComplete()、onError()
	- Mono [0..1] - onNext()、onComplete()、onError()
- Backpressure
	- Subscription
	- onRequest()、onCancel()、onDispose()
- 线程调度
	- immediate() / single() / newSingle()
	- elastic() / parallel() / newParallel()
- 错误处理
	- onError / onErrorReturn / onErrorResume
	- doOnError / doFinally

```bash
public void run(ApplicationArguments args) throws Exception {
	Flux.range(1, 6)
			.doOnRequest(n -> log.info("Request {} number", n)) // 注意顺序造成的区别
//				.publishOn(Schedulers.elastic())
			.doOnComplete(() -> log.info("Publisher COMPLETE 1"))
			.map(i -> {
				log.info("Publish {}, {}", Thread.currentThread(), i);
				return 10 / (i - 3);
//					return i;
			})
			.doOnComplete(() -> log.info("Publisher COMPLETE 2"))
//				.subscribeOn(Schedulers.single())
//				.onErrorResume(e -> {
//					log.error("Exception {}", e.toString());
//					return Mono.just(-1);
//				})
//				.onErrorReturn(-1)
			.subscribe(i -> log.info("Subscribe {}: {}", Thread.currentThread(), i),
					e -> log.error("error {}", e.toString()),
					() -> log.info("Subscriber COMPLETE")//,
//						s -> s.request(4)
			);
	Thread.sleep(2000);
}
```

-  Lettuce 支持 Reactive方式
- Spring Data Redis 中主要的支持
	- ReactiveRedisConnectioin
	- ReactiveRedisConnectionFactory
	- ReactiveRedisTemplate
		- opsForXxx()

- MongoDB官方提供Reactive驱动
	- mongodb-driver-reactivestreams
- Spring Data MongoDB中主要的支持
	- ReactiveMongoClientFactoryBean
	- ReactiveMongoDatabaseFactory
	- ReactiveMongoTemplate

- Spring Data R2DBC
	- Reactive Relational Database Connectivity
- 支持的数据库
	- Postgres
	- H2
	- Microsoft SQL Server

	- Redis

## 11. AOP
- Aspect 切面
- Join Point 连接点，Spring AOP里总是代表一次方法执行
- Advice 通知，在连接点执行的动作
- Pointcut 切入点，说明如何匹配连接点
- Introduction 引入，为现有类型声明额外的方法和属性
- Target object 目标对象
- AOP proxy： AOP代理对象，可以是JDK动态代理，也可以是CGLIB代理
- Weaving 织入，连接切面与目标对象或类型创建代理的过程
	
---
- @EnableAspectJAutoProxy
- @Aspect
- @Pointcut
- @Before
- @After/@AfterReturning/@AfterThrowing
- @Around
- @Order

```bash
@Aspect
@Component
@Slf4j
public class PerformanceAspect {
//    @Around("execution(* example.spring.springbucks.repository..*(..))")
    @Around("repositoryOps()")
    public Object logPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String name = "-";
        String result = "Y";
        try {
            name = pjp.getSignature().toShortString();
            return pjp.proceed();
        } catch (Throwable t) {
            result = "N";
            throw t;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("{};{};{}ms", name, result, endTime - startTime);
        }
    }

    @Pointcut("execution(* example.spring.springbucks.repository..*(..))")
    private void repositoryOps() {
    }
}

@EnableAspectJAutoProxy
public class SpringBucksApplication implements ApplicationRunner {
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	}
}
```

## 12. MVC
- DispatcherServlet
	- Controller
	- xxxResolver
		- ViewResolver
		- HandlerExceptionResolver
		- MultipartResolver
	- HandlerMapping

- @Controller
	- @RestController
- @RequestMapping
	- @GetMapping / @PostMapping / @PutMapping / @DeleteMapping / @PatchMapping
- @RequestBody / @ResponseBody / @ResponseStatus

### 应用程序上下文
- BeanFactory (没有特别原因，不要直接使用这个类)
	- DefaultListableBeanFactory
- ApplicationContext
	- ClassPathXmlApplicationContext
	- FileSystemXmlApplicationContext
	- AnnotationConfigApplicationContext
- WebApplicationContext

![file](http://www.saxing.cn/upload/2019/4/image-15586198333592019052321571330.png)

请求处理流程 FrontController为 DispatcherServlet 等进行的操作
![file](http://www.saxing.cn/upload/2019/4/image-155862195315020190523223233692.png)
- 绑定一些Attribute
	- WebApplicationContext /LocalResolver/ThemeResolver
- 处理Multipart
	- 如果是，则将请求转为MultipartHttpServletRequest
- Handler处理
	- 如果找到对应的Handler，执行Controller及前后置处理器逻辑
- 处理返回的Model，呈现视图

### 定义映射关系
- @Controller
- @RequestMapping
	- path / method 指定映射路径与方法
	- params / headers 限定映射范围
	- consumes / produces 限定请求与响应格式
- @RequestBody / @ResponseBody /@ResponseStatus
- @PathVariable / @RequestParam / @RequestHeader
- HttpEntity / ResponseEntity

### 定义类型转换
- Spring Boot 在WebMvcAutoConfiguration中实现了一个
- 添加自定义的Converter
- 添加自定义的Formatter

### 定义校验
- 通过Validator对绑定结果进行校验
	- Hibernate Validator
- @Valid 注解
- BindingResult

Multipart上传
- 配置MultipartResolver
	- Spring Boot自动配置MultipartAutoConfiguration
- 支持类型multipart/form-data
- MultipartFile类型

```java
@Component
public class MoneyFormatter implements Formatter<Money> {
    /**
     * 处理 CNY 10.00 / 10.00 形式的字符串
     * 校验不太严密，仅作演示
     */
    @Override
    public Money parse(String text, Locale locale) throws ParseException {
        if (NumberUtils.isParsable(text)) {
            return Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(text));
        } else if (StringUtils.isNotEmpty(text)) {
            String[] split = StringUtils.split(text, " ");
            if (split != null && split.length == 2 && NumberUtils.isParsable(split[1])) {
                return Money.of(CurrencyUnit.of(split[0]),
                        NumberUtils.createBigDecimal(split[1]));
            } else {
                throw new ParseException(text, 0);
            }
        }
        throw new ParseException(text, 0);
    }

    @Override
    public String print(Money money, Locale locale) {
        if (money == null) {
            return null;
        }
        return money.getCurrencyUnit().getCode() + " " + money.getAmount();
    }
}
```

### 视图解析实现基础
- ViewResolver 与 View 接口
	- AbstractCachingViewResolver
	- UrlBasedViewResolver
	- FreeMarkerViewResolver
	- ContentNegotiatingViewResolver
	- InternalResourceViewResolver

DispatcherServlet中的视图解析逻辑
- initStrategies()
	- initViewResolvers() 初始化了对应ViewResolver
- doDispatch()
	- processDispatchResult()
	- 没有返回视图的话，尝试RequestToViewNameTranslator
	- resolveViewName() 解析View 对象

使用@ResponseBody的情况
- 在HandlerAdapter.handle()中完成Response输出
	- RequestMappingHandlerAdapter.invokeHandlerMethod()
		- HandlerMethodReturnValueHandlerComposite.handleReturnValue()
			- RequestResponseBodyMethodProcessor.handleReturnValue()

重定向
- redirect: 类似302跳转，客户端跳转，url变化，request中的信息，可能丢失
- forward: 服务端发起，url不变化

### 配置MessageCoinverter
- 通过 WebMvcConfigurer 的 configureMessageConverters()
	- Spring Boot自动查找HttpMessageConverters进行注册

### Spring Boot对Jackson的支持
- JacksonAutoConfiguration
	- Spring Boot 通过 @JsonComponent 注册 JSON序列化组件
	- Jackson2ObjectMapperBuilderCustomizer
- JacksonHttpMessageConvertersConfiguration
	- 增加jackson-dataformat-xml以支持XML序列化

### 使用Thymeleaf
- 添加 Thymeleaf依赖
	- org.springframework.boot:spring-boot-starter-thymeleaf
- Spring Boot的自动配置
	- ThymeleafAutoConfiguration
		- ThymeleafViewResolver

### 静态资源与缓存（不建议使用，应有专门方案处理）

### 异常处理
- 核心接口
	- HandlerExceptionResolver
- 实现类
	- SimpleMappingExceptionResolver
	- DefaultHandlerExceptionResolver
	- ResponseStatusExceptionResolver
	- ExceptionHandlerExceptionResolver
- 处理方法
	- @ExceptionHandler
- 添加位置
	- @Controller / @RestController
	- @ControllerAdvice / @RestControllerAdvice (优化级比上面的低)

```java
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@AllArgsConstructor
public class FormValidationException extends RuntimeException {
    private BindingResult result;
}
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validationExceptionHandler(ValidationException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        return map;
    }
}
```

### 拦截器
核心接口
- HandlerInterceptor
	- boolean preHandle()
	- void postHandle()
	- void afterCompletion()
- 针对 @ResponseBody 和 ResponseEntity的情况
	- ResponseBodyAdvice
- 针对异步请求的接口
	- AsyncHandlerInterceptor
		- void afterConcurrentHandlingStarted()

配置方式
- 常规方法
	- WebMvcConfigurer.addInterceptors()
- Spring Boot中的配置
	- 创建一个带@Configuration的WebMvcConfigurer配置类
	- 不能带@EnableWebMvc （想彻底自己控制MVC的除外）

### RestTemplate
- Spring Boot中没有自动配置 RestTemplate
- Spring Boot提供了RestTemplateBuilder
	- RestTemplateBuilder.build()
- getForObject() / getForEntity() / postForObject() / postForEntity() / put() / delete()
- 构造URI
	- UriComponentsBuilder
- 构造相对于当前请求的URI
	- ServletUriComponentsBuilder
- 构造指向Controller的URI
	- MvcUriComponentsBuilder

```java
public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(CustomerServiceApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.web(WebApplicationType.NONE)
				.run(args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate();
		return builder.build();
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/{id}")
				.build(1);
		ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
		log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
		log.info("Coffee: {}", c.getBody());

		String coffeeUri = "http://localhost:8080/coffee/";
		Coffee request = Coffee.builder()
				.name("Americano")
				.price(BigDecimal.valueOf(25.00))
				.build();
		Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
		log.info("New Coffee: {}", response);

		String s = restTemplate.getForObject(coffeeUri, String.class);
		log.info("String: {}", s);
	}
```

- 传递HTTP Header
	- RestTemplate.exchange()
	- RequestEntity / ResponseEntity
- 类型转换
	- JsonSerializer / JsonDeserializer
	- @JsonComponent
- 解析泛型对象
	- RestTemplate.exchange()
	- ParameterizedTypeReference

```java
	@Override
	public void run(ApplicationArguments args) throws Exception {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/?name={name}")
				.build("mocha");
		RequestEntity<Void> req = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_XML)
				.build();
		ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
		log.info("Response Status: {}, Response Headers: {}", resp.getStatusCode(), resp.getHeaders().toString());
		log.info("Coffee: {}", resp.getBody());

		String coffeeUri = "http://localhost:8080/coffee/";
		Coffee request = Coffee.builder()
				.name("Americano")
				.price(Money.of(CurrencyUnit.of("CNY"), 25.00))
				.build();
		Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
		log.info("New Coffee: {}", response);

		ParameterizedTypeReference<List<Coffee>> ptr =
				new ParameterizedTypeReference<List<Coffee>>() {};
		ResponseEntity<List<Coffee>> list = restTemplate
				.exchange(coffeeUri, HttpMethod.GET, null, ptr);
		list.getBody().forEach(c -> log.info("Coffee: {}", c));
	}
```

- 通用接口
	- ClientHttpRequestFactory
- 默认实现
	- SimpleClientHttpRequestFactory
- Apache HttpComponents
	- HttpComponentsClientHttpRequestFactory
- Netty
	- Netty4ClientHttpRequestFactory
- OkHttp
	- OkHttp3ClientHttpRequestFactory
- 连接管理
	- PoolingHttpClientConnectionManager
	- KeepAlive 策略
- 超时设置
	- connectionTimeout / readTimeout
- SSL校验
	- 证书检查策略

```java
@Bean
public HttpComponentsClientHttpRequestFactory requestFactory() {
	PoolingHttpClientConnectionManager connectionManager =
			new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
	connectionManager.setMaxTotal(200);
	connectionManager.setDefaultMaxPerRoute(20);

	CloseableHttpClient httpClient = HttpClients.custom()
			.setConnectionManager(connectionManager)
			.evictIdleConnections(30, TimeUnit.SECONDS)
			.disableAutomaticRetries()
			// 有 Keep-Alive 认里面的值，没有的话永久有效
			//.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
			// 换成自定义的
			.setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
			.build();

	HttpComponentsClientHttpRequestFactory requestFactory =
			new HttpComponentsClientHttpRequestFactory(httpClient);

	return requestFactory;
}

public class CustomConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {
    private final long DEFAULT_SECONDS = 30;

    @Override
    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        return Arrays.asList(response.getHeaders(HTTP.CONN_KEEP_ALIVE))
                .stream()
                .filter(h -> StringUtils.equalsIgnoreCase(h.getName(), "timeout")
                        && StringUtils.isNumeric(h.getValue()))
                .findFirst()
                .map(h -> NumberUtils.toLong(h.getValue(), DEFAULT_SECONDS))
                .orElse(DEFAULT_SECONDS) * 1000;
    }
}

@Bean
public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate();

	return builder
			.setConnectTimeout(Duration.ofMillis(100))
			.setReadTimeout(Duration.ofMillis(500))
			.requestFactory(this::requestFactory)
			.build();
}
```

### WebClient
- 一个以Reactive方式处理HTTP请求的非阻塞式的客户端

- 支持底层HTTP库
	- Reactor Netty - ReactorClientHttpConnector
	- Jetty ReactiveStream HttpClient - JettyClientHttpConnector

```java
@Override
	public void run(ApplicationArguments args) throws Exception {
		CountDownLatch cdl = new CountDownLatch(2);

		webClient.get()
				.uri("/coffee/{id}", 1)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.retrieve()
				.bodyToMono(Coffee.class)
				.doOnError(t -> log.error("Error: ", t))
				.doFinally(s -> cdl.countDown())
				.subscribeOn(Schedulers.single())
				.subscribe(c -> log.info("Coffee 1: {}", c));

		Mono<Coffee> americano = Mono.just(
				Coffee.builder()
						.name("americano")
						.price(Money.of(CurrencyUnit.of("CNY"), 25.00))
						.build()
		);
		webClient.post()
				.uri("/coffee/")
				.body(americano, Coffee.class)
				.retrieve()
				.bodyToMono(Coffee.class)
				.doFinally(s -> cdl.countDown())
				.subscribeOn(Schedulers.single())
				.subscribe(c -> log.info("Coffee Created: {}", c));

		cdl.await();

		webClient.get()
				.uri("/coffee/")
				.retrieve()
				.bodyToFlux(Coffee.class)
				.toStream()
				.forEach(c -> log.info("Coffee in List: {}", c));
	}
```

### RESTful Web Service
- 设计好的rest接口
- Http 状态码
- HATEOAS

### Spring Data REST
- HAL
	- Hypertext Application Language
	- HAL是一种简单的格式，为API中的资源提供简单一致的链接
- HAL 模型
	- 链接
	- 内嵌资源
	- 状态
- Sprinb Boot 依赖
	- spring-boot-starter-data-rest
- 常用注解类
	- @ResponsitoryRestResource
	- `Resource<T>`
	- `PagedResource<T>`

### 分布式Session
- 常见的会话解决方案
	- 粘性会话 Sticky Session
	- 会话复制 Session Replication
	- 集中会话 Centralized Sessioin
- Spring Session
	- 简化集群中的用户会话管理
	- 无需绑定窗口特定解决方案
- 支持的存储
	- Redis
	- MongoDB
	- JDBC
	- Hazelcast
- 实现原理：通过定制的HttpServletRequest返回定制的HttpSession
	- SessionRepositoryRequestWrapper
	- SessionRepositoryFilter
	- DelegatingFilterProxy

### WebFlux
- 什么是WebFlux
	- 用于构建基于Reactive技术栈之上的Web应用程序
	- 基于Reactive Streams API，运行在非阻塞服务器上
- 为什么会有WebFlux
	- 对于非阻塞Web应用的需要
	- 函数式编程
- 关于WebFlux性能
	- 请求的耗时并不会有很大的改善
	- 仅需少量固定数量的线程和较少的内存即可实现扩展
- WebMVC v.s. WebFlux
	- 已有Spring MVC应用，运行正常，就别改了
	- 依赖了大量阻塞式持久，API和网络API，建议使用Spring MVC
	- 已经使用了非阻塞技术栈，可以考虑使用WebFlux
	- 想要使用Java 8 Lambda 结合轻量级函数式框架，可以考虑WebFlux
- 两种编程模型
	- 基于注解的控制器
	- 函数式EndPoints

## 13. Spring Boot
- 不是什么
	- 不是应用服务器
	- 不是Java EE之类的规范
	- 不是代码生成器
	- 不是Spring Framework 的升级版
- 特性
	- 方便地创建可独立运行的Spring应用程序
	- 直接内嵌Tomcat、Jetty、Undertow
	- 简化了项目的构建配置
	- 为Spring及第三方库提供自动配置
	- 提供生产级特性
	- 无需生成代码或进行XML配置
- 四大核心
	- 自动配置 - Auto Configuration
	- 起步依赖 - Starter Dependency
	- 命令行界面 - Spring Boot CLI
	- Actuator
- 自动配置
	- 基于添加JAR依赖的自动对Spring Boot应用程序进行配置
	- spring-boot-autoconfiguration
- 开启自动配置
	- @EnableAutoConfiguration
		- `exclude = Class<?>[]`
	- @SpringBootApplication
- 自动配置实现原理
	- @EnableAutoConfiguration
		- AutoConfigurationImportSelector
		- META-INF/spring.factories
			- org.springframework.boot.autoconfigure.EnableAutoConfiguration
- 条件注解
	- @Conditional
	- @ConditionalOnClass
	- @ConditionalOnBean
	- @ConditionalOnMissingBean
	- @ConditionalOnProperty
	- ...
- 了解自动配置的情况
	- 观察自动配置的判断结果
		- --debug
	- ConditionEvaluationReportLoggingListener
		- Positive matches
		- Negative matches
		- Exclusions
		- Unconditional classes

### 自己实现自动配置
- 编写Java Config
	- @Configuration
- 添加条件
	- @Conditional
- 定位自动配置
	- META-INF/spring.factories
- FailureAnalysis
- 低版本实现自动配置
	- 条件判断
		- BeanFactoryPostProcessor进行判断
	- 配置加载
		- 编写JavaConfig类
		- 引入配置类
			- 通过 component-scan
			- 通过 XML 文件 import 

### starter 起步依赖
- maven技巧
	- mvn dependency:tree
	- IDEA Maven Helper  plugin
- 排除特定依赖
	- exclusion
- 统一管理依赖
	- dependencyManagement
	- Bill of Materials - bom
- Start Dependencies
	- 直接面向功能
	- 一站获得所有相关依赖，不再复制粘贴
- 官方的Starters
	- spring-boot-starter-*

### 配置加载顺序
- 开启DevTools时， ~/.spring-boot-devtools.properties
- 测试类上的@TestPropertySource注解
- @SpringBootTest#properties属性
- 命令行参数 （--server.port=9000）
- SPRING_APPLICATION_JSON中的属性
- ServletConfig初始化参数
- ServletContext初始化参数
- java:com/env 中的JNDI属性
- System.getProperties()  (-D参数指定的属性)
- 操作系统变量
- random.*涉及到的RandomValuePropertySource
- jar包外部的application-{profile}.properties或.yml
- jar包内部的application-{profile}.properties或.yml
- jar包外部的application.properties或.yml
- jar包内部的application.properties或.yml
- @Configuration类上的@PropertySource
- SpringApplication.setDefaultProperties()

- 默认位置
	- ./config
	- ./
	- CLASSPATH中的/config
	- CLASSPATH中的/
- 修改名字或路径
	- spring.config.name
	- spring.config.location
	- spring.config.additional-location

- PropertySource, 配置转为此属性
	- @ConfigurationProperties
- 定制PropertySource
	- 实现PropertySource
	- 从Environment取得PropertySource
	- 将自己的PropertySource添加到合适的位置
- 切入位置
	- EnvironmentPostProcessor
	- BeanFactoryPostProcessor

## 14. 监控
###  Actuator
- 目的
	- 监控并管理应用程序
- 访问方式
	- HTTP
	- JMX (功能更多)
- 依赖
	- spring-boot-starter-actuator
![file](http://www.saxing.cn/upload/2019/4/image-155877181341220190525161013262.png)

- 自定义HealthIndicator接口
- 根据自定义检查逻辑返回对应Health状态
	- Health 中包含状态和详细描述信息

### Micrometer 度量门面（类似 slf4j为日志门面）
- 特性
	- 多维席度量
	- 支持Tag
- 预置大量探针
	- 缓存、类加载器、GC、CPU利用率、线程池
- 与Spring深度整合
- 核心接口
	- Meter
- 核心度量项
	- JVM、CPU、文件句柄数、日志、启动时间
- 其他度量项
	- Spring MVC、Spring WebFlux
	- Tomcat、Jersey JAX-RS
	- RestTemplate、WebClient
	- 缓存、数据源、Hibernate
	- Kafka、RabbitMQ

### Spring Boot Admin
- 目的
	- 为Spring Boot 应用程序提供一套管理界面
- 主要功能
	- 集中展示应用程序Actuator相关的内容
	- 变更通知 
- 服务端
	- de.codecentric:spring-boot-admin-starter-server:2.1.3
	- @EnableAdminServer
- 客户端 
	- de.codecentric:spring-boot-admin-starter-client:2.1.3
	- 配置服务端及Endpoint
		- spring.boot.admin.client.url=http://localhost:8080
		- management.endpoints.web.exposure.include=*
- 安全依赖
	- spring-boot-starter-security
- 服务端配置
	- spring.security.user.name
	- spring.security.user.password

## 15. 定制Web容器运行参数
- 配置方式
- 编程方式
	- `WebServerFactoryCustomizer<T>`
		- TomcatServletWebServerFactory
		- JettyServletWebServerFactory
		- UndertowServletWebServerFactory
	- 完全自己提供一个servletContainer的bean

### HTTPS支持
- server.ssl.*
	- server.ssl.key-store
	- server.ssl.key-store-type JKS或者 PCKS12
	- server.ssl.key-store-password=secret

### 命令行运行的程序
- 不同的Runner
	- ApplicationRunner
		- 参数是ApplicationArguments
	- CommandLineRunner
		- 参数是String[]
	- 返回码
		- ExitCodeGenerator

## 16. 可执行Jar
- 包含
	- Jar描述，META-INF/MANIFEST.MF
	- Spring Boot Loader，org/springframework/boot/loader
	- 项目内容，BOOT-INF/classes
	- 项目依赖，BOOT-INF/lib
- 不包含
	- JDK/JRE

- Jar的启动类
	- ANIFEST.MF
		- Main-Class: org.springframework.boot.loader.JarLauncher
- 项目的主类
	- @SpringApplication
	- MANIFEST.MF
		- Start-Class: xxx.yyy.zzz


- 如何创建可直接执行的Jar
	- 打包后可直接运行，无需Java命令
	- 可以在.conf的同名文件中配置参数
```xml
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
	<configuration>
		<executable>true</executable>
	</configuration>
</plugin>
```

## 17. Docker集成
- Dockerfile常用命令
![file](http://www.saxing.cn/upload/2019/4/image-1558776752629201905251732325.png)

- Maven 构建Dock镜像
	- 提供一个Dockerfile
	- 配置dockerfile-maven-plugin插件
- 执行构建
	- mvn package
	- mvn dockerfile:build
- 检查结果
	- docker images

```xml
<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<plugin>
				<groupId>com.spotify</groupId>
				<artifactId>dockerfile-maven-plugin</artifactId>
				<version>1.4.10</version>
				<executions>
					<execution>
						<id>default</id>
						<goals>
							<goal>build</goal>
							<goal>push</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<repository>${docker.image.prefix}/${project.artifactId}</repository>
					<tag>${project.version}</tag>
					<buildArgs>
						<JAR_FILE>${project.build.finalName}.jar</JAR_FILE>
					</buildArgs>
				</configuration>
			</plugin>
		</plugins>
```

## 18. 微服务
微服务就是一些协同工作的小而自治的服务。  -- Sam Newman

- 异构性
	- 语言、存储
- 弹性
	- 一个组件不可用，不会导致级联故障
- 扩展
	- 单体服务不易扩展，多个较小的服务可以按需扩展

- 优点 
	- 易于部署
	- 与组织结构对齐
	- 可组合性
	- 可替代性
- 缺点
	- 没有银弹
	- 分布式系统的复杂性
	- 开发测试等诸多研发过程中的复杂性
	- 部署监控等诸多运维复杂性

- 云原生应用要求
	- DevOps
		- 开发与运维一同致力于交付高品质的软件服务于客户
	- 持续交付
		- 软件的构建、测试和发布，要更快，更频繁，更稳定
	- 微服务
		- 以一组小型服务的形式来部署应用
	- 容器
		- 提供比传统虚拟机更高的效率

## 19. THE TWELVE-FACTOR APP
- 目的：
	- 为构建SaaS应用提供行为有效的方法论
	- 适用于任意语言和后端服务的开发的应用程序
	- https://12factor.net/zh_cn/

- I. 基准代码
	- 一份基准代码，多份部署
- II. 依赖
	- 显式声明依赖关系
		- 可以在项目里做相关接口，进行扫描，方便全系统相关升级
- III. 配置
	- 在环境中存储配置
- IV. 后端服务
	- 把后端服务当作附加资源
- V. 构建，发布，运行
	- 严格分离构建和运行
- VI. 进程
	- 以一个或多个无状态进程运行应用
- VII. 端口绑定
	- 通过端口绑定提供服务
- VIII. 并发
	- 通过进程模型进行扩展
- IX. 易处理
	- 快速启动和优雅终止可最大化健壮性
- X. 开发环境与线上环境等价
	- 尽可能的保持开发，预发布，线上环境相同
- XI. 日志
	- 把日志当作事件流
- XII. 管理进程
	- 后台管理任务当作一次性进程运行

## 20. Spring Cloud
### 服务发现与治理
- 服务发现
- 服务网关
- 服务熔断
- 分布式消息
- 配置服务
- 分布式跟踪
- 服务安全
- 各种云平台支持

- Eureka

- 获得服务地址
	- DiscoveryClient
		- getInstances()
	- service.port=0 随机启用一个端口
- Load Balancer Client
	- RestTemplate 与 WebClient
		- @LoadBalaced
		- 实际通过 ClientHttpRequestInterceptor实现的
			- LoadBalancerInterceptor
			- LoadBalancerClient
				- RibbonLoadBalancerClient
- Feign
	- 声明式REST Web服务客户端
- Spring Cloud OpenFeign
	- spring-cloud-starter-openfeign

- 服务注册抽象
	- ServiceRegistry抽象
- 客户发现抽象
	- DiscoveryClient抽象
		- @EnableDiscoveryClient
	- 提供了LoadBalancerClient抽象


- Zookeeper
	- 分布式协调服务
- 设计目标
	- 简单、多副本、有序、快
- Zk要注意的问题
	- 在实践中，注册中心不能因为自身的任何原因破坏服务本身之间的可连通性
	- 注册中心需要AP，而Zookeeper是CP
	- CAP - 一致性，可用性，分区容忍性

- Consul 
- 关键特性
	- 服务发现
	- 健康检查
	- KV存储
	- 多数据中心支持
	- 安全的服务间通信
- 好用的功能
	- HTTP API
	- DNS (xxx.service.consul)
	- 与Nginx 联运 ， 比如 ngx_http_consul_backend_module

- Nacos
	- 一个更易于构建云原生应用的动态服务发现，配置管理和服务管理平台
- 功能
	- 动态服务配置
	- 服务发现和管理
	- 动态DNS服务

![file](http://www.saxing.cn/upload/2019/4/image-155879550370920190525224505201.png)

- 实现自己的DiscoveryClient
	- 返回该DiscoveryClient能提供的服务名列表
	- 返回指定服务对应的ServiceInstance列表
	- 返回DiscoveryClient的顺序
	- 返回HealthIndicator里显示的描述

### 限流与断路器
- 核心思想
	- 在断路器对象中封闭受保护的方法调用
	- 该对象监控调用和断路情况
	- 调用失败触发阈值后，后续调用直接由断路器返回错误，不再执行实际调用

- Netflix Hystrix
	- 实现了断路器模式
	- fallbackMethod / commandProperties
		- @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")

- 如何了解熔断的情况
	- 打日志
		- 在发生熔断时打印特定日志
	- 看监控
		- 主动向监控系统进点，上报熔断情况
		- 提供与熔断相关的Endpoint，让第三方系统来拉取信息

- 聚合集群熔断
	- Netflix Turbine

eureka，hystrix，turbine都不建议用在生产上，应该用更专业的运维监控系统如prometheus


- Resilience4j
	- 一款受Hystrix启发的轻量级且易于使用的容错库
	- 针对Java 8 与函数式编程设计 

- Bulkhead
	- 目的
		- 防止下游依赖被并发请求冲击
		- 防止发生连环故障
	- 用法
		- BulkheadRegistry /BulkheadConfig
		- @Bulkhead(name="名称")
-RateLimiter
	- 目的
		- 限制特定时间段内的执行次数
	- 用法
		- RateLimiterRegistry / RateLimiterConfig
		- @RateLimiter(name = "名称")
- Spring cloud alibaba  Sentinel
- 建议学习 ： Google Guava。curator是zookeeper中的类似于guava对于java的意义一样
	- 基本工具
	- 令牌桶限流等
	- 缓存框架等

### 配置中心
- Spring Cloud Config
- Zookeeper 做为配置中心
	- watch，配置自动推送
- Consul 做为配置中心
	- 轮旬检查
- Nacos 为配置中心
- Apollo配置中心
	- 统一管理不同环境，不同集群的配置
	- 配置修改实时生效（热发布）
	- 版本发布管理
	- 灰度发布
	- 权限管理、发布审核、操作审计
	- 客户端配置信息监控
	- 提供开放平台API
- Spring Cloud Config配置抽象
	- 目标
		- 在分布式系统中，提供外围配置支持
	- 实现
		- 类似于Spring应用中的Environment与PropertySource
		- 在上正文中增加Spring Cloud Config 的PropertySource
		- PropertySourceLocator 定位配置
- EnvironmentRepository
	- Git/SVN/Vault(金融)/JDBC
- 功能特性
	- SSH、代理访问、配置加密
- 配置刷新
	- /actuator/refresh
	- Spring Cloud Bus - RefreshRemoteApplicationEvent
- 配置加载顺序（上面有完全版，此为简版）
	- 应用名-profile.yml
	- 应用名.yml
	- application-profile.yml
	- application.yml

### Spring Cloud Stream
- 是什么
	- 一款用于构建消息驱动的微服务应用程序的轻量级框架
- 特性
	- 声明式编程模型
	- 引入多种概念抽象
		- 发布订阅、消费组、分区
	- 支持多种消息中间件
		- RabbitMQ，Kafka...

![file](http://www.saxing.cn/upload/2019/4/image-155883963259120190526110033925.png)

- Binding
	- 应用中生产者、消费者与消息系统之间的桥梁
		- @EnableBinding
		- @Input / SubscribableChannel
		- @Output / MessageChannel
		- routingKeyExpression 路由
- 生产消息
	- 使用MessageChannel 中的send()
	- @SendTo
- 消费消息
	- @StreamListener
		- @Payload / @Headers / @Header
- 其他说明
	- 可以使用Spring Integration

- RabbitMQ
	- 官方推荐
- KafKa
	- 诞生之初用作消息队列，现已发展为强大的分布式事件流平台 
	- LinkedIn在2011年开源 

- Spring 中的定时任务
	- Spring的抽象
		- TaskScheduler / Trigger / TriggerContext
	- 配置定时任务
		- @EnableScheduling
		- <task: scheduler />
		- @Sceduled
- Spring 中的事件机制
	- ApplicationEvent
	- 发送事件
		- ApplicationEventPublisherAware
		- ApplicationEventPublisher.publishEvent()
	- 监听事件
		- `ApplicationListener<T>`
		- @EventListener

### 链路追踪治理
- 系统中有哪些服务
- 服务之间的依赖关系
- 一个常见的请求具体的执行路径
- 请求每个环节的执行是否正常与耗时情况
- 。。。

- Google Dapper 
	- Span 基本工作单元
	- Trace 由一组Span构成的树形结构
	- Annotation 用于及时记录事件
		- cs Client Sent
		- sr Server Received
		- ss Server Sent
		- cr Client Received

![file](http://www.saxing.cn/upload/2019/4/image-155884394512420190526121225979.png)

- Spring Cloud Sleuth
- Skywalking

- SOA 治理
	- 一个企业实施的用以保障事情正确完成的流程，即遵循最佳实践，体系架构原则，治理条例，法律和其他决定因素。SOA治理是指用于管理SOA的采用和实现的流程。   --Anne Thomas Manes
	- 宏观上
		- 架构设计是否合理。 好架构不是一下就设计出来的，要慢经演进过来。
		- 哪些链接算是关键链路
		- 链路和容量水位趋势，如TTS一直在上涨，怎么扩容
		- 对系统变更的管理与审计
	- 微观上
		- 一个系统都依赖了什么
		- 一个系统都有哪些配置
		- 一个系统的主观与客观质量
		- 。。。


