package gordo.fanny.flcs.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import gordo.fanny.flcs.FLCSApplication
import gordo.fanny.flcs.R
import gordo.fanny.flcs.Tags
import gordo.fanny.flcs.data.FantasyInfoManager
import gordo.fanny.flcs.data.ProPlayer
import gordo.fanny.flcs.data.ProTeam
import kotlinx.android.synthetic.main.roster_player_row_layout.view.*
import javax.inject.Inject

/**
 * Created by rww306 on 2017-07-20.
 */
class RosterStatsAdapter(mActivity: Activity) : BaseAdapter() {

    @Inject lateinit var fantasyInfoManager : FantasyInfoManager

    private var players : MutableList<ProPlayer>? = null
    private var teams : MutableList<ProTeam>? = null
    private var lf : LayoutInflater? = null
    private var mActivity : Activity? = null
    private var week : Long = 0
    private var isPlayer : Boolean = true

    init {
        this.mActivity = mActivity
        lf = mActivity.layoutInflater
        players = ArrayList<ProPlayer>()
        teams = ArrayList<ProTeam>()
        FLCSApplication.getApp().dataComponent.inject(this)
    }

    public fun setPlayerList(players : List<ProPlayer>) {
        this.players!!.clear()
        this.players!!.addAll(players)
        notifyDataSetChanged()
    }

    public fun setTeamList(teams : List<ProTeam>) {
        this.teams!!.clear()
        this.teams!!.addAll(teams)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view : View? = convertView
        if (view == null) {
            view = lf!!.inflate(R.layout.roster_player_row_layout, parent, false)
        }

        if (isPlayer) {
            val proPlayer = players!![position]

            view!!.roster_player_name.setText(proPlayer.name)
            Picasso.with(mActivity).load(proPlayer.photoUrl).into(view!!.roster_player_image)
            view!!.roster_player_team.setText(fantasyInfoManager.getTeamById(proPlayer.proTeamId).name)
        }
        else {
            val proTeam = teams!![position]
            view!!.roster_player_name.setText(proTeam.name)
            view!!.roster_player_team.setText(proTeam.shortName)
            Picasso.with(mActivity).load(Tags.TEAM_PHOTO_URL.replace("@", "" + proTeam.riotId)).into(view!!.roster_player_image)
        }

        return view
    }

    override fun getCount(): Int {
        return if (isPlayer) players!!.size else teams!!.size
    }

    override fun getItem(position: Int): Any {
        return if (isPlayer) players!![position] else teams!![position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    public fun setIsPlayer(isPlayer : Boolean) {
        this.isPlayer = isPlayer
    }
}