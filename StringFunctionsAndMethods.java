/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stringfunctionsandmethods;

/**
 *
 * @author maris
 */
public class StringFunctionsAndMethods {
    public static void main(String[] args) {
        //String product = "Apple Macbook-ModelM3-50000";
        //int firstDash = product.indexOf("-");
        //System.out.println(firstDash);
        //int lastDash = product.lastIndexOf("-");
        //System.out.println(lastDash);
        
        //String productName = product.substring(0, firstDash);
        //System.out.println(productName);
        
        String information = "John Doe, Lipa city, Information Technology";
        String name = information.substring(0, 8);
        System.out.println("Name: " + name);
        
        String address = information.substring(10, 19);
        System.out.println("Address: " + address);
        
        String program = information.substring(21, 43);
        System.out.println("Program: " + program);
        
    }
    
}
