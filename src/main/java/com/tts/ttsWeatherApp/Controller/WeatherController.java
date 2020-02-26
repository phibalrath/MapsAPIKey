package com.tts.ttsWeatherApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.ttsWeatherApp.Model.Request;
import com.tts.ttsWeatherApp.Model.Response;
import com.tts.ttsWeatherApp.Model.ZipCode;
import com.tts.ttsWeatherApp.Service.WeatherService;

@Controller
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	//Method to display information stored in the Response.java Model/Class on home page
//	@GetMapping(value="/")
//	public String getIndex(Model model) {
//		Response response = weatherService.getForecast("43210");
//		model.addAttribute("data", response);
//		return "index";
//	}
	
	@GetMapping(value="/")
	public String getIndex(Model model) {
		Iterable<ZipCode> zipCodeList = weatherService.findAllZipCode();
		model.addAttribute("renderZip", zipCodeList);
		model.addAttribute("request", new Request());
		return "index";
	}
	
	@PostMapping(value="/")
	public String postIndex(Request request, Model model) {
		Response data = weatherService.getForecast(request.getZipCode());
		model.addAttribute("data", data);
		Iterable<ZipCode> zipCodeList = weatherService.findAllZipCode();
		model.addAttribute("renderZip", zipCodeList);
		return "index";
	}
	
}
