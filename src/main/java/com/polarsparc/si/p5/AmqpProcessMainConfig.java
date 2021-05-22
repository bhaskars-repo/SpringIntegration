/*
 * Name:   AmqpProcessMainConfig
 * Author: Bhaskar S
 * Date:   05/15/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AmqpProcessMainConfig {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AmqpProcessHandler2.class, AmqpProcessConfig.class);
    }
}
