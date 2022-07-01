package br.com.giulio.springtdd.service;

import br.com.giulio.springtdd.model.Pessoa;
import br.com.giulio.springtdd.model.Telefone;
import br.com.giulio.springtdd.service.exception.TelefoneNaoEncontradoException;
import br.com.giulio.springtdd.service.exception.UnicidadeCpfException;
import br.com.giulio.springtdd.service.exception.UnicidadeTelefoneException;

public interface PessoaService {
    Pessoa salvar(Pessoa pessoa) throws UnicidadeCpfException, UnicidadeTelefoneException;
    Pessoa buscarPorTelefone(Telefone telefone) throws TelefoneNaoEncontradoException;
}
