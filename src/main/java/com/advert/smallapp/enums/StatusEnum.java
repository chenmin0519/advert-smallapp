package com.advert.smallapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    YES(1,"是"),
    NO(0,"否");
    private Integer key;
    private String value;
}
