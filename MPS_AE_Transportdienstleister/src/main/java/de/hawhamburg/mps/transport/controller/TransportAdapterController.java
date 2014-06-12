package de.hawhamburg.mps.transport.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class TransportAdapterController {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET, value = "/show/{id}")
	public @ResponseBody
	String showBusyness(@PathVariable(value = "id") int id) {
		return "Hallo";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/show")
	public @ResponseBody
	String showAll() {
		return "me";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/status")
	public @ResponseBody
	String showStatus() {
		return "Running...";
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}

}
