package kfs.kfscrm.tools;

/**
 *
 * @author pavedrim
 */
public class KfsContactListFilter {

    public final String cat;
    public final String status;
    public final String caption;

    public KfsContactListFilter(String cat, String status, String caption) {
        this.cat = cat;
        this.status = status;
        this.caption = caption;
    }
}
