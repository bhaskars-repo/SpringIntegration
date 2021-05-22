/*
 * Name:   FileProcessHandler
 * Author: Bhaskar S
 * Date:   05/01/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileProcessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessHandler.class);

    public File handler(File input) {
        LOGGER.info("Processed input file: {}, size: {} (using Xml)", input.getAbsolutePath(), input.length());

        return input;
    }
}
