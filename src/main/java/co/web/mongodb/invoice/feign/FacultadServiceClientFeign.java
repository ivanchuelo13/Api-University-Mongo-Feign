package co.web.mongodb.invoice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.web.mongodb.invoice.model.*;

@RequestMapping("/api")
@FeignClient(name = "factura-service", url = "localhost:10020")
public interface FacultadServiceClientFeign {
	
	@GetMapping("/facultades")
	public List<Facultad> getList();
	
	@GetMapping("/{id}")
	public Facultad getById(@PathVariable Long id);
}
