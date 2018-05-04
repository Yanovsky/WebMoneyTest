package ru.dreamkas.webmoney.objects.init;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.dreamkas.webmoney.objects.base.BaseResponse;

@XmlRootElement(name = "w3s.response")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenerateInvoiceResponse extends BaseResponse {
    @XmlElement(name = "getqr")
    private QRCodeResponse qrCode;

    public QRCodeResponse getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCodeResponse qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return "GenerateQrInvoiceResponse{" +
            "reqNumber=" + getReqNumber() +
            ", result=" + getResult() +
            ", resultDescription='" + getResultDescription() + '\'' +
            "qrCode=" + qrCode +
            '}';
    }
}
