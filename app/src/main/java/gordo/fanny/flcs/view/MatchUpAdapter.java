package gordo.fanny.flcs.view;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gordo.fanny.flcs.FLCSApplication;
import gordo.fanny.flcs.R;
import gordo.fanny.flcs.data.FantasyInfoManager;
import gordo.fanny.flcs.data.MatchUpInfo;
import gordo.fanny.flcs.data.RosterInfo;
import gordo.fanny.flcs.data.WeeklyRoster;
import gordo.fanny.flcs.services.response.FantasyMatch;

/**
 * Created by rww306 on 2017-06-20.
 */

public class MatchUpAdapter extends BaseAdapter {

    @Inject
    FantasyInfoManager fantasyInfoManager;

    private Context context;
    private static LayoutInflater mInflater;

    public MatchUpAdapter(Activity mActivity) {
        context = mActivity;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FLCSApplication.getApp().getDataComponent().inject(this);
    }

    @Override
    public int getCount() {
        return fantasyInfoManager.getCurrWeekMatchUps().size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewToUse = convertView;
        if (viewToUse == null) {
            viewToUse = mInflater.inflate(R.layout.match_up_row_layout, parent, false);
        }
        TextView blueTeamName = (TextView) viewToUse.findViewById(R.id.matchup_team_1_name);
        TextView redTeamName = (TextView) viewToUse.findViewById(R.id.matchup_team_2_name);
        TextView blueSummonerName = (TextView) viewToUse.findViewById(R.id.matchup_team_1_summoner);
        TextView redSummonerName = (TextView) viewToUse.findViewById(R.id.matchup_team_2_summoner);

        final MatchUpInfo matchUps = fantasyInfoManager.getCurrWeekMatchUps().get(position);
        final RosterInfo blueRoster = fantasyInfoManager.getRosterById(matchUps.getBlueTeamId());
        RosterInfo redRoster = fantasyInfoManager.getRosterById(matchUps.getRedTeamId());
        blueTeamName.setText(blueRoster.getName());
        redTeamName.setText(redRoster.getName());
        blueSummonerName.setText(blueRoster.getSummonerName());
        redSummonerName.setText(redRoster.getSummonerName());

        viewToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println(blueRoster.getSummonerName());
//                WeeklyRoster wr = blueRoster.getWeeklyRosters().get(Long.valueOf(1));
//                System.out.println(fantasyInfoManager.getPlayerById(wr.getTop()).getName());
//                System.out.println(fantasyInfoManager.getPlayerById(wr.getJug()).getName());
            }
        });
        return viewToUse;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public void update() {
        notifyDataSetChanged();
    }
}
