package ru.dreamkas.webmoney.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.dreamkas.webmoney.objects.base.SignedRequest;

@XmlRootElement(name = "w3s.request")
public class GetOutInvoicesRequest extends SignedRequest {
    @XmlElement(name = "posid")
    private String posId;
    @XmlElement(name = "outinvoices")
    private OrderContent outInvoices = new OrderContent();

    public OrderContent getOutInvoices() {
        return outInvoices;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
        resetSign();
    }

    @Override
    protected String getDataForSign() {
        return getWmId() + posId + getReqNumber();
    }
}
