package com.brianbeech.bus.dao;

import com.brianbeech.bus.domain.Person;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bbeech
 * Date: 2/28/13
 * Time: 1:53 PM
 */
@Service("personService")
@Transactional
public class PersonService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    //get all persons
    public List<Person> getAll() {
        logger.info("Getting all persons from Database");
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Person");
        return query.list();
    }

    //get all persons
    public Map<String,String> getPersonsByName(String inputName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Person WHERE firstName like (:names) or lastName like (:names)");
        query.setParameter("names","%"+inputName + "%");
        List<Person> persons = query.list();
        Map<String,String> idNameMap = new HashMap<String, String>();
        for(Person p : persons){
            idNameMap.put(String.valueOf(p.getPersonId()),p.getFirstName() + " " + p.getLastName());
        }
        return idNameMap;
    }

    public void addPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    public Person getPersonByName(int personId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Person WHERE id =  (:names)");
        query.setParameter("names",personId);
        return (Person) query.uniqueResult();
    }
}
