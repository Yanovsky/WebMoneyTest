package ru.dreamkas.webmoney.objects;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "refundorder")
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundOrder extends Order {
    @XmlElement(name = "posid")
    private String posId;
    @XmlElement(name = "amount")
    private String amount;

    public String getPosId() {
        return posId;
    }

    public RefundOrder setPosId(String posId) {
        this.posId = posId;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public RefundOrder setAmount(String amount) {
        this.amount = amount;
        return this;
    }
}
