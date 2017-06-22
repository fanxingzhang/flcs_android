package gordo.fanny.flcs.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanxing on 6/20/2017.
 */

public class WeeklyRoster {
    private long top;
    private long jug;
    private long mid;
    private long adc;
    private long supp;
    private long team;
    private long flex;
    private List<Long> reserve;

    public long getTop() {
        return top;
    }

    public void setTop(long top) {
        this.top = top;
    }

    public long getJug() {
        return jug;
    }

    public void setJug(long jug) {
        this.jug = jug;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public long getAdc() {
        return adc;
    }

    public void setAdc(long adc) {
        this.adc = adc;
    }

    public long getSupp() {
        return supp;
    }

    public void setSupp(long supp) {
        this.supp = supp;
    }

    public long getTeam() {
        return team;
    }

    public void setTeam(long team) {
        this.team = team;
    }

    public long getFlex() {
        return flex;
    }

    public void setFlex(long flex) {
        this.flex = flex;
    }

    public List<Long> getReserve() {
        return reserve;
    }

    public void setReserve(List<Long> reserve) {
        this.reserve = reserve;
    }

    public List<Long> getFullRoster() {
        List<Long> fullRoster = new ArrayList<>();
        fullRoster.add(top);
        fullRoster.add(jug);
        fullRoster.add(mid);
        fullRoster.add(adc);
        fullRoster.add(supp);

        return fullRoster;
    }
}
