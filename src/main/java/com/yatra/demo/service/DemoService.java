package com.yatra.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.SafePlace;
import com.yatra.demo.domain.SafePlaceInfo;
import com.yatra.demo.exception.SafetyDataException;

@Component
public class DemoService {

	@Value("${amadeus.auth.clientId}")
	private String clientId;

	@Value("${amadeus.auth.clientSecret}")
	private String clientSecret;

	public HashMap<String, List<SafePlaceInfo>> getSafetyData(String longitude, String latitude) throws SafetyDataException    {

		Amadeus amadeus = Amadeus.builder(clientId, clientSecret).build();

		SafePlace[] safePlaces;
		try {
			safePlaces = amadeus.safety.safetyRatedLocations
					.get(Params.with("longitude", longitude).and("latitude", latitude));
			HashMap<String, List<SafePlaceInfo>> safePlaceMap = new HashMap<String, List<SafePlaceInfo>>();
			if (safePlaces != null && safePlaces.length > 0) {
				for (SafePlace safePlace : safePlaces) {
					String subType = safePlace.getSubType();
					List<SafePlaceInfo> safePlaceInfoList = new ArrayList<SafePlaceInfo>();
					if (safePlaceMap.get(subType) != null) {
						safePlaceInfoList = safePlaceMap.get(subType);
					}
					SafePlaceInfo safePlaceInfo = new SafePlaceInfo();
					safePlaceInfo.setPlace(safePlace.getName());
					safePlaceInfo.setSafetyScores(safePlace.getSafetyScores());
					safePlaceInfoList.add(safePlaceInfo);
					safePlaceMap.put(subType, safePlaceInfoList);
				}
				return safePlaceMap;
			}

		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			throw new SafetyDataException("Internal Error", "INTERNAL_ERROR");
		}
		return null;

	}

}
