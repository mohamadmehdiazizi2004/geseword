package com.example.netlogger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.VH> {

    private final List<DisconnectSession> items = new ArrayList<>();

    public void submit(List<DisconnectSession> list) {
        items.clear();
        if (list != null) items.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        DisconnectSession s = items.get(position);

        h.titleTv.setText(s.title);

        String endText = (s.endMillis == null) ? "..." : TimeUtil.format(s.endMillis.longValue());
        h.timeTv.setText("از " + TimeUtil.format(s.startMillis) + " تا " + endText);

        String dur = (s.durationSec == null) ? "در حال قطعی..." : TimeUtil.formatDuration(s.durationSec);
        h.durTv.setText("مدت: " + dur);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView titleTv, timeTv, durTv;
        VH(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.titleTv);
            timeTv  = itemView.findViewById(R.id.timeTv);
            durTv   = itemView.findViewById(R.id.durTv);
        }
    }
}
