/*
 * Name:   StrUpCaseHandler
 * Author: Bhaskar S
 * Date:   04/16/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StrUpCaseHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCaseHandler.class);

    public String handler(String msg) {
        LOGGER.info("INPUT: msg = {}", msg);

        return msg.toUpperCase();
    }
}
