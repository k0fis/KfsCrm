package kfs.kfscrm.tools;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.lang.reflect.Field;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.domain.KfsContactMail;
import kfs.kfscrm.service.CrmService;
import kfs.kfsvaalib.comps.KfsButton;
import kfs.kfsvaalib.kfsTable.KfsTable;
import kfs.kfsvaalib.kfsTable.Pos;
import kfs.kfsvaalib.listener.KfsButtonClickListener;
import kfs.kfsvaalib.utils.KfsI18n;
import kfs.kfsvaalib.utils.KfsRefresh;

/**
 *
 * @author pavedrim
 */
public class KfsContactMailList extends VerticalLayout implements KfsRefresh,
        KfsTable.TableGeneratorFactory, Table.ColumnGenerator, Button.ClickListener {

    private final KfsI18n i18n;
    private final String i18nPrefix;
    private final UI ui;
    private final KfsContactMailDlg editDlg;
    private final CrmService crmService;
    private final BeanItemContainer<KfsContactMail> cont;
    private KfsRefresh kfsRefresh;
    private KfsContact contact;

    public KfsContactMailList(KfsI18n i18n, UI ui, CrmService crmService, KfsContactMailDlg edit,
            Component... comps) {
        this(i18n, ui, crmService, edit, "KfsContactMailList", "KfsContactMailList");
    }

    public KfsContactMailList(KfsI18n i18n, UI ui, CrmService usersService, KfsContactMailDlg edit,
            String tableName, String i18nPrefix, Component... comps) {
        this.ui = ui;
        this.i18n = i18n;
        this.i18nPrefix = i18nPrefix;
        this.editDlg = edit;
        this.crmService = usersService;
        this.cont = new BeanItemContainer<>(KfsContactMail.class);
        KfsTable<KfsContactMail> table;
        addComponent(
                (table = new KfsTable<>(tableName, i18n, KfsContactMail.class,
                        i18n.getMsg(i18nPrefix + ".title"), cont, null, this)));
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
        cont.addAll(crmService.mailLoad(contact));
        if (kfsRefresh != null) {
            kfsRefresh.kfsRefresh();
        }
    }

    public void newClick(Button.ClickEvent event) {
        KfsContactMail ne = new KfsContactMail();
        if (editDlg != null) {
            editDlg.show("", ne, this);
        }
    }

    public void refreshClick(Button.ClickEvent event) {
        kfsRefresh();
    }

    @Override
    public Table.ColumnGenerator getColumnGenerator(Class type, Field field, Pos pos) {
        if ((type == KfsContactMail.class) && (field.getName().equals("id"))) {
            return this;
        }
        return null;
    }

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        KfsContactMail ku = (KfsContactMail) itemId;
        if (editDlg != null) {
            KfsButton<KfsContactMail> user = new KfsButton<>(i18n.getMsg(i18nPrefix + ".edit"), ku, this);
            user.addStyleName("small");
            return user;
        }
        return new Label("");
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (editDlg != null) {
            editDlg.show("", ((KfsButton<KfsContactMail>) event.getButton()).getButtonData(), this);
        }
    }

}
