package br.com.jpaestudo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PHONES")
public class Phone implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PHONE")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_PHONE", nullable = false)
    private TypePhone typePhone;

    @Column(name = "NUMBER_PHONE", nullable = false)
    private String number;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PERSON_ID")
    private Person person;


    public Phone() {
    }

    public Phone(TypePhone typePhone, String number) {
        this.typePhone = typePhone;
        this.number = number;
    }

    public Phone(TypePhone typePhone, String number, Person person) {
        this.typePhone = typePhone;
        this.number = number;
        this.person = person;
    }

    public enum TypePhone{
        RESIDENCIAL, CELULAR, COMERCIAL
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypePhone getTypePhone() {
        return typePhone;
    }

    public void setTypePhone(TypePhone typePhone) {
        this.typePhone = typePhone;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", typePhone=" + typePhone +
                ", number='" + number + '\'' +
                ", person=" + person +
                '}';
    }
}
