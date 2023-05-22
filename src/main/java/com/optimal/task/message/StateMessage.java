package com.optimal.task.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateMessage {

    private String text;
    private boolean success;
    private Integer code;


    public StateMessage successAdd() {
        return new StateMessage("Muvofaqiyatli qo'shildi", true, 200);
    }

    public StateMessage successEdit() {
        return new StateMessage("Muvofaqiyatli o'zgartirildi", true, 200);
    }

    public StateMessage successDelete() {
        return new StateMessage("Muvofaqiyatli o'chirildi", true, 200);
    }


    public StateMessage suchANameExists() {
        return new StateMessage("Bunday nom bilan ma'lumot mavjud", false, 417);
    }
    public StateMessage wrongInformation() {
        return new StateMessage("Ma'vjud bo'lmagan ma'lumot kiritildi", false, 417);
    }
}
