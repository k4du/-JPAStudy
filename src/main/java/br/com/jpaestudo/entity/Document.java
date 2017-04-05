package br.com.jpaestudo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="DOCUMENTS")
public class Document implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_DOCUMENT")
    private Long id;

    @Column(name="CPF", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name="RG", unique = true)
    private Integer rg;

    public Document(){
        super();
    }

    public Document(String cpf, Integer rg) {
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getRg() {
        return rg;
    }

    public void setRg(Integer rg) {
        this.rg = rg;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        return id.equals(document.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", rg=" + rg +
                '}';
    }
}

