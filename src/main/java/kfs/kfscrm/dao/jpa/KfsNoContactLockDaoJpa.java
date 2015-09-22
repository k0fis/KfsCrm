package kfs.kfscrm.dao.jpa;

import kfs.kfscrm.dao.KfsNoContactLockDao;
import kfs.kfscrm.domain.KfsNoContactLock;
import kfs.kfsdbolock.KfsLockDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class KfsNoContactLockDaoJpa extends KfsLockDaoJpa<KfsNoContactLock, Long> implements KfsNoContactLockDao{

}
