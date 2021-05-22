/*
 * Name:   StrUpCaseHandler3
 * Author: Bhaskar S
 * Date:   04/16/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StrUpCaseHandler3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCaseHandler3.class);

    public String handler(String msg) {
        LOGGER.info("INPUT: msg = {}", msg);

        try {
            Thread.sleep(1000);
        }
        catch (Exception ignored) {
        }

        return msg.toUpperCase();
    }
}
