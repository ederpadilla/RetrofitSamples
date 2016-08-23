package eder.padilla.personal.works.pinchekenretrofitero;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eder on 20/08/2016.
 */
public class ListModelos extends RealmObject {
    @SerializedName("sismos")
    private RealmList<Sismos> lista;

    public RealmList<Sismos> getLista() {
        return lista;
    }

    @Override
    public String toString() {
        return "ListModelos{" +
                "lista=" + lista +
                '}';
    }
}
