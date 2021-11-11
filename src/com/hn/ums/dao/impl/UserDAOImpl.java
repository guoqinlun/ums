package com.hn.ums.dao.impl;

import com.hn.ums.bean.User;
import com.hn.ums.dao.UserDAO;
import com.hn.ums.tool.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: chenzikang
 * @Date: 2021-07-29
 * @Version: 1.0
 */
public class UserDAOImpl implements UserDAO {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int getCount() throws Exception{
        int count = jdbcTemplate.queryForObject("select count(*) from user",int.class);
        return count;
    }

    @Override
    public List<User> getUser(int start, int end) throws Exception {
        List<User> list = jdbcTemplate.query("select * from user limit ?,?",new BeanPropertyRowMapper<>(User.class),start,end);
        return list;
    }

    @Override
    public int addUser(User user) throws Exception {
        Object[] arr = {user.getId(),user.getName(),user.getSex(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail()};
        int res = jdbcTemplate.update("insert into user values(?,?,?,?,?,?,?)",arr);
        return res;
    }

    @Override
    public int deleteUser(int id) throws Exception {
        int res = jdbcTemplate.update("delete from user where id = ?",id);
        return res;
    }

    @Override
    public User findUserById(int id) throws Exception {
        User user = jdbcTemplate.queryForObject("select * from user where id = ?",new BeanPropertyRowMapper<>(User.class),id);
        return user;
    }

    @Override
    public int updateUser(User user) throws Exception {
        Object[] arr = {user.getName(),user.getSex(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId()};
        int res = jdbcTemplate.update("update user set name = ?,sex = ?, age = ?, address = ?, qq = ?, email = ? where id = ?",arr);
        return res;
    }
}
