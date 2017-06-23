package com.demo.common;

import com.lenovo.ShopDetail;
import com.lenovo.ShopSDK;

import java.io.Serializable;

/**
 * 租户基础信息
 * Created by yangjj7 on 2016/12/14.
 */
public final class Tenant implements Serializable {
    /**
     * Tenant Id
     */
    private Integer shopId;

    /**
     * 货币编码
     */
    private String currencyCode;

    /**
     * 语言编码
     */
    private String language;
    /**
     * 租户时区
     */
    private String timeZone;

    /**
     * 国家编码
     */
    private String nationalCode;

    /**
     * 国家名称
     */
    private String nationalName;

    /**
     * 币种符号
     */
    private String currencySign;

    public Tenant() {
    }


//    public Tenant(Integer shopId) {
//        ShopDetail shopDetail = ShopSDK.getShopDetail(shopId+"");
//        if(shopDetail != null) {
//            this.shopId = shopId;
//            this.currencyCode = shopDetail.getCurrencyCode();
//            this.language = shopDetail.getLanguage();
//            this.timeZone = shopDetail.getCountryTimeZone();
//            this.nationalCode = shopDetail.getCountryCode();
//            this.nationalName = shopDetail.getCountryName();
//            this.currencySign = shopDetail.getCurrencySymbol();
//        }
//    }

    /**
     * 根据租户id 从租户sdk中初始化租户基本信息
     *
     * @param shopId
     */
    public static Tenant getTenant(Integer shopId) {
        ShopDetail shopDetail = ShopSDK.getShopDetail(shopId + "");

        if (shopDetail == null) return null;

        Tenant tenant = new Tenant();
        tenant.setShopId(shopId);
        tenant.setCurrencyCode(shopDetail.getCurrencyCode());
        tenant.setCurrencySign(shopDetail.getCurrencySymbol());
        tenant.setLanguage(shopDetail.getLanguage());
        tenant.setNationalCode(shopDetail.getCountryName());
        tenant.setNationalName(shopDetail.getCountryName());
        tenant.setTimeZone(shopDetail.getCountryTimeZone());
        return tenant;
    }

    public String getCurrencySign() {
        return currencySign;
    }

    public void setCurrencySign(String currencySign) {
        this.currencySign = currencySign;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tenant tenant = (Tenant) o;

        if (!currencyCode.equals(tenant.currencyCode)) return false;
        if (!currencySign.equals(tenant.currencySign)) return false;
        if (!language.equals(tenant.language)) return false;
        if (!nationalCode.equals(tenant.nationalCode)) return false;
        if (!nationalName.equals(tenant.nationalName)) return false;
        if (!shopId.equals(tenant.shopId)) return false;
        if (!timeZone.equals(tenant.timeZone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shopId.hashCode();
        result = 31 * result + currencyCode.hashCode();
        result = 31 * result + language.hashCode();
        result = 31 * result + timeZone.hashCode();
        result = 31 * result + nationalCode.hashCode();
        result = 31 * result + nationalName.hashCode();
        result = 31 * result + currencySign.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "shopId=" + shopId +
                ", currencyCode='" + currencyCode + '\'' +
                ", language='" + language + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", nationalName='" + nationalName + '\'' +
                ", currencySign='" + currencySign + '\'' +
                '}';
    }

    public static void main(String[] a) {
        Tenant tenant = getTenant(1);
        System.out.println(tenant);
    }
}
