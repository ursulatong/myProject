package com.dailycodeworksbqrcodedemo.config;

import lombok.Getter;
import java.util.Properties;

import com.dailycodeworksbqrcodedemo.utils.SpringPropertyUtil;

@Getter
public class Appconfig {

  public static final String EMAIL_SUBJECT;
  public static final String EMAIL_BODY;

  
static {

  	Properties prop;
		try {
			prop = SpringPropertyUtil.getProperties();
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}

  EMAIL_SUBJECT=prop.getProperty("emailSubject");
  EMAIL_BODY=prop.getProperty("emailBody");

}

}
