package eder.padilla.personal.works.pinchekenretrofitero;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eder on 20/08/2016.
 */
public class Sismos extends RealmObject {
    @PrimaryKey
    private  String id;
    private String loc;

    public String getId() {
        return id;
    }

    public String getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return "Sismos{" +
                "id='" + id + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
