package com.hexiang.springboot.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 何翔
 * @date: 2022/5/26
 * @description: 获取问候时间
 */
@Service
public class TimeService {

    public String getTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        if (a >= 0 && a < 6) {
            return "早上好";
        }
        else if (a >= 6 && a < 12) {
            return "上午好";
        }
        else if (a >= 12 && a < 13) {
            return "中午好";
        }
        else if (a >= 13 && a < 18) {
            return "下午好";
        }
        else if (a >= 18 && a < 24) {
            return "晚上好";
        }
        return "时间未知";
    }
}
