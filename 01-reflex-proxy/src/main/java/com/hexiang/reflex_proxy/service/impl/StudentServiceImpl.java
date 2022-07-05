package com.hexiang.reflex_proxy.service.impl;

import com.hexiang.reflex_proxy.dao.StudentDao;
import com.hexiang.reflex_proxy.service.IGeneralService;

/**
 * @author: hexiang
 * @date: 2022/3/2
 * @description:
 */

public class StudentServiceImpl implements IGeneralService {

    private StudentDao studentDao;
    public StudentServiceImpl(){}
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    @Override
    public void insert() {studentDao.insertStudent();}

    @Override
    public void delete() {
       studentDao.deleteStudent();
    }

    @Override
    public void update() {
       studentDao.updateStudent();
    }
}
