package gordo.fanny.flcs.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
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
import gordo.fanny.flcs.MatchUpActivity;
import gordo.fanny.flcs.R;
import gordo.fanny.flcs.Tags;
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
    private long weekSelected = 1;

    public MatchUpAdapter(Activity mActivity) {
        context = mActivity;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FLCSApplication.getApp().getDataComponent().inject(this);
    }

    @Override
    public int getCount() {
        return fantasyInfoManager.getMatchUpsByWeek(weekSelected).size();
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

        final MatchUpInfo matchUps = fantasyInfoManager.getMatchUpsByWeek(weekSelected).get(position);
        RosterInfo blueRoster = fantasyInfoManager.getRosterById(matchUps.getBlueTeamId());
        RosterInfo redRoster = fantasyInfoManager.getRosterById(matchUps.getRedTeamId());
        blueTeamName.setText(blueRoster.getName());
        redTeamName.setText(redRoster.getName());
        blueSummonerName.setText(blueRoster.getSummonerName());
        redSummonerName.setText(redRoster.getSummonerName());

        viewToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MatchUpActivity.class);
                intent.putExtra(Tags.MATCHUP_ID, matchUps.getId());
                intent.putExtra(Tags.WEEK_SELECTED, weekSelected);
                context.startActivity(intent);
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

    public void update(long week) {
        weekSelected = week;
        notifyDataSetChanged();
    }
}
