package br.com.giulio.springtdd.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(sequenceName = "SQ_TB_PESSOA", allocationSize = 1, name = "pessoa")
@Table(name = "TB_PESSOA")
public class Pessoa {

    public Pessoa(String nome, String cpf, List<Telefone> telefones) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefones = telefones;
    }

    public Pessoa() {
    }

    @Id
    @Column(name = "cd_pessoa")
    @GeneratedValue(generator = "pessoa", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "nm_pessoa", length = 80, nullable = false)
    private String nome;
    @Column(name = "ds_cpf", length = 11, nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "pessoa")
    private List<Telefone> telefones;
    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
