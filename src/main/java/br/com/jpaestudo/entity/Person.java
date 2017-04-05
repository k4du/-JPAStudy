package br.com.jpaestudo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "PERSONS",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = "FIRST_NAME, LAST_NAME", name = "IDX_1")
//        },
        indexes = {@Index(columnList = "FIRST_NAME, LAST_NAME", name = "IDX_1", unique = true)}
)
public class Person implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSON")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    private String firstName;


    @Column(name = "LAST_NAME", nullable = false, length = 60)
    private String lastName;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DOCUMENT_ID")
    private Document document;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Phone> phones;


    //    @LazyCollection(LazyCollectionOption.FALSE) se não quiser colocar a lista para Set
    //    então colocar essa anotação para o hibernate conseguir carregar mais de uma lista no Eager

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="PERSONS_ADDRESSES",
            joinColumns = @JoinColumn(name="ID_PERSON"),
            inverseJoinColumns = @JoinColumn(name = "ID_ADDRESS")
    )
    private List<Address> addresses;


    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone){
        if(phones == null){
            System.out.println("phones = " + phones);
            phones = new HashSet<Phone>();
        }
        System.out.println("phones0 = " + phones);
        phone.setPerson(this);
        phones.add(phone);
    }

    public void removePhone(Phone phone){
        if(phones != null){
            phones.remove(phone);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return getId().equals(person.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", document=" + document +
                '}';
    }
}
