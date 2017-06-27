package gordo.fanny.flcs.services.response;

import java.util.List;

/**
 * Created by rww306 on 2017-06-27.
 */

public class LCSStats {

    public static final int PLAYER_PLAYER_ID_INDEX = 0;
    public static final int PLAYER_TEAM_ID = 1;
    public static final int PLAYER_MATCH_ID = 2;
    public static final int PLAYER_MATCH_NUMBER = 3;
    public static final int PLAYER_KILLS = 4;
    public static final int PLAYER_DEATH = 5;
    public static final int PLAYER_MINION_KILLS = 6;
    public static final int PLAYER_DOUBLE = 7;
    public static final int PLAYER_TRIPLE = 8;
    public static final int PLAYER_QUADARA = 9;
    public static final int PLAYER_PENTA = 10;

    private List<List<Float>> projectedTeamStats;
    private List<List<Float>> actualTeamStats;
    private List<List<Float>> projectedPlayerStats;
    private List<List<Float>> actualPlayerStats;

    public List<List<Float>> getActualTeamStats() {
        return actualTeamStats;
    }

    public List<List<Float>> getActualPlayerStats() {
        return actualPlayerStats;
    }

    public List<List<Float>> getProjectedTeamStats() {
        return projectedTeamStats;
    }

    public List<List<Float>> getProjectedPlayerStats() {
        return projectedPlayerStats;
    }
}
