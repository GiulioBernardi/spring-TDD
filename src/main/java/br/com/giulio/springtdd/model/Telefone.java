package br.com.giulio.springtdd.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(sequenceName = "SQ_TB_TELEFONE", allocationSize = 1, name = "telefone")
@Table(name = "TB_TELEFONE")
public class Telefone {


    @Id
    @GeneratedValue(generator = "telefone", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 2, nullable = false)

    private String ddd;
    @Column(length = 9, nullable = false)
    private String numero;


    @ManyToOne
    @JoinColumn(name = "cd_pessoa")
    private Pessoa pessoa;

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(id, telefone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
