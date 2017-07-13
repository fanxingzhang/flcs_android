package gordo.fanny.flcs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import gordo.fanny.flcs.data.FantasyInfoManager;

/**
 * Created by rww306 on 2017-06-30.
 */

public class PlayerDetailsActivity extends FLCSBaseActivity {

    private ImageView playerPhoto;

    private long playerId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_detail_layout);

        playerPhoto = (ImageView)findViewById(R.id.roster_player_image);

        playerId = getIntent().getLongExtra(Tags.PLAYER_ID, -1);
        Picasso.with(this).load(fantasyInfoManager.getPlayerById(playerId).getPhotoUrl()).into(playerPhoto);
    }
}
