package com.magic;

import java.util.ArrayList;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


/**
 * Created by YinJianFeng on 14-5-12.
 */
public class MainTest {
	public static void main(String[] args) {
//		String username = "admin";
//		String password = "123456";
//		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//		String encodedPs = encoder.encodePassword(password, username);
//		System.out.println(encodedPs);
		
	}
	
	
	public static int getInt(String str){
		int second = 0;
		String[] st = str.split(":");
		for(int i = 0; i < st.length; i++){
			char[] c = st[i].toCharArray();
			if(i==0){
				int z = Character.getNumericValue(c[0]) * 10 + Character.getNumericValue(c[1]);
				second += z * 60 * 60;
			}else if(i==1){
				int z = Character.getNumericValue(c[0]) * 10 + Character.getNumericValue(c[1]);
				second += z * 60;
			}else{
				int z = Character.getNumericValue(c[0]) * 10 + Character.getNumericValue(c[1]);
				second += z * 60;
			}
		}
		return second;
	}
	
	
}
// b594510740d2ac4261c1b2fe87850d08
