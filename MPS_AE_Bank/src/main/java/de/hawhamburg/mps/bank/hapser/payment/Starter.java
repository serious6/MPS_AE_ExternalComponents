package de.hawhamburg.mps.bank.hapser.payment;

import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hawhamburg.mps.bank.hapser.payment.dao.QueueDao;

public class Starter {
	private static final Logger logger = LogManager.getLogger(Starter.class);

	private static final HashMap<String, String> cmdLine = new HashMap<String, String>();

	public static void main(String[] args) {
		logger.info("Startup");
		try {
			// create Options object
			Options options = new Options();
			// add t option
			options.addOption("r", true, "Rechnungsnummer");
			options.addOption("b", true, "Betrag in €");
			options.addOption("v", false, "verbose");

			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("?", options);
			formatter.printHelp("help", options);

			CommandLineParser parser = new GnuParser();
			CommandLine cmd = parser.parse(options, args);

			for (Option posOption : cmd.getOptions()) {
				if (cmd.hasOption(posOption.getOpt())) {
					cmdLine.put(posOption.getOpt(), posOption.getValue());
					logger.info(String.format("Arg found %s: %s [%s]",
							posOption.getOpt(), posOption.getValue(),
							posOption.getDescription()));
				}
			}

			QueueDao queueDao = new QueueDao(cmdLine.containsKey("v"));

		} catch (Exception e) {
			logger.error(e);
		}
	}
}
