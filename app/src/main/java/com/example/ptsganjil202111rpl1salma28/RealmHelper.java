package com.example.ptsganjil202111rpl1salma28;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    // To save data into database
    public void save(final TeamModel teamModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(TeamModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    teamModel.setId(nextId);
                    TeamModel model = realm.copyToRealm(teamModel);
                    Log.d("Realm", "Item saved");
                } else {
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // TO get all data from database
    public List<TeamModel> getAllSports() {
        RealmResults<TeamModel> results = realm.where(TeamModel.class).findAll();
        return results;
    }

    // To delete data from database
    public void delete(Integer id) {
        final RealmResults<TeamModel> model = realm.where(TeamModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
}
