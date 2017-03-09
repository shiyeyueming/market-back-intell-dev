package com.zity.intell.dev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lenovo on 2017/3/8.
 */
@Entity(name = "intell_log")
@Table(name = "intell_log")
public class IntellLogDomain {

   @Id
   @GeneratedValue
   private long id;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    @Column(name = "dev_no")
    private String devNo;
    
    @Column(name = "belong_no")
    private String belongNo;

    @Column(name = "status")
    private byte status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getDevNo() {
        return devNo;
    }

    public void setDevNo(String devNo) {
        this.devNo = devNo;
    }

    public String getBelongNo() {
        return belongNo;
    }

    public void setBelongNo(String belongNo) {
        this.belongNo = belongNo;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
