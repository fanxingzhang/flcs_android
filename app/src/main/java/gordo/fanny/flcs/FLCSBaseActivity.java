package gordo.fanny.flcs;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import gordo.fanny.flcs.services.FLCSRetrofitBuilder;
import gordo.fanny.flcs.services.FLCSService;
import retrofit2.Retrofit;

/**
 * Created by fanxing on 6/13/2017.
 */

public class FLCSBaseActivity extends AppCompatActivity {


    protected FLCSService flcsService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Retrofit retrofit = FLCSRetrofitBuilder.getRetrofit();
        flcsService = retrofit.create(FLCSService.class);
    }
}
