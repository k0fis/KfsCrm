package kfs.kfscrm.dao;

import java.util.List;
import kfs.kfscrm.domain.KfsContact;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface KfsContactDao extends BaseDao<KfsContact, Long> {
    
    List<KfsContact> loadAll();
    List<KfsContact> load(int limit);
    List<KfsContact> loadByCategory(String category, int limit);
    List<KfsContact> loadByStatus(String status, int limit);

    KfsContact findByMail(String mail);

    KfsContact findByPhone(String number);

}
