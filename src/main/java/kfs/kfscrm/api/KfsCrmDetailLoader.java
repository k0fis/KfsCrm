package kfs.kfscrm.api;

import java.util.List;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfsvaalib.fields.KfsEditorField;

/**
 *
 * @author pavedrim
 * @param <T>
 */
public interface KfsCrmDetailLoader<T extends KfsCrmDetail> {

    Class<T> getDetailClass();
    
    List<T> loadByContact(KfsContact contact);
    
    KfsEditorField.Editor<T> getEditor();
}
