package com.bemInternet;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bemInternet.domain.User;

@ControllerAdvice
public class GlobalAdvice {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseBody
	@ExceptionHandler(BindException.class)
	public Map<String, Object> validExceptionHandler(BindException ex) {
		System.err.println("Bind_error");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", "error");
		return map;
	}
}
