package ru.dreamkas.webmoney.objects.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {
    @XmlElement(name = "orderid")
    private long orderId;

    @XmlElement(name = "invoiceid")
    private long invoiceId;

    @XmlElement(name = "altorderid")
    private long altOrderId;

    public long getOrderId() {
        return orderId;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public long getAltOrderId() {
        return altOrderId;
    }

    @Override
    public String toString() {
        return "Invoice{" +
            "orderId=" + orderId +
            ", invoiceId=" + invoiceId +
            ", altOrderId=" + altOrderId +
            '}';
    }
}
