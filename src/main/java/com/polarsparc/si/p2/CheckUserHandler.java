/*
 * Name:   CheckUserHandler
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class CheckUserHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserHandler.class);

    List<String> users = Arrays.asList("alice", "bob", "charlie");

    public boolean checkUser(String id) {
        LOGGER.info("Check user = {}", id);

        if (!users.contains(id)) {
            throw new RuntimeException("User " + id + " is invalid");
        }

        return true;
    }
}
