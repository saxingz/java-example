package org.saxing.aa0035_01_validatedemo.demo.dto;

import org.saxing.aa0035_01_validatedemo.demo.valid.DTOValidGroup;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DO {

    public interface add{}
    public interface update{}

    @NotNull(message = "textvalue 不为空")
    String textValue;

    @Max(value = 5, message = "numberValue 最大值为5", groups = add.class)
    @Min(value = 5, message = "numberValue 最小值为5", groups = update.class)
    Integer numberValue;


    DO preValue;

    public void setNumber (Integer numberValue) {
        this.numberValue = numberValue;
    }

    public void setText (String textValue) {
        this.textValue = textValue;
    }


    public void setPre(DO preValue) {
        this.preValue = preValue;
    }

}
