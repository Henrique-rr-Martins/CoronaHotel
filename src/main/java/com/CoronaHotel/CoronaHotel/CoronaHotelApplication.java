package com.CoronaHotel.CoronaHotel;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.CoronaHotel.CoronaHotel.model.Hotel;
import com.CoronaHotel.CoronaHotel.repository.HotelRepository;

@SpringBootApplication
public class CoronaHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaHotelApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(HotelRepository repository) {
		return args ->{
			repository.deleteAll();
			
			IntStream.range(1, 11)
				.mapToObj(i -> {
					Hotel h = new Hotel();
					h.setCnpj("" + i);
					h.setNome("Hotel " + i);
					h.setCapacidade(i);
					h.setCep(new String("" + i));
					h.setRua("Rua " + i);
					h.setBairro("Bairro " + i);
					h.setCidade("Cidade " + i);
					
					return h;
				})
				.map(m -> repository.save(m))
				.forEach(System.out::println);
		};
	}

}
