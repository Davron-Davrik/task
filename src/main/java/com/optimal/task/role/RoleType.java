package com.optimal.task.role;


public enum RoleType {

    SUPER_ADMIN(1, "ROLE_SUPER_ADMIN"),
    ADMIN(2, "ROLE_ADMIN"),
    ADMIN_DEPARTMENT(3, "ROLE_ADMIN_DEPARTMENT"),
    MANAGEMENT_ACCOUNTANT(4, "ROLE_BOSHQARMA_BUXGALTER"),
    TECHNOLOGIST(5, "ROLE_TEXNOLOG"),
    ACCOUNTANT(6, "ROLE_BO`LIM_BUXGALTER"),  //BO`LIM BOSH XISOBCHISI
    NURSE(7, "ROLE_HAMSHIRA"),
    COOK(8, "ROLE_OMBORCHI"),
    HUMAN_RESOURCES(9, "ROLE_XODIMLAR_BO`LIMI"),
    ROLE_RAXBAR(10, "ROLE_RAXBAR"),  //BOG`CHA
    ROLE_BUXGALTER(11, "ROLE_BUXGALTER"); //YORDAMCHI HISOBCHI


    private Integer id;
    private String name;

    RoleType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
