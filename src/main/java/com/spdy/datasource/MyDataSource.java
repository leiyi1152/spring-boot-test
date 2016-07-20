package com.spdy.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.spdy.plugin.Page;
import com.spdy.plugin.PagePlugin;

/**
 * 数据源1 多数据源配置
 * @author leiyi
 *
 */

@Configuration
@Component
@MapperScan(basePackages ="com.spdy.mapper",sqlSessionFactoryRef="sqlSessionFactory",sqlSessionTemplateRef="sqlSessionTemplate")
public class MyDataSource {

	@Bean(name = "primaryDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.primary")
	public  DataSource dataSource() {
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}

	@Bean(name = "sqlSessionFactory")
	@Primary
	public  SqlSessionFactory sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		//sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:/mybatis/config/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(resolver
				.getResources("classpath:/mybatis/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "sqlSessionTemplate")
	@Primary
	public SqlSessionTemplate SqlSessionTemplateBean() throws Exception{
		return new org.mybatis.spring.SqlSessionTemplate(this.sqlSessionFactoryBean());
	}
	
	@Bean(name = "platformTransactionManager")
	@Primary
	public  PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
