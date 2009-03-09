package com.jagsrpg.jagsrolls.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class StringListConverter extends StrutsTypeConverter {

	private static String SPLIT = "\\s*,\\s*";

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String value = values[0];
		String[] split = value.split(SPLIT);
		List<String> result = new ArrayList<String>();
		for (String el : split) {
			result.add(el);
		}
		return result;
	}

	@Override
	public String convertToString(Map context, Object o) {
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		List<String> strings = (List<String>) o;
		for (String el : strings) {
			if (first) {
				builder.append(',');
				first = false;
			}
			builder.append(el);
		}
		return builder.toString();
	}

}
