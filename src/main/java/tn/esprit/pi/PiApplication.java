package tn.esprit.pi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import tn.esprit.pi.services.EmailSenderService;

@SpringBootApplication
public class PiApplication {

	/* @Autowired
	private EmailSenderService service;*/

	public static void main(String[] args) {
		SpringApplication.run(PiApplication.class, args);
	}

	/*@EventListener(ApplicationReadyEvent.class)
	public  void triggermail(){
		service.sendEmail("khaled.zgolli0@gmail.com","body","sub");
	}*/

}
