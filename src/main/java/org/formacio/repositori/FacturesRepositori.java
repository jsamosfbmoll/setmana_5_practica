package org.formacio.repositori;

import java.util.List;

import org.formacio.domain.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FacturesRepositori extends CrudRepository<Factura, Long> {

	@Query("select sum(lf.total) from Factura f join f.linies lf where f.client.nom = ?1")
	public Number totalClient(String client);
	
	@Query("select f from Factura f where f.client.nom = ?1")
	public List<Factura> getFactures(String nomClient);
	
}
