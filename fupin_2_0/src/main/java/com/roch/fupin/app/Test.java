/**
 * 
 */
package com.roch.fupin.app;

import junit.framework.TestCase;

/**
 * @author ZhaoDongShao
 *
 * 2016年8月16日 
 *
 */
public class Test extends TestCase {

	public static void main(String[] args) {
		
		NumberService service = new NumberService();
		
		int count = service.add(2, 3);
		System.out.println(count + "");
	}
	
}
