package com.whu.charityangel.model.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class DonationInfo {
    String name;
    Integer money;
    Date time;
}
