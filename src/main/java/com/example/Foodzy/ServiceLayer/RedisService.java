package com.example.Foodzy.ServiceLayer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	@Autowired
	RedisTemplate<String, String> redistemplate;

	public String updateLocation(Long id, double latitude, double longitude) {
		redistemplate.opsForGeo().add("deliveryPartner:locations", new Point(latitude, longitude),id.toString());
		return "updated";
	}

	public List<String> getNearByPartners(double latitude, double longitude, double radiusKm) {
		Circle searchArea=new Circle(new Point(latitude, longitude), new Distance(radiusKm, Metrics.KILOMETERS));
		GeoResults<RedisGeoCommands.GeoLocation<String>> results=redistemplate.opsForGeo().radius("deliveryPartner:locations", searchArea);
		if(results==null) {
			return List.of();
		}
		return results.getContent().stream().map(result ->result.getContent().getName()).collect(Collectors.toList());
		
	}

}
