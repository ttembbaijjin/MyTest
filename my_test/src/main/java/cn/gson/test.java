package cn.gson;

import java.util.HashMap;

import com.google.gson.Gson;

public class test {
	
	public static void main(String[] args) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("1", "a");
		paramMap.put("2", "b");
		String s = new Gson().toJson(paramMap);
		System.out.println(s);
	}

}
