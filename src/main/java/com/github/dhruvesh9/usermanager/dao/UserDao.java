package com.github.dhruvesh9.usermanager.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.github.dhruvesh9.usermanager.model.User;
import com.github.dhruvesh9.usermanager.util.UserUtil;

public class UserDao {
    private static Map<Integer, User> userVault = new HashMap<Integer, User>();
	
	public static Integer createNewUser(User user) {
		user.setId(userVault.size()+1);
        user.setCreatedDt(new Date());

        int token = UserUtil.generateHashCodeForUser(user);

        userVault.put(token, user);

        return token;
	}
	
	public static User getUserById(Integer token) {
		return userVault.get(token);
	}
}
