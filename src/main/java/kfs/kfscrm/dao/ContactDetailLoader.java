package kfs.kfscrm.dao;

import java.util.List;
import kfs.kfscrm.domain.KfsContact;

/**
 *
 * @author pavedrim
 * @param <T>
 */
public interface ContactDetailLoader<T> {

    List<T> loadByContact(KfsContact contact);
}
