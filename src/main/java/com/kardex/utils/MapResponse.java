package com.kardex.utils;

import java.util.HashMap;
import java.util.Map;

public class MapResponse {
	private static Map<String, String> GenericMap = new HashMap<>();
	
	public static void addGenericMap(String key, String value){
		GenericMap.put(key, value);
	}
	
	public static Map<String, String> getGenericMap(){
		return GenericMap;
	}
	public static void clearGenericMap() {
		GenericMap.clear();
	}
	
	
}
