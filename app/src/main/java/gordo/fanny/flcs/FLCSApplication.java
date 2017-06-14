package gordo.fanny.flcs;

import android.app.Application;

import gordo.fanny.flcs.dependency.DaggerDataComponent;
import gordo.fanny.flcs.dependency.DataComponent;
import gordo.fanny.flcs.dependency.DataModule;

/**d
 * Created by rww306 on 2017-06-14.
 */

public class FLCSApplication extends Application {

    private DataComponent dataComponent;
    private static FLCSApplication app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        dataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule(this))
                .build();
        dataComponent.inject(this);
    }

    public static FLCSApplication getApp() {
        return app;
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}
