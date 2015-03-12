package com.hms.dto.servermsg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SMsgPolicy {

    private Integer transfer       = 0;

    private Integer storeintrans   = 1;

    private Integer mesgsendreport = 0;

    public Integer getTransfer() {
        return transfer;
    }

    public void setTransfer(Integer transfer) {
        this.transfer = transfer;
    }

    public Integer getStoreintrans() {
        return storeintrans;
    }

    public void setStoreintrans(Integer storeintrans) {
        this.storeintrans = storeintrans;
    }

    public Integer getMesgsendreport() {
        return mesgsendreport;
    }

    public void setMesgsendreport(Integer mesgsendreport) {
        this.mesgsendreport = mesgsendreport;
    }

}
