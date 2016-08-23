package eder.padilla.personal.works.pinchekenretrofitero;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    public static String BASEURL = "http://pokeapi.co/api/v2/pokemon/12/";
    Realm realm;
    RealmConfiguration realmConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView)findViewById(R.id.tv);
        realmConfiguration= new RealmConfiguration.Builder(getApplicationContext()).build();
        realm= Realm.getDefaultInstance().getInstance(realmConfiguration);

        getSismos();
        //getRetrofitArray2();

        //getPokemonByIdRetrofit2(40);
    }



    public void getPokemonById() {

        new AsyncTask<Void,Void,String>(){


            @Override
            protected String doInBackground(Void... voids) {
                StringBuilder resultado = new StringBuilder();
                try {
                    URL url = new URL(BASEURL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String line;
                    while ((line=bufferedReader.readLine())!= null){
                        resultado.append(line);
                    }
                    bufferedReader.close();
                    Log.e("Buffer ","estamos guardando en el buffer"+resultado.toString());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return resultado.toString();
            }
            @Override
            protected void onPostExecute(String s) {
                Log.e("on PostExecute",""+ s);
                super.onPostExecute(s);
            }
        }.execute();
 }

    public void getPokemonByIdRetrofit(int pokemonId) {
        Api pokemonClient = ServiceGenerator.createService(Api.class);

        pokemonClient.getPokemonDats(pokemonId).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                log("nombre"+ pokemon.getName());

                //log("code: " + response.code());
                //log("body: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                log("fail1");
            }
        });

    }


    public void getPokemonByIdRetrofit2(int pokemonId) {
        Api pokemonClient = ServiceGenerator.createService(Api.class);

        pokemonClient.getPokemonById2(pokemonId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String jsonResponse = null;
                try {
                    jsonResponse = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("jsonResponse",""+jsonResponse);




              //  try {
              //      textView.append(response.body().toString());
              //      String jsonData=response.body().string();
//
              //      JSONObject Jobject = new JSONObject(jsonData);
              //      JSONArray Jarray = Jobject.getJSONArray("employees");
//
              //      ArrayList<Pokemon> yourArray = new Gson().fromJson(Jarray.toString(), new TypeToken<List<Pokemon>>(){}.getType());
//
              //      Log.e("ArrayList",""+yourArray.toString());
              //      for (int i = 0; i < Jarray.length(); i++) {
              //          JSONObject object   = Jarray.getJSONObject(i);
              //      }
//
              //  } catch (IOException e) {
              //      e.printStackTrace();
              //      Log.e("!!!!!!!","exception");
              //  } catch (JSONException e) {
              //      e.printStackTrace();
              //      Log.e("!!!!!!!","exception");
              //  }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log("fail2");
            }

        });

    }
    public void log(String content) {
        Log.d("myLog", content);
    }

    public void getRetrofitArray() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.androidtutorialpoint.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service = retrofit.create(Api.class);

        Call<List<Student>> call = service.getStudentDetails();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                try {
                    List<Student> StudentData = response.body();
                    Log.d("onResponse", ""+StudentData.toString());
                    for (int i = 0; i < StudentData.size(); i++) {

                        if (i == 0) {
                            textView.append("StudentId  :  " + StudentData.get(i).getStudentId());
                            textView.append("StudentName  :  " + StudentData.get(i).getStudentName());
                            textView.append("StudentMarks  : " + StudentData.get(i).getStudentMarks());
                        } else if (i == 1) {
                            textView.append("StudentId  :  " + StudentData.get(i).getStudentId());
                            textView.append("StudentName  :  " + StudentData.get(i).getStudentName());
                            textView.append("StudentMarks  : " + StudentData.get(i).getStudentMarks());
                        }
                    }


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });



     //  Call<List<Student>> call = service.getStudentDetails();

     //  call.enqueue(new Callback<List<Student>>() {
     //      @Override
     //      public void onResponse(Response<List<Student>> response, Retrofit retrofit) {

     //          try {

     //              List<Student> StudentData = response.body();

     //              for (int i = 0; i < StudentData.size(); i++) {

     //                  if (i == 0) {
     //                      text_id_1.setText("StudentId  :  " + StudentData.get(i).getStudentId());
     //                      text_name_1.setText("StudentName  :  " + StudentData.get(i).getStudentName());
     //                      text_marks_1.setText("StudentMarks  : " + StudentData.get(i).getStudentMarks());
     //                  } else if (i == 1) {
     //                      text_id_2.setText("StudentId  :  " + StudentData.get(i).getStudentId());
     //                      text_name_2.setText("StudentName  :  " + StudentData.get(i).getStudentName());
     //                      text_marks_2.setText("StudentMarks  : " + StudentData.get(i).getStudentMarks());
     //                  }
     //              }


     //          } catch (Exception e) {
     //              Log.d("onResponse", "There is an error");
     //              e.printStackTrace();
     //          }

     //      }

     //      @Override
     //      public void onFailure(Throwable t) {
     //          Log.d("onFailure", t.toString());
     //      }
     //  });
    }
    void getRetrofitArray2() {
        Api pokemonClient = ServiceGenerator.createService(Api.class);
        pokemonClient.getStudentDetails().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                try {

                    List<Student> StudentData = response.body();
                    Log.e("Lista",""+StudentData.toString());
                    for (int i = 0; i < StudentData.size(); i++) {

                        if (i == 0) {
                            textView.append("StudentId  :  " + StudentData.get(i).getStudentId());
                            textView.append("StudentName  :  " + StudentData.get(i).getStudentName());
                            textView.append("StudentMarks  : " + StudentData.get(i).getStudentMarks());
                        } else if (i == 1) {
                            textView.append("StudentId  :  " + StudentData.get(i).getStudentId());
                            textView.append("StudentName  :  " + StudentData.get(i).getStudentName());
                            textView.append("StudentMarks  : " + StudentData.get(i).getStudentMarks());
                        }
                    }


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });

    }
    public void getSismos(){
        Api pokemonClient = ServiceGenerator.createService(Api.class);
        pokemonClient.getSismos().enqueue(new Callback<ListModelos>() {
            @Override
            public void onResponse(Call<ListModelos> call, Response<ListModelos> response) {
            Log.e("onResponse",""+response.code());
                ListModelos listModelos= response.body();
                creatList(listModelos);
                //RealmResults<ListModelos> results = realm.where(ListModelos.class).findAll();
                //for (ListModelos listModelos1: results){
                //    Log.e("listmodelos",""+listModelos1);
                //}

                //Log.e("ListModelos",""+listModelos.toString());


               // try {
               //     String listModelos=response.body().string();
               //     JSONObject jsonResponse = new JSONObject(listModelos);
               //     JSONArray jsonArray = jsonResponse.getJSONArray("sismos");
               //     List<String> list = new ArrayList<String>();
               //     if (jsonArray != null){
               //         for(int n = 0; n < jsonArray.length(); n++)
               //         {
               //             //JSONObject object = jsonArray.getJSONObject(n);
               //             //Log.e("JsonObject",""+object.toString());
               //             list.add(jsonArray.get(n).toString());
               //             Log.e("Lista",list.toString());
               //             // do some stuff....
               //         }
               //     }
               //     //Log.e("Array",""+jsonArray.toString());
//
               // } catch (IOException e) {
               //     e.printStackTrace();
               // } catch (JSONException e) {
               //     e.printStackTrace();
               // }

                //String Json= response.body().toString();
                //Log.e("string","response"+Json);
               // String Json=response.body().string();
                //JSONObject jsonResponse = new JSONObject();
                //List<Sismos> lista=response.body();
                //Log.e("Lista","Lista"+lista.toString());

            }

            @Override
            public void onFailure(Call<ListModelos> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
    }
    private void creatList(ListModelos listsismos){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(listsismos);
        realm.commitTransaction();
    }
}