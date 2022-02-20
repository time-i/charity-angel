package com.whu.charityangel.model.entity;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Transfer {
    Long id;
    Long from;
    Long to;
    Integer money;
    Date dateTime;
}
