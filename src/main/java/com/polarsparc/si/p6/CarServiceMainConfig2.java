/*
 * Name:   CarServiceMainConfig2
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CarServiceMainConfig2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RefNoHandler2.class,
                CarServiceHandler2.class,
                CarServiceGateway2.class,
                CarServiceConfig2.class);

        CarServiceGateway2 gateway = (CarServiceGateway2) context.getBean("gateway");

        gateway.serviceCar(new Car("Toyota", 30000));
        gateway.serviceCar(new Car("Honda", 5000));
    }
}
