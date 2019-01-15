package com.smartplanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ShowPlanAdapter extends ArrayAdapter {
    ArrayList<Row> items;
    public ShowPlanAdapter(@NonNull Context context, ArrayList<Row> objects) {
        super(context, 0, objects);
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.plan_row_simple_view, null);
        }
        Row currentRow = items.get(position);
        ((TextView) convertView.findViewById(R.id.textView_starting_hour)).setText(currentRow.getStartingTime());
        ((TextView) convertView.findViewById(R.id.textView_ending_hour)).setText(currentRow.getEndingTime());
        ((TextView) convertView.findViewById(R.id.textView_task)).setText(currentRow.getName());
        return convertView;
    }
}


