package com.hdsx.lwgl.tjfx.config.dataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "dataSource71")
    @ConfigurationProperties(prefix = "spring.datasource.orcl71") // application.properteis中对应属性的前缀
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource141")
    @ConfigurationProperties(prefix = "spring.datasource.orcl141") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource184")
    @ConfigurationProperties(prefix = "spring.datasource.orcl184") // application.properteis中对应属性的前缀
    public DataSource dataSource3() {
        return DataSourceBuilder.create().build();
    }
}
