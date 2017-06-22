package com.demo.service;

import com.demo.common.PageModel;
import com.demo.common.PageQuery;
import com.demo.domain.BankSettleBillFlow;
import com.demo.manager.impl.bankBillSettleManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxs7 on 2017/6/22.
 */
@RestController
@RequestMapping({"/home"})
public class getBankFlowDemo {
   @Autowired
   bankBillSettleManagerImpl bankBillSettleManager;

   @RequestMapping(value = "/all")
   @ResponseBody
   public String user(){
        BankSettleBillFlow flow = bankBillSettleManager.getBank1(31);
        return flow.toString();
   }

    @RequestMapping(value = "/all2")
    @ResponseBody
    public String user2(){
        PageQuery pageQuery=new PageQuery(1,100);
        Map map=new HashMap();
        map.put("dayIdStart","2017-04-08");
        map.put("dayIdEnd","2017-04-18");

        PageModel<BankSettleBillFlow> flow = bankBillSettleManager.getBank2(pageQuery,map);
        return flow.toString();
    }
}