package com.demo.dao;

import com.demo.common.PageModel;
import com.demo.common.PageQuery;
import com.demo.domain.BankSettleBillFlow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface BankSettleBillFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankSettleBillFlow record);

    int insertSelective(BankSettleBillFlow record);

    BankSettleBillFlow selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(BankSettleBillFlow record);

    int updateByPrimaryKey(BankSettleBillFlow record);

    PageModel<BankSettleBillFlow> getPage(PageQuery pageQuery,Map paramsMap);
}