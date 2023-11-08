package org.autoframework.utils;

import java.time.Duration;

public class Utils {
	
	public static void staticSleep(int seconds) {
		try {
			Thread.sleep(Duration.ofSeconds(seconds).toMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
