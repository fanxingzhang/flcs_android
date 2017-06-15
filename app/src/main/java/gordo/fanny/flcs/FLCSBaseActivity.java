package gordo.fanny.flcs;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import gordo.fanny.flcs.services.FLCSRetrofitBuilder;
import gordo.fanny.flcs.services.FLCSService;
import retrofit2.Retrofit;

/**
 * Created by fanxing on 6/13/2017.
 */

public class FLCSBaseActivity extends AppCompatActivity {

    @Inject
    Bus bus;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((FLCSApplication)getApplication()).getDataComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.register(this);
    }
}
