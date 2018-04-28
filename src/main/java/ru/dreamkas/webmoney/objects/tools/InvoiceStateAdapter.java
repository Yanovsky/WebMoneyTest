package ru.dreamkas.webmoney.objects.tools;

import javax.xml.bind.annotation.adapters.XmlAdapter;

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
