package ru.dreamkas.webmoney.objects.refund;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ru.dreamkas.webmoney.objects.check.Order;
import ru.dreamkas.webmoney.objects.tools.BigDecimalAdapter;

@XmlType(name = "refundorder")
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundOrder extends Order {
    @XmlElement(name = "posid")
    private String posId;

    @XmlElement(name = "amount")
    @XmlJavaTypeAdapter(BigDecimalAdapter.class)
    private BigDecimal amount;

    public String getPosId() {
        return posId;
    }

    public RefundOrder setPosId(String posId) {
        this.posId = posId;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public RefundOrder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}
