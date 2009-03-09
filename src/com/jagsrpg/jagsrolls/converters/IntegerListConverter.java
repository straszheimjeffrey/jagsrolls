package com.jagsrpg.jagsrolls.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.util.TypeConversionException;

public class IntegerListConverter extends StrutsTypeConverter {

	private static String SPLIT = "\\s*,\\s*";

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String value = values[0];
		String[] split = value.split(SPLIT);
		List<Integer> result = new ArrayList<Integer>();
		for (String el : split) {
			try {
				result.add(Integer.valueOf(el));
			} catch (NumberFormatException e) {
				throw new TypeConversionException(el);
			}
		}
		return result;
	}

	@Override
	public String convertToString(Map context, Object o) {
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		List<Integer> ints = (List<Integer>) o;
		for (int el : ints) {
			if (first) {
				builder.append(',');
				first = false;
			}
			builder.append(el);
		}
		return builder.toString();
	}

}
