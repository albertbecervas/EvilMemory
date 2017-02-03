package com.evilmem.albert.evilmemory.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.evilmem.albert.evilmemory.Interface.FOAASResponse;
import com.evilmem.albert.evilmemory.Interface.FOAASService;
import com.evilmem.albert.evilmemory.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.evilmem.albert.evilmemory.R.id.result;

public class Weather extends AppCompatActivity {

    ButterKnife butterKnife;

    TextView celcius;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    FOAASService service = retrofit.create(FOAASService.class);

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        butterKnife.bind(this);

        String name = "flugencio";
        String from = "ermengildo";
        String appid="31b6b748c4c0db46e501ca60f2df064c";


        service.getWeather(41.3818,2.1685,appid).enqueue(new retrofit2.Callback<FOAASResponse>() {
            @Override
            public void onResponse(Call<FOAASResponse> call, Response<FOAASResponse> response) {
                if(response.isSuccessful()){
                    FOAASResponse foaasResponse = response.body();
                    //result= "tjykb";
                    result = "   "+foaasResponse.getLat()+ "    "  + foaasResponse.getLon();
                    //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(),"OH NO", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FOAASResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        celcius = (TextView) findViewById(R.id.textView2);


    }


}
