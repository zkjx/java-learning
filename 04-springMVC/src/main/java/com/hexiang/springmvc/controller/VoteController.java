package com.hexiang.springmvc.controller;

import com.hexiang.springmvc.service.StarService;
import com.hexiang.springmvc.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 何翔
 * @date: 2022/5/9
 * @description:
 */

@RestController
public class VoteController {

    @Autowired
    private StarService starService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/vote/{id}")
    public RespBean getVote(@PathVariable("id") Integer id) {
        Integer vote;
        try {
            vote =  starService.getVote(id);
        }catch (Exception e){
            return RespBean.error("error","系统故障，投票异常！");
        }
        return RespBean.success("投票成功！",vote);
    }

}
