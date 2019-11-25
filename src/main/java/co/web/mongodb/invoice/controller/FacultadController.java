package co.web.mongodb.invoice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.web.mongodb.model.Facultad;
import co.web.mongodb.repositorio.FacultadRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FacultadController {

	@Autowired
	FacultadRepository repository;

	@GetMapping("/facultades")
	public List<Facultad> getAllCustomers() {
		System.out.println("Get all Facultades...");

		List<Facultad> facultades = new ArrayList<>();
		repository.findAll().forEach(facultades::add);

		return facultades;
	}

	@PostMapping("/facultades/create")
	public Facultad postFacultad(@RequestBody Facultad facultad) {

		Facultad _facultad = repository.save(new Facultad(facultad.getNombre(), facultad.getDirector(), facultad.getTelefono(), facultad.getFundacion()));
		return _facultad;
	}

	@DeleteMapping("/facultades/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/facultades/delete")
	public ResponseEntity<String> deleteAllCustomers() {
		System.out.println("Delete All Customers...");

		repository.deleteAll();

		return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/facultades/{id}")
	public Optional<Facultad> findByAge(@PathVariable("id") String id) {
		System.out.println("Get Faucltad por ID OP...");
		Optional<Facultad> facultad = repository.findById(id);
		return facultad;
	}

	
	
	@PutMapping("/facultades/{id}")
	public ResponseEntity<Facultad> updateFacultad(@PathVariable("id") String id, @RequestBody Facultad facultad) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<Facultad> facultadData = repository.findById(id);

		if (facultadData.isPresent()) {
			Facultad _facultad = facultadData.get();
			_facultad.setNombre(facultad.getNombre());
			_facultad.setDirector(facultad.getDirector());
			_facultad.setTelefono(facultad.getTelefono());
			_facultad.setFundacion(facultad.getFundacion());
			return new ResponseEntity<>(repository.save(_facultad), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
