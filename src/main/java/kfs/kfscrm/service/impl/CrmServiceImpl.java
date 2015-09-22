package kfs.kfscrm.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kfs.kfscrm.api.*;
import kfs.kfscrm.dao.*;
import kfs.kfscrm.domain.*;
import kfs.kfscrm.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pavedrim
 */
@Service
@Transactional
public class CrmServiceImpl implements CrmService {

    @Autowired
    private KfsContactDao contactDao;
    @Autowired
    private KfsContactMailDao mailDao;
    @Autowired
    private KfsContactPhoneDao phoneDao;
    @Autowired
    private KfsContactLogDao logDao;
    @Autowired
    private KfsNoContactDao noDao;
    @Autowired
    private CrmContactLockService lockContact;
    @Autowired
    private CrmNoContactLockService lockNocontact;
    
    private final Map<Class, KfsCrmDetailLoader> loaders = new HashMap<>();

    
    @Override
    public <T extends KfsCrmDetail> void registerContactDetail(Class<T> cls,
            KfsCrmDetailLoader<T> loader) {
        if (loader != null) {
            loaders.put(cls, loader);
        }
    }

    Timestamp now() {
        return new Timestamp(new Date().getTime());
    }

    private void contactSaveInternal(KfsContact contact, String usr) {
        contact.setLastUpdate(now());
        contact.setLastUpdateBy(usr);
        if (contact.getId() == null) {
            contact.setCreated(contact.getLastUpdate());
            contact.setCreatedBy(contact.getLastUpdateBy());
            contactDao.insert(contact);
        } else {
            contactDao.update(contact);
        }
    }

    @Override
    public void contactSave(KfsContact contact, String usr) {
        contactSaveInternal(contact, usr);
        if (mailDao.findById(contact.getLastEmail()) == null) {
            KfsContactMail mail = new KfsContactMail();
            mail.setActive(true);
            mail.setContact(contact);
            mail.setImported(contact.getLastUpdate());
            mail.setMail(contact.getLastEmail());
            mailDao.insert(mail);
        }
        if (phoneDao.findById(contact.getLastPhone()) == null) {
            KfsContactPhone phone = new KfsContactPhone();
            phone.setActive(true);
            phone.setContact(contact);
            phone.setImported(contact.getLastUpdate());
            phone.setPhone(contact.getLastPhone());
            phoneDao.insert(phone);
        }
    }

    @Override
    public void contactAddMail(KfsContact contact, String mail, boolean useAsLast, String usr) {
        if (mail == null) {
            return;
        }
        if (mailDao.findById(mail) != null) {
            return;
        }
        KfsContactMail cmail = new KfsContactMail();
        cmail.setActive(true);
        cmail.setContact(contact);
        cmail.setImported(now());
        cmail.setMail(mail);
        mailDao.insert(cmail);
        if (useAsLast) {
            contact.setLastEmail(mail);
        }
        contactSaveInternal(contact, usr);
    }

    @Override
    public void contactAddPhone(KfsContact contact, String phone, boolean useAsLast, String usr) {
        if (phone == null) {
            return;
        }
        if (phoneDao.findById(phone) != null) {
            return;
        }
        KfsContactPhone cphone = new KfsContactPhone();
        cphone.setActive(true);
        cphone.setContact(contact);
        cphone.setImported(now());
        cphone.setPhone(phone);
        phoneDao.insert(cphone);
        if (useAsLast) {
            contact.setLastPhone(phone);
        }
        contactSaveInternal(contact, usr);
    }

    @Override
    public KfsContact contactRefresh(KfsContact contact) {
        return contactDao.find(contact);
    }

    @Override
    public List<KfsContact> contactLoadAll() {
        return contactDao.loadAll();
    }

    @Override
    public List<KfsContact> contactLoad(int limit) {
        return contactDao.load(limit);
    }

    @Override
    public KfsContact contactCreate(String mail, String phone, String usr) {
        if (phone == null) {
            phone = "";
        }
        if (mail == null) {
            mail = "";
        } else {
            mail = mail.trim();
        }
        if (phone.isEmpty() && mail.isEmpty()) {
            return null;
        }
        KfsContact cont = null;
        if (!phone.isEmpty()) {
            cont = contactDao.findByPhone(phone);
        }
        if ((cont == null) && !mail.isEmpty()) {
            cont = contactDao.findByMail(mail);
        }
        if (cont == null) {
            cont = new KfsContact();
            cont.setLastEmail(mail);
            cont.setLastPhone(phone);
            contactSave(cont, usr);
        }
        return cont;
    }

    @Override
    public void phoneSave(KfsContactPhone phone, String usr) {
        if (phoneDao.findById(phone.getPhone()) == null) {
            phoneDao.insert(phone);
        } else {
            phoneDao.update(phone);
        }
        phone.getContact().setLastPhone(phone.getPhone());
        contactSaveInternal(phone.getContact(), usr);
    }

    @Override
    public void mailSave(KfsContactMail mail, String usr) {
        if (mailDao.findById(mail.getMail()) == null) {
            mailDao.insert(mail);
        } else {
            mailDao.update(mail);
        }
        mail.getContact().setLastEmail(mail.getMail());
        contactSaveInternal(mail.getContact(), usr);
    }

    @Override
    public List<KfsContactMail> mailLoad(KfsContact cont) {
        return mailDao.loadByContact(cont);
    }

    @Override
    public List<KfsContactPhone> phoneLoad(KfsContact cont) {
        return phoneDao.loadByContact(cont);
    }

    @Override
    public void logSave(KfsContactLog log, String user) {
        logDao.insert(log);
        contactSaveInternal(log.getContact(), user);
    }

    @Override
    public List<KfsContactLog> logLoad(KfsContact cont) {
        return logDao.loadByContact(cont);
    }

    @Override
    public KfsContact contactFindByMail(String mail, String user) {
        KfsContact cont = contactDao.findByMail(mail);
        if (cont == null) {
            cont = contactCreate(mail, "", user);
        }
        return cont;
    }

    @Override
    public KfsContact contactFindByPhone(String number, String user) {
        KfsContact cont = contactDao.findByPhone(number);
        if (cont == null) {
            cont = contactCreate("", number, user);
        }
        return cont;
    }

    @Override
    public KfsNoContact nocontactCreate(String user) {
        KfsNoContact nc = new KfsNoContact();
        nc.setCreated(now());
        nc.setCreatedBy(user);
        nc.setLastUpdate(nc.getCreated());
        nc.setLastUpdateBy(user);
        nc.setNote("");
        nc.setWorkDone(false);
        noDao.insert(nc);
        return nc;
    }

    @Override
    public void nocontactSave(KfsNoContact nc, String user) {
        nc.setLastUpdate(now());
        nc.setLastUpdateBy(user);
        noDao.update(nc);
    }

    @Override
    public List<KfsNoContact> nocontactLoad(int limit) {
        return noDao.load(limit);
    }

    @Override
    public CrmContactLockService getLockContactservice() {
        return lockContact;
    }

    @Override
    public CrmNoContactLockService getLockNoContactservice() {
        return lockNocontact;
    }

    @Override
    public List<KfsContact> contactLoadByStatus(String status, int limit) {
        return contactDao.loadByStatus(status, limit);
    }

    @Override
    public List<KfsContact> contactLoadByCategory(String category, int limit) {
        return contactDao.loadByCategory(category, limit);
    }
}
