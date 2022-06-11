package com.hexiang.reflex_proxy.proxy;

import com.hexiang.reflex_proxy.service.IGeneralService;

/**
 * @author: hexiang
 * @date: 2022/3/2
 * @description: 静态代理
 */

public class StaticProxy {

    //只能代理IGeneralService接口的实现类，要想用静态代理，就必须实现IGeneralService接口
    private IGeneralService iGeneralService;

    public StaticProxy(IGeneralService iGeneralService){
        this.iGeneralService = iGeneralService;
    }

    public void insert() {
        begin();
        iGeneralService.insert();
        commit();
    }


    public void delete() {
        begin();
        iGeneralService.delete();
        commit();
    }


    public void update() {
        begin();
        iGeneralService.update();
        commit();
    }

    public void begin(){
        System.out.println("---开启事务---");
    }
    public void commit(){
        System.out.println("---提交事务---");
    }


}
