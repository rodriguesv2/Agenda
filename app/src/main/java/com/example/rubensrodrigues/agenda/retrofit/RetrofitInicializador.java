package com.example.rubensrodrigues.agenda.retrofit;

import com.example.rubensrodrigues.agenda.services.AlunoService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Rubens Rodrigues on 04/07/2017.
 */

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador(){
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.249:8080/api/").addConverterFactory(JacksonConverterFactory.create()).build();
    }

    public AlunoService getAlunoService() {
        return retrofit.create(AlunoService.class);
    }
}
