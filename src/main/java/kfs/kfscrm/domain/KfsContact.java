package kfs.kfscrm.domain;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextArea;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import kfs.kfsvaalib.convertors.KfsEnumI18n;
import kfs.kfsvaalib.fields.ComboField;
import kfs.kfsvaalib.kfsForm.KfsField;
import kfs.kfsvaalib.kfsForm.KfsMField;
import kfs.kfsvaalib.kfsTable.KfsTablePos;
import kfs.kfsvaalib.kfsTable.Pos;

/**
 *
 * @author pavedrim
 */
@Entity
public class KfsContact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @KfsTablePos(
            @Pos(value = 0, name = "KfsContactList", genName = "id0"))
    private Long id;

    @Size(max = 128)
    @Column(length = 128)
    @KfsTablePos({
        @Pos(5),
        @Pos(value = 5, name = "KfsContactList"),})
    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 60),
        @KfsField(name = "WebWflContactDlg", pos = 60),})
    private String address = "";

    @Size(max = 128)
    @Column(length = 128)
    @KfsTablePos({
        @Pos(4),
        @Pos(value = 4, name = "KfsContactList"),})
    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 50, isRequired = true),
        @KfsField(name = "WebWflContactDlg", pos = 50, isRequired = true),})
    private String lastEmail = "";

    @Size(max = 128)
    @Column(length = 128)
    @KfsTablePos({
        @Pos(1),
        @Pos(value = 1, name = "KfsContactList"),})
    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 10, isRequired = true),
        @KfsField(name = "WebWflContactDlg", pos = 10, isRequired = true),})
    private String firstName = "";

    @Size(max = 128)
    @Column(length = 128)
    @KfsTablePos({
        @Pos(2),
        @Pos(value = 2, name = "KfsContactList")})
    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 20, isRequired = true),
        @KfsField(name = "WebWflContactDlg", pos = 20, isRequired = true),})
    private String secondName = "";

    @Size(max = 128)
    @Column(length = 128)
    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 30, isRequired = true),
        @KfsField(name = "WebWflContactDlg", pos = 30, isRequired = true),})
    private String vocativeName = "";

    @Size(max = 128)
    @Column(length = 128)
    @KfsTablePos({
        @Pos(3),
        @Pos(value = 3, name = "KfsContactList")})
    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 40),
        @KfsField(name = "WebWflContactDlg", pos = 40),})
    private String lastPhone = "";

    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 70),
        @KfsField(name = "WebWflContactDlg", pos = 70),})
    private boolean sendEmails = true;

    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 75),
        @KfsField(name = "WebWflContactDlg", pos = 75),})
    private boolean sendSms = true;

    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 78),
        @KfsField(name = "WebWflContactDlg", pos = 78),})
    private boolean blacklist = false;

    @Enumerated(EnumType.STRING)
    @KfsMField({
        @KfsField(name = "KfsContactDlg", pos = 80, fieldClass = ComboBox.class, comboBoxItemsPrefix = "KfsContactType"),
        @KfsField(name = "WebWflContactDlg", pos = 80, fieldClass = ComboBox.class, comboBoxItemsPrefix = "KfsContactType"),})
    @KfsTablePos({
        @Pos(value = 6, converter = KfsEnumI18n.class),
        @Pos(value = 6, name = "KfsContactList", converter = KfsEnumI18n.class)}
    )
    private KfsContactType contactType = KfsContactType.client;

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

    @KfsMField(@KfsField(pos=799, name = "KfsContactDlg", fieldClass = ComboField.class, readOnly = true))
    @KfsTablePos(@Pos(value=33))
    private String status;
    
    @KfsMField(@KfsField(pos=800, name = "KfsContactDlg", fieldClass = ComboField.class))
    @KfsTablePos(@Pos(value=20))
    private String clasification;
    
    @KfsMField(@KfsField(pos=900, name = "KfsContactDlg", readOnly = true))    
    @Column(length = 64)
    private String lastText;    
    
    @KfsMField(@KfsField(pos=1000, name = "KfsContactDlg", fieldClass = TextArea.class))    
    @Column(length = 1024)
    private String note;    
    
    @KfsMField(@KfsField(pos=1100, name = "KfsContactDlg", readOnly = true))
    @Column(length = 50)
    private String lastSmsSender;
    
    @Override
    public String toString() {
        return firstName + " " + secondName + " " + lastPhone + " " + lastEmail;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the lastEmail
     */
    public String getLastEmail() {
        return lastEmail;
    }

    /**
     * @param lastEmail the lastEmail to set
     */
    public void setLastEmail(String lastEmail) {
        this.lastEmail = lastEmail;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the secondName
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * @param secondName the secondName to set
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * @return the vocativeName
     */
    public String getVocativeName() {
        return vocativeName;
    }

    /**
     * @param vocativeName the vocativeName to set
     */
    public void setVocativeName(String vocativeName) {
        this.vocativeName = vocativeName;
    }

    /**
     * @return the lastPhone
     */
    public String getLastPhone() {
        return lastPhone;
    }

    /**
     * @param lastPhone the lastPhone to set
     */
    public void setLastPhone(String lastPhone) {
        this.lastPhone = lastPhone;
    }

    /**
     * @return the contactType
     */
    public KfsContactType getContactType() {
        return contactType;
    }

    /**
     * @param contactType the contactType to set
     */
    public void setContactType(KfsContactType contactType) {
        this.contactType = contactType;
    }

    /**
     * @return the sendEmails
     */
    public boolean isSendEmails() {
        return sendEmails;
    }

    /**
     * @param sendEmails the sendEmails to set
     */
    public void setSendEmails(boolean sendEmails) {
        this.sendEmails = sendEmails;
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

    public String getLastSmsSender() {
        return lastSmsSender;
    }

    public void setLastSmsSender(String lastSmsSender) {
        this.lastSmsSender = lastSmsSender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClasification() {
        return clasification;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }

    public String getLastText() {
        return lastText;
    }

    public void setLastText(String lastText) {
        this.lastText = lastText;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isSendSms() {
        return sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }

    public boolean isBlacklist() {
        return blacklist;
    }

    public void setBlacklist(boolean blacklist) {
        this.blacklist = blacklist;
    }
}
