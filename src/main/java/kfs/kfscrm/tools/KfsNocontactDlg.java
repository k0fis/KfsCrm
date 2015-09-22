package kfs.kfscrm.tools;

import com.vaadin.ui.UI;
import kfs.kfscrm.domain.KfsNoContact;
import kfs.kfscrm.domain.KfsNoContactLock;
import kfs.kfscrm.service.CrmService;
import kfs.kfsvaalib.dlg.SimpleDlgLockTemplate;
import kfs.kfsvaalib.kfsForm.MFieldGroup;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class KfsNocontactDlg extends SimpleDlgLockTemplate<KfsNoContact, KfsNoContactLock, Long> {

    private final CrmService crmService;
    private final String userName;

    public KfsNocontactDlg(UI ui, KfsI18n i18n, CrmService crmService, String userName,
            String dlgName, String i18nPrefix) {
        super(ui, i18n, i18nPrefix, new MFieldGroup(i18n, dlgName, null, KfsNoContact.class), 
                crmService.getLockNoContactservice(), userName);
        this.crmService = crmService;
        this.userName = userName;
    }

    @Override
    protected Long getId(KfsNoContact data) {
        return data.getId();
    }

    @Override
    protected void kfsSave(KfsNoContact data) {
        crmService.nocontactSave(data, userName);
    }

    @Override
    public String getKfsInfo(KfsNoContact data) {
        return " NC ";
    }


}
