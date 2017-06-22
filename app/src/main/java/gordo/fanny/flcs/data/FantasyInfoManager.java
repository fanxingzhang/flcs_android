package gordo.fanny.flcs.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import gordo.fanny.flcs.services.response.FantasyMatch;
import gordo.fanny.flcs.services.response.FantasyMatchTeamRoster;
import gordo.fanny.flcs.services.response.FantasyTeam;
import gordo.fanny.flcs.services.response.LCSInfo;
import gordo.fanny.flcs.services.response.LCSPlayers;
import gordo.fanny.flcs.services.response.LeagueInfo;

/**
 * Created by rww306 on 2017-06-20.
 */

@Singleton
public class FantasyInfoManager {

    private Map<Long, RosterInfo> rosters;
    private List<MatchUpInfo> matchUps;
    private Map<Long, ProPlayer> proPlayerMap;
    private long currWeek;

    public FantasyInfoManager() {
        rosters = new HashMap<>();
        matchUps = new ArrayList<>();
        proPlayerMap = new HashMap<>();
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
            rosters.put(rosterInfo.getId(), rosterInfo);
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

            RosterInfo blueRoster = rosters.get(matchUpInfo.getBlueTeamId());
            RosterInfo redRoster = rosters.get(matchUpInfo.getRedTeamId());
            WeeklyRoster blueWeeklyRoster = new WeeklyRoster();
            WeeklyRoster redWeeklyRoster = new WeeklyRoster();
            FantasyMatchTeamRoster fantasyBlueRoster = fm.blueTeam.getRoster();
            FantasyMatchTeamRoster fantasyRedRoster = fm.redTeam.getRoster();

            blueWeeklyRoster.setTop(fantasyBlueRoster.getTOP_LANE().get(0).getTargetId());
            blueWeeklyRoster.setJug(fantasyBlueRoster.getJUNGLER().get(0).getTargetId());
            blueWeeklyRoster.setMid(fantasyBlueRoster.getMID_LANE().get(0).getTargetId());
            blueWeeklyRoster.setAdc(fantasyBlueRoster.getAD_CARRY().get(0).getTargetId());
            blueWeeklyRoster.setSupp(fantasyBlueRoster.getSUPPORT().get(0).getTargetId());

            redWeeklyRoster.setTop(fantasyRedRoster.getTOP_LANE().get(0).getTargetId());
            redWeeklyRoster.setJug(fantasyRedRoster.getJUNGLER().get(0).getTargetId());
            redWeeklyRoster.setMid(fantasyRedRoster.getMID_LANE().get(0).getTargetId());
            redWeeklyRoster.setAdc(fantasyRedRoster.getAD_CARRY().get(0).getTargetId());
            redWeeklyRoster.setSupp(fantasyRedRoster.getSUPPORT().get(0).getTargetId());

            blueRoster.addWeeklyRoster(fm.getWeek(), blueWeeklyRoster);
            redRoster.addWeeklyRoster(fm.getWeek(), redWeeklyRoster);
        }
    }

    public void setLCSInfo(LCSInfo lcsInfo) {
        List<LCSPlayers> lcsPlayersList = lcsInfo.getProPlayers();
        for (LCSPlayers players : lcsPlayersList) {
            ProPlayer proPlayer = new ProPlayer();
            proPlayer.setId(players.getId());
            proPlayer.setName(players.getName());
            proPlayer.setPhotoUrl(players.getPhotoUrl());
            proPlayer.setProTeamId(players.getProTeamId());
            proPlayer.setRiotId(players.getRiotId());

            proPlayerMap.put(proPlayer.getId(), proPlayer);
        }
    }

    public RosterInfo getRosterById(long id) {
        return rosters.get(id);
    }

    public ProPlayer getPlayerById(long id) {
        return proPlayerMap.get(id);
    }

    public List<MatchUpInfo> getMatchUps() {
        return matchUps;
    }

    public MatchUpInfo getMatchUpById(long id) {
        for (MatchUpInfo matchUpInfo : matchUps) {
            if (matchUpInfo.getId() == id) {
                return matchUpInfo;
            }
        }
        return null;
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

    public long getCurrWeek() {
        return currWeek;
    }
}
