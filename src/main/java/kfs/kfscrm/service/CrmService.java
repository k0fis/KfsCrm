package kfs.kfscrm.service;

import java.util.List;
import kfs.kfscrm.api.CrmContactLockService;
import kfs.kfscrm.api.CrmNoContactLockService;
import kfs.kfscrm.api.KfsCrmDetail;
import kfs.kfscrm.api.KfsCrmDetailLoader;
import kfs.kfscrm.domain.*;

/**
 *
 * @author pavedrim
 */
public interface CrmService {

    KfsContact contactCreate (String mail, String phone, String user);
    void contactSave (KfsContact contact, String usr);
    void contactAddMail (KfsContact contact, String mail, boolean useAsLast, String user);
    void contactAddPhone (KfsContact contact, String phone, boolean useAsLast, String user);
    KfsContact contactRefresh(KfsContact contact);
    List<KfsContact> contactLoadAll();
    List<KfsContact> contactLoad(int limit);
    List<KfsContact> contactLoadByStatus(String status, int limit);
    List<KfsContact> contactLoadByCategory(String category, int limit);
    KfsContact contactFindByMail(String mail, String user);
    KfsContact contactFindByPhone(String number, String user);
    
    
    List<KfsContactPhone> phoneLoad(KfsContact cont);    
    void phoneSave(KfsContactPhone phone, String usr);
    
    List<KfsContactMail> mailLoad(KfsContact cont);
    void mailSave(KfsContactMail phone, String usr);
    
    List<KfsContactLog> logLoad(KfsContact cont);
    void logSave(KfsContactLog log, String user);

    KfsNoContact nocontactCreate(String user);
    void nocontactSave(KfsNoContact nc, String user);

    public List<KfsNoContact> nocontactLoad(int limit);
    
    public CrmContactLockService getLockContactservice();
    public CrmNoContactLockService getLockNoContactservice();
}
