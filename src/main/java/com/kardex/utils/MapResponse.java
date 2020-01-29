package com.kardex.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kardex.domain.MKardexMain;

public class MapResponse {
	private static Map<String, String> GenericMap = new HashMap<>();
	private static Map<String, MKardexMain> GenericMapById = new HashMap<>();
	private static Map<String, List<MKardexMain>> GenericMapList = new HashMap<>();
	private static Map<String, String> ResultKardexMainList = new HashMap<>();
	private static Map<String, Map<String, String>> MapGenericMapped = new HashMap<>();

	public static void addGenericMap(String key, String value) {
		GenericMap.put(key, value);
	}

	public static void addGenericMap(String key, List<MKardexMain> value) {
		GenericMapList.put(key, value);
	}

	public static void addGenericMap(String key, Map<String, String> value) {
		MapGenericMapped.put(key, value);
	}

	public static Map<String, Map<String, String>> getMapGenericMapped() {
		return MapGenericMapped;
	}

	public static void addGenericMap(String key, MKardexMain value) {
		GenericMapById.put(key, value);
	}

	public static void clearGenericMapped() {
		MapGenericMapped.clear();
	}

	public static void clearGenericMapList() {
		GenericMapList.clear();
	}

	public static Map<String, String> getGenericMap() {
		return GenericMap;
	}

	public static Map<String, List<MKardexMain>> getGenericMapList() {
		return GenericMapList;
	}

	public static void clearGenericMap() {
		GenericMap.clear();
	}

	public static void setKardexMainMap(MKardexMain kardexMain) {
		String minCast = String.valueOf(kardexMain.getMin());
		String maxCast = String.valueOf(kardexMain.getMax());

		ResultKardexMainList.put("id", kardexMain.getId().toString());
		ResultKardexMainList.put("object", kardexMain.getObject());
		ResultKardexMainList.put("supplier", kardexMain.getSupplier());
		ResultKardexMainList.put("reference", kardexMain.getReference());
		ResultKardexMainList.put("location", kardexMain.getLocation());
		ResultKardexMainList.put("min", minCast);
		ResultKardexMainList.put("max", maxCast);
	}

	public static Map<String, String> getKardexMainMap() {
		return ResultKardexMainList;
	}
}
