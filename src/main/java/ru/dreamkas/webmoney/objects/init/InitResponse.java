package ru.dreamkas.webmoney.objects.init;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.dreamkas.webmoney.objects.base.Invoice;
import ru.dreamkas.webmoney.objects.base.BaseResponse;

@XmlRootElement(name = "w3s.response")
@XmlAccessorType(XmlAccessType.FIELD)
public class InitResponse extends BaseResponse {
    @XmlElement(name = "invoice")
    private Invoice invoice;

    public Invoice getInvoice() {
        return invoice;
    }

    @Override
    public String toString() {
        return "InitResponse{" +
            "reqNumber=" + getReqNumber() +
            ", result=" + getResult() +
            ", resultDescription='" + getResultDescription() + '\'' +
            ", invoice=" + invoice +
            '}';
    }
}
