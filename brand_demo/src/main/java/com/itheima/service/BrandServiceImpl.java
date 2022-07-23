package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService{

    // 1 创建SqlSessionFactory
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> SelectAll() {
         // 2 获取SqlSession对象
        SqlSession sqlSession=factory.openSession(true);// 填true则事务提交

        // 3 获取BrandMapper
        BrandMapper Mapper = sqlSession.getMapper(BrandMapper.class);

        // 4 调用方法
        List<Brand> brands = Mapper.SelectAll();

        sqlSession.close();

        return brands;

    }

    @Override
    public void add(Brand brand) {
        // 2 获取SqlSession对象
        SqlSession sqlSession=factory.openSession(true);// 填true则事务提交

        // 3 获取BrandMapper
        BrandMapper Mapper = sqlSession.getMapper(BrandMapper.class);

        // 4 调用方法
         Mapper.add(brand);


        sqlSession.close();

    }


}
