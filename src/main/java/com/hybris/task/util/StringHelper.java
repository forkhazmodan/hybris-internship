package com.hybris.task.util;

public class StringHelper {
    public static Long[] parseStringOfIds(String stringOfIds) {
        if(stringOfIds == null) return new Long[0];

        String[] productIdsStrings = stringOfIds.split(",");
        Long[] productIdsIntegers = new Long[productIdsStrings.length];

        for (int i = 0; i < productIdsStrings.length; i++) {
            productIdsIntegers[i] = Long.parseLong(productIdsStrings[i]);
        }

        return productIdsIntegers;
    }
}
