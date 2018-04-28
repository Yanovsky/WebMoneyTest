package ru.dreamkas.webmoney.objects.tools;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import ru.dreamkas.webmoney.objects.check.Invoice;

public class InvoceTypeAdapter extends XmlAdapter<Integer, Invoice.InvoiceState> {

}
