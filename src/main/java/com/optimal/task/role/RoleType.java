package com.optimal.task.role;


public enum RoleType {

    ADMIN(1, "ROLE_ADMIN"),
    KINDERGARDEN_PRINCIPAL(2, "ROLE_BOG`CHA_MUDIRASI"),
    MANAGER(3, "ROLE_MANAGER"),
    PROGRAMMER(3, "ROLE_PROGRAMMER"),
    TECHNOLOGIST(4, "ROLE_TECHNOLOGIST");
//    TECHNOLOGIST(5, "ROLE_TEXNOLOG"),
//    ACCOUNTANT(6, "ROLE_BO`LIM_BUXGALTER"),
//    NURSE(7, "ROLE_HAMSHIRA"),
//    COOK(8, "ROLE_OMBORCHI"),
//    HUMAN_RESOURCES(8, "ROLE_XODIMLAR_BO`LIMI");


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
