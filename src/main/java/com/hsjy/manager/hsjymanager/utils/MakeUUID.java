package com.hsjy.manager.hsjymanager.utils;

import java.util.UUID;

public class MakeUUID {

	public static String makerandomuuid (){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
}
