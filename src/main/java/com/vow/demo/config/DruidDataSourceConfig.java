package com.vow.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Data
@Configuration
public class DruidDataSourceConfig {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
    private Integer maxWait;
    private Integer timeBetweenEvictionRunsMillis;
    private Integer minEvictableIdleTimeMillis;
    private String validationQuery;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private Boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
    private String xy;
    private String driver;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.main")
    public DruidDataSourceConfig mainDruidDataSourceConfig() {
        return new DruidDataSourceConfig();
    }

    @Bean
    @Primary
    public DataSource mainDataSource(@Qualifier("mainDruidDataSourceConfig") DruidDataSourceConfig config) {
        return dataSourceFactory(config);
    }

    public static DataSource dataSourceFactory(DruidDataSourceConfig config) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(config.url);
        datasource.setUsername(config.username);
        datasource.setPassword(config.password);
        if (StringUtils.isEmpty(config.driver)) {
            datasource.setDriverClassName("com.mysql.jdbc.Driver");
        } else {
            datasource.setDriverClassName(config.driver);
        }
        datasource.setInitialSize(3);
        datasource.setMinIdle(3);
        datasource.setMaxActive(100);
        datasource.setMaxWait(300);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setMinEvictableIdleTimeMillis(300000);
        datasource.setValidationQuery("SELECT 'x'");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setPoolPreparedStatements(false);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return datasource;
    }
}
