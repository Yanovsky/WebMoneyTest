package ru.dreamkas.webmoney.objects.check;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.dreamkas.webmoney.objects.base.BaseResponse;

@XmlRootElement(name = "w3c.response")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetOutInvoicesResponse extends BaseResponse {
    @XmlElement(name = "invoices")
    private List<Invoice> invoices;

    public List<Invoice> getInvoices() {
        if (invoices == null) {
            invoices = new ArrayList<>();
        }
        return invoices;
    }

    @Override
    public String toString() {
        return "GetOutInvoicesResponse{" +
            "reqNumber=" + getReqNumber() +
            ", result=" + getResult() +
            ", resultDescription='" + getResultDescription() + '\'' +
            ", invoices=" + invoices +
            '}';
    }
}
