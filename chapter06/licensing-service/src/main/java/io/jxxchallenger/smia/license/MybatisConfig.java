package io.jxxchallenger.smia.license;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jxxchallenger.smia.license.repository.LicenseMapper;

@Configuration
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
    
    @Bean
    public MapperFactoryBean<LicenseMapper> licenseMapper(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<LicenseMapper> mapperFactoryBean = new MapperFactoryBean<LicenseMapper>(LicenseMapper.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return mapperFactoryBean;
    }
}
