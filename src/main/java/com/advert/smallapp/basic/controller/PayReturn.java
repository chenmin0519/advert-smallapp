package com.advert.smallapp.basic.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PayReturn implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Code;
    private String Msg;
    private String Timestamp;
    private PayReturnData Data;
}
