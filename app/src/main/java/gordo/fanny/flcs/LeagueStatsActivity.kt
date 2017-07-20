package gordo.fanny.flcs

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import gordo.fanny.flcs.data.ProPlayer
import gordo.fanny.flcs.data.ProTeam
import gordo.fanny.flcs.view.RosterStatsAdapter
import kotlinx.android.synthetic.main.activity_league_stats.*

class LeagueStatsActivity : FLCSBaseActivity(), SearchView.OnQueryTextListener{

    private var playersList : List<ProPlayer>? = null
    private var teamsList : List<ProTeam>? =null
    private var playersListNew : List<ProPlayer>? = null
    private var teamsListNew : List<ProTeam>? =null
    private var weekAdapter : ArrayAdapter<String>? = null
    private var rosterAdapter : RosterStatsAdapter? = null
    private var selectedWeek = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_stats)

        playersList = fantasyInfoManager.allPlayers
        teamsList = fantasyInfoManager.allTeams
        playersListNew = ArrayList(playersList).sortedBy { it.name.toLowerCase() }
        teamsListNew = ArrayList(teamsList).sortedBy { it.name.toLowerCase() }
        rosterAdapter = RosterStatsAdapter(this)
        stats_list.adapter = rosterAdapter

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.player -> {
                    setPlayersList()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.team -> {
                    setTeamList()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        weekAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        week_spinner.adapter = weekAdapter!!
        week_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWeek = position + 1
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        updateWeekSpinner()
        setPlayersList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_stats, menu)
        val searchView : SearchView = MenuItemCompat.getActionView(menu!!.findItem(R.id.action_search)) as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        playersListNew = playersList!!.filter {
            it.name.contains(newText!!, true) ||
                    fantasyInfoManager.getTeamById(it.proTeamId).name.contains(newText!!, true) ||
                    fantasyInfoManager.getTeamById(it.proTeamId).shortName.contains(newText!!, true)
        }
        setPlayersList()
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    private fun setPlayersList() {
        rosterAdapter!!.setIsPlayer(true)
        rosterAdapter!!.setPlayerList(playersListNew!!)
    }

    private fun setTeamList() {
        rosterAdapter!!.setIsPlayer(false)
        rosterAdapter!!.setTeamList(teamsListNew!!)
    }

    private fun updateWeekSpinner() {
        weekAdapter!!.clear()
        val numWeeks = fantasyInfoManager.currWeek.toInt()
        val weeks = arrayOfNulls<String>(numWeeks)
        for (i in 0..numWeeks - 1) {
            weeks[i] = "Week " + (i + 1)
        }
        weekAdapter!!.addAll(*weeks)
    }

}
