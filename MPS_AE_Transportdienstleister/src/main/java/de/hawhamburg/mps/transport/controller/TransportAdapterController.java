package de.hawhamburg.mps.transport.controller;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.hawhamburg.mps.transport.service.dao.TransportService;
import de.hawhamburg.mps.transport.transObjects.TransportObject;

@Controller
@RequestMapping
public class TransportAdapterController {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private final TransportService transportService = new TransportService();

	@RequestMapping(method = RequestMethod.GET, value = "/show/{id}")
	public @ResponseBody
	TransportObject showBusyness(@PathVariable(value = "id") int id) {
		logger.info("Recieved get on '/show/{id}'");
		return transportService.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/show")
	public @ResponseBody
	Set<TransportObject> showAll() {
		logger.info("Recieved get on '/show'");
		return transportService.showAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/status")
	public @ResponseBody
	String showStatus() {
		logger.info("Recieved get on '/status'");
		logger.info("TransportService is "
				+ (transportService != null ? "running" : "offline"));
		return "Running...";
	}

}
