package com.winter.app.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//xml 대신에 java를 이용해서 객체 생성

@Configuration  //xml 역할을 하는 annotation
public class RobotConfig {
	
	//<bean class="">
	@Bean("ra")
	RobotArm makeArm() {
		return new RobotArm();
	}
	
	@Bean
	Robot makeRobot() {
		Robot robot = new Robot();
		robot.setRobotArm(makeArm());
		return robot;
	}

}
