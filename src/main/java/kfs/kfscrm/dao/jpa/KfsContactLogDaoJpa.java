package kfs.kfscrm.dao.jpa;

import java.util.List;
import kfs.kfscrm.dao.KfsContactLogDao;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.domain.KfsContactLog;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class KfsContactLogDaoJpa extends BaseDaoJpa<KfsContactLog, Long> implements KfsContactLogDao{

    @Override
    protected Class<KfsContactLog> getDataClass() {
        return KfsContactLog.class;
    }

    @Override
    protected Long getId(KfsContactLog data) {
        return data.getId();
    }

    @Override
    public List<KfsContactLog> loadByContact(KfsContact contact) {
        return em.createQuery("SELECT a FROM KfsContactLog a WHERE a.contact = :contact ORDER BY a.logDate DESC")
                .setParameter("contact", contact)
                .getResultList();
    }

}
