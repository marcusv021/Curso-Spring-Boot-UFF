package br.uff.sti.ap3;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ap3Application {

	public static void main(String[] args) {
		try(var context  = SpringApplication.run(Ap3Application.class, args)){

			var ap3Service = context.getBean(Ap3Service.class);

			ap3Service.prepararBancoDeDados();
			ap3Service.testarConsultas();

		}
	}


}
