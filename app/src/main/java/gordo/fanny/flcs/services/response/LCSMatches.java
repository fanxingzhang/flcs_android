package gordo.fanny.flcs.services.response;

/**
 * Created by rww306 on 2017-07-13.
 */

public class LCSMatches {
    private long id;
    private long week;
    private long blueTeamId;
    private long redTeamId;
    private boolean completed;
    private long winner;
    private int redTeamMutiplier;
    private int blueTeamMutiplier;

    public long getMatchId() {
        return id;
    }

    public long getWeek() {
        return week;
    }

    public long getBlueTeamId() {
        return blueTeamId;
    }

    public long getRedTeamId() {
        return redTeamId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public long getWinner() {
        return winner;
    }

    public int getRedTeamMutiplier() {
        return redTeamMutiplier;
    }

    public int getBlueTeamMutiplier() {
        return blueTeamMutiplier;
    }
}
