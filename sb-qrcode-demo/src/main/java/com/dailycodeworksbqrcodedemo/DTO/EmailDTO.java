package com.dailycodeworksbqrcodedemo.DTO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailDTO {

  private String recipientEmail;
  private String recipientName;
  private List<String> ccList;
  private List<String> bccList;
  private String subject;
  private String body;
  private Boolean isHtml;
  private String attachmentPath;

}
