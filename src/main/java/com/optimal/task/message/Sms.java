package com.optimal.task.message;

public enum Sms {
    RESET_PASSWORD("mtt-menyu.uz tizimida parolni tiklash uchun tasdiqlash kodi: "),
    SIGN_UP("mtt-menyu.uz tizimidan ro'yxatdan o'tish uchun tasdiqlash kodi: ");

    private String name;



    Sms(String name) {

        this.name = name;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
