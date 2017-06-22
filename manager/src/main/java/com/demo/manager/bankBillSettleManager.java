package com.demo.manager;

import com.demo.common.PageModel;
import com.demo.common.PageQuery;
import com.demo.domain.BankSettleBillFlow;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

/**
 * Created by zhangxs7 on 2017/6/22.
 */
public interface bankBillSettleManager {
    public BankSettleBillFlow getBank1(Integer id);
    public PageModel<BankSettleBillFlow> getBank2(PageQuery pageQuery,Map map);

}
