package com.demo.service;

import com.demo.common.PageModel;
import com.demo.common.PageQuery;
import com.demo.common.RemoteResult;
import com.demo.domain.BankSettleBillFlow;
import com.demo.manager.impl.bankBillSettleManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxs7 on 2017/6/22.
 */
@Service
public class GetBankFlowDemoServiceImpl implements GetBankFlowDemoService {

   @Autowired
   bankBillSettleManagerImpl bankBillSettleManagerImpl;

   public String user(){
        BankSettleBillFlow flow = bankBillSettleManagerImpl.getBank1(31);
        return flow.toString();
   }

    public String user2(){
        PageQuery pageQuery=new PageQuery(1,100);
        Map map=new HashMap();
        map.put("dayIdStart","2017-04-08");
        map.put("dayIdEnd","2017-04-18");

        PageModel<BankSettleBillFlow> flow = bankBillSettleManagerImpl.getBank2(pageQuery,map);
        return flow.toString();
    }

    public String transactionTest(){
        BankSettleBillFlow bankSettleBillFlow=new BankSettleBillFlow();
        bankSettleBillFlow.setId(31);
        RemoteResult<Boolean> flow = bankBillSettleManagerImpl.insertTransaction(bankSettleBillFlow);
        return flow.toString();
    }
}
