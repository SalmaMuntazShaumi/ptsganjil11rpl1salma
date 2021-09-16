package com.example.ptsganjil202111rpl1salma28;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class favList extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    TeamAdapter teamAdapter;
    List<TeamModel> teamModels;
    Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);

        teamModels = new ArrayList<>();

        recyclerView = findViewById(R.id.rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm.init(this);
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        teamModels = realmHelper.getAllSports();

        show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        teamAdapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        teamAdapter = new TeamAdapter(this, teamModels);
        recyclerView.setAdapter(teamAdapter);
    }

    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()){
            case 1:
                id = teamModels.get(item.getGroupId()).getId();
                realmHelper.delete(id);
                Toast.makeText(favList.this, "Item deleted", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}