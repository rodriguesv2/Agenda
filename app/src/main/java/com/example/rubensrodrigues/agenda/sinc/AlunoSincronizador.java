package com.example.rubensrodrigues.agenda.sinc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rubensrodrigues.agenda.dao.AlunoDAO;
import com.example.rubensrodrigues.agenda.dto.AlunoSync;
import com.example.rubensrodrigues.agenda.event.AtualizaListaAlunoEvent;
import com.example.rubensrodrigues.agenda.preferences.AlunoPreferences;
import com.example.rubensrodrigues.agenda.retrofit.RetrofitInicializador;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoSincronizador {
    private final Context context;
    private EventBus bus = EventBus.getDefault();
    private AlunoPreferences preferences;

    public AlunoSincronizador(Context context) {
        this.context = context;
        preferences = new AlunoPreferences(context);
    }

    public void buscaTodos(){
        if(preferences.temVersao()){
            buscaNovos();
        }else{
            buscaAlunos();
        }
    }

    private void buscaNovos() {
        String versao = preferences.getVersao();
        Call<AlunoSync> call = new RetrofitInicializador().getAlunoService().novos(versao);
        call.enqueue(buscaAlunoCallback());
    }

    private void buscaAlunos() {
        Call<AlunoSync> call = new RetrofitInicializador().getAlunoService().lista();

        call.enqueue(buscaAlunoCallback());
    }

    @NonNull
    private Callback<AlunoSync> buscaAlunoCallback() {
        return new Callback<AlunoSync>() {
            @Override
            public void onResponse(Call<AlunoSync> call, Response<AlunoSync> response) {
                AlunoSync alunoSync = response.body();
                String versao = alunoSync.getMomentoDaUltimaModificacao();

                preferences.salvaVersao(versao);

                new AlunoPreferences(context).salvaVersao(versao);

                AlunoDAO dao = new AlunoDAO(context);
                dao.sincroniza(alunoSync.getAlunos());
                dao.close();
                Log.i("versao", preferences.getVersao());

                bus.post(new AtualizaListaAlunoEvent());
            }

            @Override
            public void onFailure(Call<AlunoSync> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
                bus.post(new AtualizaListaAlunoEvent());
            }
        };
    }
}