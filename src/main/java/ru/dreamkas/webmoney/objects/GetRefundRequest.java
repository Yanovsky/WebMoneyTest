package ru.dreamkas.webmoney.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "w3s.request")
public class GetRefundRequest extends SignedRequest {
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
