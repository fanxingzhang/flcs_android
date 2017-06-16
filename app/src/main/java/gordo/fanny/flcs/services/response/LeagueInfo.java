package gordo.fanny.flcs.services.response;

import java.util.ArrayList;
import java.util.List;

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

    public List<FantasyMatch> getCurrentWeekMatches() {
        List<FantasyMatch> currWeekMatches = new ArrayList<>();
        for (FantasyMatch fm : fantasyMatches) {
            if (fm.getWeek() == currentWeek) {
                currWeekMatches.add(fm);
            }
        }
        return currWeekMatches;
    }

    public String getFantasyTeamNameById(long id) {
        for (FantasyTeam ft : fantasyTeams) {
            if (ft.getId() == id) {
                return ft.getName();
            }
        }
        return null;
    }
}
