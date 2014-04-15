/*a. Assignment #.   In Class 4
b. File Name.      In Class4b
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/
package com.example.inclass4b;

import java.util.Random;

public class HeavyWork {
	static final int COUNT = 900000;
	static double getNumber(){
		double num = 0;
		Random rand = new Random();
		for(int i=0;i<COUNT; i++){
			num = num + rand.nextDouble();
		}
		return num / ((double) COUNT);
	}
}