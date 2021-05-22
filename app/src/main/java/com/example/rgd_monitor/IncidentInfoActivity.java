package com.example.rgd_monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rgd_monitor.adapter.Constants;

public class IncidentInfoActivity extends AppCompatActivity {
    private TextView tvDescTickedId;
    private TextView tvRegistrator;
    private TextView tvCriticalLvl;
    private TextView tvBeginDate;
    private TextView tvEndDate;
    private TextView tvSystem;
    private TextView tvStatus;
    private TextView tvDeviation;
    private TextView tvDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident_info);

        init();

        Intent intent = getIntent();
        if(intent != null) {
            tvDescTickedId.setText(intent.getStringExtra(Constants.DESC_TICKED_ID_KEY));
            tvRegistrator.setText(intent.getStringExtra(Constants.REGISTRATOR_KEY));
            tvCriticalLvl.setText(String.valueOf(intent.getIntExtra(Constants.CRITIC_LVL_KEY, 0)));
            tvBeginDate.setText(intent.getStringExtra(Constants.BEGIN_DATE_KEY));
            tvEndDate.setText(intent.getStringExtra(Constants.END_DATE_KEY));
            tvSystem.setText(intent.getStringExtra(Constants.SYSTEM_KEY));
            tvStatus.setText(intent.getStringExtra(Constants.STATUS_KEY));
            tvDeviation.setText(String.valueOf(intent.getDoubleExtra(Constants.DEVIATION_KEY, 0.0)));
            tvDistance.setText(intent.getStringExtra(Constants.DISTANCE_KEY));
        }
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.action_bar_title_incident);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvDescTickedId = findViewById(R.id.inc_info_desc_ticketId);
        tvRegistrator = findViewById(R.id.inc_info_registrator);
        tvCriticalLvl = findViewById(R.id.inc_info_critical_lvl);
        tvBeginDate = findViewById(R.id.inc_info_begin_date);
        tvEndDate = findViewById(R.id.inc_info_end_date);
        tvSystem = findViewById(R.id.inc_info_system);
        tvStatus = findViewById(R.id.inc_info_status);
        tvDeviation = findViewById(R.id.inc_info_deviation);
        tvDistance = findViewById(R.id.inc_info_distance);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();

        return super.onOptionsItemSelected(item);
    }
}