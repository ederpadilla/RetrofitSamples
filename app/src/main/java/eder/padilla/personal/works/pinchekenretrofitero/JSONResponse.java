package eder.padilla.personal.works.pinchekenretrofitero;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Eder on 20/08/2016.
 */
public class JSONResponse {
    @SerializedName("forms")
    ArrayList<Pokemon> arrayList;

    public ArrayList<Pokemon> getArrayList() {
        return arrayList;
    }
}
