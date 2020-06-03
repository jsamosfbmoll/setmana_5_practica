package org.formacio.repositori;

import org.formacio.domain.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface FacturesRepositori extends Repository<Factura, Long> {

	@Query("select sum(lf.total) from Factura f join f.linies lf where f.client.nom = ?1")
	public Number totalClient(String client);
	
}
