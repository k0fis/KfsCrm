package kfs.kfscrm.dao.jpa;

import java.util.List;
import javax.persistence.NoResultException;
import kfs.kfscrm.dao.KfsContactDao;
import kfs.kfscrm.domain.KfsContact;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class KfsContactDaoJpa extends BaseDaoJpa<KfsContact, Long> implements KfsContactDao {

    @Override
    public List<KfsContact> loadAll() {
        return em.createQuery("select c from KfsContact c order by c.lastUpdate").getResultList();
    }

    @Override
    public List<KfsContact> load(int limit) {
        return em.createQuery("select c from KfsContact c order by c.lastUpdate")
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    protected Class<KfsContact> getDataClass() {
        return KfsContact.class;
    }

    @Override
    protected Long getId(KfsContact data) {
        return data.getId();
    }

    @Override
    public KfsContact findByMail(String mail) {
        try {
            return (KfsContact) em.createQuery("select c from KfsContact c WHERE c in (SELECT a.contact FROM KfsContactMail a WHERE a.mail = :mail)")
                    .setParameter("mail", mail)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public KfsContact findByPhone(String number) {
        try {
            return (KfsContact) em.createQuery("select c from KfsContact c WHERE c in (SELECT a.contact FROM KfsContactPhone a WHERE a.phone = :phone)")
                    .setParameter("phone", number)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<KfsContact> loadByCategory(String category, int limit) {
        return em.createQuery("select c from KfsContact c where c.clasification = :category order by c.lastUpdate")
                .setParameter("category", category)
                .setMaxResults(limit)
                .getResultList();

    }

    @Override
    public List<KfsContact> loadByStatus(String status, int limit) {
        return em.createQuery("select c from KfsContact c where c.status = :status order by c.lastUpdate")
                .setParameter("status", status)
                .setMaxResults(limit)
                .getResultList();
    }

}
