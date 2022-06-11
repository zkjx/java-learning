package com.hexiang.redis.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: hexiang
 * @date: 2022/3/6
 * @description:
 */

public class SerializeUtil {

    /**
     * 序列化对象
     *
     * @param obj 对象
     * @return 序列化后的字节数组
     * @throws IOException
     */
    public static byte[] serialize(Object obj) {
        if (null == obj) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream =null;
        try
        {
            byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
            out.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes 对象序列化后的字节数组
     * @return 反序列化后的对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(byte[] bytes)  {
        if (null == bytes) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream in;
        try {
             byteArrayInputStream = new ByteArrayInputStream(bytes);
             in = new ObjectInputStream(byteArrayInputStream);
             return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
       return null;
    }

    /**
     * 反序列化成指定类型的对象
     *
     * @param bytes 对象序列化后的字节数组
     * @param c 反序列化后的对象类型
     * @return 指定类型的对象
     */
    public static <T> T deserialize(byte[] bytes, Class<T> c) {
        return c.cast(deserialize(bytes));
    }

    public static void main(String[] args) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "Logan");
            map.put("age", 26);

            byte[] bytes = serialize(map);
            Map<?, ?> map2 = deserialize(bytes, Map.class);
            System.out.println(map2);

            System.out.println(map.equals(map2));
            System.out.println(map == map2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
