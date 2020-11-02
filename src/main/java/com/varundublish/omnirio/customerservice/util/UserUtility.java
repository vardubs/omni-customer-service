package com.varundublish.omnirio.customerservice.util;

import java.util.UUID;

public class UserUtility {

    public static String generateUserId() {
        return "USER-" + UUID.randomUUID().toString();
    }
}
