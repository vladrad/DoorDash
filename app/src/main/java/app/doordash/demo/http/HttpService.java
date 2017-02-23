package app.doordash.demo.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vladi on 2/9/17.
 */

public class HttpService {
    public static final String BASE_URL = "https://api.doordash.com/v2/";

    public static Api getApi() {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new HeaderInterceptor()).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Api.class);
    }

}
