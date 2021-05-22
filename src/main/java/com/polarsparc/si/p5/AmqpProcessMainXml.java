/*
 * Name:   AmqpProcessMainXml
 * Author: Bhaskar S
 * Date:   05/15/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p5;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AmqpProcessMainXml {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("p5/AmqpProcess.xml");
    }
}
