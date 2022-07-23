package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/SelectAllServlet")
public class SelectAllServlet extends HttpServlet {
    // 定义一个多态  可以在后面出现多个类的时候 耦合性好
    // 在后面用spring技术可以进行注入  不能new实现类
    private BrandService brandService=new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // 1 调用 brandService
        List<Brand> brands = brandService.SelectAll();

        // 2 类转换为JSON 因为在response中的write 要写入JSON语言的数据
        String toJSONString = JSON.toJSONString(brands);

        // 3 将数据响应给页面
        // 有中文 要先设置一下
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(toJSONString);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
