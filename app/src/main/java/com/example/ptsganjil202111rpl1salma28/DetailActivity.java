package com.example.ptsganjil202111rpl1salma28;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class DetailActivity extends AppCompatActivity {

    RealmHelper realmHelper;
    String name, image, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FloatingActionButton fav = findViewById(R.id.fab);

        TextView tv_name = findViewById(R.id.tv_name_detail);
        TextView tv_desc = findViewById(R.id.tv_desc_detail);
        ImageView img_team = findViewById(R.id.img_team_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            desc = bundle.getString("desc");
            image = bundle.getString("image");

            tv_name.setText(name);
            tv_desc.setText(desc);
            Picasso.get().load(image).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).into(img_team);

            // Set up
            Realm.init(this);
            RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
            Realm realm = Realm.getInstance(configuration);
            realmHelper = new RealmHelper(realm);


            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Snackbar.make(view, "data telah ditambahkan", Snackbar.LENGTH_SHORT);
                    TeamModel teamModel = new TeamModel(image, name, desc);
                    realmHelper.save(teamModel);

                }
            });

        }
    }
}