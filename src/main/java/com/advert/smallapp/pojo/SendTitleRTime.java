package com.advert.smallapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel("内容信息表")
@Table(name = "context")
@Data
public class SendTitleRTime {

  private String title;

  private String time;
}
