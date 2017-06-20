package gordo.fanny.flcs.dependency;

import android.app.Application;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gordo.fanny.flcs.data.FantasyInfoManager;
import gordo.fanny.flcs.services.FLCSBus;

/**
 * Created by rww306 on 2017-06-14.
 */
@Module
public class DataModule {

    Application application;

    public DataModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Bus ottoBus() {
        return new FLCSBus(ThreadEnforcer.ANY);
    }

    @Provides
    @Singleton
    FantasyInfoManager fantasyInfoManager() {
        return new FantasyInfoManager();
    }
}
