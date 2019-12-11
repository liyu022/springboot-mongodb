package com.hdsx.lwgl.statanalysis;

import com.hdsx.lwgl.statanalysis.config.SqlInjectionfilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@EnableScheduling
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
@EnableTransactionManagement
public class LwglStatanalysisApplication {

	/**
	 * 支持跨域
	 * @return
	 */
//	@Bean
//	public FilterRegistrationBean addCrossDomainFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CrossDomainFilter());
//		registrationBean.addUrlPatterns("/*");
//		registrationBean.setOrder(Integer.MAX_VALUE - 1);
//		return registrationBean;
//	}

	/**
	 * sql注入过滤
	 * @return
	 */
	@Bean
	public FilterRegistrationBean addSqlInjectionFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SqlInjectionfilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(Integer.MAX_VALUE - 1);
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(LwglStatanalysisApplication.class, args);
	}
}
