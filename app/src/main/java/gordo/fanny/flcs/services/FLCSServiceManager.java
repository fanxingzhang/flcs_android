package gordo.fanny.flcs.services;

import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

import gordo.fanny.flcs.FLCSApplication;

/**
 * Created by rww306 on 2017-06-14.
 */

@Singleton
public class FLCSServiceManager {

    @Inject
    Bus bus;

    @Inject
    public FLCSServiceManager(Bus bus) {
        this.bus = bus;
        this.bus.register(this);
        FLCSApplication.getApp().getDataComponent().inject(this);
    }

    @Subscribe
    public void onTest(String s) {
        System.out.println(s);
    }
}
