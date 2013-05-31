package com.brianbeech.bus.controllers;

import com.brianbeech.bus.dao.PersonDAO;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 5/24/13
 * Time: 7:56 AM
 */
@Controller
@RequestMapping(value = "/ajax")
public class PersonController {

    @Autowired
    PersonDAO personDAO;
    protected static Logger logger = Logger.getLogger("controller");

    @RequestMapping(value = "/person", method = RequestMethod.GET, params = {"id"})
    public String getAllPersons(@RequestParam(value ="id") int searchVal, ModelMap model) {
        logger.debug("AJAX CALL!");
        model.addAttribute("viewPerson", personDAO.getPersonById(searchVal));
/*        Gson gson = new Gson();
        return gson.toJson().trim();*/
        return "pages/person";
    }
}
