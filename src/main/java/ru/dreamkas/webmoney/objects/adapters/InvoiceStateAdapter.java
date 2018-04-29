package ru.dreamkas.webmoney.objects.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import ru.dreamkas.webmoney.objects.base.InvoiceState;

public class InvoiceStateAdapter extends XmlAdapter<Integer, InvoiceState> {

    @Override
    public InvoiceState unmarshal(Integer state) {
        return InvoiceState.of(state);
    }

    @Override
    public Integer marshal(InvoiceState invoiceState) {
        return invoiceState.getState();
    }
}
