package kfs.kfscrm.tools;

import kfs.kfscrm.domain.KfsContact;
import kfs.kfsvaalib.convertors.KfsObjectProperties;

/**
 *
 * @author pavedrim
 */
public class KfsContactConvertor extends KfsObjectProperties<KfsContact> {

    public KfsContactConvertor() {
        super(KfsContact.class, " ", "getFirstName", "getSecondName", "getLastPhone", "getLastEmail");
    }
}
