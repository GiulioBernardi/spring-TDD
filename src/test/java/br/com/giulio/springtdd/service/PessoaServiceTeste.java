package br.com.giulio.springtdd.service;

import br.com.giulio.springtdd.model.Pessoa;
import br.com.giulio.springtdd.model.Telefone;
import br.com.giulio.springtdd.repository.PessoaRepository;
import br.com.giulio.springtdd.service.exception.TelefoneNaoEncontradoException;
import br.com.giulio.springtdd.service.exception.UnicidadeCpfException;
import br.com.giulio.springtdd.service.exception.UnicidadeTelefoneException;
import br.com.giulio.springtdd.service.impl.PessoaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PessoaServiceTeste {
    private PessoaServiceImpl pessoaService;

    private static final String NOME = "Giulio Bernardi";
    private static final String CPF = "489.658.486-99";
    private static final String DDD = "13";
    private static final String NUMERO = "988473800";



    @MockBean
    private PessoaRepository pessoaRepository;

    private Pessoa pessoa;

    @Before
    public void setUp() throws Exception{
        pessoaService = new PessoaServiceImpl(pessoaRepository);
        pessoa = new Pessoa();
        pessoa.setNome(NOME);
        pessoa.setCpf(CPF);

        Telefone telefone = new Telefone();
        telefone.setDdd(DDD);
        telefone.setNumero(NUMERO);

        pessoa.setTelefones(Arrays.asList(telefone));

        when(pessoaRepository.findByCpf(CPF)).thenReturn(Optional.empty());
        when(pessoaRepository.findByTelefoneDddAndTelefoneNumero(DDD, NUMERO)).thenReturn(Optional.empty());
    }

    @Test
    public void deveSalvarPessoaNoRepositorio() throws UnicidadeCpfException, UnicidadeTelefoneException {
        pessoaService.salvar(pessoa);

        verify(pessoaRepository).save(pessoa);
    }

    @Test(expected = UnicidadeCpfException.class)
    public void naoDeveSalvarDuasPessasComCpfsIguais() throws UnicidadeCpfException, UnicidadeTelefoneException {
        when(pessoaRepository.findByCpf(CPF)).thenReturn(Optional.of(pessoa));
        pessoaService.salvar(pessoa);
    }

    @Test(expected = UnicidadeTelefoneException.class)
    public void naoDeveSalvarPessoasComTelefonesIguais() throws UnicidadeCpfException, UnicidadeTelefoneException {
        when(pessoaRepository.findByTelefoneDddAndTelefoneNumero(DDD, NUMERO)).thenReturn(Optional.of(pessoa));
        pessoaService.salvar(pessoa);
    }

    @Test(expected = TelefoneNaoEncontradoException.class)
    public void deveRetornarExcecaoDeNaoEncontradoQuandoNaoTiverPessoaComOTelefoneBuscado() throws TelefoneNaoEncontradoException {
        Telefone telefone = new Telefone( );
        telefone.setDdd(DDD);
        telefone.setNumero(NUMERO);
        pessoaService.buscarPorTelefone(telefone);
    }

    @Test
    public void deveProcurarPessoaPeloDddENumeroDeTelefone() throws TelefoneNaoEncontradoException {
        when(pessoaRepository.findByTelefoneDddAndTelefoneNumero(DDD, NUMERO)).thenReturn(Optional.of(pessoa));
        Telefone telefone = new Telefone();
        telefone.setDdd(DDD);
        telefone.setNumero(NUMERO);
        Pessoa pessoaTeste = pessoaService.buscarPorTelefone(telefone);

        verify(pessoaRepository).findByTelefoneDddAndTelefoneNumero(DDD, NUMERO);

        assertThat(pessoaTeste).isNotNull();
        assertThat(pessoaTeste.getNome()).isEqualTo(NOME);
        assertThat(pessoaTeste.getCpf()).isEqualTo(CPF);

    }

}
