package kfs.kfscrm.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import kfs.kfsvaalib.kfsForm.KfsField;
import kfs.kfsvaalib.kfsForm.KfsMField;
import kfs.kfsvaalib.kfsTable.KfsTablePos;
import kfs.kfsvaalib.kfsTable.Pos;

/**
 *
 * @author pavedrim
 */
@Entity
public class KfsContactMail {

    @Id
    @KfsTablePos(
            @Pos(10))
    @KfsMField({
        @KfsField(name = "KfsContactMailDlg", pos = 10),
    })            
    String mail;
    
    @Column(nullable = false)
    @KfsTablePos(
            @Pos(30))
    @KfsMField({
        @KfsField(name = "KfsContactMailDlg", pos = 30, readOnly = true),
    })            
    Timestamp imported;

    @Column(nullable = false)
    @KfsTablePos(
            @Pos(20))
    @KfsMField({
        @KfsField(name = "KfsContactMailDlg", pos = 20),
    })                        
    boolean active = true;
    
    @OneToOne
    private KfsContact contact;
    

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Timestamp getImported() {
        return imported;
    }

    public void setImported(Timestamp imported) {
        this.imported = imported;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public KfsContact getContact() {
        return contact;
    }

    public void setContact(KfsContact contact) {
        this.contact = contact;
    }
    
    
}
