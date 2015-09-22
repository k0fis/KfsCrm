package kfs.kfscrm.service.impl;

import kfs.kfscrm.dao.KfsContactLockDao;
import kfs.kfscrm.domain.KfsContactLock;
import kfs.kfscrm.api.CrmContactLockService;
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
public class CrmContactLockServiceImpl extends KfsLockToolsImpl<KfsContactLock, Long>
        implements CrmContactLockService {

    @Value("${CrmService.lockMinutesDelay:30}")
    private int lockMinutesDelay;

    @Autowired
    private KfsContactLockDao dao;
    
    @Override
    public Class<KfsContactLock> getCls(){
        return KfsContactLock.class;
    }
    @Override
    public KfsContactLockDao getDao() {
        return dao;
    }
    @Override
    public int getMinutesDelay() {
        return lockMinutesDelay;
    }



}
