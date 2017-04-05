package br.com.jpaestudo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ADDRESSES")
public class Address implements Serializable {

    public enum TypeAddress{
        COMERCIAL, RESIDENCIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ADDRESS")
    private Long id;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "TYPE_ADDRESS", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeAddress typeAddress;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="PERSONS_ADDRESSES",
        joinColumns = @JoinColumn(name="ID_ADDRESS"),
        inverseJoinColumns = @JoinColumn(name = "ID_PERSON")
    )
    private List<Person> persons;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public TypeAddress getTypeAddress() {
        return typeAddress;
    }

    public void setTypeAddress(TypeAddress typeAddress) {
        this.typeAddress = typeAddress;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        return getId().equals(address.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", typeAddress=" + typeAddress +
                ", persons=" + persons +
                '}';
    }
}
