package com.hdsx.lwgl.statanalysis.config;

import com.hdsx.lwgl.statanalysis.util.BigDecimalToDecimal128Converter;
import com.hdsx.lwgl.statanalysis.util.Decimal128ToBigDecimalConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix="spring.data.mongodb") //接收application.yml中的roadQuery下面的属性
public class MongodbConfig extends AbstractMongoConfiguration {
    private String uri;

    @Override
    protected String getDatabaseName() {
        return new MongoClientURI(uri).getDatabase();
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoClient mongoClient = new MongoClient();
        return mongoClient;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.dbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
        List<Object> list = new ArrayList<>();
        list.add(new BigDecimalToDecimal128Converter());//自定义的类型转换器
        list.add(new Decimal128ToBigDecimalConverter());//自定义的类型转换器
        converter.setCustomConversions(new CustomConversions(list));
        return converter;
    }
    @Bean
    public MongoDbFactory dbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClientURI(uri));
    }
    @Bean
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        return mappingContext;
    }
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(this.dbFactory(), this.mappingMongoConverter());
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
