package ru.dreamkas.webmoney.objects.init;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ru.dreamkas.webmoney.objects.adapters.BigDecimalAdapter;

@XmlType(name = "getqr")
@XmlAccessorType(XmlAccessType.FIELD)
public class QRCodeRequest {
    @XmlElement(name = "currency")
    private final String currency = "RUB";

    @XmlElement(name = "amount")
    @XmlJavaTypeAdapter(BigDecimalAdapter.class)
    private BigDecimal amount;

    @XmlElement(name = "posid")
    private String posId;

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public QRCodeRequest setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getPosId() {
        return posId;
    }

    public QRCodeRequest setPosId(String posId) {
        this.posId = posId;
        return this;
    }
}
