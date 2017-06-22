package gordo.fanny.flcs.data;

/**
 * Created by rww306 on 2017-06-22.
 */

public class ProTeam {

    private long id;
    private long riotId;
    private String name;
    private String shortName;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
