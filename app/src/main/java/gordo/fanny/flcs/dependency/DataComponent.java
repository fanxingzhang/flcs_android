package gordo.fanny.flcs.dependency;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import gordo.fanny.flcs.FLCSApplication;
import gordo.fanny.flcs.FLCSBaseActivity;
import gordo.fanny.flcs.services.FLCSServiceManager;
import gordo.fanny.flcs.view.MatchUpAdapter;
import gordo.fanny.flcs.view.RosterAdapter;
import gordo.fanny.flcs.view.RosterStatsAdapter;

/**
 * Created by rww306 on 2017-06-14.
 */

@Singleton
@Component(modules = DataModule.class)


public interface DataComponent {
    void inject(FLCSApplication flcsApplication);
    void inject(FLCSBaseActivity flcsBaseActivity);
    void inject(FLCSServiceManager flcsServiceManager);
    void inject(MatchUpAdapter matchUpAdapter);
    void inject(RosterAdapter rosterAdapter);
    void inject(RosterStatsAdapter rosterStatsAdapter);
}
