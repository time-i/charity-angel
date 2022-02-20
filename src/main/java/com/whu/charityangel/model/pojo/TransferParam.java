package com.whu.charityangel.model.pojo;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransferParam {
    String from;
    Integer fromKind;
    String to;
    Integer toKind;
    Integer money;
}
