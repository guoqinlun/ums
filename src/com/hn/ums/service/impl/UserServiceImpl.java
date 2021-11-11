package com.hn.ums.service.impl;

import com.hn.ums.bean.BeanPage;
import com.hn.ums.bean.User;
import com.hn.ums.dao.UserDAO;
import com.hn.ums.dao.impl.UserDAOImpl;
import com.hn.ums.service.UserService;

import java.util.List;

/**
 * @Author: chenzikang
 * @Date: 2021-07-29
 * @Version: 1.0
 */
public class UserServiceImpl  implements UserService {
    UserDAO userDAO = new UserDAOImpl();
    @Override
    public BeanPage<User> getPage(int curPage) throws Exception {
        BeanPage<User> beanPage = new BeanPage<>();
        //限制页码在合理范围内
        if(curPage < 1){
            curPage = 1;
        }

        int pageNum = 2;
        beanPage.setPageNum(pageNum);
        int totalCount = userDAO.getCount();
        beanPage.setTotalCount(totalCount);
        int totalPage = (totalCount % pageNum ==0)?(totalCount/pageNum):(totalCount/pageNum+1);
        beanPage.setTotalPage(totalPage);

        //限制页码在合理范围内
        if(curPage > totalPage){
            curPage = totalPage;
        }
        beanPage.setCurPage(curPage);
        int start = (curPage-1)*pageNum;
        int end = pageNum;
        List<User> list = userDAO.getUser(start,end);
        beanPage.setList(list);
        return beanPage;
    }

    @Override
    public int addUser(User user) throws Exception {
        return userDAO.addUser(user);
    }

    @Override
    public int deleteUser(int id) throws Exception {
        return userDAO.deleteUser(id);
    }

    @Override
    public User findUserById(int id) throws Exception {
        return userDAO.findUserById(id);
    }

    @Override
    public int updateUser(User user) throws Exception {
        return userDAO.updateUser(user);
    }
}
