package kfs.kfscrm.tools;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import kfs.kfscrm.domain.KfsNoContact;
import kfs.kfscrm.service.CrmService;
import kfs.kfsvaalib.dlg.SimpleListTemplate;
import kfs.kfsvaalib.fields.KfsEditorField;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class KfsNoContactList extends SimpleListTemplate<KfsNoContact> {

    public KfsNoContactList(KfsI18n i18n, UI ui, CrmService crmService, 
            KfsEditorField.Editor<KfsNoContact> edit, Component... comps) {
        this(i18n, ui, crmService, edit, "KfsContactList", "KfsContactList");
    }

    public KfsNoContactList(KfsI18n i18n, UI ui, CrmService usersService, 
            KfsEditorField.Editor<KfsNoContact> edit,
            String tableName, String i18nPrefix, Component... comps) {
        super(ui, i18n, KfsNoContact.class, edit, tableName, i18nPrefix, "id", false, false);
    }

    @Override
    protected KfsNoContact createNew() {
        return null;
    }

    
}
