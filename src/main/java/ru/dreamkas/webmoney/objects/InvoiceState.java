package ru.dreamkas.webmoney.objects;

import java.util.Arrays;

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
