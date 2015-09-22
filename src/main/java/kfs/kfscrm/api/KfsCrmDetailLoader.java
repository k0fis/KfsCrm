package kfs.kfscrm.api;

import java.util.List;
import kfs.kfscrm.domain.KfsContact;

/**
 *
 * @author pavedrim
 * @param <T>
 */
public interface KfsCrmDetailLoader<T extends KfsCrmDetail> {
    
    List<T> loadByContact(KfsContact contact);
    
}
