package gordo.fanny.flcs

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_league_stats.*

class LeagueStatsActivity : FLCSBaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_stats)

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.player -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.team -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

    public fun setContent(): Int {
        return 0;
    }

}
