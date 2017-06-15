package gordo.fanny.flcs.services;

import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

import gordo.fanny.flcs.FLCSApplication;
import gordo.fanny.flcs.services.request.LeagueRequest;
import gordo.fanny.flcs.services.response.LeagueInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rww306 on 2017-06-14.
 */

@Singleton
public class FLCSServiceManager {

    @Inject
    Bus bus;

    private FLCSService flcsService;

    @Inject
    public FLCSServiceManager(Bus bus) {
        this.bus = bus;
        this.bus.register(this);
        FLCSApplication.getApp().getDataComponent().inject(this);

        Retrofit retrofit = FLCSRetrofitBuilder.getRetrofit();
        flcsService = retrofit.create(FLCSService.class);
    }

    @Subscribe
    public void onLeagueRequest(LeagueRequest r) {
        Call<LeagueInfo> call = flcsService.getLeagueInfo(r.getId());
        call.enqueue(new Callback<LeagueInfo>() {
            @Override
            public void onResponse(Call<LeagueInfo> call, Response<LeagueInfo> response) {
                bus.post(response.body());
            }

            @Override
            public void onFailure(Call<LeagueInfo> call, Throwable t) {

            }
        });
    }
}
