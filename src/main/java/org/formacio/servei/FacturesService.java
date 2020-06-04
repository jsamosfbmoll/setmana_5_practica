package org.formacio.servei;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturesService {
	
	@Autowired
	FidalitzacioService fidelitzacio;
	
	@Autowired
	FacturesRepositori repositori;
	
	/*
	 * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
	 * passades (producte i totalProducte)
	 * 
	 * S'ha de retornar la factura modificada
	 * 
	 * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
	 */
	@Transactional
	public Factura afegirProducte (long idFactura, String producte, int totalProducte) {
		
		Factura producteFactura = repositori.findById(idFactura).get();
		LiniaFactura liniaFactura = new LiniaFactura();
		Set<LiniaFactura> linies = producteFactura.getLinies();
		
		liniaFactura.setProducte(producte);
		liniaFactura.setTotal(totalProducte);
		linies.add(liniaFactura);
		
		if (linies.size() >= 4) fidelitzacio.notificaRegal(producteFactura.getClient().getEmail());
		
		return producteFactura;
	}
}
