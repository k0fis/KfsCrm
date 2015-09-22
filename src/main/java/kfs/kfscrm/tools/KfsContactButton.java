package kfs.kfscrm.tools;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.service.CrmService;
import kfs.kfsvaalib.dlg.SelectDlg;
import kfs.kfsvaalib.fields.KfsEditorField;
import kfs.kfsvaalib.utils.KfsI18n;
import kfs.kfsvaalib.utils.KfsRefresh;

/**
 *
 * @author pavedrim
 */
public class KfsContactButton extends CustomField<KfsContact>
        implements Button.ClickListener, KfsRefresh, KfsEditorField.Editor<KfsContact> {

    private final KfsI18n i18n;
    private final String i18nPref;
    private final boolean useNewOnSelectContacts;
    private final HorizontalLayout row;
    private final Button buttonEdit;
    private final Button buttonNew;
    private final Button buttonSelect;
    private final KfsEditorField.Editor<KfsContact> editor;
    private final SelectDlg<KfsContact> selectDlg;
    private final CrmService crmService;
    private KfsRefresh kfsRefresh;

    public KfsContactButton(KfsI18n i18n, String i18nPref, boolean showSelectExisting,
            boolean showCreateNewOne, boolean useNewOnSelectContacts,
            KfsEditorField.Editor<KfsContact> editor,
            SelectDlg<KfsContact> selectDlg, CrmService crmService) {
        this.i18n = i18n;
        this.i18nPref = i18nPref;
        //this.contact = null;
        this.editor = editor;
        this.crmService = crmService;
        this.selectDlg = selectDlg;
        this.useNewOnSelectContacts = useNewOnSelectContacts;
        this.row = new HorizontalLayout();
        row.setSpacing(true);
        if (showCreateNewOne) {
            buttonNew = new Button(i18n.getMsg(i18nPref + ".new"), this);
            row.addComponent(buttonNew);
        } else {
            buttonNew = null;
        }
        if (showSelectExisting) {
            buttonSelect = new Button(i18n.getMsg(i18nPref + ".select"), this);
            row.addComponent(buttonSelect);
        } else {
            buttonSelect = null;
        }
        buttonEdit = new Button(i18n.getMsg(i18nPref + ".edit"), this);
        row.addComponent(buttonEdit);
    }

    @Override
    protected Component initContent() {
        return row;
    }

    @Override
    public Class<KfsContact> getType() {
        return KfsContact.class;
    }

    @Override
    protected void setInternalValue(KfsContact contact) {
        super.setInternalValue(contact);
        kfsRefresh0();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Button eb = event.getButton();
        if ((getInternalValue() != null) && (eb == buttonEdit)) {
            editor.show(getInternalValue(), this);
        } else if ((buttonNew != null) && (eb == buttonNew)) {
            setInternalValue(new KfsContact());
            editor.show(getInternalValue(), this);
        } else if ((buttonSelect != null) && (eb == buttonSelect)) {
            selectDlg.show(this, new SelectDlg.Selected() {

                @Override
                public void selected(Object contact) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }, useNewOnSelectContacts, crmService.contactLoadAll());
        }
    }

    private void kfsRefresh0() {
        String c = null;
        if (getInternalValue() != null) {
            c = getInternalValue().toString();
        }
        if (c != null) {
            c = c.trim();
        }
        if ((c == null) || (c.length() <= 0)) {
            c = i18n.getMsg(i18nPref + ".edit");
        }
        buttonEdit.setCaption(c);

    }

    @Override
    public void kfsRefresh() {
        kfsRefresh0();
        if (kfsRefresh != null) {
            kfsRefresh.kfsRefresh();
        }
    }

    @Override
    public void show(KfsContact data, KfsRefresh kfsRefresh) {
        setInternalValue(data);
        this.kfsRefresh = kfsRefresh;
    }

    @Override
    public KfsContact getKfsValue() {
        return getInternalValue();
    }

    @Override
    public String getKfsInfo(KfsContact data) {
        return data.toString();
    }
}
