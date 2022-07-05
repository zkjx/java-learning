package com.hexiang.order.service;

import com.hexiang.feign.clients.UserClient;
import com.hexiang.feign.pojo.User;
import com.hexiang.order.mapper.OrderMapper;
import com.hexiang.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用feign发起http请求，远程调用，查询用户
        User user = userClient.findById(order.getUserId());
        // 3.封装user到order
        order.setUser(user);
        // 4.返回
        return order;
    }

    //public Order queryOrderById(Long orderId) {
    //    // 1.查询订单
    //    Order order = orderMapper.findById(orderId);
    //    // 2.利用restTemplate发起http请求，远程调用，查询用户
    //    String url = "http://userservice/user/"+order.getUserId();
    //    User user = restTemplate.getForObject(url, User.class);
    //    // 3.封装user到order
    //    order.setUser(user);
    //    // 4.返回
    //    return order;
    //}

}
