package gordo.fanny.flcs.data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import gordo.fanny.flcs.services.response.FantasyMatch;
import gordo.fanny.flcs.services.response.FantasyTeam;
import gordo.fanny.flcs.services.response.LeagueInfo;

/**
 * Created by rww306 on 2017-06-20.
 */

@Singleton
public class FantasyInfoManager {

    private List<RosterInfo> rosters;
    private List<MatchUpInfo> matchUps;
    private long currWeek;

    public FantasyInfoManager() {
        rosters = new ArrayList<>();
        matchUps = new ArrayList<>();
    }

    public void setInfo(LeagueInfo info) {
        currWeek = info.getCurrentWeek();

        List<FantasyTeam> fantasyTeams = info.getFantasyTeams();
        rosters.clear();
        for (FantasyTeam ft : fantasyTeams) {
            RosterInfo rosterInfo = new RosterInfo();
            rosterInfo.setId(ft.getId());
            rosterInfo.setName(ft.getName());
            rosterInfo.setSummonerName(ft.getSummonerName());
            rosters.add(rosterInfo);
        }

        List<FantasyMatch> fantasyMatches = info.getFantasyMatches();
        matchUps.clear();
        for (FantasyMatch fm : fantasyMatches) {
            MatchUpInfo matchUpInfo = new MatchUpInfo();
            matchUpInfo.setId(fm.getId());
            matchUpInfo.setWeek(fm.getWeek());
            matchUpInfo.setBlueTeamId(fm.blueTeam.getId());
            matchUpInfo.setRedTeamId(fm.redTeam.getId());
            matchUps.add(matchUpInfo);
        }
    }

    public RosterInfo getRosterById(long id) {
        for (RosterInfo rosterInfo : rosters) {
            if (rosterInfo.getId() == id) {
                return rosterInfo;
            }
        }
        return null;
    }

    public List<MatchUpInfo> getMatchUps() {
        return matchUps;
    }

    public List<MatchUpInfo> getMatchUpsByWeek(long week) {
        List<MatchUpInfo> returnMatchUps = new ArrayList<>();
        for (MatchUpInfo matchUpInfo : matchUps) {
            if (matchUpInfo.getWeek() == week) {
                returnMatchUps.add(matchUpInfo);
            }
        }
        return returnMatchUps;
    }

    public List<MatchUpInfo> getCurrWeekMatchUps() {
        return getMatchUpsByWeek(currWeek);
    }
}
