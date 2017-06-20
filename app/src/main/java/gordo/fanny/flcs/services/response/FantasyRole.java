package gordo.fanny.flcs.services.response;

import java.util.List;

/**
 * Created by fanxing on 6/20/2017.
 */

public class FantasyRole {

    private long targetId;
    private String position;
    private int slot;
    private String tragetType;

    public long getTargetId() {
        return targetId;
    }

    public String getTragetType() {
        return tragetType;
    }

    public String getPosition() {
        return position;
    }

    public int getSlot() {
        return slot;
    }
}
