package com.example.rubensrodrigues.agenda.dto;

import com.example.rubensrodrigues.agenda.modelo.Aluno;

import java.util.List;

/**
 * Created by rubens on 06/07/17.
 */

public class AlunoSync {

    private List<Aluno> alunos;
    private String momentoDaUltimaModificacao;

    public String getMomentoDaUltimaModificacao() {
        return momentoDaUltimaModificacao;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
