package com.vow.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.google.common.collect.ImmutableMap;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationPropertyKey;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



@Configuration
public class ShardingDataSourceConfig {

    @Bean(name = "shardDataSource")
    public DataSource getShardingDataSource(Environment env) throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        shardingRuleConfig.getBindingTableGroups().add("app_order");
        shardingRuleConfig.getTableRuleConfigs().add(getTableRuleConfiguration("app_order", "order_id", "test_${0..1}.app_order_${0..1}"));

        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("uid", (PreciseShardingAlgorithm<Long>) (availableTargetNames, shardingValue) -> {
            String shard = "_" + (shardingValue.getValue() % 50 / 10 + 1);
            return availableTargetNames.stream().filter(name -> name.endsWith(shard)).findFirst().orElseThrow(IllegalArgumentException::new);
        }));

        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("uid", (PreciseShardingAlgorithm<Long>) (availableTargetNames, shardingValue) -> {
            String shard = "_" + (shardingValue.getValue() % 10 + 1);
            return availableTargetNames.stream().filter(name -> name.endsWith(shard)).findFirst().orElseThrow(IllegalArgumentException::new);
        }));
        shardingRuleConfig.setDefaultDataSourceName("test_0");
        Properties properties = new Properties();
        properties.setProperty(ConfigurationPropertyKey.CHECK_TABLE_METADATA_ENABLED.getKey(), "false");
        properties.setProperty(ConfigurationPropertyKey.MAX_CONNECTIONS_SIZE_PER_QUERY.getKey(), "20");
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(env), shardingRuleConfig, properties);
    }

    private Map<String, DataSource> createDataSourceMap(Environment env) {
        Map<String, DataSource> result = new HashMap<>(5);
        for (int shard = 0; shard <= 1; shard++) {
            DataSource dataSource = createDataSource(env, shard);
            result.put("test_" + shard, dataSource);
        }
        return result;
    }

    private DataSource createDataSource(Environment env, int shard) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(env.getRequiredProperty("spring.datasource.shard.url-" + shard));
        druidDataSource.setUsername(env.getRequiredProperty("spring.datasource.shard.username"));
        druidDataSource.setPassword(env.getRequiredProperty("spring.datasource.shard.password"));
        druidDataSource.setInitialSize(env.getRequiredProperty("spring.datasource.shard.initial-size", Integer.class));
        druidDataSource.setMinIdle(env.getRequiredProperty("spring.datasource.shard.min-idle", Integer.class));
        druidDataSource.setMaxActive(env.getRequiredProperty("spring.datasource.shard.max-active", Integer.class));
        druidDataSource.setMaxWait(60000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("SELECT 'x'");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(false);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return druidDataSource;
    }

    @Bean(name = "shardSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("shardDataSource") DataSource dataSource, MybatisPlusInterceptor interceptor) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mybatis/shard/*Mapper.xml"));
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPlugins(interceptor);
        return sessionFactory.getObject();
    }

    @Bean(name = "shardSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("shardSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "shardMapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.vow.demo.domain.shard.*.mapper");
        configurer.setSqlSessionTemplateBeanName("shardSqlSessionTemplate");
        return configurer;
    }

    @Bean(name = "shardDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("shardDataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    public static TableRuleConfiguration getTableRuleConfiguration(String tableName, String shardingColumn, String actualDataNodes) {
        return getTableRuleConfiguration(tableName, shardingColumn, actualDataNodes, 2, 2);
    }

    public static TableRuleConfiguration getTableRuleConfiguration(String tableName,String shardingColumn,String actualDataNodes,Integer databaseCount,Integer tableCount){

        TableRuleConfiguration  tableRuleConfiguration = new TableRuleConfiguration(tableName, actualDataNodes);
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration(shardingColumn, (PreciseShardingAlgorithm<Long>) (availableTargetNames, shardingValue) -> {
            String shard = "_" + (shardingValue.getValue() % (databaseCount*10) / 10);
            return availableTargetNames.stream().filter(name -> name.endsWith(shard)).findFirst().orElseThrow(IllegalArgumentException::new);
        }));
        tableRuleConfiguration.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration(shardingColumn, (PreciseShardingAlgorithm<Long>) (availableTargetNames, shardingValue) -> {
            String shard = "_" + (shardingValue.getValue() % tableCount);
            return availableTargetNames.stream().filter(name -> name.endsWith(shard)).findFirst().orElseThrow(IllegalArgumentException::new);
        }));


        return tableRuleConfiguration;
    }


    public static void main(String[] args) {
        Integer databaseCount = 5;
        Integer tableCount = 10;
        Long id = 2595280942797427L;
        String shard = "_" + (id % (databaseCount * 10) / 10 + 1);
        String shard2 = "_" + (id % tableCount + 1);
        System.out.println(shard + "====" +shard2);

//        String shard = "_" + (int) (Math.ceil( 557309728 % 50 / 10 + 1));
//        String shard2 = "_" + (557309728 % 10);
//        System.out.println(shard + "====" +shard2);

//        String s = "12321321";
//        System.out.println(StringUtils.isNumeric(s));
    }
}
