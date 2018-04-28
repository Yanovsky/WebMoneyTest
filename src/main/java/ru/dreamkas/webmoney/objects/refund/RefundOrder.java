package ru.dreamkas.webmoney.objects.refund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import ru.dreamkas.webmoney.objects.base.Order;

@XmlType(name = "refundorder")
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundOrder extends Order {

}
