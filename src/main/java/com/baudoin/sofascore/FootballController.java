package com.baudoin.sofascore;

import okhttp3.OkHttpClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RequestMapping("/api")
@RestController
public class FootballController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.sofascore.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        FootballService footballService = retrofit.create(FootballService.class);
        Call<FootballResponse> events = footballService.getEvents("2019-04-22");
        try{
            Response<FootballResponse> response = events.execute();
            FootballResponse footballResponse = response.body();
            return footballResponse.sportItem.toString();
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
}
