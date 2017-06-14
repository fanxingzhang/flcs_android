package gordo.fanny.flcs.dependency;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import gordo.fanny.flcs.FLCSApplication;
import gordo.fanny.flcs.FLCSBaseActivity;
import gordo.fanny.flcs.services.FLCSServiceManager;

/**
 * Created by rww306 on 2017-06-14.
 */

@Singleton
@Component(modules = DataModule.class)


public interface DataComponent {
    void inject(FLCSApplication flcsApplication);
    void inject(FLCSBaseActivity flcsBaseActivity);
    void inject(FLCSServiceManager flcsServiceManager);
}
