package com.dailycodeworksbqrcodedemo.service.email;

import com.dailycodeworksbqrcodedemo.DTO.EmailDTO;

public interface EmailService {

   public Boolean sendSimpleMessage(EmailDTO emailDTO) throws Exception;
   public Boolean sendEmailWithAttachment(EmailDTO emailDTO) throws Exception;


}
