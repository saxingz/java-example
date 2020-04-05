package org.saxing.aa0035_01_validatedemo.demo.dto;

import org.saxing.aa0035_01_validatedemo.demo.valid.DTOValidGroup;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class DTO {

    public interface add{}
    public interface update{}

    @NotNull(message = "text 不为空")
    String text;

    @Max(value = 10, message = "number 最大值为10", groups = add.class)
    @Min(value = 10, message = "number 最小值为10", groups = update.class)
    Integer number;

    @NotNull(message = "names add 不为空", groups = add.class)
    @NotNull(message = "names update 不为空", groups = update.class)
    List<DTO> sons;

    DTO pre;

    public void setNumber (Integer number) {
        this.number = number;
    }

    public void setText (String text) {
        this.text = text;
    }

    public void setSons(List<DTO> sons) {
        this.sons = sons;
    }

    public void setPre(DTO pre) {
        this.pre = pre;
    }
}
