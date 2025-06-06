/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deocampo.controstructure;

/**
 *
 * @author maris
 */
import java.util.*;
public class Controstructure {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final double AMOUNT = 5000.00;
        final double DISCOUNT = 0.10;
        
        System.out.print("Enter Price: ");
        double price = scan.nextDouble();
        
        if (price >= AMOUNT){
            double discountedPrice = price - (price * DISCOUNT);
            System.out.println("The price is: " + discountedPrice);
        }   else{
            System.out.println("No discount");
            System.out.println("The price is: " + price);
        }     
        
        System.out.print("Enter grade: ");
        int grade =scan.nextInt();
        
        if(grade < 75){
            System.out.println("A");
        }else if (grade >= 92){
            System.out.println("A-");
        }else if (grade >= 87){
            System.out.println("B+");
        }else if (grade >= 82){
            System.out.println("B");
        }else if (grade >= 77){
            System.out.println("B-");
        }else{
            System.out.println("failed");
        }
        
        System.out.print("Enter the day number: ");
        int day =  scan.nextInt();
        
        switch(day){
            case 1: 
                System.out.println("Sunday");
                break;
            case 2:
                System.out.println("Monday");
                break;
            case 3:
                System.out.println("Tuesday");
                break;
            case 4:
                System.out.println("Wednesday");
                break;
            case 5:
                System.out.println("Thursday");
                break;
            case 6:
                System.out.println("Friday");
                break;
            case 7:
                System.out.println("Saturday");
                break;
            default:
                System.out.println("Invalid");
              
        }          
    }
}
