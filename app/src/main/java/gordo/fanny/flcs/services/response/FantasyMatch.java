package gordo.fanny.flcs.services.response;

/**
 * Created by rww306 on 2017-06-16.
 */

public class FantasyMatch {

    private long id;
    private long week;

    public FantasyMatchTeam redTeam;
    public FantasyMatchTeam blueTeam;

    public long getWeek() {
        return week;
    }

    public long getId() {
        return id;
    }
}
