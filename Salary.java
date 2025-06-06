/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deocampo.salary;

/**
 *
 * @author maris
 */
import java.util.*;
public class Salary {

    private static String fulltime;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter Name: " );
        String name = scan.nextLine();
        
        System.out.print("Enter work status(part-time or full-time): ");
        String workStatus = scan.nextLine().toLowerCase();
        
        if(workStatus.equals("part-time")){
            System.out.print("Enter working hours: ");
            int workingHours = scan.nextInt();
            
            System.out.print("Enter salary rate: ");
            double rate = scan.nextDouble();
            
            double salary = rate * workingHours;
            System.out.print("The salary is: " + salary);
            
        }else if(workStatus.equals("full-time")){
            System.out.print("The salary is: " + fulltime);
        }else{
            System.out.println("Invalid");
        }
    }
}
