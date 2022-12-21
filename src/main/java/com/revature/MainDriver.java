package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.RequestMapping;


import io.javalin.Javalin;

public class MainDriver {
	public static Logger logger = LoggerFactory.getLogger(MainDriver.class);

	public static void main(String[] args) {
		Javalin app = Javalin.create(confg ->{
			confg.plugins.enableDevLogging();
		});
		RequestMapping.setupEndpoints(app);
		app.start(7000);
	}
}
