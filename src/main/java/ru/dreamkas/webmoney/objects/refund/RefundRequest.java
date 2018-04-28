package ru.dreamkas.webmoney.objects.refund;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.dreamkas.webmoney.objects.base.SignedRequest;

@XmlRootElement(name = "w3s.request")
public class RefundRequest extends SignedRequest {
    @XmlElement(name = "refundorder")
    private RefundOrder order;

    @Override
    protected String getDataForSign() {
        return getWmId() + order.getPosId() + order.getOrderId() + getReqNumber();
    }

    public RefundOrder getOrder() {
        return order;
    }

    public void setOrder(RefundOrder order) {
        this.order = order;
        resetSign();
    }
}
