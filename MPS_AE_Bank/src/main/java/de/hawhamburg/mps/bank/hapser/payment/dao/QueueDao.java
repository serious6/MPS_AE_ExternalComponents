package de.hawhamburg.mps.bank.hapser.payment.dao;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import de.hawhamburg.mps.bank.hapser.payment.Starter;

public class QueueDao {

	private final boolean verbose;
	private final static String QUEUE_NAME = "hapsar";
	private static final Logger logger = LogManager.getLogger(Starter.class);

	public QueueDao(boolean verbose) {
		this.verbose = verbose;
	}

	public void send(HashMap<String, String> cmd) throws IOException {
		String json = "";
		// FIXME make a json

		send(json);
	}

	private void send(String json) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicPublish("", QUEUE_NAME, null, json.getBytes());
		logger.info(" [x] Sent '" + json + "'");
	}

}
