
## Steps to Implement the Saga Pattern

````
Implementing the Saga pattern in a microservices architecture using Spring Boot and Eureka involves several key steps. The Saga pattern allows you to maintain data consistency across multiple services by splitting a large transaction into smaller, manageable steps, each with its own compensating action in case of failure.
````

### Steps to Implement the Saga Pattern
#### Add Dependencies (pom.xml)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>   
```

### Integrate Kafka (Event-Driven Coordination)
#### application.properties
```yaml
  kafka:
    bootstrap-servers: 9092
    consumer:
      group-id: pitchfork
      auto-offset-reset: earliest
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    producer:
      value-serializer: org.apache.kafka.common.serialization.StringDeserializer
      key-serializer: org.apache.kafka.common.serialization.StringDeserializer
```

