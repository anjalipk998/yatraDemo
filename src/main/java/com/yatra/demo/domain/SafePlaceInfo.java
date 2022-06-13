package com.yatra.demo.domain;


import com.amadeus.resources.SafePlace.SafetyScores;

public class SafePlaceInfo {

	String place;

	SafetyScores safetyScores;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public SafetyScores getSafetyScores() {
		return safetyScores;
	}

	public void setSafetyScores(SafetyScores safetyScores) {
		this.safetyScores = safetyScores;
	}

}
