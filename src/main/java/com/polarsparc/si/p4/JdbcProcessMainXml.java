/*
 * Name:   JdbcProcessMailXml
 * Author: Bhaskar S
 * Date:   05/08/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcProcessMainXml {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("p4/JdbcProcess.xml");
    }
}
