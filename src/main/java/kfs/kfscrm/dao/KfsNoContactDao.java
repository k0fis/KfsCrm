package kfs.kfscrm.dao;

import java.util.List;
import kfs.kfscrm.domain.KfsNoContact;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface KfsNoContactDao extends BaseDao<KfsNoContact, Long>{

    public List<KfsNoContact> load(int limit);
    
}
