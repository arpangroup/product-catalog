[README.md syntax](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)

# Configure Databases Datasource URL
```
## postgresql
spring.datasource.url=jdbc:postgresql://localhost:5432/testDB
spring.datasource.driver-class-name=org.hibernate.dialect.PostgreSQLDialect

## mysql
spring.datasource.url=jdbc:mysql://localhost:3306/testDB
spring.datasource.driver-class-name=org.hibernate.dialect.MySQL8Dialect

## H2
# spring.datasource.url=jdbc:h2:mem:testDB
spring.datasource.url=jdbc:h2:file:./data/sampledata  
spring.datasource.driver-class-name=org.hibernate.dialect.H2Dialect
```

# Configure H2 Database
H2 is an **embedded**, **open-source**, and **in-memory** database. It is a relational database management system written in Java

http://localhost:8080/h2-console
```
spring.datasource.url=jdbc:h2:mem:testDB
#spring.datasource.url=jdbc:h2:file:./data/sampledata
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
### Persist the data in H2 Database
If we want to persist the data in the H2 database, we should store data in a file. To achieve the same, we need to change the datasource URL property.
```
#persist the data
spring.datasource.url=jdbc:h2:file:./data/sampledata
spring.datasource.url=jdbc:h2:C:/data/sampledata

Note: ./ means that that the directory or file is relative to the current working directory of the application.
```

# Various Database Drivers
```
# postgresql
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>

# mysql
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>



# H2
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```

# Setup the MockMVC
https://rieckpil.de/guide-to-testing-spring-boot-applications-with-mockmvc/
https://spring.io/guides/gs/testing-web/
https://www.geeksforgeeks.org/spring-boot-mockmvc-testing-with-example-project/
```
class TaskControllerTest {
 
  private MockMvc mockMvc;
 
  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new TaskController(new TaskService()))
      .setControllerAdvice()
      .setLocaleResolver(localResolver)
      .addInterceptors(interceptorOne)
      .build();
  }
}
```
or,
```
@WebMvcTest(TaskController.class)
public class TaskControllerSecondTest {
 
  @Autowired
  private WebApplicationContext context;
 
  @MockBean
  private TaskService taskService;
 
  protected MockMvc mockMvc;
 
  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders
      .webAppContextSetup(this.context)
      .apply(springSecurity())
      .build();
  }
 
}
```