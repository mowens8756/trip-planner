package com.example.demo.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Currency {
	public static final Map<Integer, String> CURRENCIES; static {
		Map<Integer, String> currencies = new LinkedHashMap<>();
		currencies.put(0, "JPY");
		currencies.put(1, "USD");
		currencies.put(2, "EUR");
		currencies.put(3, "GBP");
		currencies.put(4, "AUD");
		currencies.put(5, "CAD");
		currencies.put(6, "CHF");
		currencies.put(7, "CNY");
		currencies.put(8, "HKD");
		CURRENCIES = Collections.unmodifiableMap(currencies);
	}
}
