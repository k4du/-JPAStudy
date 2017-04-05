package br.com.jpaestudo.dao;

import br.com.jpaestudo.entity.Address;

import java.util.HashMap;
import java.util.List;

public class AddressDAO extends GenericDAO<Address>{

    public AddressDAO() {
        super(Address.class);
    }

    public List<Address> findByCity(String city){
        String jpql = " from Address a where a.city like :city";

        HashMap<String, Object> params = new HashMap();
        params.put("city", city);

        List<Address> addressList = find(jpql, params);
        return addressList;
    }
}
