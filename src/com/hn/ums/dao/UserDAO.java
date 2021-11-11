package com.hn.ums.dao;

import com.hn.ums.bean.User;

import java.util.List;

/**
 * @Author: chenzikang
 * @Date: 2021-07-29
 * @Version: 1.0
 */
public interface UserDAO {
    int getCount() throws Exception;
    List<User> getUser(int start, int end) throws  Exception;
    int addUser(User user) throws  Exception;
    int deleteUser(int id) throws  Exception;
    User findUserById(int id) throws  Exception;
    int updateUser(User user) throws  Exception;

}
