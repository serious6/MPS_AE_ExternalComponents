package de.hawhamburg.mps.bank.hapser.payment.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

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

	/**
	 * 
	 * @param cmd
	 * @throws IOException
	 */
//	public void send(HashMap<String, String> cmd) throws IOException {
//		String json = Json.createObjectBuilder()
//				.add("transaction", getTransaction(cmd)).build().toString();
//		send(json);
//	}

	/**
	 * 
	 * @param cmd
	 * @return
	 */
	private JsonArrayBuilder getTransaction(HashMap<String, String> cmd) {
		JsonObjectBuilder obj = Json.createObjectBuilder();
		JsonArrayBuilder objectBuilder = Json.createArrayBuilder();
		for (String key : cmd.keySet()) {
			obj.add(key, cmd.get(key));
		}
		obj.add("time",
				new SimpleDateFormat("yyyyMMddHHmmssZ").format(new Date()));
		objectBuilder.add(obj);
		return objectBuilder;
	}

	/**
	 * 
	 * @param json
	 * @throws IOException
	 */
	public void send(String json) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicPublish("", QUEUE_NAME, null, json.getBytes());
		logger.info(" [x] Sent '" + json + "'");
	}

}
