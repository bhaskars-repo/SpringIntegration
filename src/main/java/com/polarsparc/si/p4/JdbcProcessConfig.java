/*
 * Name:   JdbcProcessConfig
 * Author: Bhaskar S
 * Date:   05/08/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableIntegration
@PropertySource("classpath:p4/jdbc.properties")
public class JdbcProcessConfig {
    @Value("${jdbc.driver.class}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUser;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${query.orders}")
    private String selectOrders;

    @Value("${update.orders}")
    private String updateOrders;

    @Bean
    public DataSource jdbcDataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(jdbcDriver);
        source.setUrl(jdbcUrl);
        source.setUsername(jdbcUser);
        source.setPassword(jdbcPassword);
        return source;
    }

    @Bean
    @InboundChannelAdapter(channel = "inChannel", poller = @Poller(fixedDelay = "10000"))
    public MessageSource<Object> jdbcChannelAdapter() {
        JdbcPollingChannelAdapter adapter = new JdbcPollingChannelAdapter(jdbcDataSource(), selectOrders);
        adapter.setUpdateSql(updateOrders);
        adapter.setMaxRows(5);
        return adapter;
    }
}
