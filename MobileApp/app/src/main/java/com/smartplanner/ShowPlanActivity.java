package com.smartplanner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowPlanActivity extends AppCompatActivity implements OnJsonReceived, OnPlansReceived {
    public static String serverPlanAddress = LoginActivity.serverAddress + "/api/plans";
    public static String serverAllPlansAddress = LoginActivity.serverAddress + "/api/users/plans";
    private static final int daysInWeek = 7;
    private static int weekDay = 0;
    private int daysInCycle;
    private Button forwardButton;
    private Button backwardButton;
    private TextView weekDateTextview;
    private ListView listView;
    private DrawerLayout drawerLayout;
    private ListView navDrawerListView;
    CycleDay[] cycleDays;
    private ShowPlanAdapter adapters[];
    String[] weekDays;
    Boolean dataReceived = false;
    ArrayList<Plan> plans;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        weekDays = getResources().getStringArray(R.array.week_days);
        initViews();
        forwardButton.setEnabled(false);
        backwardButton.setEnabled(false);
        plans = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        token = bundle.getString("token");
        new GetPlanFromServer(this, this, RequestType.ALL_PLANS).execute(serverAllPlansAddress, token);

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weekDay++;
                if (weekDay == daysInCycle)
                    weekDay = 0;
                weekDateTextview.setText(cycleDays[weekDay].getName());
                listView.setAdapter(adapters[weekDay]);

            }});
        backwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataReceived) {
                    weekDay--;
                    if (weekDay == -1)
                        weekDay = weekDays.length - 1;
                    weekDateTextview.setText(cycleDays[weekDay].getName());
                    listView.setAdapter(adapters[weekDay]);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void jsonReceived(String jsonString) {
        if(jsonString.contains(getResources().getString(R.string.string_server_connection_error))){
            serverErrorDialog().show();
        } else {
            try {
                forwardButton.setEnabled(true);
                backwardButton.setEnabled(true);
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                dataReceived = true;
                ArrayList<Task> tasks = jsonToTasks(stringToJson(jsonString));
                cycleDays = new CycleDay[daysInCycle];
                adapters = new ShowPlanAdapter[daysInCycle]; // im not sure about whether i should create all adapters and then access them or create them everytime dynamically or mb sotre them somewhere else?
                for (int i = 0; i < daysInCycle; i++) {
                    int weekNumber = i / daysInWeek;
                    int weekDayNameIndex = i % weekDays.length;
                    cycleDays[i] = new CycleDay(Integer.toString(weekNumber + 1) + ". " + weekDays[weekDayNameIndex]);
                    adapters[i] = new ShowPlanAdapter(this, cycleDays[i].getRows());
                }
                for (int i = 0; i < tasks.size(); i++) {
                    Term term = tasks.get(i).getTerm();
                    int repeatingPeriod = tasks.get(i).getRepeatingPeriod();
                    if (repeatingPeriod == 0)
                        repeatingPeriod = daysInCycle;
                    for (int j = 0; repeatingPeriod * j < daysInCycle; j++) {
                        int index = term.getCycleDayNumber() + repeatingPeriod * j;
                        cycleDays[index].addTask(tasks.get(i).getName(), term.getStartTime(), term.getDuration());
                    }
                }
                // set first views
                drawerLayout.closeDrawers();
                listView.setAdapter(adapters[0]);
                weekDateTextview.setText(cycleDays[weekDay].getName());
            } catch (NullPointerException ex){
                ex.printStackTrace();
                Toast.makeText(this, getResources().getString(R.string.string_plan_loading_error), Toast.LENGTH_LONG).show();
                forwardButton.setEnabled(false);
                backwardButton.setEnabled(false);
            }
        }

    }

    @Override
    public void plansReceived(String plansJsonString) {
        try {
            JSONArray jsonArray = new JSONArray(plansJsonString);
            // for each not working with jsonArray
            for(int i = 0; i < jsonArray.length(); ++i){
                JSONObject tempObj = jsonArray.getJSONObject(i);
                int id = tempObj.getInt("id");
                plans.add(new Plan(id));
            }
            NavDrawerAdapter navDrawerAdapter = new NavDrawerAdapter(this, plans, this, token);
            navDrawerListView.setAdapter(navDrawerAdapter);
            StringBuilder serverUrlBuilder = new StringBuilder(serverPlanAddress);
            serverUrlBuilder.append("/1");
            new GetPlanFromServer(this, null, RequestType.SINGLE_PLAN).execute(serverUrlBuilder.toString(), token); // init first plan
        } catch (JSONException ex){
            ex.printStackTrace();
            Toast.makeText(this, getResources().getString(R.string.string_plans_loading_error), Toast.LENGTH_LONG).show();
        }

    }

    private AlertDialog serverErrorDialog(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.string_server_error);
        builder.setMessage(R.string.string_server_connection_error);
        builder.setPositiveButton(R.string.string_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAndRemoveTask();
            }
        });
        return builder.create();
    }

    private JSONObject stringToJson(String json){
        try {
            return new JSONObject(json);
        } catch (org.json.JSONException exception) {
            exception.printStackTrace();
        }
        throw new NullPointerException(); // this is wrong think of sth else just for quick code
    }

    private ArrayList<Task> jsonToTasks(JSONObject json) throws NullPointerException{
        // TODO : put json names into resources?
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            daysInCycle = json.getInt("daysInCycle");
            Log.i("daysInCycle", Integer.toString(daysInCycle));
            JSONArray jsonTasksArray = json.getJSONArray("lessons");
            for(int i = 0; i < jsonTasksArray.length(); i++){
                JSONObject tempJsonTask= jsonTasksArray.getJSONObject(i);
                String name = tempJsonTask.getString("name");
                int repeatingPeriod = tempJsonTask.getInt("repeatingPeriod");
                JSONObject tempJsonTerm = tempJsonTask.getJSONObject("term");
                int durationInMinutes = tempJsonTerm.getInt("durationInMinutes");
                int cycleDayNumber = tempJsonTerm.getInt("cycleDayNumber");
                String startTime = tempJsonTerm.getString("startTime");
                startTime = HhMmSsStringToHhMm(startTime);
                Term term = new Term(startTime, durationInMinutes, cycleDayNumber);
                tasks.add(new Task(name, repeatingPeriod, term));
            }
            return tasks;
        }
        catch (org.json.JSONException ex) {
            ex.printStackTrace();
        }
        throw new NullPointerException(); // this is wrong thing of sth else just for quick code
    }
    // error on the server site not it should return HH:MM string in the first place at least that's what i've been told
    private static String HhMmSsStringToHhMm(String string){
        // return only HH:MM part
        return string.substring(0,5);
    }

    private void initViews(){
        forwardButton = findViewById(R.id.button_forward);
        backwardButton = findViewById(R.id.button_backward);
        weekDateTextview = findViewById(R.id.textview_week_day);
        listView = findViewById(R.id.listView);
        drawerLayout = findViewById(R.id.drawer_layout);
        navDrawerListView = findViewById(R.id.listView_nav_drawer);
    }


}