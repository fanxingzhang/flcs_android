package gordo.fanny.flcs.services.response;

/**
 * Created by rww306 on 2017-06-16.
 */

public class FantasyMatchTeam {
    private long id;
    private FantasyMatchTeamRoster roster;

    public FantasyMatchTeamRoster getRoster() {
        return roster;
    }

    public long getId() {
        return id;

    }
}
