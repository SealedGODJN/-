package com.DesignPattern.CompositePattern;

import java.util.ArrayList;
import java.util.List;

public class OrganizationComposite extends OrganizationComponent {

    //很关键，这体现了组合的思想
    private List<OrganizationComponent> organizations = new ArrayList<>();

    public OrganizationComposite(String name) {
        super(name);
    }

    @Override
    public void add(OrganizationComponent organization) {
        organizations.add(organization);
    }

    @Override
    public OrganizationComponent getChild(String orgName) {
        for (OrganizationComponent org : organizations) {
            OrganizationComponent targetOrg = org.getChild(orgName);
            if (targetOrg != null) {
                return targetOrg;
            }
//            if(org.getName().equals(orgName)) return org;
        }
        return null;
    }

    @Override
    public int getStaffCount() {
        int count = 0;
        for (OrganizationComponent organization : organizations) {
            count += organization.getStaffCount();
        }
        return count;
    }
}
