package com.brianbeech.bus.controllers;

import com.brianbeech.bus.dao.PersonDAO;
import com.brianbeech.bus.dao.PersonService;
import com.brianbeech.bus.domain.Person;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bbeech
 * Date: 2/28/13
 * Time: 10:43 AM
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Resource(name="personService")
    private PersonService personService;

    @Resource(name="PersonDAO")
    private PersonDAO personDAO;
    protected static Logger logger = Logger.getLogger("controller");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(ModelMap model) {
        return "/forms/findPerson";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String getPersons(ModelMap model) {

        logger.debug("Got request for all persons");
        // Retrieve all persons by delegating the call to PersonService
        //HIBERNATE IMPLEMENTATION
        //List<Person> persons = personService.getAll();
        //SPRING JDBCTemplate IMPLEMENTATION
        List<Person> persons = personDAO.getAllPeople();
        // Attach persons to the Model
        model.addAttribute("persons", persons);
        // This will resolve to /WEB-INF/jsp/pages/members.jsp
        return "pages/tabs";
    }

    @RequestMapping(value = "/persons/all", method = RequestMethod.GET, params = {"name"})
    public @ResponseBody String getAllPersons(@RequestParam(value ="name") String searchVal) {
        logger.debug("AJAX CALL!");
        Gson gson = new Gson();
        return gson.toJson(personDAO.getPersonsByName(searchVal)).trim();
    }

    @RequestMapping(value = "/person/", method = RequestMethod.GET, params = {"id"})
    public String getPerson(@RequestParam(value ="id") String id, ModelMap model) {
        logger.debug("Getting Person with ID: " + id);
        model.put("viewPerson",personDAO.getPersonById(Integer.valueOf(id)));
//        model.put("viewPerson",personService.getPersonByName(Integer.valueOf(id)));
        return "pages/person";
    }

}
