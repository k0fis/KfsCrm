package kfs.kfscrm.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class KfsNoContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @KfsTablePos(
            @Pos(value = 0, name = "KfsContactList", genName = "id0"))
    private Long id;
    
    @OneToOne
    private KfsContact contact;
    
    private boolean workDone = false;
    private String note;
    
    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 500, readOnly = true),})
    @Column(nullable = false)
    private Timestamp lastUpdate;

    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 510, readOnly = true),})
    @Column(nullable = false, length = 50)
    private String lastUpdateBy;

    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 400, readOnly = true),})
    @Column(nullable = false)
    private Timestamp created;

    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 410, readOnly = true),})
    @Column(nullable = false, length = 50)
    private String createdBy;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KfsContact getContact() {
        return contact;
    }

    public void setContact(KfsContact contact) {
        this.contact = contact;
    }

    public boolean isWorkDone() {
        return workDone;
    }

    public void setWorkDone(boolean workDone) {
        this.workDone = workDone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
}
