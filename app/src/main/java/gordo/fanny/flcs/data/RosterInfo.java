package gordo.fanny.flcs.data;

/**
 * Created by rww306 on 2017-06-20.
 */

public class RosterInfo {

    private long id;
    private String name;
    private String summonerName;

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
        return name;
    }

    public String getSummonerName() {
        return summonerName;
    }
}
