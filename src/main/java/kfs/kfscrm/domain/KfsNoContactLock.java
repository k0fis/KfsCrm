package kfs.kfscrm.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import kfs.kfsdbolock.KfsLock;

/**
 *
 * @author pavedrim
 */
@Entity
public class KfsNoContactLock extends KfsLock<Long> {

    @Id
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long data) {
        this.id = data;
    }


}
