package gordo.fanny.flcs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import gordo.fanny.flcs.data.ProPlayer;
import gordo.fanny.flcs.services.response.LCSMatches;
import gordo.fanny.flcs.services.response.LCSStats;

/**
 * Created by rww306 on 2017-06-30.
 */

public class PlayerDetailsActivity extends FLCSBaseActivity {

    private LayoutInflater layoutInflater;

    private ImageView playerPhoto;
    private TextView playerName;
    private TextView teamName;
    private LinearLayout statsList;

    private long playerId;
    private ProPlayer player;
    private long week;
    private List<LCSMatches> matches;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_detail_layout);

        layoutInflater = getLayoutInflater();

        playerPhoto = (ImageView)findViewById(R.id.roster_player_image);
        playerName = (TextView)findViewById(R.id.roster_player_name);
        teamName = (TextView)findViewById(R.id.roster_player_team);
        statsList = (LinearLayout) findViewById(R.id.match_stats_list);

        playerId = getIntent().getLongExtra(Tags.PLAYER_ID, -1);
        week = getIntent().getLongExtra(Tags.WEEK_SELECTED, -1);
        player = fantasyInfoManager.getPlayerById(playerId);
        matches = fantasyInfoManager.getProMatchesByIdAndWeek(player.getProTeamId(), week);

        playerName.setText(player.getName());
        teamName.setText(fantasyInfoManager.getTeamById(player.getProTeamId()).getName());
        Picasso.with(this).load(fantasyInfoManager.getPlayerById(playerId).getPhotoUrl()).into(playerPhoto);

        statsList.removeAllViews();
        for (LCSMatches lcsMatches : matches) {
            addMatchLayout(lcsMatches);
        }
    }

    public void addMatchLayout(LCSMatches lcsMatches) {
        List<List<Float>> matchStats = fantasyInfoManager.getPlayerMatchStats(playerId, lcsMatches.getMatchId());
        System.out.println("TEST: " + matchStats.size());
        for (List<Float> stats : matchStats) {
            View view = layoutInflater.inflate(R.layout.player_stats_layout, null);
            TextView vsTeam = (TextView) view.findViewById(R.id.vs_team);
            TextView kill = (TextView) view.findViewById(R.id.kill);
            TextView death = (TextView) view.findViewById(R.id.death);
            TextView assist = (TextView) view.findViewById(R.id.assist);
            TextView cs = (TextView) view.findViewById(R.id.cs);
            TextView doubleKill = (TextView) view.findViewById(R.id.double_kill);
            TextView tripleKill = (TextView) view.findViewById(R.id.triple_kill);
            TextView quadraKill = (TextView) view.findViewById(R.id.quadra_kill);
            TextView pentaKill = (TextView) view.findViewById(R.id.penta_kill);

            if (player.getProTeamId() == lcsMatches.getBlueTeamId()) {
                vsTeam.setText("vs. " + fantasyInfoManager.getTeamById(lcsMatches.getRedTeamId()).getShortName());
            }
            else {
                vsTeam.setText("vs. " + fantasyInfoManager.getTeamById(lcsMatches.getBlueTeamId()).getShortName());
            }
            kill.setText("" + stats.get(LCSStats.PLAYER_KILLS).intValue());
            death.setText("" + stats.get(LCSStats.PLAYER_DEATH).intValue());
            assist.setText("" + stats.get(LCSStats.PLYATER_ASSIST).intValue());
            cs.setText("" + stats.get(LCSStats.PLAYER_MINION_KILLS).intValue());
            doubleKill.setText("" + stats.get(LCSStats.PLAYER_DOUBLE).intValue());
            tripleKill.setText("" + stats.get(LCSStats.PLAYER_TRIPLE).intValue());
            quadraKill.setText("" + stats.get(LCSStats.PLAYER_QUADRA).intValue());
            pentaKill.setText("" + stats.get(LCSStats.PLAYER_PENTA).intValue());

            statsList.addView(view);
        }
    }
}
