package ru.dreamkas.webmoney.objects.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BaseResponse {
    @XmlElement(name = "reqn")
    private long reqNumber;

    @XmlElement(name = "retval")
    private long result;

    @XmlElement(name = "retdesc")
    private String resultDescription;

    public long getReqNumber() {
        return reqNumber;
    }

    public void setReqNumber(long reqNumber) {
        this.reqNumber = reqNumber;
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

}
