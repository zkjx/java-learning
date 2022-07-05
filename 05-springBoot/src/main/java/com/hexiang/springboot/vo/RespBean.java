package com.hexiang.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @ClassName RespBean
 * @description:公共返回对象
 * @author: 何翔
 * @Date 2022/2/5 12:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object data;

    public static RespBean success( String msg, Object data) {
        /*
         * @author: 何翔
         * @param: [msg, data]
         * @return: com.yeb.server.utils.RespBean
         * @date: 2022/2/5 12:47
         * @description：成功返回结果（有data数据）
         */
        return new RespBean(200, msg, data);
    }

    public static RespBean success(String msg) {
        /*
         * @author: 何翔
         * @param: [msg]
         * @return: com.yeb.server.utils.RespBean
         * @date: 2022/2/5 12:47
         * @description：成功返回结果（无data数据）
         */
        return success(msg, null);
    }

    public static RespBean error(String msg,Object data) {
        /*
         * @author: 何翔
         * @param: [msg, data]
         * @return: com.yeb.server.utils.RespBean
         * @date: 2022/2/5 12:47
         * @description：失败返回结果（有data数据）
         */
        return new RespBean(500, msg, data);
    }

    public static RespBean error(String msg) {
        /*
         * @author: 何翔
         * @param: [msg]
         * @return: com.yeb.server.utils.RespBean
         * @date: 2022/2/5 12:47
         * @description：失败返回结果（无data数据）
         */
        return error(msg, null);
    }
}
