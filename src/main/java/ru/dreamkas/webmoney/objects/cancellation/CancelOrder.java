package ru.dreamkas.webmoney.objects.cancellation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "setorder")
@XmlAccessorType(XmlAccessType.FIELD)
public class CancelOrder {
    @XmlElement(name = "orderid", required = true)
    private long orderId;

    @XmlElement(name = "posid")
    private String posId;

    @XmlElement(name = "amount")
    private final String amount = "-1";

    public long getOrderId() {
        return orderId;
    }

    public CancelOrder setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getPosId() {
        return posId;
    }

    public CancelOrder setPosId(String posId) {
        this.posId = posId;
        return this;
    }

    public String getAmount() {
        return amount;
    }
}
