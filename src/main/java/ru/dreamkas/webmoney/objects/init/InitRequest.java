package ru.dreamkas.webmoney.objects.init;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.dreamkas.webmoney.objects.base.BaseRequest;

@XmlRootElement(name = "w3s.request")
public class InitRequest extends BaseRequest {

    @XmlElement(name = "createsession")
    private CreateSession createSession;

    @Override
    protected String getDataForSign() {
        return createSession.getAgentId() + createSession.getPosId() + createSession.getWmId() + getReqNumber();
    }

    public CreateSession getCreateSession() {
        return createSession;
    }

    public void setCreateSession(CreateSession createSession) {
        this.createSession = createSession;
    }
}
