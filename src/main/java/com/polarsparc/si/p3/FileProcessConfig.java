/*
 * Name:   FileProcessConfig
 * Author: Bhaskar S
 * Date:   05/01/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.RegexPatternFileListFilter;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
@EnableIntegration
@PropertySource("classpath:p3/file.properties")
public class FileProcessConfig {
    @Value("${input.files.directory}")
    private String filesInDir;

    @Value("${output.files.directory}")
    private String filesOutDir;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @InboundChannelAdapter(value = "inChannel", poller = @Poller(fixedDelay = "5000"))
    public MessageSource<File> fileInboundChannelAdapter() {
        FileReadingMessageSource adapter = new FileReadingMessageSource();
        adapter.setDirectory(new File(filesInDir));
        CompositeFileListFilter<File> filter = new CompositeFileListFilter<>();
        filter.addFilter(new AcceptOnceFileListFilter<>());
        filter.addFilter(new RegexPatternFileListFilter("[a-z]+.dat"));
        adapter.setFilter(filter);
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "outChannel")
    public MessageHandler fileOutboundChannelAdapter() {
        FileWritingMessageHandler adapter = new FileWritingMessageHandler(new File(filesOutDir));
        adapter.setDeleteSourceFiles(true);
        adapter.setExpectReply(false);
        return adapter;
    }
}
