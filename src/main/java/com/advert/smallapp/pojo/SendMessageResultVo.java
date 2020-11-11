package com.advert.smallapp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class SendMessageResultVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer errcode;
    private String errmsg;
}
