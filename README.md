
# Spring AOP Example

In this repository, you can find simple AOP implementation and use-case of it.



# What is AOP 
AOP (Aspect-Oriented Programming) is an approaching that aims to reduce complexity of software and increase modularity of project,software etc.More often used to apply Seperation of Cutting Concerns.So what is Seperation of Cutting Concerns?

# Seperation of Cutting Concerns
Have you ever used same functionality that is not related with application logic on many places while developing any application?Suppose you have to log every user related business logic in your app.Here logging is a cutting concern.It is required but it is not related with your business logic.Following examples can be given as a cutting concern:
#### - Security
#### - Caching
#### - Error handling

## SPRING AOP
Spring AOP is a most powerful tool in Spring ecosystem.With aspect you can change your application logic like however you want.

### AOP Concepts in Spring
Aspect: When certain method is called, aspect is a part of logic executed by Framework.

Join Point: A point during the execution of a program, such as the execution of a method or the handling of an exception. In Spring AOP, a join point always represents a method execution.

Advice:Advice tells when application execute the aspect logic.

Pointcut:Which method framework needs while executing.We call this pointcut.



# REPOSITORY OVERVIEW

Lets assume we need to log when user check his orders. In log we need to get name,username and orderId. In this repoistory there is a simple Spring application of AOP use-case.

# Introduction

### Project Structure
  
    ├── ...
    ├── aspect                  # Aspect folder where we implement all aspects.
    │   ├── UserAspect.java  
    ├── config                  # Project Configuration
    │   ├── Configuration.java           
    ├── model                   # Models
    │   ├── User.java           
    ├── service                 # Services for business logic operations
    │   ├── UserService.java 
    │── pom.xml               
    │── ...
     
     
#### pom.xml
```xml
 <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>6.0.11</version>
    </dependency>
  </dependencies>
```
Add spring-aspects dependencies in your project.     

#### UserAspect.java
```java
@Aspect
public class UserAspect {
private Logger logger=Logger.getLogger(UserAspect.class.getName());

    @Around("execution(* org.example.service.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint)throws Throwable{


                          //Log operations

            }
}
```     
@Aspect:We need to identify Aspect class by this annotation. But this annotation doesn't create bean in Context. We have to make configuration.      

@Around:Some type of advice.Most powerful advice in Spring because @Around advice can perform custom behavior before and after the method invocation.

"execution(* org.example.service.*.*(..))":Some kind of AspectJ pointcut expression. More info on REFERENCE DOCUMENTATION.

#### Configuration.java

```java
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.example.service")
public class Configuration {
    @Bean
  public UserAspect userAspect(){
    return new UserAspect();
}

}
```
As i mentioned above on @Aspect annotation, this annotation doesn't create bean in Context.So we need to make configuration.

@EnableAspectJAutoProxy: To enable Aspect properties in Spring we need to declare this annotation.

And simple bean operations.

#### User.java

```java
public class User {
    private String name;
    private String userName;

    //... getter setters
}

```
An user model.

#### UserService.java

```java 
@Service
public class UserService {


    public void checkOrder(User user,int orderId){

        /*
        Some business logic
         */

    }

}

```
Simple UserService and checkOrder() function which will apply Aspect.


#### App.java

```java
public class App 
{
    public static void main( String[] args )
    {
     var ac=new AnnotationConfigApplicationContext(Configuration.class);

        var service=ac.getBean(UserService.class);

        User user=new User();

        user.setName("Kubilay");
        user.setUserName("bencoobie");

        service.checkOrder(user,1234);

    }
}

```

Lets get ApplicationContext instance and get UserService bean.Then lets test the app.

If everything correct and you run the app your output will be like this

```
Eyl 05, 2023 2:22:20 ÖS org.example.aspect.UserAspect log
INFO: New Query
 Name:Kubilay
Username:bencoobie
OrderId:1234
```

# REFERENCE DOCUMENTATION
For further reference, please consider the following sections:

* [Official AOP with Spring](https://docs.spring.io/spring-framework/reference/core/aop.html)

* [For understanding aspect terminology](https://livebook.manning.com/book/spring-start-here/chapter-6/18)

* [Pointcut expressions ](https://blog.espenberntsen.net/2010/03/20/aspectj-cheat-sheet/)






