package com.demo.manager.impl;

import com.demo.common.PageModel;
import com.demo.common.PageQuery;
import com.demo.dao.BankSettleBillFlowMapper;
import com.demo.domain.BankSettleBillFlow;
import com.demo.manager.bankBillSettleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.ManagedBean;
import java.util.Map;

/**
 * Created by zhangxs7 on 2017/6/22.
 */
@ManagedBean
@EnableTransactionManagement
public class bankBillSettleManagerImpl implements bankBillSettleManager {

    @Autowired
    BankSettleBillFlowMapper bankSettleBillFlowMapper;

    @Override
    public BankSettleBillFlow getBank1(Integer id) {
        return bankSettleBillFlowMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageModel<BankSettleBillFlow> getBank2(PageQuery pageQuery, Map map) {
        return bankSettleBillFlowMapper.getPage(pageQuery,map);
    }
}
