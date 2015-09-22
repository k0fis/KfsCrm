package kfs.kfscrm.tools;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import kfs.kfscrm.domain.KfsContactLog;
import kfs.kfscrm.service.CrmService;
import kfs.kfsvaalib.kfsForm.MFieldGroup;
import kfs.kfsvaalib.listener.KfsButtonClickListener;
import kfs.kfsvaalib.utils.KfsI18n;
import kfs.kfsvaalib.utils.KfsRefresh;

/**
 *
 * @author pavedrim
 */
public class KfsContactLogDlg extends Window {

    private final UI ui;
    private final KfsI18n i18n;
    private final String i18nPref;
    private final MFieldGroup fg;
    private final CrmService crmService;
    private final String userName;
    private KfsContactLog contactLog;
    private KfsRefresh refresh;

    public KfsContactLogDlg(UI ui, KfsI18n i18n, CrmService crmService, String userName) {
        this(ui, i18n, crmService, userName, "KfsContactLogDlg", "KfsContactLogDlg");
    }

    public KfsContactLogDlg(UI ui, KfsI18n i18n, CrmService crmService, String userName, String dlgName,
            String i18nPrefix) {
        this.ui = ui;
        this.i18n = i18n;
        this.i18nPref = i18nPrefix;
        this.crmService = crmService;
        this.userName = userName;
        fg = new MFieldGroup(i18n, dlgName, null, KfsContactLog.class);
        FormLayout f = new FormLayout();
        f.addComponents(fg.getSortedComponents());
        setWidth("500px");
        Button b = new Button(i18n.getMsg(i18nPref + ".save"),
                new KfsButtonClickListener(this, "save"));
        f.addComponent(b);
        f.setMargin(true);
        setContent(f);
        setModal(true);
        setCaption(i18n.getMsg(i18nPref + ".title"));
    }

    public CharSequence validate(String prefix, KfsContactLog contact) {
        fg.setItems(contact);
        return fg.validate(prefix);
    }

    public void save(Button.ClickEvent event) {
        try {
            fg.commit();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            CharSequence val = fg.validate("");
            Notification.show(i18n.getMsg(i18nPref + ".cannotSave") + ":\n" + val,
                    Notification.Type.WARNING_MESSAGE);
            return;
        }
        crmService.logSave(contactLog, userName);
        Notification.show(i18n.getMsg(i18nPref + ".saved"));
        if (refresh != null) {
            refresh.kfsRefresh();
        }
    }

    public void show(String text, KfsContactLog contact, KfsRefresh refresh) {
        this.contactLog = contact;
        this.refresh = refresh;
        fg.setItems(contact);
        ui.addWindow(this);
    }

}
