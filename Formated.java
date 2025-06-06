/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deocampo.formated;

/**
 *
 * @author maris
 */
public class Formated {

    public static void main(String[] args) {
        String name = "baby";
        
        String formatedName = String.format("hi, %s, I love you! \n", name);
        System.out.print(formatedName);
    }
}
