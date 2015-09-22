package kfs.kfscrm.dao.jpa;

import java.util.List;
import kfs.kfscrm.dao.KfsContactMailDao;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.domain.KfsContactMail;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class KfsContactMailDaoJpa extends BaseDaoJpa<KfsContactMail, String> implements KfsContactMailDao{

    @Override
    protected Class<KfsContactMail> getDataClass() {
        return KfsContactMail.class;
    }

    @Override
    protected String getId(KfsContactMail data) {
        return data.getMail();
    }

    @Override
    public List<KfsContactMail> loadByContact(KfsContact contact) {
        return em.createQuery("SELECT c FROM KfsContactMail c WHERE c.contact = :contact")
                .setParameter("contact", contact)
                .getResultList();
    }

}
