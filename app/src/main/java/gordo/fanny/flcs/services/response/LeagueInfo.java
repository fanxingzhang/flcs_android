package gordo.fanny.flcs.services.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rww306 on 2017-06-13.
 */

public class LeagueInfo {

    private long id;
    private String name;
    private long owner;
    private long size;
    private long proSeasonId;
    private long currentWeek;
    private String status;
    private boolean predraft;
    private String gamesCounted;

    private PointsPerStat pointsPerStat;
    private List<FantasyTeam> fantasyTeams;
    private List<FantasyMatch> fantasyMatches;

    public String getName() {
        return name;
    }

    public long getCurrentWeek() {
        return currentWeek;
    }

    public long getSize() {
        return size;
    }

    public List<FantasyTeam> getFantasyTeams() {
        return fantasyTeams;
    }

    public List<FantasyMatch> getFantasyMatches() {
        return fantasyMatches;
    }
}
