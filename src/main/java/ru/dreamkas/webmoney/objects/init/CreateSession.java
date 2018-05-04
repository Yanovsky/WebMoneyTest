package ru.dreamkas.webmoney.objects.init;

import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ru.dreamkas.webmoney.objects.base.Order;
import ru.dreamkas.webmoney.utils.WebMoneyUtils;
import ru.dreamkas.webmoney.objects.adapters.BigDecimalAdapter;

@XmlType(name = "createsession")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateSession extends Order {
    @XmlElement(name = "agent_id")
    private int agentId = 1;

    @XmlElement(name = "wmid")
    private String wmId = "0";

    @XmlElement(name = "crc")
    public Integer getCrc() {
        try {
            return WebMoneyUtils.calculateCRC(getPosId(), getOrderId(), BigDecimalAdapter.format(getAmount()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getWmId() {
        return wmId;
    }

    public void setWmId(String wmId) {
        this.wmId = wmId;
    }

}
