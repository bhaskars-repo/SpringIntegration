/*
 * Name:   SftpProcessConfig
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

import java.io.File;

@Configuration
@EnableIntegration
@PropertySource("classpath:p3/sftp.properties")
public class SftpProcessConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SftpProcessConfig.class);

    @Value("${sftp.host}")
    private String sftpHost;

    @Value("${sftp.port:22}")
    private int sftpPort;

    @Value("${sftp.username}")
    private String sftpUser;

    @Value("${sftp.privateKey}")
    private Resource sftpPrivateKey;

    @Value("${sftp.allowUnknownKeys}")
    private boolean sftpAllowUnknownKeys;

    @Value("${sftp.remote.dir}")
    private String sftpRemoteDir;

    @Value("${sftp.local.dir}")
    private String sftpLocalDir;

    @Value("${sftp.file.pattern}")
    private String sftpFilePattern;

    @Bean
    public DefaultSftpSessionFactory sftpSessionFactory() {
        LOGGER.info(String.format("Host: %s, Port: %d, User: %s", sftpHost, sftpPort, sftpUser));

        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost(sftpHost);
        factory.setPort(sftpPort);
        factory.setUser(sftpUser);
        factory.setPrivateKey(sftpPrivateKey);
        factory.setAllowUnknownKeys(sftpAllowUnknownKeys);
        return factory;
    }

    @Bean
    public SftpInboundFileSynchronizer sftpFileSynchronizer() {
        LOGGER.info(String.format("Remote Dir: %s", sftpRemoteDir));

        SftpInboundFileSynchronizer synchronizer = new SftpInboundFileSynchronizer(sftpSessionFactory());
        synchronizer.setDeleteRemoteFiles(false);
        synchronizer.setRemoteDirectory(sftpRemoteDir);
        synchronizer.setFilter(new SftpSimplePatternFileListFilter(sftpFilePattern));
        return synchronizer;
    }

    @Bean
    @InboundChannelAdapter(channel = "inChannel", poller = @Poller(fixedDelay = "5000"))
    public MessageSource<File> sftpChannelAdapter() {
        LOGGER.info(String.format("Local Dir: %s", sftpLocalDir));

        SftpInboundFileSynchronizingMessageSource adapter =
                new SftpInboundFileSynchronizingMessageSource(sftpFileSynchronizer());
        adapter.setLocalDirectory(new File(sftpLocalDir));
        adapter.setMaxFetchSize(1);
        adapter.setLocalFilter(new AcceptOnceFileListFilter<File>());
        return adapter;
    }
}
