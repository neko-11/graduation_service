package cn.edu.ahut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("cn.edu.ahut.Mappers")
public class DemoApplication {

	@Bean
	public com.arcsoft.fd.FaceDetection faceDetection(){
		return new com.arcsoft.fd.FaceDetection();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
