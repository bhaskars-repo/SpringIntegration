/*
 * Name:   CarServiceMainXml
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarServiceMainXml {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(args[0]);

        CarServiceGateway gateway = (CarServiceGateway) context.getBean("gateway");

        gateway.serviceCar(new Car("Toyota", 30000));
        gateway.serviceCar(new Car("Honda", 5000));
    }
}
