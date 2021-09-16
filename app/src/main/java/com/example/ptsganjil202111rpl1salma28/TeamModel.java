package com.example.ptsganjil202111rpl1salma28;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TeamModel extends RealmObject {

    @PrimaryKey
    Integer id;
    String sport,image,desc;

    public TeamModel() {}

    public TeamModel(String image, String sport, String desc) {
        this.sport = sport;
        this.image = image;
        this.desc = desc;
    }


    public void setId(int id){ this.id = id; }

    public int getId(){ return id; }

    public void setSport(String sport) { this.sport = sport; }

    public String getSport() { return sport; }

    public void setImage(String image) { this.image = image; }

    public String getImage() { return image; }

    public void setDesc(String desc) { this.desc = desc; }

    public String getDesc() { return desc; }
}
