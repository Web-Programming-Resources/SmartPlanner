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

import static com.smartplanner.ShowPlanActivity.serverPlanAddress;

public class NavDrawerAdapter extends ArrayAdapter{
    OnJsonReceived onJsonReceived;
    ArrayList<Plan> items;
    String token;
    public NavDrawerAdapter(@NonNull Context context, ArrayList<Plan> items, OnJsonReceived onJsonReceived, String token) {
        super(context, 0, items);
        this.items = items;
        this.onJsonReceived = onJsonReceived;
        this.token = token;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.navigation_row_simple_view, null);
        }
        final Plan plan = items.get(position);
        String currentItemName = plan.getName();
        ((TextView)convertView.findViewById(R.id.textView_nav_view)).setText(currentItemName);
        (convertView.findViewById(R.id.textView_nav_view)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder serverUrlBuilder = new StringBuilder(serverPlanAddress);
                serverUrlBuilder.append('/');
                serverUrlBuilder.append(plan.getId());
                // that is super bad
                new GetPlanFromServer(onJsonReceived, null, RequestType.SINGLE_PLAN).execute(serverUrlBuilder.toString(), token);
            }
        });
        return convertView;
    }
}
