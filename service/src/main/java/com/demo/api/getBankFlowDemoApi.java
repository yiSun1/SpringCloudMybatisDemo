package com.demo.api;

import com.demo.service.GetBankFlowDemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangxs7 on 2017/6/26.
 */
@RestController
public class getBankFlowDemoApi {

    @Autowired
    GetBankFlowDemoServiceImpl service;

    @RequestMapping(value = "/all")
    @ResponseBody
    public String user(){
        return service.user();
    }

    @RequestMapping(value = "/all2")
    @ResponseBody
    public String user2(){
        return service.user2();
    }

    @RequestMapping(value = "/transactionTest")
    @ResponseBody
    @Transactional  //所有需添加事务的地方均需要配置该参数 注意该事务 只能在control 层使用！！！
    /**
     * 默认spring事务只在发生未被捕获的 RuntimeException 时才回滚。
     spring aop  异常捕获原理：被拦截的方法需显式抛出异常，并不能经任何处理，这样aop代理才能捕获到方法的异常，才能进行回滚，默认情况下aop只捕获 RuntimeException 的异常，但可以通过配置来捕获特定的异常并回滚
     换句话说在service的方法中不使用try catch 或者在catch中最后加上throw new runtimeexcetpion（），这样程序异常时才能被aop捕获进而回滚
     解决方案：
     方案1.例如service层处理事务，那么service中的方法中不做异常捕获，或者在catch语句中最后增加throw new RuntimeException()语句，以便让aop捕获异常再去回滚，并且在service上层（webservice客户端，view层action）要继续捕获这个异常并处理
     方案2.在service层方法的catch语句中增加：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();语句，手动回滚，这样上层就无需去处理异常
     mysql  innodb 引擎 才支持事务
     */
    public String transactionTest(){
        return  service.transactionTest();
    }

}
