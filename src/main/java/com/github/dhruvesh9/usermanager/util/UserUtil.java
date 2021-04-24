package com.github.dhruvesh9.usermanager.util;

import com.github.dhruvesh9.usermanager.model.User;

public class UserUtil {
    public static int generateHashCodeForUser(User user) {
        int hashCode = 0;
        hashCode = Integer.hashCode(user.getId()) + user.getFirstName().hashCode() + user.getLastName().hashCode() + user.getEmailAddress().hashCode()
                + user.getPassword().hashCode() + user.getCreatedDt().hashCode();
        return hashCode;
    }
}
