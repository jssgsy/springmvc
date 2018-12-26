# spring的定时任务

# 使用步骤(注解的方式)
参见TaskDemo.java文件
* 在spring的配置文件applicationContext.xml中开启对@Async and @Scheduled的支持
```xml
<!--Enables the detection of @Async and @Scheduled annotations on any Spring-managed object. If present, a proxy will be generated for executing the annotated methods asynchronously.-->
<task:annotation-driven/>
```
* 使任务类为bean,如使用@Component注解；
* 使用`@Scheduled`注解实际的任务方法

