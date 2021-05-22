/*
 * Name:   FileProcessMainXml
 * Author: Bhaskar S
 * Date:   05/01/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FileProcessMainXml {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("p3/FileProcess.xml");
    }
}
