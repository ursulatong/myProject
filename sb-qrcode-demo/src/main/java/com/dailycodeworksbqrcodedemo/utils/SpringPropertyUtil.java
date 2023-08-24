package com.dailycodeworksbqrcodedemo.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/* values from application.properties and application-{profile}.properties */
public class SpringPropertyUtil {

	private static final Logger logger = LoggerFactory.getLogger(SpringPropertyUtil.class);
	private static final String SPRING_BASE_PROPERTIES_FILE = "application{0}.properties";
	private static final String SPRING_PROFILE_JAVA_PROPERTY = "spring.profiles.active";

	public static Properties getProperties() throws Exception 
	{
		
		Properties springProps = new Properties();

		Properties defaultProps = loadPropertiesFromFile(getPropertyFilename(null));
		springProps.putAll(defaultProps);		
		
		final String profile = System.getProperty(SPRING_PROFILE_JAVA_PROPERTY);
		if(!StringUtils.isBlank(profile)) {
			Properties profileProps = loadPropertiesFromFile(getPropertyFilename(profile));
			springProps.putAll(profileProps);
		}

		
		logger.debug("=============== start of merged property list:");
		logger.debug("");

		displayProperties(springProps);
		
		logger.debug("");
		logger.debug("=============== end of merged property list:");

		return springProps;
	}
	
	private static String getPropertyFilename(final String profile) {
		if(StringUtils.isBlank(profile)) {
			return MessageFormat.format(SPRING_BASE_PROPERTIES_FILE, "");
		}
		
		return MessageFormat.format(SPRING_BASE_PROPERTIES_FILE, "-".concat(profile));
	}
	
	private static Properties loadPropertiesFromFile(final String filename) throws Exception 
	{
		Properties props = new Properties();
		try (InputStream in = SpringPropertyUtil.class.getClassLoader().getResourceAsStream(filename);
			Reader reader = new InputStreamReader(in, "UTF-8");
		) {
			props.load(reader);
			
			//for debugging
			if(logger.isDebugEnabled()) {
				logger.debug("=============== start of {} property list:", filename);
				logger.debug("");

				displayProperties(props);
				
				logger.debug("");
				logger.debug("=============== end of {} property list", filename);
			}
		} catch (Exception e) {
			logger.error("", e);
			throw e;
		}
		
		return props;
	}
	
	//for debugging
	private static void displayProperties(final Properties props) {
		//for debugging
		if(logger.isDebugEnabled()) {
			props.entrySet().stream()
			//skip printing out the username / password / secret / key in console or log file
			.filter(
				((Predicate<Entry<Object, Object>>)e -> !e.getKey().toString().toLowerCase().contains("pass"))
				.and(e -> !e.getKey().toString().toLowerCase().contains("user"))
				.and(e -> !e.getKey().toString().toLowerCase().contains("secret"))
				.and(e -> !e.getKey().toString().toLowerCase().contains("key"))
			).forEach(e -> {
				logger.debug("key: {}, value: {}", e.getKey(), e.getValue());
			});

		}
	}
}
