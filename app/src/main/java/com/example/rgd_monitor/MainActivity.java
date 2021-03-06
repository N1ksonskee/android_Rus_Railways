package com.example.rgd_monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.MatrixCursor;
import android.os.Build;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.rgd_monitor.adapter.Constants;
import com.example.rgd_monitor.adapter.CustomArrayAdapter;
import com.example.rgd_monitor.db.DBManager;

import org.json.JSONException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CustomArrayAdapter adapter;
    private ArrayList<RusRailwaysInfo> rusRailwaysInfos;
    private ListView listView;
    SharedPreferences sp;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.action_bar_title_incidents);

        dbManager = new DBManager(this);
        dbManager.openDB();
        //dbManager.removeAll();


        try {
            InputStream in = getResources().openRawResource(R.raw.incidents);
            InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
            JsonReader reader = new JsonReader(inputStreamReader);

            try {
                readMessagesArray(reader);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        // ?????????????? ?????????????????? ?????? ???????????????? ?????????????????????? ????????????
        sp = getSharedPreferences("table", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // ???????????? ????????????: ???????????????????? ?? ?????????????????? ??????, ???? defValue = true => ?????? ????????????????????
        // ?????????????????????? ????????????: ???????????????????? ?? ?????????????????? ???????? ?? ?????? ?????????? false => ?????? ???? ????????????????????
        if(sp.getBoolean("first_run", true)) {
            for(int i = 0; i < rusRailwaysInfos.size(); i++) {
                dbManager.insertToDB(rusRailwaysInfos.get(i));
            }

            // ???????????????? ?? ?????????????????? first_run = false;
            editor.putBoolean("first_run", !getResources().getBoolean(R.bool.first_run));
            editor.apply();
        }



        listView = findViewById(R.id.list_view);
        adapter = new CustomArrayAdapter(this, R.layout.list_item, rusRailwaysInfos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                RusRailwaysInfo item = rusRailwaysInfos.get(position);


                Intent intent = new Intent(getApplicationContext(), IncidentInfoActivity.class);
                intent.putExtra(Constants.DESC_TICKED_ID_KEY, item.getDescription() + item.getTickedId());
                intent.putExtra(Constants.REGISTRATOR_KEY, item.getReportedBy());
                intent.putExtra(Constants.CRITIC_LVL_KEY, item.getCriticLevel());
                intent.putExtra(Constants.BEGIN_DATE_KEY, item.getIsKnownErrorDate());
                intent.putExtra(Constants.END_DATE_KEY, item.getTargetFinish());
                intent.putExtra(Constants.SYSTEM_KEY, item.getExtSysName());
                intent.putExtra(Constants.STATUS_KEY, item.getStatus());
                intent.putExtra(Constants.DEVIATION_KEY, item.getNorm());
                intent.putExtra(Constants.DISTANCE_KEY, "???? ??????????????");

                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();



       // adapter.updateAdapter(dbManager.getFromDB(""));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }


    public void readMessagesArray(JsonReader reader) throws IOException {
        rusRailwaysInfos = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            rusRailwaysInfos.add(readMessage(reader));
        }
        reader.endArray();
    }

    public RusRailwaysInfo readMessage(JsonReader reader) throws IOException {
        String status = null;
        String tickedId = null;
        String reportedBy = null;
        String classIdMain = null;
        int criticalLevel = 0;
        String isKnownErrorDate = null;
        String targetFinish = null;
        String description = null;
        String extSysName = null;
        double norm = 0;
        int lnorm = 0;

        String isKnownErrorDateParsed = null;
        String targetFinishParsed = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("STATUS")) {
                status = reader.nextString();
            } else if (name.equals("TICKETID")) {
                tickedId = "(" + reader.nextString() + ")";
            } else if(name.equals("REPORTEDBY")) {
                reportedBy = reader.nextString();
            } else if(name.equals("CLASSIDMAIN")) {
                classIdMain = reader.nextString();
            } else if(name.equals("CRITIC_LEVEL")) {
                criticalLevel = reader.nextInt();
            } else if(name.equals("ISKNOWNERRORDATE")) {
                isKnownErrorDate = reader.nextString();
                isKnownErrorDateParsed = parseDateTime(isKnownErrorDate.substring(0, 10), isKnownErrorDate.substring(11, 16));
            } else if(name.equals("TARGETFINISH")) {
                targetFinish = reader.nextString();
                targetFinishParsed = parseDateTime(targetFinish.substring(0, 10), targetFinish.substring(11, 16));
            } else if(name.equals("DESCRIPTION")) {
                description = reader.nextString();
            } else if(name.equals("EXTSYSNAME")) {
                extSysName = reader.nextString();
            } else if(name.equals("NORM")) {
                norm = reader.nextDouble();
            } else if(name.equals("LNORM")) {
                lnorm = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();


        // add 1 row into table`s DB
//        dbManager.insertToDB(status, tickedId, reportedBy, classIdMain, criticalLevel, isKnownErrorDateParsed, targetFinishParsed,description,
//                extSysName, norm, lnorm);

        return new RusRailwaysInfo(status, tickedId, reportedBy, classIdMain, criticalLevel, isKnownErrorDateParsed, targetFinishParsed, description,
                extSysName, norm, lnorm);
    }

    private String parseDateTime(String date, String time) {
        Log.d("MyLog", date);
        Log.d("MyLog", time);

        // day + "-" + mouth + "-" + "year" + " " + time
        return date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date.substring(0, 4) +
                " " + time;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.id_search);
        SearchView sv = (SearchView) menuItem.getActionView();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.updateAdapter(dbManager.getFromDB(newText));

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}
