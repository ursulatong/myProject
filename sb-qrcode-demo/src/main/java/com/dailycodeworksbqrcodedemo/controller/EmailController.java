package com.dailycodeworksbqrcodedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodeworksbqrcodedemo.DTO.EmailDTO;
import com.dailycodeworksbqrcodedemo.service.emaiImpl.QRCodeEmailService;

@RestController
@RequestMapping("/api")
public class EmailController {

  @Autowired
  private QRCodeEmailService qrCodeEmailService;

  // Sending email
  @PostMapping("/sendSimpleEmail")
  public ResponseEntity<Boolean> sendSimpleEmail(@RequestBody EmailDTO emailDTO) throws Exception{
    return ResponseEntity.ok(qrCodeEmailService.sendSimpleMessage(emailDTO));
  }

  // Sending email with attachment
  @PostMapping("/sendAttachment")
  public ResponseEntity<Boolean> sendMailWithAttachment(@RequestBody EmailDTO emailDTO) throws Exception{
    return ResponseEntity.ok(qrCodeEmailService.sendEmailWithAttachment(emailDTO));
  }

}
