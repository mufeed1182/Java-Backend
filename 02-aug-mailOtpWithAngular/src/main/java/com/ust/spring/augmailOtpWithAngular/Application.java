package com.ust.spring.augmailOtpWithAngular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.spring.augmailOtpWithAngular.service.EmailService;

// class MyOtp
// {
// 	String otp;

// 	public String getOtp() {
// 		return otp;
// 	}

// 	public void setOtp(String otp) {
// 		this.otp = otp;
// 	}
	
// }

@SpringBootApplication
@RestController
@CrossOrigin({"http://localhost:4200/","*"})
public class Application {
	@Autowired
	private EmailService es;
	
	@GetMapping
	public String sendMail() {
		String otp="";
		for (int i=0;i<6;i++) {
			int digit=(int) (Math.random()*10);
			otp+=digit;
		}
		es.send("mailtomufeed.u@gmail.com", "Hi", otp+" Is the otp to signUp our website, Never Share your otp to Others");
		return otp+" is the otp to validate the website";
		// MyOtp motp=new MyOtp();
		// motp.otp=otp;
		// return motp;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Otp Service is working Fine......");
	}

}
