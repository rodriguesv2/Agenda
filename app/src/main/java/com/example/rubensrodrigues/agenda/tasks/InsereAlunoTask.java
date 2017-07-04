package com.example.rubensrodrigues.agenda.tasks;

import android.os.AsyncTask;

import com.example.rubensrodrigues.agenda.WebClient;
import com.example.rubensrodrigues.agenda.converter.AlunoConverter;
import com.example.rubensrodrigues.agenda.modelo.Aluno;

/**
 * Created by rubens on 04/07/17.
 */

public class InsereAlunoTask extends AsyncTask{
    private final Aluno aluno;

    public InsereAlunoTask(Aluno aluno) {

        this.aluno = aluno;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String json = new AlunoConverter().converteParaJSONCompleto(aluno);
        new WebClient().insere(json);
        return null;
    }
}
