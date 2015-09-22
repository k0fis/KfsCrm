package kfs.kfscrm.dao.jpa;

import kfs.kfscrm.dao.KfsContactLockDao;
import kfs.kfscrm.domain.KfsContactLock;
import kfs.kfsdbolock.KfsLockDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class KfsContactLockDaoJpa extends KfsLockDaoJpa<KfsContactLock, Long> implements KfsContactLockDao{



}
