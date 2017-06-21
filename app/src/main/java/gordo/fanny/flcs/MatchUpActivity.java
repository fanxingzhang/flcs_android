package gordo.fanny.flcs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import gordo.fanny.flcs.data.MatchUpInfo;
import gordo.fanny.flcs.data.RosterInfo;

/**
 * Created by fanxing on 6/21/2017.
 */

public class MatchUpActivity extends FLCSBaseActivity {

    private TabLayout tabLayout;
    private long matchUpId;
    private MatchUpInfo currMatchUp;
    private RosterInfo blueRoster;
    private RosterInfo redRoster;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.match_up_detail_layout);
        tabLayout = (TabLayout) findViewById(R.id.matchup_tabs);
        setLayout();
    }

    private void setLayout() {
        matchUpId = getIntent().getLongExtra(Tags.MATCHUP_ID, 0);
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
    }
}
