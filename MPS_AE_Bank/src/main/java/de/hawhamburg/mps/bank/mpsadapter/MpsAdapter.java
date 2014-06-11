package de.hawhamburg.mps.bank.mpsadapter;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class MpsAdapter {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private final static String QUEUE_NAME = "hello";
	private boolean run = true;

	public MpsAdapter() throws IOException {
		try {
			logger.info("MpsAdapter is booting");
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			try {
				channel.queueDeclare(QUEUE_NAME, false, false, false, null);
				waitForMessages(channel);
			} finally {
				if (channel != null)
					channel.close();
				if (connection != null)
					connection.close();
			}
		} finally {
			logger.info("MpsAdapter is shutting Down");
		}
	}

	private void waitForMessages(Channel channel) throws IOException {
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

		logger.info("MpsAdapter is ready and waiting for Messages...");
		while (isRunning()) {
			try {
				QueueingConsumer.Delivery delivery = null;
				delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				logger.info(String.format("[x] Received %s", message));
			} catch (ShutdownSignalException | ConsumerCancelledException
					| InterruptedException e) {
				cancel();
				logger.error(e);
			}
		}
	}

	public boolean isRunning() {
		return run;
	}

	public void cancel() {
		this.run = false;
	}
}
