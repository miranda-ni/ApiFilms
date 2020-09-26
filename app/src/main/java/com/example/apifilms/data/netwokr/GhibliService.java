package com.example.apifilms.data.netwokr;

import android.util.Log;

import com.example.apifilms.data.models.FilmModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GhibliService {


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://ghibliapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    GhibliApi service = retrofit.create(GhibliApi.class);

    public void getFilmById(String id, final GhibliFilmCallback callback) {
        Call<FilmModel> call = service.getFilmById(id);
        call.enqueue(new Callback<FilmModel>() {
            @Override
            public void onResponse(Call<FilmModel> call, Response<FilmModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                    Log.d("tag", response.body().getTitle());
                }
            }

            @Override
            public void onFailure(Call<FilmModel> call, Throwable t) {
                callback.onFailure(t);
                Log.d("tag", "Failure");
            }
        });
    }
    public void getFilms(GhibliListCallBack ghibliListCallBack){
        Call<ArrayList<FilmModel>> call = service.getFilms();
        call.enqueue(new Callback<ArrayList<FilmModel>>() {
            @Override
            public void onResponse(Call<ArrayList<FilmModel>> call, Response<ArrayList<FilmModel>> response) {
                ghibliListCallBack.onSuccess(response.body());
            }

            @Override //esli oshibka to onFailure
            public void onFailure(Call<ArrayList<FilmModel>> call, Throwable t) {
                ghibliListCallBack.onFailure(t);

            }
        });


    }

    public interface GhibliListCallBack{
        void onSuccess(ArrayList<FilmModel> filmModels);

        void onFailure(Throwable e);

    }

    public interface GhibliFilmCallback {
        void onSuccess(FilmModel filmModel);

        void onFailure(Throwable ex);
    }

    public interface GhibliApi {
        @GET("films/{filmId}")
        Call<FilmModel> getFilmById
                (@Path("filmId") String filmId);


        @GET("films")
        Call<ArrayList<FilmModel>> getFilms();


    }
}
