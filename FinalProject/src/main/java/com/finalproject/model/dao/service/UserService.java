package com.finalproject.model.dao.service;

import com.finalproject.model.dao.DaoFactory;
import com.finalproject.model.dao.UserDao;
import com.finalproject.model.dao.impl.JDBCUserFactory;
import com.finalproject.model.entity.User;
import com.finalproject.model.exception.NotUniqueEmailException;
import com.finalproject.model.exception.NotUniqueIdCodeException;
import com.finalproject.model.exception.NotUniquePhoneException;
import com.finalproject.model.exception.NotUniqueUsernameException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.List;

public class UserService {
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    public static boolean userHasInspector(int userId) {
        return !daoFactory.createTaxReturn().getUserTaxReturn(userId).isEmpty();
    }

    public static Optional<User> username(String username) {
        Optional<User> result;
        try (UserDao userDao = daoFactory.createUser()) {
            result = userDao.findByType("username", username);
        }
        return result;
    }

    public static boolean signIn(String username, String password) {
            Optional<User> user = username(username);
            return user.filter(user1 -> password.equals(user1.getPassword())).isPresent();
    }

    public static List<Integer> getInspectorIdList(){
        try (JDBCUserFactory dao = daoFactory.createUser()){
            return dao.getInspectorIdList();
        }
    }

    public static boolean register(HttpServletRequest request, String fullName,
                                   String username, String email, String idCode, String phone, String password) {
        if (fullName == null && username == null
                && email == null && password == null
                && idCode == null && phone == null) {
            return false;
        } else {
            try (JDBCUserFactory factory = daoFactory.createUser()) {
                User user = new User();
                user.setRole(User.Role.USER);
                user.setFullname(fullName);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(phone);
                user.setIdCode(idCode);
                factory.create(user);
                return true;
            } catch (NotUniqueUsernameException | NotUniquePhoneException | NotUniqueEmailException | NotUniqueIdCodeException e) {
                request.setAttribute("notUnique", e.getMessage());
                return false;
            }
        }
    }

}
