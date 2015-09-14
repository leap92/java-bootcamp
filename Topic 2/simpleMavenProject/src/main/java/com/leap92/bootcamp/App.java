package com.leap92.bootcamp;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Escriba su nombre:");
        String input = System.console().readLine();
        System.out.println("Hola " + input);
    }
}
