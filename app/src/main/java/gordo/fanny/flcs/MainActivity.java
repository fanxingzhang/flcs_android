package gordo.fanny.flcs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import gordo.fanny.flcs.services.request.LCSRequest;
import gordo.fanny.flcs.services.request.LeagueRequest;
import gordo.fanny.flcs.services.response.LCSInfo;
import gordo.fanny.flcs.services.response.LeagueInfo;
import gordo.fanny.flcs.utils.SharedPref;
import gordo.fanny.flcs.view.MatchUpAdapter;

public class MainActivity extends FLCSBaseActivity {

    private ListView matchupListVivew;
    private MatchUpAdapter matchUpAdapter;
    private AlertDialog.Builder alertDialogBuilder;
    private Spinner leagueSpinner;
    private Spinner weekSpinner;
    private ArrayAdapter spinnerAdapter;
    private ArrayAdapter weekSpinnerAdapter;
    private long selectedWeek;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        leagueSpinner = (Spinner) findViewById(R.id.league_spinner);
        weekSpinner = (Spinner) findViewById(R.id.week_spinner);
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        weekSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        leagueSpinner.setAdapter(spinnerAdapter);
        weekSpinner.setAdapter(weekSpinnerAdapter);
        leagueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long leagueId = SharedPref.getLeagueIDByName(MainActivity.this, ((TextView)view).getText().toString());
                if (leagueId > 0) {
                    bus.post(new LeagueRequest(leagueId));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        updateSpinner();

        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedWeek = position + 1;
                matchUpAdapter.update(selectedWeek);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        matchupListVivew = (ListView) findViewById(R.id.match_up_list_view);
        matchUpAdapter = new MatchUpAdapter(this);
        matchupListVivew.setAdapter(matchUpAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_league:
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Enter League ID");
                LayoutInflater layoutInflater = this.getLayoutInflater();
                View dialogView = layoutInflater.inflate(R.layout.add_league_dialog_layout, null);
                alertDialogBuilder.setView(dialogView);
                final EditText leagueIdInput = (EditText) dialogView.findViewById(R.id.league_id_input);
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long id = Long.valueOf(leagueIdInput.getText().toString());
                        bus.post(new LeagueRequest(id));
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", null);
                alertDialogBuilder.create().show();
                break;
            case R.id.action_league_stats:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateSpinner() {
        spinnerAdapter.clear();
        spinnerAdapter.addAll(SharedPref.getLeagueIds(MainActivity.this));
        spinnerAdapter.notifyDataSetChanged();
    }

    private void updateWeekSpinner() {
        weekSpinnerAdapter.clear();
        int numWeeks = (int)fantasyInfoManager.getCurrWeek();
        String[] weeks = new String[numWeeks];
        for (int i = 0; i < numWeeks; i++) {
            weeks[i] = "Week " + (i + 1);
        }
        weekSpinnerAdapter.addAll(weeks);
    }

    @Subscribe
    public void onLeagueInfo(LeagueInfo leagueInfo) {
        fantasyInfoManager.clear();
        fantasyInfoManager.setInfo(leagueInfo);
        bus.post(new LCSRequest(15));

        SharedPref.addLeagueId(leagueInfo.getId(), MainActivity.this, leagueInfo.getName());
        updateSpinner();
        updateWeekSpinner();
    }

    @Subscribe
    public void onLCSInfo(LCSInfo lcsInfo) {
        fantasyInfoManager.setLCSInfo(lcsInfo);
        matchUpAdapter.update(selectedWeek);
    }
}
