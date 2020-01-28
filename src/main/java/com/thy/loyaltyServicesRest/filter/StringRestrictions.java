package com.thy.loyaltyServicesRest.filter;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LikeExpression;
import org.hibernate.criterion.MatchMode;

/**
 * Created by INNGKISAKOL on 12/28/2017.
 */
public class StringRestrictions
{
    public static Criterion ilike(String propertyName, String value) {
        return new EscapedILikeExpression(propertyName, value);
    }

    public static Criterion ilike(String propertyName, String value, MatchMode matchMode) {
        return new EscapedILikeExpression(propertyName, value, matchMode);
    }
}

class EscapedILikeExpression extends LikeExpression
{
    private static final String HIBERNATE_ESCAPE_CHAR = "\\";

    public EscapedILikeExpression(String propertyName, String value)
    {
        super(propertyName, value);
    }

    public EscapedILikeExpression(String propertyName, String value, MatchMode matchMode)
    {
        super(propertyName, replaceAll(value), matchMode, (Character)null, true);
    }

    private static String replaceAll(String value) {
        String revertedValue =
               value.replace("\\",  HIBERNATE_ESCAPE_CHAR + "\\")
                    .replace("_",   HIBERNATE_ESCAPE_CHAR + "_")
                    .replace("%",   HIBERNATE_ESCAPE_CHAR + "%")
                    .replace("�",  "c")
                    .replace("�",  "g")
                    .replace("�",  "o")
                    .replace("�",  "u")
                    .replace("�",  "i");

        return revertedValue;
    }

}