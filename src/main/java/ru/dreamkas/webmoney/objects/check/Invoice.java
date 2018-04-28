package ru.dreamkas.webmoney.objects.check;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ru.dreamkas.webmoney.objects.base.BaseInvoice;
import ru.dreamkas.webmoney.tools.InvoiceState;
import ru.dreamkas.webmoney.tools.InvoiceStateAdapter;

@XmlType(name = "invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice extends BaseInvoice {
    @XmlElement(name = "amount")
    private BigDecimal amount;

    @XmlElement(name = "internalorderid")
    private long internalOrderId;

    @XmlElement(name = "wmid")
    private String wmId;

    @XmlElement(name = "state")
    @XmlJavaTypeAdapter(InvoiceStateAdapter.class)
    private InvoiceState state;

    public long getInternalOrderId() {
        return internalOrderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getWmId() {
        return wmId;
    }

    public InvoiceState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Invoice{" +
            "orderId=" + getOrderId() +
            ", amount=" + amount +
            ", internalOrderId=" + internalOrderId +
            ", invoiceId=" + getInvoiceId() +
            ", altOrderId=" + getAltOrderId() +
            ", wmId='" + wmId + '\'' +
            ", state=" + state + (state != null ? "(" + state.getDescription()+")" : "") +
            '}';
    }
}
