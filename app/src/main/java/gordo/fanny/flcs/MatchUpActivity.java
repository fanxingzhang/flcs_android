package gordo.fanny.flcs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.widget.ListView;

import gordo.fanny.flcs.data.MatchUpInfo;
import gordo.fanny.flcs.data.RosterInfo;
import gordo.fanny.flcs.data.WeeklyRoster;
import gordo.fanny.flcs.view.RosterAdapter;

/**
 * Created by fanxing on 6/21/2017.
 */

public class MatchUpActivity extends FLCSBaseActivity {

    private TabLayout tabLayout;
    private long matchUpId;
    private MatchUpInfo currMatchUp;
    private RosterInfo blueRoster;
    private RosterInfo redRoster;
    private ListView rosterListView;
    private RosterAdapter rosterAdapter;
    private long weekSelected;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.match_up_detail_layout);
        tabLayout = (TabLayout) findViewById(R.id.matchup_tabs);
        rosterListView = (ListView) findViewById(R.id.matchup_details_list_view);
        rosterAdapter = new RosterAdapter(this);
        rosterListView.setAdapter(rosterAdapter);
        setLayout();
        setRosterListView(0);
    }

    private void setLayout() {
        matchUpId = getIntent().getLongExtra(Tags.MATCHUP_ID, 0);
        weekSelected = getIntent().getLongExtra(Tags.WEEK_SELECTED, -1);

        if (matchUpId == 0) {
            return;
        }

        currMatchUp = fantasyInfoManager.getMatchUpById(matchUpId);
        if (currMatchUp == null) {
            return;
        }

        blueRoster = fantasyInfoManager.getRosterById(currMatchUp.getBlueTeamId());
        redRoster = fantasyInfoManager.getRosterById(currMatchUp.getRedTeamId());
        if (blueRoster == null || redRoster == null) {
            return;
        }

        tabLayout.addTab(tabLayout.newTab().setText(blueRoster.getName()));
        tabLayout.addTab(tabLayout.newTab().setText(redRoster.getName()));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setRosterListView(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setRosterListView(int team) {
        RosterInfo rosterInfo = team == 0 ? blueRoster : redRoster;
        WeeklyRoster weeklyRoster = rosterInfo.getWeeklyRosters().get(weekSelected);
        rosterAdapter.setRoster(weeklyRoster.getFullRoster(), weekSelected);
    }
}
