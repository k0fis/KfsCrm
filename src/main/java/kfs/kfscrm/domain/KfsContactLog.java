package kfs.kfscrm.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import kfs.kfscrm.api.KfsCrmDetail;

/**
 *
 * @author pavedrim
 */
@Entity
public class KfsContactLog implements KfsCrmDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String logUser;
    
    @OneToOne(optional = false)
    private KfsContact contact;
    
    @Lob
    @Column(nullable = false)
    private String logText;
    
    @Column(nullable = false)
    private Timestamp logDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogUser() {
        return logUser;
    }

    public void setLogUser(String logUser) {
        this.logUser = logUser;
    }

    public KfsContact getContact() {
        return contact;
    }

    public void setContact(KfsContact contact) {
        this.contact = contact;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    public Timestamp getLogDate() {
        return logDate;
    }

    public void setLogDate(Timestamp logDate) {
        this.logDate = logDate;
    }

    @Override
    public Timestamp getDetailDate() {
        return logDate;
    }

    public static String crmDetailName = "Pozn";

    @Override
    public String getDetailName() {
        return crmDetailName;
    }

    @Override
    public String getDetailText() {
        return logUser +" - " + ((logText.length() > 40)?logText.substring(0,40)+"...":logText);
    }
}
