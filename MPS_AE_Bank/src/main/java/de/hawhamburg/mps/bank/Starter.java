package de.hawhamburg.mps.bank;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hawhamburg.mps.bank.mpsadapter.MpsAdapter;

public class Starter {
	private static final Logger logger = LogManager.getLogger(Starter.class);

	public static void main(String[] args) {
		logger.info("BankAdapter starting...");
		try {
			MpsAdapter mpsAdapter = new MpsAdapter();
		} catch (IOException e) {
			logger.error(e);
			logger.info("BankAdapter going down");
		}
	}
}
