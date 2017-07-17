package com.demo.manager;

import com.demo.common.PageModel;
import com.demo.common.PageQuery;
import com.demo.common.RemoteResult;
import com.demo.domain.BankSettleBillFlow;

import java.util.Map;

/**
 * Created by zhangxs7 on 2017/6/22.
 */
public interface BankBillSettleManager {
    public BankSettleBillFlow getBank1(Integer id);
    public PageModel<BankSettleBillFlow> getBank2(PageQuery pageQuery,Map map);

    public RemoteResult<Boolean> insertTransaction(BankSettleBillFlow bankSettleBillFlow);

}
