/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deocampo.scannernumbers;

/**
 *
 * @author maris
 */
import java.util.Scanner;
public class Scannernumbers {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double num1, num2, result;
        char operator;
        
        System.out.print("Enter number: ");
        num1 = input.nextDouble();
        num2 = input.nextDouble();
        
        System.out.println("Enter operator: ");
        operator = input.next().charAt(0);
        
        switch (operator){
            case '+':
                result = num1 + num2;
                System.out.println(result);
                break;
            case '-':
                result = num1 - num2;
                System.out.println(result);
                break;
            case '*':
                result = num1 * num2;
                System.out.println(result);
                break;
            case '/':
                result = num1 / num2;
                System.out.println(result);
                break;
            default:
                System.out.println("Invalid! ");
                
            
        }
    }
}
