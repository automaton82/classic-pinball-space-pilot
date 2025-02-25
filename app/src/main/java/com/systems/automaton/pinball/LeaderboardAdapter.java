package com.systems.automaton.pinball;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {
    private List<LeaderboardElement> localLeaderboard;
    private boolean isPlaceholder;
    private boolean isCheatRanking;
    public static int pagesize = 250;
    public int page;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView rankTxtV;
        private final TextView nameTxtV;
        private final TextView scoreTxtV;
        private final TextView dateTxtV;
        private final ProgressBar pBar;
        private final ImageView star;
        private final ConstraintLayout border;

        public ViewHolder(View view) {
            super(view);
            rankTxtV = view.findViewById(R.id.ranktxtv);
            nameTxtV = view.findViewById(R.id.nametxtv);
            scoreTxtV = view.findViewById(R.id.scoretxtv);
            dateTxtV = view.findViewById(R.id.datetxtv);
            pBar = view.findViewById(R.id.progressBar);
            star = view.findViewById(R.id.star);
            border = view.findViewById(R.id.border);
        }
    }

    public LeaderboardAdapter(List<LeaderboardElement> leaderboard, boolean isPlaceholder, boolean isCheatRanking, int page) {
        this.localLeaderboard = leaderboard;
        this.page = page;
        this.isPlaceholder = isPlaceholder;
        this.isCheatRanking = isCheatRanking;

        if (isCheatRanking) {
            Collections.sort(this.localLeaderboard, (t1, t2) -> -Integer.compare(t1.cheatScore, t2.cheatScore));
        } else {
            Collections.sort(this.localLeaderboard, (t1, t2) -> -Integer.compare(t1.normalScore, t2.normalScore));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leaderboard_element, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (isPlaceholder) {
            viewHolder.pBar.setVisibility(View.VISIBLE);
            viewHolder.nameTxtV.setText(R.string.loading);
        } else {
            viewHolder.rankTxtV.setText(((page*pagesize) + position + 1) + ".");
            if ((page*pagesize) + position == 0) {
                viewHolder.rankTxtV.setVisibility(View.INVISIBLE);
                viewHolder.star.setVisibility(View.VISIBLE);
            } else {
                viewHolder.rankTxtV.setVisibility(View.VISIBLE);
                viewHolder.star.setVisibility(View.GONE);
            }
            viewHolder.nameTxtV.setText(localLeaderboard.get(position).username);
            if (isCheatRanking) viewHolder.scoreTxtV.setText("" + localLeaderboard.get(position).cheatScore);
            else viewHolder.scoreTxtV.setText("" + localLeaderboard.get(position).normalScore);
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH.mm", Locale.getDefault());
            fmt.setTimeZone(TimeZone.getDefault());
            viewHolder.dateTxtV.setText(fmt.format(localLeaderboard.get(position).lastUploaded));

            if (localLeaderboard.get(position).uid.equals(PrefsHelper.getUserId())) {
                viewHolder.border.setVisibility(View.VISIBLE);
            } else {
                viewHolder.border.setVisibility(View.GONE);
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localLeaderboard.size();
    }
}
