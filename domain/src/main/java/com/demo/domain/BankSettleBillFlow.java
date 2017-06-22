package com.demo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BankSettleBillFlow implements Serializable{
    private Integer id;

    private String settleDay;

    private String bankDealTime;

    private String bankCode;

    private String merchantTradeNo;

    private String accountCode;

    private String bankTradeNo;

    private BigDecimal tradeAmonut;

    private BigDecimal serviceFee;

    private String serviceFeeRate;

    private String tradeType;

    private BigDecimal preferentialAmount;

    private String preferentialType;

    private String tradeStatus;

    private String instalmentsNum;

    private String currencyCode;

    private String transMsg;

    private Date bathNo;

    private String isSettle;

    private Integer isValiable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSettleDay() {
        return settleDay;
    }

    public void setSettleDay(String settleDay) {
        this.settleDay = settleDay;
    }

    public String getBankDealTime() {
        return bankDealTime;
    }

    public void setBankDealTime(String bankDealTime) {
        this.bankDealTime = bankDealTime;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getMerchantTradeNo() {
        return merchantTradeNo;
    }

    public void setMerchantTradeNo(String merchantTradeNo) {
        this.merchantTradeNo = merchantTradeNo;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getBankTradeNo() {
        return bankTradeNo;
    }

    public void setBankTradeNo(String bankTradeNo) {
        this.bankTradeNo = bankTradeNo;
    }

    public BigDecimal getTradeAmonut() {
        return tradeAmonut;
    }

    public void setTradeAmonut(BigDecimal tradeAmonut) {
        this.tradeAmonut = tradeAmonut;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getServiceFeeRate() {
        return serviceFeeRate;
    }

    public void setServiceFeeRate(String serviceFeeRate) {
        this.serviceFeeRate = serviceFeeRate;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public BigDecimal getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(BigDecimal preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public String getPreferentialType() {
        return preferentialType;
    }

    public void setPreferentialType(String preferentialType) {
        this.preferentialType = preferentialType;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getInstalmentsNum() {
        return instalmentsNum;
    }

    public void setInstalmentsNum(String instalmentsNum) {
        this.instalmentsNum = instalmentsNum;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTransMsg() {
        return transMsg;
    }

    public void setTransMsg(String transMsg) {
        this.transMsg = transMsg;
    }

    public Date getBathNo() {
        return bathNo;
    }

    public void setBathNo(Date bathNo) {
        this.bathNo = bathNo;
    }

    public String getIsSettle() {
        return isSettle;
    }

    public void setIsSettle(String isSettle) {
        this.isSettle = isSettle;
    }

    public Integer getIsValiable() {
        return isValiable;
    }

    public void setIsValiable(Integer isValiable) {
        this.isValiable = isValiable;
    }

    @Override
    public String toString() {
        return "BankSettleBillFlow{" +
                "id=" + id +
                ", settleDay='" + settleDay + '\'' +
                ", bankDealTime='" + bankDealTime + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", merchantTradeNo='" + merchantTradeNo + '\'' +
                ", accountCode='" + accountCode + '\'' +
                ", bankTradeNo='" + bankTradeNo + '\'' +
                ", tradeAmonut=" + tradeAmonut +
                ", serviceFee=" + serviceFee +
                ", serviceFeeRate='" + serviceFeeRate + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", preferentialAmount=" + preferentialAmount +
                ", preferentialType='" + preferentialType + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", instalmentsNum='" + instalmentsNum + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", transMsg='" + transMsg + '\'' +
                ", bathNo=" + bathNo +
                ", isSettle='" + isSettle + '\'' +
                ", isValiable=" + isValiable +
                '}';
    }
}