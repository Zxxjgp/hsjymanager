package com.hsjy.manager.hsjymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HsjymanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsjymanagerApplication.class, args);
		System.out.println("////////////////////////////////////////////////////////////////////\n" +
				"//                          _ooOoo_                               //\n" +
				"//                         o8888888o                              //\n" +
				"//                         88\" . \"88                              //\n" +
				"//                         (| ^_^ |)                              //\n" +
				"//                         O\\  =  /O                              //\n" +
				"//                      ____/`---'\\____                           //\n" +
				"//                    .'  \\\\|     |//  `.                         //\n" +
				"//                   /  \\\\|||  :  |||//  \\                        //\n" +
				"//                  /  _||||| -:- |||||-  \\                       //\n" +
				"//                  |   | \\\\\\  -  /// |   |                       //\n" +
				"//                  | \\_|  ''\\---/''  |   |                       //\n" +
				"//                  \\  .-\\__  `-`  ___/-. /                       //\n" +
				"//                ___`. .'  /--.--\\  `. . ___                     //\n" +
				"//              .\"\" '<  `.___\\_<|>_/___.'  >'\"\".                  //\n" +
				"//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //\n" +
				"//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //\n" +
				"//      ========`-.____`-.___\\_____/___.-`____.-'========         //\n" +
				"//                           `=---='                              //\n" +
				"//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //\n" +
				"//            佛祖保佑       永不宕机     永无BUG                 //\n" +
				"////////////////////////////////////////////////////////////////////");
	}
}
