package ru.dreamkas.webmoney.objects.check;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ru.dreamkas.webmoney.objects.adapters.InvoiceStateAdapter;
import ru.dreamkas.webmoney.objects.base.Invoice;
import ru.dreamkas.webmoney.objects.base.InvoiceState;

@XmlAccessorType(XmlAccessType.FIELD)
public class OutInvoice extends Invoice {
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
        return "OutInvoice{" +
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
