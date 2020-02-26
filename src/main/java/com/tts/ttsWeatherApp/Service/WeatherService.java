package com.tts.ttsWeatherApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.tts.ttsWeatherApp.Model.Response;
import com.tts.ttsWeatherApp.Model.ZipCode;
import com.tts.ttsWeatherApp.repository.ZipCodeRepository;

@Service
public class WeatherService {
	
	@Autowired
	ZipCodeRepository zipCodeRepository;
	
	@Value("${api_key}")
	private String apiKey;
	
	//Method for making HTTP request to that specific URL and store the info in Response class
	public Response getForecast(String zipCode) {
		//Set end point URL based on zipCode and individual apiKey
		String url = "http://api.openweathermap.org/data/2.5/weather?zip=" +
	zipCode + "&units=imperial&appid=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();
		//return restTemplate.getForObject(url, Response.class);
		
		//Add zipCode to zipCodeHistory that will be saved in repo
		ZipCode zipCodesHistory = new ZipCode();
		zipCodesHistory.setZipCodes(zipCode);
		zipCodeRepository.save(zipCodesHistory);
		
		try {
			return restTemplate.getForObject(url, Response.class);
		} catch (HttpClientErrorException ex) {
			Response response = new Response();
			response.setName("error");
			return response;
		}
	}
	
	public Iterable<ZipCode> findAllZipCode() {
		return zipCodeRepository.findAll();
	}

}
