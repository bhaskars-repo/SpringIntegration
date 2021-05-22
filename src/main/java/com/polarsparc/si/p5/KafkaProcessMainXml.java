/*
 * Name:   KafkaProcessMainXml
 * Author: Bhaskar S
 * Date:   05/15/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p5;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KafkaProcessMainXml {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("p5/KafkaProcess.xml");
    }
}
