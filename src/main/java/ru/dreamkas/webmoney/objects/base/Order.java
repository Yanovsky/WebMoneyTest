package ru.dreamkas.webmoney.objects.base;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ru.dreamkas.webmoney.objects.adapters.BigDecimalAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlElement(name = "orderid", required = true)
    private long orderId;

    @XmlElement(name = "posid")
    private String posId;

    @XmlElement(name = "amount")
    @XmlJavaTypeAdapter(BigDecimalAdapter.class)
    private BigDecimal amount;

    public Order() {
    }

    public Order(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
    }

    public Order setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }
}
