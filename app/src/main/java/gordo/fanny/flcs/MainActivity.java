package gordo.fanny.flcs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import gordo.fanny.flcs.services.request.LCSRequest;
import gordo.fanny.flcs.services.request.LeagueRequest;
import gordo.fanny.flcs.services.response.FantasyMatch;
import gordo.fanny.flcs.services.response.LCSInfo;
import gordo.fanny.flcs.services.response.LeagueInfo;
import gordo.fanny.flcs.view.MatchUpAdapter;

public class MainActivity extends FLCSBaseActivity {

    private ListView matchupListVivew;
    private MatchUpAdapter matchUpAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        matchupListVivew = (ListView) findViewById(R.id.match_up_list_view);
        matchUpAdapter = new MatchUpAdapter(this);
        matchupListVivew.setAdapter(matchUpAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bus.post(new LeagueRequest(1191481));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onLeagueInfo(LeagueInfo leagueInfo) {
        fantasyInfoManager.setInfo(leagueInfo);
        bus.post(new LCSRequest(15));
    }

    @Subscribe
    public void onLCSInfo(LCSInfo lcsInfo) {
        fantasyInfoManager.setLCSInfo(lcsInfo);
        matchUpAdapter.update();
    }
}
