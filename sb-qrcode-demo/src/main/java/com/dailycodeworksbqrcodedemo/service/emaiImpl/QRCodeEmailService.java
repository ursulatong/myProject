package com.dailycodeworksbqrcodedemo.service.emaiImpl;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.dailycodeworksbqrcodedemo.DTO.EmailDTO;
import com.dailycodeworksbqrcodedemo.config.Appconfig;
import com.dailycodeworksbqrcodedemo.service.email.EmailService;

@Service
public class QRCodeEmailService implements EmailService {

  private final Logger LOGGER = LoggerFactory.getLogger(QRCodeEmailService.class);

  @Autowired
  private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String sender;

  /**
   * Method for sending simple e-mail message.
   * 
   * @param emailDTO - data to be send.
   */
  @Override
  public Boolean sendSimpleMessage(EmailDTO emailDTO) throws Exception {

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    // String[] recipients =
    // emailDTO.getRecipientName().stream().toArray(String[]::new);
    // if (LOGGER.isDebugEnabled()) {
    // LOGGER.debug("recipients: " + Arrays.toString(recipients));
    // }

    helper.setFrom(sender);
    helper.setTo(emailDTO.getRecipientEmail());
    helper.setSubject(Appconfig.EMAIL_SUBJECT);
    helper.setText(String.format(Appconfig.EMAIL_BODY, emailDTO.getRecipientName(), true));

    Boolean isSent = false;
    try {
      mailSender.send(message);
      isSent = true;
    } catch (Exception e) {
      LOGGER.error("Sending e-mail error: {}", e.getMessage());
    }
    return isSent;
  }

  @Override
  public Boolean sendEmailWithAttachment(EmailDTO emailDTO) throws Exception {

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    helper.setFrom(sender);
    helper.setTo(emailDTO.getRecipientEmail());
    helper.setSubject(Appconfig.EMAIL_SUBJECT);
    helper.setText(String.format(Appconfig.EMAIL_BODY, emailDTO.getRecipientName(), true));

    // Adding the file attachment
    FileSystemResource file = new FileSystemResource(new File(emailDTO.getAttachmentPath()));

    helper.addAttachment(file.getFilename(), file);

    Boolean isSent = false;
    try {
      mailSender.send(message);
      isSent = true;
    } catch (Exception e) {
      LOGGER.error("Sending e-mail error: {}", e.getMessage());
    }
    return isSent;
  }

}
