/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package java_file;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maris
 */
public class Java_file {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

            File obj = new File("file.txt");
            BufferedWriter writer = new BufferedWriter(file);
        try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("example.txt"));
            
            System.out.println("File Created!");
        } catch (IOException ex) {
            Logger.getLogger(Java_file.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
