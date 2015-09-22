package kfs.kfscrm.dao.jpa;

import java.util.List;
import kfs.kfscrm.dao.KfsNoContactDao;
import kfs.kfscrm.domain.KfsNoContact;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class KfsNoContactDaoJpa extends BaseDaoJpa<KfsNoContact, Long> implements KfsNoContactDao{

    @Override
    protected Class<KfsNoContact> getDataClass() {
        return KfsNoContact.class;
    }

    @Override
    protected Long getId(KfsNoContact data) {
        return data.getId();
    }

    @Override
    public List<KfsNoContact> load(int limit) {
        return em.createQuery("SELECT a FROM KfsNoContact a ORDER BY a.created desc")
                .setMaxResults(limit)
                .getResultList();
    }

}
