package de.hawhamburg.mps.transport.service.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.util.Assert;

import de.hawhamburg.mps.transport.transObjects.TransportObject;

public class TransportService {

	private final HashSet<TransportObject> theObjects = new HashSet<TransportObject>();
	private final AtomicInteger counter = new AtomicInteger();

	public synchronized Set<TransportObject> showAll() {
		return new HashSet<TransportObject>(theObjects);
	}

	public synchronized void store(TransportObject theObject) {
		Assert.notNull(theObject);
		theObject.setId(counter.incrementAndGet());
		theObjects.add(theObject);
	}

	public synchronized TransportObject findById(int id) {
		Iterator<TransportObject> iter = theObjects.iterator();
		boolean found = false;

		TransportObject finder = new TransportObject();
		finder.setId(id);

		TransportObject theObject = null;
		while (iter.hasNext() && !found) {
			TransportObject cur = iter.next();
			if (cur.equals(finder)) {
				theObject = cur;
				found = true;
			}
		}

		Assert.notNull(theObject);
		return theObject;
	}

}
