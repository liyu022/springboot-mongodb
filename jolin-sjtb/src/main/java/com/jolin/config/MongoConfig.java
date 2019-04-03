package com.jolin.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig{
    // 注入配置实体
    @Value("${spring.data.mongodb.datebase}")
    private String datebase;
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private int port;
    @Value("${spring.data.mongodb.username}")
    private String username;
    @Value("${spring.data.mongodb.password}")
    private String password;

    // 覆盖默认的MongoDbFactory
    @Bean
    public MongoDbFactory mongoDbFactory() {
        //客户端配置（连接数、副本集群验证）
        ServerAddress serverAddress = new ServerAddress(host, port);
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        mongoCredentialList.add(MongoCredential.createCredential(username, datebase, password.toCharArray()));
        return new SimpleMongoDbFactory(new MongoClient(serverAddress, mongoCredentialList), datebase);
    }
    @Bean(name = "mongodbManager")
    public MongoTemplate getMongoTemplate(){
        return new MongoTemplate(mongoDbFactory(),mappingMongoConverter());
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(){
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
        List<Object> list = new ArrayList<>();
        list.add(new BigDecimalToDecimal128Converter());//自定义的类型转换器
        list.add(new Decimal128ToBigDecimalConverter());//自定义的类型转换器
        converter.setCustomConversions(new MongoCustomConversions(list));
        return converter;
    }
    @Bean
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        return mappingContext;
    }
}