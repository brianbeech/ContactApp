package com.brianbeech.bus.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 5/28/13
 * Time: 12:03 PM
 */
public class DashboardStatistics {
    private int visitsThisMonth;
    private int activeMembersVisitedThisMonth;
    private int inactiveMembersVisitedThisMonth;
    private int visitorsVisitedThisMonth;

    public int getVisitsThisMonth() {
        return visitsThisMonth;
    }

    public void setVisitsThisMonth(int visitsThisMonth) {
        this.visitsThisMonth = visitsThisMonth;
    }

    public int getActiveMembersVisitedThisMonth() {
        return activeMembersVisitedThisMonth;
    }

    public void setActiveMembersVisitedThisMonth(int activeMembersVisitedThisMonth) {
        this.activeMembersVisitedThisMonth = activeMembersVisitedThisMonth;
    }

    public int getInactiveMembersVisitedThisMonth() {
        return inactiveMembersVisitedThisMonth;
    }

    public void setInactiveMembersVisitedThisMonth(int inactiveMembersVisitedThisMonth) {
        this.inactiveMembersVisitedThisMonth = inactiveMembersVisitedThisMonth;
    }

    public int getVisitorsVisitedThisMonth() {
        return visitorsVisitedThisMonth;
    }

    public void setVisitorsVisitedThisMonth(int visitorsVisitedThisMonth) {
        this.visitorsVisitedThisMonth = visitorsVisitedThisMonth;
    }
}
