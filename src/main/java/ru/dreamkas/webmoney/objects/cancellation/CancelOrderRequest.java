package ru.dreamkas.webmoney.objects.cancellation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.dreamkas.webmoney.objects.base.BaseRequest;

@XmlRootElement(name = "w3s.request")
public class CancelOrderRequest extends BaseRequest {
    @XmlElement(name = "setorder")
    private CancelOrder order;

    @Override
    protected String getDataForSign() {
        return getWmId()+ order.getPosId() + order.getOrderId() + order.getAmount() + getReqNumber();
    }

    public CancelOrder getOrder() {
        return order;
    }

    public CancelOrderRequest setOrder(CancelOrder order) {
        this.order = order;
        return this;
    }

}
