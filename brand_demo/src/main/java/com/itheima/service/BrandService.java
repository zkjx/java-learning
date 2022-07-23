package com.itheima.service;

import com.itheima.pojo.Brand;

import java.util.List;

public interface BrandService {

       List<Brand> SelectAll();

       void add(Brand brand);
}
