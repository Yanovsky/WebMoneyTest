package ru.dreamkas.webmoney.objects.init;

import javax.xml.bind.annotation.XmlElement;

import ru.dreamkas.webmoney.objects.base.BaseInvoice;
import ru.dreamkas.webmoney.objects.base.BaseResponse;

public class InitResponse extends BaseResponse {
    @XmlElement(name = "invoice")
    private BaseInvoice invoice;

    public BaseInvoice getInvoice() {
        return invoice;
    }

}
