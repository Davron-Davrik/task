package com.optimal.task.message;

public enum Message {
    SUCCESS_UZ(1, "Muvaffaqiyatli qo`shildi.", 200, true),
    EDIT_UZ(2, "Muvaffaqiyatli o`zgartirildi", 200, true),
    DELETE_UZ(5, "Muvaffaqiyatli o`chirildi", 200, true),
    THE_DATA_WAS_ENTERED_INCORRECTLY(3, "Ma'lumotlar noto'g'ri kiritilgan", 500, false),
    INFORMATION_WITH_THIS_NAME_EXISTS(4, "Bunday nom bilan ma`lumot mavjud", 500, false),
    PROBLEM_SAVING_IMAGE(5, "Rasmni saqlashda muammo bo`ldi", 500, false),
    THE_CONTRACT_CANNOT_BE_CANCELED(6, "Shartnoma yakunlangan o`chirishni imkoni yo`q", 500, false),
    THE_CONTRACT_DOES_NOT_THE_SPECIFIED_CRITERIA(7, "Shartnomda kiritilgan narxlar belgilangan mezonlarga muvofiq emas", 404, false),
    SUCCESS_VERIFIED(8, "Muvaffaqiyatli tasdiqlandi.", 200, true),
    THE_AMOUNT_ENTERED_IS_TOO_HIGH(9, "Kiritilgan miqdor juda ko`p.", 500, false),
    BU_KUNGA_MENYU_BIRIKTIRILMAGAN(10, "Bu kunga Menyu biriktirilmagan.", 500, false),
    SUCCESS_SEND(1, "Muvaffaqiyatli yuborildi.", 200, true);

    private Integer id;
    private String name;
    private Integer code;
    private boolean state;


    Message(Integer id, String name, Integer code, boolean state) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
