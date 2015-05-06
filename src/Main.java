

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;

public class Main {
        private static GUI panel;
	
	public static void main(String[] args) {
		enableLogonScreen();
	}
        
        public static void enableLogonScreen(){
            panel = new GUI();
            panel.setVisible(true);
        }
        

}
