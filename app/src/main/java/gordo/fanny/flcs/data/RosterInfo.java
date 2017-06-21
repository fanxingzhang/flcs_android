package gordo.fanny.flcs.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rww306 on 2017-06-20.
 */

public class RosterInfo {

    private long id;
    private String name;
    private String summonerName;
    private Map<Long, WeeklyRoster> weeklyRosters;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public String getSummonerName() {
        return summonerName;
    }

    public Map<Long, WeeklyRoster> getWeeklyRosters() {
        return weeklyRosters;
    }

    public void addWeeklyRoster(long week, WeeklyRoster wr) {
        if (weeklyRosters == null) {
            weeklyRosters = new HashMap<>();
        }
        weeklyRosters.put(week, wr);
    }
}
