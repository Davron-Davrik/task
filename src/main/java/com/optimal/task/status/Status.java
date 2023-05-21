package com.optimal.task.status;

public enum Status {

    ACTIVE(1, "FAOL"),
    IN_ACTIVE(2, "NOFAOL"),
    DELETE(3, "OʻCHIRILDI"),
    NEW(4, "YANGI"),
    INCLUDED(5, "KIRITILDI"),
    INDEFINITE(6, "NOANIQ"),
    CREATED(7, "YARATILDI"),
    ADD_ORDER(8, "ZAYAFKAGA QO`SHILDI"),
    SEND(9, "YUBORILDI"),
    APPEALED_FOR_REGISTRATION(10, "RO`YXATDAN O`TISHGA ARIZA YUBORDI"),
    REJECTED(11, "RAD ETILDI"),
    FULLY_SENT(12, "TO`LIQ TUGALLANDI"),
    PARTIALLY_SENT(13, "QISMAN TUGALLANDI"),
    PARTIALLY_ACCEPTED(14, "QISMAN QABUL QILINDI"),
    THE_PRODUCT_DID_NOT_ARRIVE(15, "MAXSULOT YETIB KELMADI"),
    QISMAN_YUBORILDI(16, "QISMAN YUBORILDI"),
    EDIT(17, "O`ZGARTIRILDI"),
    PAID(18, "TO`LANGAN"),
    UN_PAID(19, "TO`LANMAGAN"),
    CASH(20, "NAQD"),
    CASHLESS(21, "NAQDSIZ"),
    MAXSULOT_YUBORILDI(23, "MAXSULOT YUBORILDI"),
    TOLIQ_YETKAZIB_BERILDI(22, "TO`LIQ YETKAZIB BERILDI"),
    TASDIQLANDI(23, "TASDIQLANDI"),
    SHARTNOMA_MUDDATI_TUGADI(24, "SHARTNOMA MUDDATI TUGADI");

    private Integer id;
    private String name;

    Status(int id, String name) {
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
