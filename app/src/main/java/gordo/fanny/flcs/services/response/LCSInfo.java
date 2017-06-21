package gordo.fanny.flcs.services.response;

import java.util.List;

/**
 * Created by rww306 on 2017-06-21.
 */

public class LCSInfo {
    private String seasonName;
    private String seasonSplit;
    private List<LCSTeams> proTeams;
    private List<LCSPlayers> proPlayers;

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonSplit() {
        return seasonSplit;
    }

    public List<LCSTeams> getProTeams() {
        return proTeams;
    }

    public List<LCSPlayers> getProPlayers() {
        return proPlayers;
    }
}
