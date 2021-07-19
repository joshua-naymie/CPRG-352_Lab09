/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.regex.Pattern;

/**
 *
 * @author Main
 */
public class MiscUtil
{
    private static final
    Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    
    public static boolean stringIsNumber(String input)
    {
        if (input == null)
        {
            return false; 
        }
        return numberPattern.matcher(input).matches();
    }
    
    public static boolean hasEmptyValues(String[] input)
    {
        for(String value : input)
        {
            if(value == null || value.equals(""))
            {
                return true;
            }
        }
        return false;
    }
}