package eder.padilla.personal.works.pinchekenretrofitero;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Eder on 20/08/2016.
 */
public interface Api {
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonDats(@Path("id")int id);
    @GET("pokemon/{id}")
    Call<ResponseBody> getPokemonById2(@Path("id") int id);
    @GET("api/RetrofitAndroidArrayResponse")
    Call<List<Student>> getStudentDetails();
    @GET("?")
    Call<ListModelos> getSismos();
}
