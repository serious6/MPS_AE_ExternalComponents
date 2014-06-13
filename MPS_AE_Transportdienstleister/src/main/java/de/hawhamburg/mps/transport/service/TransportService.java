package de.hawhamburg.mps.transport.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hawhamburg.mps.transport.service.dao.TransportDao;
import de.hawhamburg.mps.transport.transObjects.TransportObject;

@Service
public class TransportService {

	@Autowired(required = true)
	private TransportDao transportDao;

	public Set<TransportObject> showAll() {
		return transportDao.showAll();
	}

	public void store(TransportObject theObject) {
		transportDao.store(theObject);
	}

	public TransportObject findById(int id) {
		return transportDao.findById(id);
	}

}
