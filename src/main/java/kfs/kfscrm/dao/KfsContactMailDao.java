package kfs.kfscrm.dao;

import kfs.kfscrm.domain.KfsContactMail;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface KfsContactMailDao extends BaseDao<KfsContactMail, String>, ContactDetailLoader<KfsContactMail> {

}
