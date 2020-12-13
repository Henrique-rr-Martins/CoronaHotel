package com.CoronaHotel.CoronaHotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CoronaHotel.CoronaHotel.model.Hotel;
import com.CoronaHotel.CoronaHotel.repository.HotelRepository;

@RestController
@RequestMapping({"/hotel"})
public class HotelController {
	@Autowired
	private HotelRepository repository;
	
	@GetMapping
	public List  findAll() {
		return repository.findAll();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity findById(@PathVariable int id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Hotel create(@RequestBody Hotel hotel) {
		return repository.save(hotel);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Hotel> update(@PathVariable("id") int id, @RequestBody Hotel hotel){
		return repository.findById(id)
				.map(record -> {
					record.setCnpj(hotel.getCnpj());
					record.setNome(hotel.getNome());
					record.setCapacidade(hotel.getCapacidade());
					record.setCep(hotel.getCep());
					record.setRua(hotel.getRua());
					record.setBairro(hotel.getBairro());
					record.setCidade(hotel.getCidade());
					Hotel update = repository.save(record);
					
					return ResponseEntity.ok().body(update);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable int id){
		return repository.findById(id)
				.map(record ->{
					repository.deleteById(id);
					
					return ResponseEntity.ok().body(record.getNome() + " deletado com sucesso!");
				}).orElse(ResponseEntity.notFound().build());
	}
}
