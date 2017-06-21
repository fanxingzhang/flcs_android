package gordo.fanny.flcs.data;

/**
 * Created by rww306 on 2017-06-21.
 */

public class ProPlayer {
    private long id;
    private long riotId;
    private String name;
    private String photoUrl;
    private long proTeamId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRiotId() {
        return riotId;
    }

    public void setRiotId(long riotId) {
        this.riotId = riotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public long getProTeamId() {
        return proTeamId;
    }

    public void setProTeamId(long proTeamId) {
        this.proTeamId = proTeamId;
    }
}
