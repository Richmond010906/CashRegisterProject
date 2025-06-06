/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deocampo.loop;

/**
 *
 * @author maris
 */
import java.util.*;
public class Loop {

    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       String response;
       do{
           System.out.println("Crush mo ba ako?");
           response = scan.nextLine();
       }while(!response.equals("yes"));
       
    }
}
