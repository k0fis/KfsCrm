package kfs.kfscrm.tools;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.domain.KfsContactLog;
import kfs.kfscrm.service.CrmService;
import kfs.kfsvaalib.kfsTable.KfsTable;
import kfs.kfsvaalib.listener.KfsButtonClickListener;
import kfs.kfsvaalib.utils.KfsI18n;
import kfs.kfsvaalib.utils.KfsRefresh;

/**
 *
 * @author pavedrim
 */
public class KfsContactLogList extends VerticalLayout implements KfsRefresh {

    private final KfsI18n i18n;
    private final String i18nPrefix;
    private final UI ui;
    private final KfsContactLogDlg editDlg;
    private final CrmService crmService;
    private final BeanItemContainer<KfsContactLog> cont;
    private KfsRefresh kfsRefresh;
    private KfsContact contact;

    public KfsContactLogList(KfsI18n i18n, UI ui, CrmService crmService, KfsContactLogDlg edit,
            Component... comps) {
        this(i18n, ui, crmService, edit, "KfsContactLogList", "KfsContactLogList");
    }

    public KfsContactLogList(KfsI18n i18n, UI ui, CrmService usersService, KfsContactLogDlg edit,
            String tableName, String i18nPrefix, Component... comps) {
        this.ui = ui;
        this.i18n = i18n;
        this.i18nPrefix = i18nPrefix;
        this.editDlg = edit;
        this.crmService = usersService;
        this.cont = new BeanItemContainer<>(KfsContactLog.class);
        KfsTable<KfsContactLog> table;
        addComponent(
                (table = new KfsTable<>(tableName, i18n, KfsContactLog.class,
                        i18n.getMsg(i18nPrefix + ".title"), cont, null, null)));
        if (editDlg != null) {
            HorizontalLayout hl = new HorizontalLayout();
            hl.addComponent(
                    new Button(
                            i18n.getMsg(i18nPrefix + ".new"),
                            new KfsButtonClickListener(this, "newClick")));
            addComponent(hl);
            hl.setSpacing(true);
        }
        addComponents(comps);
        table.setWidth("100%");
        table.setHeight("500px");
        setSpacing(true);
        setMargin(true);
    }

    public void show(KfsContact contact, KfsRefresh kfsRefresh) {
        this.contact = contact;
        this.kfsRefresh = null;
        kfsRefresh();
        this.kfsRefresh = kfsRefresh;
    }

    @Override
    public void kfsRefresh() {
        cont.removeAllItems();
        cont.addAll(crmService.logLoad(contact));
        if (kfsRefresh != null) {
            kfsRefresh.kfsRefresh();
        }
    }

    public void newClick(Button.ClickEvent event) {
        KfsContactLog ne = new KfsContactLog();
        if (editDlg != null) {
            editDlg.show(ne, this);
        }
    }

    public void refreshClick(Button.ClickEvent event) {
        kfsRefresh();
    }

}
