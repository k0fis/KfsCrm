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
import kfs.kfscrm.domain.KfsContactPhone;
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
public class KfsContactPhoneList extends VerticalLayout implements KfsRefresh,
        KfsTable.TableGeneratorFactory, Table.ColumnGenerator, Button.ClickListener {

    private final KfsI18n i18n;
    private final String i18nPrefix;
    private final UI ui;
    private final KfsContactPhoneDlg editDlg;
    private final CrmService crmService;
    private final BeanItemContainer<KfsContactPhone> cont;
    private KfsRefresh kfsRefresh;
    private KfsContact contact;

    public KfsContactPhoneList(KfsI18n i18n, UI ui, CrmService crmService, KfsContactPhoneDlg edit,
            Component... comps) {
        this(i18n, ui, crmService, edit, "KfsContactPhoneList", "KfsContactPhoneList");
    }

    public KfsContactPhoneList(KfsI18n i18n, UI ui, CrmService usersService, KfsContactPhoneDlg edit,
            String tableName, String i18nPrefix, Component... comps) {
        this.ui = ui;
        this.i18n = i18n;
        this.i18nPrefix = i18nPrefix;
        this.editDlg = edit;
        this.crmService = usersService;
        this.cont = new BeanItemContainer<>(KfsContactPhone.class);
        KfsTable<KfsContactPhone> table;
        addComponent(
                (table = new KfsTable<>(tableName, i18n, KfsContactPhone.class,
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
        cont.addAll(crmService.phoneLoad(contact));
        if (kfsRefresh != null) {
            kfsRefresh.kfsRefresh();
        }
    }

    public void newClick(Button.ClickEvent event) {
        KfsContactPhone ne = new KfsContactPhone();
        if (editDlg != null) {
            editDlg.show("", ne, this);
        }
    }

    public void refreshClick(Button.ClickEvent event) {
        kfsRefresh();
    }

    @Override
    public Table.ColumnGenerator getColumnGenerator(Class type, Field field, Pos pos) {
        if ((type == KfsContactPhone.class) && (field.getName().equals("id"))) {
            return this;
        }
        return null;
    }

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        KfsContactPhone ku = (KfsContactPhone) itemId;
        if (editDlg != null) {
            KfsButton<KfsContactPhone> user = new KfsButton<>(i18n.getMsg(i18nPrefix + ".edit"), ku, this);
            user.addStyleName("small");
            return user;
        }
        return new Label("");
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (editDlg != null) {
            editDlg.show("", ((KfsButton<KfsContactPhone>) event.getButton()).getButtonData(), this);
        }
    }

}
