package com.brianbeech.bus.controllers;

import com.brianbeech.bus.dao.PersonDAO;
import com.brianbeech.bus.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 3/8/13
 * Time: 11:20 AM
 */
@Controller
@RequestMapping("/person/add")
public class AddPerson {

    @Resource
    private PersonDAO personDao;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String processSubmit(@ModelAttribute Person person,SessionStatus status,Model model){
        personDao.addPerson(person);
        status.setComplete();
        return "Person Added!";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model){
        Person person = new Person();
        model.addAttribute(person);
        return "/forms/addPerson";
    }

}
