package br.com.jpaestudo;

import br.com.jpaestudo.dao.AddressDAO;
import br.com.jpaestudo.dao.DocumentDAO;
import br.com.jpaestudo.dao.PersonDAO;
import br.com.jpaestudo.dao.PhoneDAO;
import br.com.jpaestudo.entity.Address;
import br.com.jpaestudo.entity.Document;
import br.com.jpaestudo.entity.Person;
import br.com.jpaestudo.entity.Phone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 *
 */
public class AppRevJPA
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!");

        //****** PERSON ******//
        //insertPerson();
        //findPersonById();
        //findAllPersons();
        //countPersons();
        //findByLastName();
        //findByAge();
        //findAgeIsBetween();
        //findByFullName();
        //updatePerson();
        //deletePerson();

        //****** DOCUMENT ******//
        //isertDocument();
        //updateDocument();
        //findPersonByCpf();

        //****** PHONE ******//
        //insertPhone();
        //insertPhoneByPerson();
        //updatePhone();
        //updatePhoneByPerson();
        //deleteOnCascade();
        //insertAddressByPerson();
        //insertPersonByAddress();
        findByCity();
    }

    private static void findByCity() {
        List<Address> addresses = new AddressDAO().findByCity("Rio de Janeiro");
        for (Address address : addresses){
            System.out.println("address = " + address);
        }
    }

    private static void insertPersonByAddress() {
        Person person = new PersonDAO().findById(9L);

        Address address = new Address();
        address.setCity("Porto Alegre");
        address.setStreet("Av. Beira Rio, 102");
        address.setTypeAddress(Address.TypeAddress.RESIDENCIAL);
        address.setPersons(Arrays.asList(person));

        AddressDAO addressDAO = new AddressDAO();
        addressDAO.save(address);

        System.out.println("address = " + addressDAO.findById(address.getId()));
    }

    private static void insertAddressByPerson() {
        Address address = new Address();
        address.setCity("Duque de Caxias");
        address.setStreet("Av.Nilo");
        address.setTypeAddress(Address.TypeAddress.RESIDENCIAL);

        Address address02 = new Address();
        address02.setCity("Jacarepagua");
        address02.setStreet("Estrada Tindiba");
        address02.setTypeAddress(Address.TypeAddress.COMERCIAL);

        Person person01 = new Person();
        person01.setFirstName("Midian07");
        person01.setLastName("Abreu da Rocha");
        person01.setAge(29);
        person01.setDocument(new Document("733333333333", 733333333));

        Phone phone01 = new Phone(Phone.TypePhone.RESIDENCIAL, "46572354");
        Phone phone02 = new Phone(Phone.TypePhone.COMERCIAL, "46572345");

        person01.addPhone(phone01);
        person01.addPhone(phone02);
        person01.setAddresses(Arrays.asList(address,address02));

        new PersonDAO().save(person01);

        System.out.println("new PersonDAO().findById(person01.getId()) = " + new PersonDAO().findById(person01.getId()));
    }

    private static void deleteOnCascade() {
//        new PersonDAO().delete(5L);

        PhoneDAO phoneDAO = new PhoneDAO();
        Phone phone = phoneDAO.findById(4l);
        System.out.println("phone = " + phone);
        Person person = phone.getPerson();
        person.removePhone(phone);
        phoneDAO.delete(phone);
    }

    private static void updatePhoneByPerson() {
        Person person = new PersonDAO().findById(5L);

        for (Phone phone : person.getPhones()){
            if(phone.getTypePhone().equals(Phone.TypePhone.COMERCIAL)){
                phone.setTypePhone(Phone.TypePhone.RESIDENCIAL);
            }
        }

        new PersonDAO().update(person);
        for (Phone phone : person.getPhones()){
            System.out.println("phone = " + phone);
        }
    }

    private static void updatePhone() {

        PhoneDAO phoneDAO =  new PhoneDAO();
        Phone phone = phoneDAO.findById(2L);
        Phone phone0 = phoneDAO.findById(3L);

        System.out.println("phone = " + phone);
        System.out.println("phone0 = " + phone0);

        Person person = new PersonDAO().findById(5L);
        System.out.println("person = " + person);
        person.addPhone(phone);
        person.addPhone(phone0);
        new PersonDAO().update(person);
    }

    private static void insertPhoneByPerson() {
        Phone phone01 = new Phone(Phone.TypePhone.CELULAR, "588888888");
        Phone phone02 = new Phone(Phone.TypePhone.COMERCIAL, "54444444");

        Person person01 = new Person();
        person01.setFirstName("Midian05");
        person01.setLastName("Abreu da Rocha");
        person01.setAge(29);
        person01.setDocument(new Document("533333333333", 533333333));

//        person01.setPhones(Arrays.asList(phone01,phone02));
//
//        phone01.setPerson(person01);
//        phone02.setPerson(person01);

        person01.addPhone(phone01);
        person01.addPhone(phone02);
        new PersonDAO().save(person01);

        System.out.println("person01 = " + person01);

    }

    private static void insertPhone() {
        Person person01 = new Person();
        person01.setFirstName("Midian02");
        person01.setLastName("Abreu da Rocha");
        person01.setAge(29);
        person01.setDocument(new Document("222222222222", 222222222));

        Phone phone = new Phone(Phone.TypePhone.CELULAR, "999999999" , person01);
        PhoneDAO phoneDAO = new PhoneDAO();
        phoneDAO.save(phone);

        phone = phoneDAO.findById(phone.getId());

        System.out.println("phone = " + phone);

    }

    private static void findPersonByCpf() {
        Person person = new PersonDAO().findByCpf("111.111.111.11");
        System.out.println("person = " + person);
    }

    private static void updateDocument() {
        Document document = new DocumentDAO().findById(1l);
        System.out.println("document = " + document);
        document.setCpf("111.111.111.11");
        new DocumentDAO().update(document);
        System.out.println("document = " + document);
    }

    private static void deletePerson() {
        new PersonDAO().delete(2L);

    }

    private static void updatePerson() {
        Person person = new PersonDAO().findById(2L);
        System.out.println("person = " + person);
        person.setAge(10);
        new PersonDAO().update(person);
        System.out.println("person = " + person);
    }

    private static void findByFullName() {
        Person person = new PersonDAO().findByFullName("Carlos","Maia Couto");
        System.out.println("person = " + person);
    }

    private static void findAgeIsBetween() {
        List<Person> personList = new PersonDAO().findAgeIsBetween(30,30);
        for (Person person: personList){
            System.out.println("person = " + person);
        }
    }

    private static void findByAge() {
        List<Person> personList = new PersonDAO().findByAge(29);
        for (Person person: personList){
            System.out.println("person = " + person);
        }
    }

    private static void findByLastName() {

        List<Person> personList = new PersonDAO().findByLastName("Maia Couto");
        for (Person person: personList){
            System.out.println("person = " + person);
        }
    }

    private static void countPersons() {
        Long count = new PersonDAO().count();

        System.out.println("Total = " + count);
    }

    private static void findAllPersons() {
        List<Person> persons = new PersonDAO().findAll();

        for (Person p : persons){
            System.out.println("p = " + p);
        }
    }

    private static void findPersonById() {
        Person p1 = new PersonDAO().findById(1L);

        Person p2 = new PersonDAO().findById(2L);


        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);

    }

    private static void insertPerson() {

//        Person p1 = new Person();
//        p1.setAge(29);
//        p1.setFirstName("Carlos");
//        p1.setLastName("Maia Couto");
//
//        new PersonDAO().save(p1);
//
//        System.out.println("p1 = " + p1);

        Person p2 = new Person();
        p2.setAge(30);
        p2.setFirstName("Carlos 02");
        p2.setLastName("Maia Couto 02");

        new PersonDAO().save(p2);

        System.out.println("p2 = " + p2);
        System.out.println("count = " + new PersonDAO().count());
        System.out.println("findAll = " + new PersonDAO().findAll());

    }

    public static void isertDocument() {
        Person person01 = new Person();
        person01.setFirstName("Midian");
        person01.setLastName("Abreu da Rocha");
        person01.setAge(28);
        person01.setDocument(new Document("111111111111", 123456789));

        new PersonDAO().save(person01);
        System.out.println("person01 = " + person01);
    }
}
