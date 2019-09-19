package com.zhaozhiguang.component.admin.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.zhaozhiguang.component.admin.mapper"})
@EnableJpaRepositories("com.zhaozhiguang.component.admin.repository")
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.druid")
    public DataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }



}
