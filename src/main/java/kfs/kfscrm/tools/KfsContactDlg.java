package kfs.kfscrm.tools;

import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.UI;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.domain.KfsContactLock;
import kfs.kfscrm.service.CrmService;
import kfs.kfsvaalib.dlg.SimpleDlgLockTemplate;
import kfs.kfsvaalib.fields.ComboEnumField;
import kfs.kfsvaalib.kfsForm.KfsField;
import kfs.kfsvaalib.kfsForm.MFieldGroup;
import kfs.kfsvaalib.utils.KfsI18n;
import kfs.kfsvaalib.utils.KfsRefresh;

/**
 *
 * @author pavedrim
 * @param <TClass> Classification Enum or null
 * @param <TStatus> Status Enum or null
 */
public class KfsContactDlg<TClass extends Enum, TStatus extends Enum> 
extends SimpleDlgLockTemplate<KfsContact, KfsContactLock, Long> {

    private final CrmService crmService;
    private final String userName;

    public KfsContactDlg(UI ui, KfsI18n i18n, CrmService crmService, String userName, 
            Class<TClass> clasEnum, Class<TStatus> statusEnum) {
        this(ui, i18n, crmService, userName, "KfsContactDlg", "KfsContactDlg", clasEnum, statusEnum, null, null);
    }

    public KfsContactDlg(UI ui, final KfsI18n i18n, CrmService crmService, String userName, String dlgName,
            String i18nPrefix, final Class<TClass> clasEnum, final Class<TStatus> statusEnum, 
            Component right, KfsRefresh rightRefresh) {
        super(ui, i18n, i18nPrefix, new MFieldGroup(i18n, dlgName, new MFieldGroup.MFieldFactory() {

            @Override
            public Field createField(Class objectClass, String filedName, KfsField field, Class fieldClass) {
                if (filedName.equals("clasification") && (clasEnum != null)) {
                    return new ComboEnumField(clasEnum, field.caption(), !field.isRequired(), i18n);
                }
                if (filedName.equals("status") && (statusEnum != null)) {
                    return new ComboEnumField(statusEnum, field.caption(), !field.isRequired(), i18n);
                }
                return null;
            }
        }, KfsContact.class), crmService.getLockContactservice(), userName, right, rightRefresh);
        this.crmService = crmService;
        this.userName = userName;
    }

    @Override
    public String getKfsInfo(KfsContact data) {
        if (data == null) {
            return "";
        }
        return data.getFirstName() + " " + data.getSecondName();
        
    }

    @Override
    protected void kfsSave(KfsContact data) {
        crmService.contactSave(data, userName);
    }

    @Override
    protected Long getId(KfsContact data) {
        return data.getId();
    }

}
