package gordo.fanny.flcs.data;

/**
 * Created by rww306 on 2017-06-20.
 */

public class MatchUpInfo {
    private long id;
    private long week;
    private long blueTeamId;
    private long redTeamId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWeek() {
        return week;
    }

    public void setWeek(long week) {
        this.week = week;
    }

    public long getBlueTeamId() {
        return blueTeamId;
    }

    public void setBlueTeamId(long blueTeamId) {
        this.blueTeamId = blueTeamId;
    }

    public long getRedTeamId() {
        return redTeamId;
    }

    public void setRedTeamId(long redTeamId) {
        this.redTeamId = redTeamId;
    }
}
