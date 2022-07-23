package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    // 定义一个多态  可以在后面出现多个类的时候 耦合性好
    // 在后面用spring技术可以进行注入  不能new实现类
    private BrandService brandService=new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // 1 获取前端页面请求过来的数据
        BufferedReader br = request.getReader();
        String s = br.readLine();

        // 2将获取到的数据 转化为对象的形式
        Brand brand = JSON.parseObject(s, Brand.class);

        // 3调用方法 将对象传入add()中
        brandService.add(brand);

        // 4成功后 回应一个success给前端页面
        response.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
