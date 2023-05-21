package com.optimal.task.message;

import lombok.Getter;

@Getter
public enum Message {
    SUCCESS_UZ(1, "Muvaffaqiyatli qo`shildi.", 200, true),
    EDIT_UZ(2, "Muvaffaqiyatli o`zgartirildi", 200, true),
    DELETE_UZ(5, "Muvaffaqiyatli o`chirildi", 200, true),
    THE_DATA_WAS_ENTERED_INCORRECTLY(3, "Ma'lumotlar noto'g'ri kiritilgan", 404, false),
    SIZ_AVVAL_TANLAGAN_MENYUNI_TOLIQ_TAMOMLAMAGANSIZ(16, "Siz avval tanlagan menyu asosida to`liq ovqatlantirib bo`lmagansiz", 404, false),
    INFORMATION_WITH_THIS_NAME_EXISTS(4, "Bunday nom bilan ma`lumot mavjud", 404, false),
    PROBLEM_SAVING_IMAGE(5, "Rasmni saqlashda muammo bo`ldi", 404, true),
    THIS_PHONE_NUMBER_IS_ALREADY_REGISTERED(6, "Ushbu telefon raqam avval ro`yxatdan o`tgan", 404, false),
    A_VERIFICATION_CODE_HAS_BEEN_SENT(7, "Tasdiqlash kodi yuborildi.", 200, true),
    THE_PHONE_NUMBER_ENTERED_IS_INVALID(8, "Kiritilgan telefon raqami yaroqsiz", 404, false),
    CONFIRMED_SUCCESSFULLY(9, "Muvafaqiyatli tasdiqlandi", 200, true),
    THE_VERIFICATION_CODE_ENTERED_IS_INCORRECT(10, "Kiritilgan tasdiqlash kodi noto'g'ri", 404, false),
    BU_KUNGA_MENYU_BIRIKTIRILMAGAN(11, "Bu kunga menyu biriktirilmagan", 404, false),
    KIRITILGAN_BOLA_SONI_QOSHILMADI(12, "Bu kun bolalar sonini o`zgartirning imkoni yo`q. Limit tugagan", 404, false),
    BU_OY_XISOBOTI_UCHUN_TOLOV_AMALGA_OSHIRILGAN(13, "Bu oy uchun to`lov amalga oshirilgan", 404, false),
    TOLOV_AMALGA_OSHIRILDI(14, "uchun to`lov muvaffaqiyatli amalga oshirildi", 200, true),
    TOLOV_AMALGA_OSHIRISH_UCHUN_MABLAG_YETARLI_EMAS(15, "To`lovni amalga oshirish uchun mablag` yetarli emas", 404, false),
    USER_WITH_NO_KINDERGARTEN(16,"Ushbu foydalanuvchining bogcha ma'lumotlari topilmadi",404,false);

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
