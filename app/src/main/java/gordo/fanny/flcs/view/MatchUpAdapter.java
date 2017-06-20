package gordo.fanny.flcs.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import gordo.fanny.flcs.R;
import gordo.fanny.flcs.services.response.FantasyMatch;

/**
 * Created by rww306 on 2017-06-20.
 */

public class MatchUpAdapter extends BaseAdapter {

    private Context context;
    private List<FantasyMatch> fantasyMatchList;
    private static LayoutInflater mInflater;

    public MatchUpAdapter(Activity mActivity) {
        context = mActivity;
        fantasyMatchList = new ArrayList<>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return fantasyMatchList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewToUse = convertView;
        if (viewToUse == null) {
            viewToUse = mInflater.inflate(R.layout.match_up_row_layout, parent, false);
        }
        FantasyMatch currentMatchup = fantasyMatchList.get(position);

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

    public void setData(List<FantasyMatch> matchList) {
        fantasyMatchList.clear();
        fantasyMatchList.addAll(matchList);
        notifyDataSetChanged();
    }

}
