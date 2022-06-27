package com.company;

public class UserInput
{
    public static boolean isInputCorrect(String input){
        if(input.length() != 4) return false;

        for(int i = 0; i<input.length(); i++)
        {
            if(!Character.isDigit(input.charAt(i))) return false;

            else {
                int number = Character.getNumericValue(input.charAt(i));
                if(number <= 0 || number > 8) return false;
            }
        }
        return true;
    }
}
