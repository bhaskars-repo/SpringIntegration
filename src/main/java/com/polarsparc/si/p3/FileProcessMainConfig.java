/*
 * Name:   FileProcessMainConfig
 * Author: Bhaskar S
 * Date:   05/01/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FileProcessMainConfig {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(FileProcessHandler2.class, FileProcessConfig.class);

        for (;;) {
            try {
                Thread.sleep(60000);
            }
            catch (Exception ignored) {
            }
        }
    }
}
