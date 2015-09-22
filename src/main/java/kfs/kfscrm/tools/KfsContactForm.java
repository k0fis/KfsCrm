package kfs.kfscrm.tools;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.service.CrmService;
import kfs.kfsvaalib.fields.KfsEditorField;
import kfs.kfsvaalib.kfsForm.MFieldGroup;
import kfs.kfsvaalib.listener.KfsButtonClickListener;
import kfs.kfsvaalib.utils.KfsI18n;
import kfs.kfsvaalib.utils.KfsRefresh;

/**
 *
 * @author pavedrim
 */
public class KfsContactForm extends FormLayout implements KfsEditorField.Editor<KfsContact> {

    private final KfsI18n i18n;
    private final String i18nPref;
    private final MFieldGroup fg;
    private final CrmService crmService;
    private final String userName;
    private KfsContact contact;
    private KfsRefresh refresh;

    public KfsContactForm(KfsI18n i18n, CrmService crmService, String userName) {
        this(i18n, crmService, userName, "KfsContactDlg", "KfsContactDlg");
    }

    public KfsContactForm(KfsI18n i18n, CrmService crmService, String userName, String dlgName,
            String i18nPrefix) {
        this.i18n = i18n;
        this.i18nPref = i18nPrefix;
        this.crmService = crmService;
        this.userName = userName;
        fg = new MFieldGroup(i18n, dlgName, null, KfsContact.class);
        addComponents(fg.getSortedComponents());
        setWidth("500px");
        Button b = new Button(i18n.getMsg(i18nPref + ".save"),
                new KfsButtonClickListener(this, "save"));
        addComponent(b);
    }

    public CharSequence validate(String prefix, KfsContact contact) {
        fg.setItems(contact);
        return fg.validate(prefix);
    }

    public void save(Button.ClickEvent event) {
        try {
            fg.commit();
        } catch (Exception ex) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            CharSequence val = fg.validate("");
            Notification.show(i18n.getMsg(i18nPref + ".cannotSave") + ":\n" + val,
                    Notification.Type.WARNING_MESSAGE);
            return;
        }
        crmService.contactSave(contact, userName);
        Notification.show(i18n.getMsg(i18nPref + ".saved"));
        if (refresh != null) {
            refresh.kfsRefresh();
        }
    }

    @Override
    public void show(KfsContact contact, KfsRefresh refresh) {
        if (contact.getId() != null) {
            this.contact = crmService.contactRefresh(contact);
        } else {
            this.contact = contact;
        }
        this.refresh = refresh;
        super.setCaption(getKfsInfo(contact));
        fg.setItems(contact);
    }
    
    @Override
    public String getKfsInfo(KfsContact c) {
        if (c == null) {
            return "";
        }
        return c.getFirstName() + " " + c.getSecondName();
    }

    @Override
    public KfsContact getKfsValue() {
        return contact;
    }

}
