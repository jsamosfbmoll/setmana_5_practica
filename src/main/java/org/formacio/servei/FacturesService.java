package org.formacio.servei;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.springframework.stereotype.Service;

@Service
public class FacturesService {

	@PersistenceContext
	EntityManager em;
	
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
		
		Factura producteFactura = em.find(Factura.class, idFactura);
		LiniaFactura liniaFactura = new LiniaFactura();
		
		liniaFactura.setProducte(producte);
		liniaFactura.setTotal(totalProducte);
		producteFactura.getLinies().add(liniaFactura);
		
		return producteFactura;
	}
}
