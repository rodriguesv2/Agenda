package com.example.rubensrodrigues.agenda.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rubens on 12/07/17.
 */

public class AlunoPreferences {
    private static final String ALUNO_PREFERENCES = "com.example.rubensrodrigues.agenda.preferences.AlunoPreferences";
    private static final String VERSAO_DO_DADO = "versao_do_dado";
    private final Context context;

    public AlunoPreferences(Context context) {

        this.context = context;
    }

    public void salvaVersao(String versao) {
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(VERSAO_DO_DADO, versao);
        editor.commit();
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(ALUNO_PREFERENCES, context.MODE_PRIVATE);
    }

    public String getVersao() {
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getString(VERSAO_DO_DADO, "");
    }

    public boolean temVersao() {
        return !getVersao().isEmpty();
    }
}
