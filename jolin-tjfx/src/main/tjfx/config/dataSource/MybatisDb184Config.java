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
@MapperScan(basePackages = {"com.hdsx.lwgl.statanalysis.mapper184"}, sqlSessionFactoryRef = "sqlSessionFactory3")
public class MybatisDb184Config {
    @Autowired
    @Qualifier("dataSource184")
    private DataSource ds3;


    @Bean
    public SqlSessionFactory sqlSessionFactory3() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds3);
        try {
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:/xml/184/*.xml"));
        }catch (Exception e ){
            e.printStackTrace();
        }
        return factoryBean.getObject();

    }

    @Bean(name = "transactionManager184")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(ds3);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate3() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory3()); // 使用上面配置的Factory
        return template;
    }
}
