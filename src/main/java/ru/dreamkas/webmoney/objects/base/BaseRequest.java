package ru.dreamkas.webmoney.objects.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ru.dreamkas.webmoney.utils.WebMoneyUtils;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class BaseRequest {

    @XmlElement(name = "reqn")
    private long reqNumber;
    @XmlElement(name = "wmid")
    private String wmId;

    private String calculatedSign;

    protected abstract String getDataForSign();

    public long getReqNumber() {
        return reqNumber;
    }

    public void setReqNumber(long reqNumber) {
        this.reqNumber = reqNumber;
        resetSign();
    }

    public String getWmId() {
        return wmId;
    }

    public void setWmId(String wmId) {
        this.wmId = wmId;
        resetSign();
    }

    @XmlElement(name = "sign")
    public String getSign() {
        try {
            if (calculatedSign == null) {
                calculatedSign = WebMoneyUtils.calculateSignature(getDataForSign());
            }
            return calculatedSign;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void resetSign() {
        this.calculatedSign = null;
    }

}
