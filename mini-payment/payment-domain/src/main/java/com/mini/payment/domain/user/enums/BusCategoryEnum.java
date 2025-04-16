package com.mini.payment.domain.user.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum BusCategoryEnum {
	XXLS("1001", "Offline Retail", "0", "5000", "0", "24"), // Offline Retail

	CYSP("1002", "Catering/Food", "0", "2000", "7", "24"), // Catering, Food

	PWLY("1003", "Tickets/Tourism", "0", "999", "0", "24"), // Tickets, Tourism

	JYPX("1004", "Education/Training", "1000", "5000", "0", "24"), // Education, Training

	YLJSFW("1006", "Entertainment/Fitness Services", "0", "1000", "0", "24"), // Lifestyle, Fitness Services

	YL("1007", "Medical", "0", "1500", "0", "24"), // Medical

	SCPM("1008", "Collectibles/Auctions", "1000", "5000", "9", "21"), // Collectibles, Auctions

	WLKD("1009", "Logistics/Express Delivery", "0", "300", "9", "20"), // Logistics, Express

	GY("1010", "Charity", "0", "500", "9", "20"), // Charity

	TX("1011", "Telecommunications", "0", "500", "0", "24"), // Telecommunications

	JRBX("1012", "Finance/Insurance", "500", "5000", "0", "24"), // Finance, Insurance

	WLXNFW("1013", "Online Virtual Services", "0", "5000", "0", "24"), // Online Virtual Services

	SHJF("1014", "Utility Payments", "0", "1000", "0", "24"), // Utility Payments

	JD("1015", "Hotel", "200", "5000", "0", "24"), // Hotel

	JJ("1016", "Home Furnishing", "200", "5000", "0", "24"), // Home Furnishing

	DSTG("1017", "E-commerce Group Buying", "0", "5000", "0", "24"), // E-commerce Group Buying

	QT("1018", "Others", "0", "5000", "0", "24"); // Others

	private String code;

	private String desc;

	private String minAmount;

	private String maxAmount;

	private String beginTime;

	private String endTime;

	public String getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	BusCategoryEnum(String code, String desc, String minAmount, String maxAmount, String beginTime, String endTime) {
		this.code = code;
		this.desc = desc;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static BusCategoryEnum getEnum(String enumName) {
		BusCategoryEnum resultEnum = null;
		BusCategoryEnum[] arr = BusCategoryEnum.values();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].name().equals(enumName)) {
				resultEnum = arr[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		BusCategoryEnum[] arr = BusCategoryEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < arr.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = arr[num].name();
			map.put("desc", arr[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	public static List toList() {
		BusCategoryEnum[] arr = BusCategoryEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < arr.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", arr[i].name());
			map.put("desc", arr[i].getDesc());
			map.put("minAmount", arr[i].getMinAmount());
			map.put("maxAmount", arr[i].getMaxAmount());
			map.put("beginTime", arr[i].getBeginTime());
			map.put("endTime", arr[i].getEndTime());
			list.add(map);
		}
		return list;
	}

	public static String getJsonStr() {
		BusCategoryEnum[] enums = BusCategoryEnum.values();
		StringBuffer jsonStr = new StringBuffer("[");
		for (BusCategoryEnum item : enums) {
			if (!"[".equals(jsonStr.toString())) {
				jsonStr.append(",");
			}
			jsonStr.append("{id:'").append(item).append("',desc:'").append(item.getDesc()).append("'}");
		}
		jsonStr.append("]");
		return jsonStr.toString();
	}
}
