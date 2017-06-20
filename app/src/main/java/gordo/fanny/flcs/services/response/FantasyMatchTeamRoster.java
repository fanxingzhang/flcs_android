package gordo.fanny.flcs.services.response;

import java.util.List;

/**
 * Created by fanxing on 6/20/2017.
 */

public class FantasyMatchTeamRoster {

    private List<FantasyRole> TOP_LANE;
    private List<FantasyRole> JUNGLER;
    private List<FantasyRole> MID_LANE;
    private List<FantasyRole> AD_CARRY;
    private List<FantasyRole> SUPPORT;
    private List<FantasyRole> FLEX;
    private List<FantasyRole> TEAM;
    private List<FantasyRole> RESERVE;

    public List<FantasyRole> getJUNGLER() {
        return JUNGLER;
    }

    public List<FantasyRole> getMID_LANE() {
        return MID_LANE;
    }

    public List<FantasyRole> getAD_CARRY() {
        return AD_CARRY;
    }

    public List<FantasyRole> getSUPPORT() {
        return SUPPORT;
    }

    public List<FantasyRole> getFLEX() {
        return FLEX;
    }

    public List<FantasyRole> getTEAM() {
        return TEAM;
    }

    public List<FantasyRole> getRESERVE() {
        return RESERVE;
    }

    public List<FantasyRole> getTOP_LANE() {
        return TOP_LANE;
    }
}
