package com.hexiang.springmvc.controller;

import com.hexiang.springmvc.service.BookService;
import com.hexiang.springmvc.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: 何翔
 * @date: 2022/5/10
 * @description:
 */

@RestController
public class SaleController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/sale")
    public RespBean sale(@RequestBody Map<String, Object> map) {
        try {
            Integer saleNum = (Integer) map.get("saleNum");
            bookService.sale(saleNum);
            List<Integer> bookNum = bookService.getBookNum();
            map.put("storeNum",bookNum.get(0));
            map.put("soldNum",bookNum.get(1));
            return RespBean.success("卖出成功", map);
        }catch (Exception e) {
           e.printStackTrace();
        }
        return RespBean.error("卖出失败！");
    }

    @RequestMapping("/getBookNum")
    public RespBean getBookNum(Map<String, Object> map) {
        try {
            List<Integer> bookNum = bookService.getBookNum();
            map.put("storeNum",bookNum.get(0));
            map.put("soldNum",bookNum.get(1));
            return RespBean.success("获取库存成功！",map);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("获取库存信息失败！");
    }

}
