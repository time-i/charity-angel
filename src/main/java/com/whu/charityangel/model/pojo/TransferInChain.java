package com.whu.charityangel.model.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class TransferInChain {
    Long id;
    Long amount;
    String from;
    String to;
    Date time;

}
