package ru.dreamkas.webmoney.objects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "w3c.response")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetOutInvoicesResponse {
    @XmlElement(name = "reqn")
    private long reqNumber;

    @XmlElement(name = "retval")
    private long result;

    @XmlElement(name = "retdesc")
    private String resultDescription;

    @XmlElement(name = "invoices")
    private List<Invoice> invoices;

    public long getReqNumber() {
        return reqNumber;
    }

    public GetOutInvoicesResponse setReqNumber(long reqNumber) {
        this.reqNumber = reqNumber;
        return this;
    }

    public long getResult() {
        return result;
    }

    public GetOutInvoicesResponse setResult(long result) {
        this.result = result;
        return this;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public GetOutInvoicesResponse setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
        return this;
    }

    public List<Invoice> getInvoices() {
        if (invoices == null) {
            invoices = new ArrayList<>();
        }
        return invoices;
    }

}
