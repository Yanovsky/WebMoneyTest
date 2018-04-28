package ru.dreamkas.webmoney.objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlElement(name = "orderid", required = true)
    private long orderId;

    public long getOrderId() {
        return orderId;
    }

    public Order setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }
}
