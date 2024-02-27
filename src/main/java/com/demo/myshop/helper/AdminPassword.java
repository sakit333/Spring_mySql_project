package com.demo.myshop.helper;

import java.io.File;
import java.io.IOException;

public class AdminPassword {
	public static void main(String[] args) throws IOException {
		File file = new File("src/main/resources/static/images");
		if (!file.isDirectory())
			file.mkdir();
		
	}
}
