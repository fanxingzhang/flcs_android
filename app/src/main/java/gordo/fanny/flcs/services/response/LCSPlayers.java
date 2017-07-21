package gordo.fanny.flcs.services.response;

import java.util.List;
import java.util.Map;

/**
 * Created by rww306 on 2017-06-21.
 */

public class LCSPlayers {

    private long id;
    private long riotId;
    private String name;
    private String photoUrl;
    private long proTeamId;
    private Map<String, List<Double>> trendsByWeek;

    public Map<String, List<Double>> getTrendsByWeek() {
        return trendsByWeek;
    }

    public long getId() {
        return id;
    }

    public long getRiotId() {
        return riotId;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public long getProTeamId() {
        return proTeamId;
    }
}
