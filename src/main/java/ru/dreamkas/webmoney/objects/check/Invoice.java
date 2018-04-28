package ru.dreamkas.webmoney.objects.check;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ru.dreamkas.webmoney.objects.tools.InvoiceState;
import ru.dreamkas.webmoney.objects.tools.InvoiceStateAdapter;

@XmlType(name = "invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {
    @XmlElement(name = "orderid")
    private Long orderId;

    @XmlElement(name = "amount")
    private BigDecimal amount;

    @XmlElement(name = "internalorderid")
    private Long internalOrderId;

    @XmlElement(name = "invoiceid")
    private Long invoiceId;

    @XmlElement(name = "altorderid")
    private Long altOrderId;

    @XmlElement(name = "wmid")
    private String wmId;

    @XmlElement(name = "state")
    @XmlJavaTypeAdapter(InvoiceStateAdapter.class)
    private InvoiceState state;

    public Long getOrderId() {
        return orderId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public Long getInternalOrderId() {
        return internalOrderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getAltOrderId() {
        return altOrderId;
    }

    public String getWmId() {
        return wmId;
    }

    public InvoiceState getState() {
        return state;
    }

}
