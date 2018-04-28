package ru.dreamkas.webmoney.objects;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

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
    private int state;

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
        return InvoiceState.of(state);
    }

    public enum InvoiceState {
        UNPAYED(0, "Счет выставлен, не оплачен"),
        PAYED(2, "Счет оплачен"),
        REJECTED(3,"Отказ от оплаты"),
        TOTAL_REFUND(5,"Полностью возвращен"),
        CHECK_ABSENT(-1, "Счет не выставлен"),
        ORDER_DELETED(-2, "Заказ удален"),
        ORDER_NOT_FOUND(-3,"Заказ не найден");

        private final int state;
        private final String description;

        InvoiceState(int state, String description) {
            this.state = state;
            this.description = description;
        }

        public static InvoiceState of(int state) {
            return Arrays.stream(values()).filter(i -> i.state == state).findFirst().orElse(InvoiceState.ORDER_NOT_FOUND);
        }

        public int getState() {
            return state;
        }

        public String getDescription() {
            return description;
        }
    }

}
