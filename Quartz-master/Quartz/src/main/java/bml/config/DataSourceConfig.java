package bml.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Bean(name = "db1")
    @Primary
    @ConfigurationProperties("spring.datasource.db1")
    @QuartzDataSource
    public DruidDataSource dataSource1 () {
        return DruidDataSourceBuilder.create().build();
    }

}
