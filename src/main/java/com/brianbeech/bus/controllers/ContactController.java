package com.brianbeech.bus.controllers;

import com.brianbeech.bus.dao.PersonDAO;
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
 * Date: 5/20/13
 * Time: 9:44 AM
 */
@Controller
@RequestMapping(value = "/contacts")
public class ContactController {
    @Autowired
    PersonDAO personDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(ModelMap model) {
        model.put("persons", personDAO.getAllPeople());
        return "/pages/contact";
    }

    @RequestMapping(value = "/updateContact", method = RequestMethod.GET, params = {"id"})
    public @ResponseBody String updateContact(@RequestParam(value = "id") int personId, ModelMap model) {
        String ret = "";
        try {
            personDAO.addContactDateForPerson(personId);
            ret = personDAO.getLatestContactDateForPerson(personId);
        } catch (Exception e) {
            ret = "Error: " + e.getMessage();
        }
        return ret;
    }
}
