package com.lab.cashflow.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.lab.cashflow.config.security.SecurityConfig;


@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan(value={"com.lab.cashflow.*"})
@Import({ SecurityConfig.class })
public class Config {

	public static final Logger logger = Logger.getLogger(Config.class);
	
	@Autowired
	Environment env;
	
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(env.getProperty("mysql.url"));
		dataSource.setPassword(env.getProperty("mysql.password"));
		dataSource.setUsername(env.getProperty("mysql.user"));
		logger.info("dataSource initialized");
		return dataSource;
	}
	
	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplate(DataSource dataSource){
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		logger.info("namedParameterJdbctemplate initialized");
		return template;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEMFactoryBean(DataSource ds){
		LocalContainerEntityManagerFactoryBean emBean = new LocalContainerEntityManagerFactoryBean();
		
		emBean.setDataSource(ds);
		emBean.setPackagesToScan("com.lab.cashflow.domain");
		emBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		emBean.setJpaProperties(getVendorProperties());
		logger.info("EMF instantiated");
		
		return emBean;
	}
	
	@Bean
	PlatformTransactionManager getTransactionManager(EntityManagerFactory emf){
		JpaTransactionManager jpaManager = new JpaTransactionManager();
		jpaManager.setEntityManagerFactory(emf);
		
		return jpaManager;
	}

	private Properties getVendorProperties() {
		Properties props = new Properties();
		props.put("org.hibernate.dialect.MySQL5Dialect", this.env.getProperty("hibernate.dialect"));
		props.put("hibernate.show_sql", this.env.getProperty("hibernate.show_sql"));
		props.put("hibernate.packages_to_scan", this.env.getProperty("hibernate.packages_to_scan"));
		props.put("hibernate.hbm2ddl.auto", this.env.getProperty("hibernate.hbm2ddl.auto"));
		return props;
	}
	
	
	@Bean
	public InternalResourceViewResolver jspViewResolver(){
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}
	 
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

	
	
	
	
}
