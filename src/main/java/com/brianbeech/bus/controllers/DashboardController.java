package com.brianbeech.bus.controllers;

import com.brianbeech.bus.dao.ContactDAO;
import com.brianbeech.bus.dao.PersonDAO;
import com.brianbeech.bus.domain.DashboardStatistics;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 5/20/13
 * Time: 9:44 AM
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {
    @Autowired
    ContactDAO contactDAO;

    @Autowired
    PersonDAO personDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(ModelMap model) {
        model.put("persons",personDAO.getAllPeople());
        model.put("visitsThisMonth",contactDAO.getNumberOfVisitsThisMonth());
        return "/pages/dashboard";
    }

    @RequestMapping(value = "/numVisitsThisMonth", method = RequestMethod.GET)
    public @ResponseBody String getMonthsVisits(){
        DashboardStatistics ds = new DashboardStatistics();
        ds.setActiveMembersVisitedThisMonth(contactDAO.getNumberOfMembersVisitedThisMonth());
        ds.setInactiveMembersVisitedThisMonth(contactDAO.getNumberOfInactiveMemberVisitsThisMonth());
        ds.setVisitorsVisitedThisMonth(contactDAO.getNumberOfVisitorsVisitedThisMonth());
        ds.setVisitsThisMonth(contactDAO.getNumberOfVisitsThisMonth());
        Gson gson = new Gson();
        return gson.toJson(ds);
    }
}
