package com.hn.ums.controller;

import com.hn.ums.bean.BeanPage;
import com.hn.ums.bean.User;
import com.hn.ums.dao.UserDAO;
import com.hn.ums.service.UserService;
import com.hn.ums.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: chenzikang
 * @Date: 2021-07-29
 * @Version: 1.0
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("utf-8");
        String method = request.getParameter("method");
        Class<? extends UserServlet> clazz = this.getClass();
        try {
            Method m = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            m.setAccessible(true);
            m.invoke(this,request,response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    int totalPage = 0;
    int curPage1 = 0;
    private void getPage(HttpServletRequest request,HttpServletResponse response) {
        String curPage0 = request.getParameter("curPage");
        if(curPage0 == null){
            curPage0 = "1";
        }
        int curPage = Integer.parseInt(curPage0);
        try {
            BeanPage<User> beanPage = userService.getPage(curPage);
            // System.out.println(beanPage);
            totalPage = beanPage.getTotalPage();
            curPage1 = beanPage.getCurPage();
            request.setAttribute("beanPage",beanPage);
            request.getRequestDispatcher("list_page.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        int res = 0;
        try {
            BeanUtils.populate(user,map);
            System.out.println(user);
            res = userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(res > 0 ){
            System.out.println("添加成功");
            //添加一条数据，跳转到最后一页
            //添加在新的最后一页，如果没有，在service层会自动处理大于最大页数的情况
            totalPage++;
            request.getRequestDispatcher("UserServlet?method=getPage&curPage="+totalPage).forward(request,response);
        }else{
            System.out.println("添加失败");
        }
    }
    private void deleteUser(HttpServletRequest request,HttpServletResponse response) {
        String id0 = request.getParameter("id");
        int id = Integer.parseInt(id0);
        int res = 0;
        try {
            res = userService.deleteUser(id);
            if(res > 0 ){
                System.out.println("删除成功");
                //删除后，跳转到删除时的当页，
                request.getRequestDispatcher("UserServlet?method=getPage&curPage="+curPage1).forward(request,response);
            }else{
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void findUserById (HttpServletRequest request,HttpServletResponse response) {
        String id0 = request.getParameter("id");
        int id = Integer.parseInt(id0);
        try {
            User user = userService.findUserById(id);
            if(user != null ){
                System.out.println("通过id成功找到用户");
                request.setAttribute("user",user);
                request.getRequestDispatcher("update.jsp").forward(request,response);
            }else{
                System.out.println("通过id没找到用户");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUser (HttpServletRequest request,HttpServletResponse response){
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
            // System.out.println(user);
            int res = userService.updateUser(user);
            if(res > 0 ){
                // System.out.println("修改成功");
                //修改后，跳转到修改时的页
                request.getRequestDispatcher("UserServlet?method=getPage&curPage="+curPage1).forward(request,response);
            }else{
                System.out.println("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
