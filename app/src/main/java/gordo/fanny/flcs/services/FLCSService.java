package gordo.fanny.flcs.services;

import gordo.fanny.flcs.services.response.LCSInfo;
import gordo.fanny.flcs.services.response.LeagueInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rww306 on 2017-06-13.
 */

public interface FLCSService {
    @GET("/en-US/api/league/{leagueId}")
    Call<LeagueInfo> getLeagueInfo(@Path("leagueId") long leagueId);

    @GET("/en-US/api/season/{seasonId}")
    Call<LCSInfo> getLCSInfo(@Path("seasonId") long seasonId);
}
