package com.yatra.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yatra.demo.domain.SafePlaceInfo;
import com.yatra.demo.exception.SafetyDataException;
import com.yatra.demo.service.DemoService;


@RestController
@RequestMapping("safetyApi")
public class DemoContoller {
	
	@Autowired
	DemoService demoService;

	@GetMapping("/getData")
	HashMap<String, List<SafePlaceInfo>> getSafetyData(@RequestParam String longitude,@RequestParam String latitude) throws SafetyDataException {
		return demoService.getSafetyData(longitude,latitude);
	}

}
