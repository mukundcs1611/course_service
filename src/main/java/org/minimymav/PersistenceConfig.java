package org.minimymav;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class PersistenceConfig
{
    @Bean
    public LocalContainerEntityManagerFactoryBean emf(){
        LocalContainerEntityManagerFactoryBean emf=new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("org.minimymav.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(jpaProperties());
        return emf;
    }
    public DataSource dataSource(){
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://dbinstance6331.c9rqzaszzyy5.us-east-1.rds.amazonaws.com:3306/student_enroll_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCOde=false&useSSL=false&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("password");
        return ds;
    }
    @Bean
    public PlatformTransactionManager txnManager(EntityManagerFactory emf){
        JpaTransactionManager txMgr=new JpaTransactionManager(emf);
        return txMgr;
    }
    private Properties jpaProperties(){
        Properties props=new Properties();
        props.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        props.put("hibernate.show_sql",true);
        props.put("hibernate.hbm2ddl.auto","update");
        return props;
    }

}
