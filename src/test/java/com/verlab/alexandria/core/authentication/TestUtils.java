package com.verlab.alexandria.core.authentication;

import com.verlab.alexandria.core.authentication.entity.User;

public class TestUtils {
    public static User getUser(String name) {
        return new User(1, "Test " , "");
    }
}
