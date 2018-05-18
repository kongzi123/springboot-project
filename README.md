# springboot-project
springboot系列demo，在前一个的工程上集成新的功能
<br>
<br>

# 01.spring-boot-mybatis-demo
spring boot 集成mybatis，mybatis-generator<br>

# 02.spring-boot-redis-demo
在01的基础上集成redis，logback<br>

# 03.spring-boot-dynamicdb-demo
在02的基础上，实现多数据源、动态数据源切换<br>
可通过注解方式或者手动方式指定数据源<br>
测试发现注解添加在mapper的方法上无效，只能加在service上，如果service方法逻辑复杂（有查询又有更新），考虑增加dao层，dao再调用mapper<br>



