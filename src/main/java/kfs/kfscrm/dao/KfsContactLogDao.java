package kfs.kfscrm.dao;

import kfs.kfscrm.domain.KfsContactLog;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface KfsContactLogDao extends BaseDao<KfsContactLog, Long>, ContactDetailLoader<KfsContactLog>{

}
