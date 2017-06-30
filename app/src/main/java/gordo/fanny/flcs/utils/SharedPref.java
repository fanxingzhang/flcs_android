package gordo.fanny.flcs.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by rww306 on 2017-06-30.
 */

public class SharedPref {

    private static final String LIST_LEAGUE_ID = "list_league_id";

    public static void addLeagueId(long leagueId, Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> listIds = getLeagueIds(context);
        listIds.add(name);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(LIST_LEAGUE_ID, listIds);
        editor.putLong(name, leagueId);
        editor.commit();
    }

    public static Set<String> getLeagueIds(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> listIds = sharedPreferences.getStringSet(LIST_LEAGUE_ID, new HashSet<String>());
        return listIds;
    }

    public static long getLeagueIDByName(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        long id = sharedPreferences.getLong(name, -1);
        return id;
    }
}
