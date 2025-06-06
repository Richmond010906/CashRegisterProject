/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deocampo.age;

/**
 *
 * @author maris
 */
import java.util.*;
public class Age {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter age: ");
        int age = scan.nextInt();
        
        if(age >= 18){
            System.out.println("You are allowed to vote");
        if(age >= 60)
            System.out.println("Please go to priority lane");
        }else{
            System.out.println("You are not allowed to vote");
        }
    }
}
