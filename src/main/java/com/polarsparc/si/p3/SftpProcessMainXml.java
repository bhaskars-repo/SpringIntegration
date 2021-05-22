/*
 * Name:   SftpProcessMainXml
 * Author: Bhaskar S
 * Date:   05/01/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SftpProcessMainXml {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("p3/SftpProcess.xml");
    }
}
