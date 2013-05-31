package com.brianbeech.bus.controllers;

import com.brianbeech.bus.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        System.out.println("contact context");
        model.put("persons",personDAO.getAllPeople());
        return "/pages/contact";
    }
}
