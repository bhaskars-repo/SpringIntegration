/*
 * Name:   JdbcProcessMainConfig
 * Author: Bhaskar S
 * Date:   05/08/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcProcessMainConfig {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(DbProcessHandler2.class, JdbcProcessConfig.class);
    }
}
