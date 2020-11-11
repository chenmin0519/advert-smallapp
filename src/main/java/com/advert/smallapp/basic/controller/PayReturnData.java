package com.advert.smallapp.basic.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PayReturnData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String amount;
    private String currency;
    private String doing;
    private String merTransNo;
    private String message;
    private String resultCode;
    private String sign;
    private String tradeNo;
    private String url;
}
