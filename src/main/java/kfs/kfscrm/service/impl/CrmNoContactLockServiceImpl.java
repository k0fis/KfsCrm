package kfs.kfscrm.service.impl;

import kfs.kfscrm.api.CrmNoContactLockService;
import kfs.kfscrm.dao.KfsNoContactLockDao;
import kfs.kfscrm.domain.KfsNoContactLock;
import kfs.kfsdbolock.KfsLockDao;
import kfs.kfsdbolock.KfsLockToolsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pavedrim
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CrmNoContactLockServiceImpl extends KfsLockToolsImpl<KfsNoContactLock, Long>
        implements CrmNoContactLockService {

    @Value("${CrmService.lockMinutesDelay:30}")
    private int lockMinutesDelay;

    @Autowired
    private KfsNoContactLockDao dao;

    @Override
    public Class<KfsNoContactLock> getCls() {
        return KfsNoContactLock.class;
    }

    @Override
    public KfsLockDao<KfsNoContactLock, Long> getDao() {
        return dao;
    }

    @Override
    public int getMinutesDelay() {
        return lockMinutesDelay;
    }

}
