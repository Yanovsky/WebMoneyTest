package ru.dreamkas.webmoney.objects.init;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.dreamkas.webmoney.objects.base.BaseRequest;

@XmlRootElement(name = "w3s.request")
public class GenerateInvoiceRequest extends BaseRequest {

    @XmlElement(name = "getqr")
    private QRCodeRequest qrCode;

    @Override
    protected String getDataForSign() {
        return getWmId() + qrCode.getPosId() + qrCode.getCurrency() + getReqNumber();
    }

    public QRCodeRequest getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCodeRequest qrCode) {
        this.qrCode = qrCode;
    }
}
