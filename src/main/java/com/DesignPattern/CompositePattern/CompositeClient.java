package com.DesignPattern.CompositePattern;

public class CompositeClient {

    public static void test() {
        OrganizationComposite company = new OrganizationComposite("总公司");
        OrganizationComposite managerDepartment = new OrganizationComposite("总公司行政部门");
        ItDepartment it = new ItDepartment("总公司IT");
        company.add(managerDepartment);
        company.add(it);

        OrganizationComposite tianjinCompany = new OrganizationComposite("天津分公司");
        OrganizationComposite tianjinManagerDepartment = new OrganizationComposite("天津分公司行政部门");
        ItDepartment tianjinIt = new ItDepartment("天津分公司IT");
        tianjinCompany.add(tianjinManagerDepartment);
        tianjinCompany.add(tianjinIt);

        company.add(tianjinCompany);

        System.out.println(company.getStaffCount());

        System.out.println(company.getChild("总公司行政部门").getStaffCount());

        System.out.println(company.getChild("天津分公司").getChild("天津分公司IT").getStaffCount());
    }

    public static void main(String[] args) {
//        test();
        listOrgInfo();
    }

    private static OrganizationComponent constructOrganization() {
        //构建总部
        OrganizationComposite head = new OrganizationComposite("总公司");
        AdminDepartment headAdmin = new AdminDepartment("总公司行政部");
        ItDepartment headIt = new ItDepartment("总公司It部");
        head.add(headAdmin);
        head.add(headIt);

        //构建分公司
        OrganizationComposite branch1 = new OrganizationComposite("天津分公司");
        AdminDepartment branch1Admin = new AdminDepartment("天津分公司行政部");
        ItDepartment branch1It = new ItDepartment("天津分公司It部");
        branch1.add(branch1Admin);
        branch1.add(branch1It);

        //将分公司加入到head中
        head.add(branch1);

        return head;
    }

    public static void listOrgInfo() {
        OrganizationComponent org = constructOrganization();
        System.out.println(String.format("%s共有%d名员工",
                org.getName(), org.getStaffCount()));

        OrganizationComponent subOrg = org.getChild("天津分公司行政部");
        System.out.println(String.format("%s共有%d名员工",
                subOrg.getName(), subOrg.getStaffCount()));
    }

}
