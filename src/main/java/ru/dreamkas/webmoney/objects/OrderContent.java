package ru.dreamkas.webmoney.objects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderContent {
    @XmlElement(name = "order")
    private List<Order> outInvoices;

    public List<Order> getOutInvoices() {
        if (outInvoices == null) {
            outInvoices = new ArrayList<>();
        }
        return outInvoices;
    }
}
