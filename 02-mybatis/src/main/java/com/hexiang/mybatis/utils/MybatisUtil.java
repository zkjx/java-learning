package com.hexiang.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author: hexiang
 * @date: 2022/2/28
 * @description: MybatisUtil工具类
 */

public class MybatisUtil {
        public static  SqlSessionFactory factory = null;
        private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();

        static{
            try {
                //加载mybatis配置文件
                InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
                SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
                //会话工厂
                factory = builder.build(is);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        private static SqlSession getSqlSession(boolean isAutoCommit){
            SqlSession sqlSession = local.get();
            if(sqlSession==null){
                sqlSession = factory.openSession(isAutoCommit);
                local.set(sqlSession);
            }
            return sqlSession;
        }
        public static SqlSession getSqlSession(){
            return getSqlSession(false);
        }

        public static <T> T getMapper(Class<T> var1){
            return getSqlSession(false).getMapper(var1);
        }

        public static <T> T getMapper(Class<T> var1,boolean isAutoCommit){return getSqlSession(isAutoCommit).getMapper(var1);}

}
