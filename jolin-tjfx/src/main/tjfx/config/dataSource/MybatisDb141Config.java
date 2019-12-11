package com.hdsx.lwgl.statanalysis.config.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.hdsx.lwgl.statanalysis.mapper141"}, sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisDb141Config {
    @Autowired
    @Qualifier("dataSource141")
    private DataSource ds2;


    @Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds2);
        try {
            factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/xml/141/*.xml"));
        }catch (Exception e ){
            e.printStackTrace();
        }
        return factoryBean.getObject();

    }

    @Bean(name = "transactionManager141")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(ds2);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2()); // 使用上面配置的Factory
        return template;
    }
}
