package gordo.fanny.flcs.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gordo.fanny.flcs.FLCSApplication;
import gordo.fanny.flcs.PlayerDetailsActivity;
import gordo.fanny.flcs.R;
import gordo.fanny.flcs.Tags;
import gordo.fanny.flcs.data.FantasyInfoManager;
import gordo.fanny.flcs.data.ProPlayer;
import gordo.fanny.flcs.data.ProTeam;

/**
 * Created by fanxing on 6/21/2017.
 */

public class RosterAdapter extends BaseAdapter {

    @Inject
    FantasyInfoManager fantasyInfoManager;

    private Context context;
    private LayoutInflater mInflater;

    private List<Long> roster;
    private long weekSelected;

    public RosterAdapter(Activity mActivity) {
        super();
        context = mActivity;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FLCSApplication.getApp().getDataComponent().inject(this);

        this.roster = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return roster.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View returnView = view;
        if (returnView == null) {
            returnView = mInflater.inflate(R.layout.roster_player_row_layout, viewGroup, false);
        }
        TextView playerName = (TextView) returnView.findViewById(R.id.roster_player_name);
        TextView teamName = (TextView) returnView.findViewById(R.id.roster_player_team);
        ImageView playerPhoto = (ImageView) returnView.findViewById(R.id.roster_player_image);

        final Long playerId = roster.get(i);
        ProPlayer currPlayer = fantasyInfoManager.getPlayerById(playerId);
        if (currPlayer != null) {
            playerName.setText(currPlayer.getName());
            teamName.setText(fantasyInfoManager.getTeamById(currPlayer.getProTeamId()).getName());
            Picasso.with(context).load(currPlayer.getPhotoUrl()).into(playerPhoto);

            returnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayerDetailsActivity.class);
                    intent.putExtra(Tags.PLAYER_ID, playerId);
                    intent.putExtra(Tags.WEEK_SELECTED, weekSelected);
                    context.startActivity(intent);
                }
            });
        }
        else {
            ProTeam proTeam = fantasyInfoManager.getTeamById(playerId);
            System.out.println(playerId);
            playerName.setText(proTeam.getName());
            teamName.setText("Team");
            Picasso.with(context).load(Tags.TEAM_PHOTO_URL.replace("@", "" + proTeam.getRiotId())).into(playerPhoto);

            returnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        return returnView;
    }

    public void setRoster(List<Long> roster, long weekSelected) {
        this.roster.clear();
        this.roster.addAll(roster);
        this.weekSelected = weekSelected;
        notifyDataSetChanged();
    }
}
