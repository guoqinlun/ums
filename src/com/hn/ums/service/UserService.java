package com.hn.ums.service;

import com.hn.ums.bean.BeanPage;
import com.hn.ums.bean.User;

/**
 * @Author: chenzikang
 * @Date: 2021-07-29
 * @Version: 1.0
 */
public interface UserService {
    BeanPage<User> getPage(int curPage) throws Exception;
    int addUser(User user) throws  Exception;
    int deleteUser(int id) throws  Exception;
    User findUserById(int id) throws  Exception;
    int updateUser(User user) throws  Exception;
}
