package com.zhimin115200.test.SkyFavor.controller;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.zhimin115200.test.SkyFavor.service.DemoService;

@Component
@Path("/demo")
public class DemoResource {
	
	@Resource
	private DemoService service;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/test")
	public String test() {
		return service.test();
	}
}
