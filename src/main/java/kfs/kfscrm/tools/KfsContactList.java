package kfs.kfscrm.tools;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.lang.reflect.Field;
import java.util.List;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.service.CrmService;
import kfs.kfsvaalib.comps.KfsButton;
import kfs.kfsvaalib.fields.KfsEditorField;
import kfs.kfsvaalib.kfsTable.KfsTable;
import kfs.kfsvaalib.kfsTable.Pos;
import kfs.kfsvaalib.listener.KfsButtonClickListener;
import kfs.kfsvaalib.utils.KfsI18n;
import kfs.kfsvaalib.utils.KfsRefresh;

/**
 *
 * @author pavedrim
 */
public class KfsContactList extends VerticalLayout implements KfsRefresh,
        KfsTable.TableGeneratorFactory, Table.ColumnGenerator, Button.ClickListener {

    private final KfsI18n i18n;
    private final String i18nPrefix;
    private final UI ui;
    private final KfsEditorField.Editor<KfsContact> editDlg;
    private final CrmService crmService;
    private final BeanItemContainer<KfsContact> cont;
    private final KfsTable<KfsContact> table;
    private int limit = 500;
    private KfsRefresh kfsRefresh;
    private KfsContactListFilter filter;

    public KfsContactList(KfsI18n i18n, UI ui, CrmService crmService,
            KfsEditorField.Editor<KfsContact> edit, Component... comps) {
        this(i18n, ui, crmService, edit, "KfsContactList", "KfsContactList");
    }

    public KfsContactList(KfsI18n i18n, UI ui, CrmService usersService,
            KfsEditorField.Editor<KfsContact> edit,
            String tableName, String i18nPrefix, Component... comps) {
        this.ui = ui;
        this.i18n = i18n;
        this.i18nPrefix = i18nPrefix;
        this.editDlg = edit;
        this.crmService = usersService;
        this.cont = new BeanItemContainer<>(KfsContact.class);
        filter = new KfsContactListFilter(null, null, "");
        HorizontalLayout hl = new HorizontalLayout();
        addComponent(
                (table = new KfsTable<>(tableName, i18n, KfsContact.class,
                        i18n.getMsg(i18nPrefix + ".title"), cont, null, this)));
        if (editDlg != null) {
            hl.addComponent(
                    new Button(
                            i18n.getMsg(i18nPrefix + ".new"),
                            new KfsButtonClickListener(this, "newClick")));
        }
        hl.addComponent(
                new Button(
                        i18n.getMsg(i18nPrefix + ".refresh"),
                        new KfsButtonClickListener(this, "refreshClick"))
        );
        addComponent(hl);
        addComponents(comps);
        table.setWidth("100%");
        table.setHeight("500px");
        hl.setSpacing(true);
        setSpacing(true);
        setMargin(true);
    }

    public void show(KfsRefresh kfsRefresh) {
        this.kfsRefresh = null;
        kfsRefresh();
        this.kfsRefresh = kfsRefresh;
    }

    @Override
    public void kfsRefresh() {
        cont.removeAllItems();
        List<KfsContact> lst;
        table.setCaption(filter.caption);
        if (filter.cat != null) {
            lst = crmService.contactLoadByCategory(filter.cat, limit);
        } else if (filter.status != null) {
            lst = crmService.contactLoadByStatus(filter.status, limit);
        } else {
            lst = crmService.contactLoad(limit);
        }
        cont.addAll(lst);
        if (kfsRefresh != null) {
            kfsRefresh.kfsRefresh();
        }
    }

    public void newClick(Button.ClickEvent event) {
        KfsContact ne = new KfsContact();
        if (editDlg != null) {
            editDlg.show(ne, this);
        }
    }

    public void refreshClick(Button.ClickEvent event) {
        kfsRefresh();
    }

    @Override
    public Table.ColumnGenerator getColumnGenerator(Class type, Field field, Pos pos) {
        if ((type == KfsContact.class) && (field.getName().equals("id"))) {
            return this;
        }
        return null;
    }

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        KfsContact ku = (KfsContact) itemId;
        KfsButton<KfsContact> user = new KfsButton<>(i18n.getMsg(i18nPrefix + ".edit"), ku, this);
        user.addStyleName("small");
        return user;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (editDlg != null) {
            editDlg.show(((KfsButton<KfsContact>) event.getButton()).getButtonData(), this);
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setFilter(KfsContactListFilter filter) {
        this.filter = filter;
        kfsRefresh();
    }

}
