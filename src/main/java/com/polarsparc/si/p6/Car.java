/*
 * Name:   Car
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p6;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@ToString
public class Car {
    private String ref;
    private String make;

    private long miles;

    private Map<String, Float> services = new TreeMap<>();

    public Car(String make, long miles) {
        this.make = make;
        this.miles = miles;
    }

    public static Car makeClone(Car car) {
        return new Car(car.make, car.miles);
    }
}
