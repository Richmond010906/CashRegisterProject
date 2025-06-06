/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deocampo.repetitionstructure;

/**
 *
 * @author maris
 */
public class Repetitionstructure {

    public static void main(String[] args) {
        System.out.println("Repetition Structure");
        
        //for loop increment
        for(int i = 0; i <= 5; i++){
            System.out.println("Hello");
        }
        
        //for loop decrement
        for(int i = 10; i >= 0; i--){
            System.out.println("Countdown: " + i);
        }
        
        //for loop decrement if
        for(int i = 5; i >= 0; i--){
            if(i == 2){
                break;
            }else{
            System.out.println("hello");
        }
        }
        
        
        String fruit = "grapes";
        for(int i = 0; i < fruit.length(); i++){
            System.out.println(fruit.charAt(i));
        }
        for(int i = fruit.length()-1; i >= 0; i--){
            System.out.println(fruit.charAt(i));
        }
        
        //while loop
        int num = 0;
        while(num <= 10){
            System.out.println(num);
            num++;
        }
        while(num >= 10){
            System.out.println(num);
            num--;
        }
        
        //do... while loop
        
        int nums = 0;
        do{
            System.out.println(nums);
            nums++;
        }while(nums <= 10);
    }
}
