package com.tts.ttsWeatherApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.ttsWeatherApp.Model.ZipCode;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCode, Long>{
	
}
