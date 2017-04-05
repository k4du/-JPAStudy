package br.com.jpaestudo.dao;

import br.com.jpaestudo.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

public class PersonDAO extends GenericDAO<Person>{

    public PersonDAO(){
        super(Person.class);
    }

    public List<Person> findByLastName(String lastName){

        String jpql = " from Person p where p.lastName like :lastName ";

        HashMap<String, Object> params = new HashMap();
        params.put("lastName", lastName);

        return find(jpql,params);
    }

    public List<Person> findByAge(Integer age){
        String jpql = " from Person p where p.age = :age ";

        HashMap<String, Object> params = new HashMap();
        params.put("age", age);

        return find(jpql,params);
    };

    public List<Person> findAgeIsBetween(Integer firstAge, Integer secondAge) {

        String jpql = " from Person p where p.age between :firstAge and :secondAge ";

        HashMap<String, Object> params = new HashMap();
        params.put("firstAge", firstAge);
        params.put("secondAge", secondAge);

        return find(jpql,params);
    }

    public Person findByFullName(String firstName, String lastName){

        String jpql = " from Person p  where p.firstName = :firstName and p.lastName = :lastName";

        HashMap<String, Object> params = new HashMap();
        params.put("firstName", firstName);
        params.put("lastName", lastName);

        return findOne(jpql,params);
    }

    public Person findByCpf(String cpf){

        String jpql = "select p from Person p, Document d where d.cpf like :cpf and p.document.id = d.id";

        HashMap<String, Object> params = new HashMap();
        params.put("cpf", cpf);

        return findOne(jpql,params);

    }


}
