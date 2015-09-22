package kfs.kfscrm.dao.jpa;

import java.util.List;
import kfs.kfscrm.dao.KfsContactPhoneDao;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.domain.KfsContactPhone;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class KfsContactPhoneDaoJpa extends BaseDaoJpa<KfsContactPhone, String> implements KfsContactPhoneDao{

    @Override
    protected Class<KfsContactPhone> getDataClass() {
        return KfsContactPhone.class;
    }

    @Override
    protected String getId(KfsContactPhone data) {
        return data.getPhone();
    }

    @Override
    public List<KfsContactPhone> loadByContact(KfsContact contact) {
        return em.createQuery("SELECT c FROM KfsContactPhone c WHERE c.contact = :contact")
                .setParameter("contact", contact)
                .getResultList();
    }

}
