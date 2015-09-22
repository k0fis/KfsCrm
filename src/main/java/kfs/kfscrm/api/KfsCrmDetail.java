package kfs.kfscrm.api;

import java.sql.Timestamp;

/**
 *
 * @author pavedrim
 */
public interface KfsCrmDetail {

    Timestamp getDetailDate();
    String getDetailName();
    String getDetailText();
}
