/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;
/**
 *
 * @author Thor
 */
public class StringUtility {
    /**
     * turns the first letter for a string to upper case
     * @param source the first character in this string will be uppercased
     * @return source string with first character uppercased
     */
    public String toU1Case(String source){
        return source.substring(0,1).toUpperCase()+source.substring(1);
    }
    /**
     * turns the first lleter for a string to lower case
     * @param source the first character in this string will be lowercased
     * @return  source string with first character lowercased
     */
    public String toL1Case(String source){
        return source.substring(0,1).toLowerCase()+source.substring(1);
    }
    /**
     * turns input string into uppercase
     * @param source string to be transformed
     * @return input parameter in uppercase
     */
    public String toUpperCase(String source){
        return source.toUpperCase();
    }
    /**
     * turns a string to lower case
     * @param source string to be transformed
     * @return input parameter in lowercase
     */
    public String toLowerCase(String source){
        return source.toLowerCase();
    }
    /**
     * transforms a string into a static variable name
     * @param source string that will be transformed
     * @return static variable name for source string
     */
    public String toStaticVarName(String source){
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(source.substring(0, 1).toUpperCase());
        for(char c : source.substring(1).toCharArray()){
            if(Character.isUpperCase(c)){
                sBuilder.append("_");
                sBuilder.append(c);
            }else{
                sBuilder.append(Character.toUpperCase(c));
            }
        }
        return sBuilder.toString();
    }
}
