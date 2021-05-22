/*
 * Name:   FileProcessHandler2
 * Author: Bhaskar S
 * Date:   05/01/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

import java.io.File;

@Configuration
@EnableIntegration
public class FileProcessHandler2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessHandler2.class);

    @ServiceActivator(inputChannel = "inChannel", outputChannel = "outChannel")
    public File handler(File input) {
        LOGGER.info("Processed input file: {}, size: {} (using Config)", input.getAbsolutePath(), input.length());

        return input;
    }
}
