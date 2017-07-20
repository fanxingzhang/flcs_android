package gordo.fanny.flcs

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import gordo.fanny.flcs.data.ProPlayer
import gordo.fanny.flcs.view.RosterStatsAdapter
import kotlinx.android.synthetic.main.activity_league_stats.*

class LeagueStatsActivity : FLCSBaseActivity() {

    private var playersList : List<ProPlayer>? = null
    private var weekAdapter : ArrayAdapter<String>? = null
    private var rosterAdapter : RosterStatsAdapter? = null
    private var selectedWeek = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_stats)

        playersList = fantasyInfoManager.allPlayers
        rosterAdapter = RosterStatsAdapter(this)
        stats_list.adapter = rosterAdapter

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.player -> {
                    setPlayersList()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.team -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        weekAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        week_spinner.adapter = weekAdapter
        week_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedWeek = position + 1
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        updateWeekSpinner()
    }

    private fun setPlayersList() {
        rosterAdapter!!.setPlayerList(playersList!!)
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
