package com.m2mci.mqttKafkaBridge;

import java.io.OutputStream;
import java.io.PrintStream;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CommandLineParser {
	private static final String ALL_MQTT_TOPICS = "#";
	private static final String DEFAULT_BROKER_LIST = "localhost:9092";
	private static final String DEFAULT_MQTT_SERVER_URI = "tcp://localhost:1883";

	@Option(name="--id", usage="MQTT Client ID")
	private String clientId = "mqttKafkaBridge";

	@Option(name="--uri", usage="MQTT Server URI")
	private String serverURI = DEFAULT_MQTT_SERVER_URI;

	@Option(name="--brokerlist", aliases="-b", usage="Broker list (comma-separated)")
	private String brokerList = DEFAULT_BROKER_LIST;
	
	@Option(name="--topics", usage="MQTT topic filters (comma-separated)")
	private String mqttTopicFilters = ALL_MQTT_TOPICS;
	
	@Option(name="--help", aliases="-h", usage="Show help")
	private boolean showHelp = false;
	
	private CmdLineParser parser = new CmdLineParser(this);
	
	public String getClientId() {
		return clientId;
	}

	public String getServerURI() {
		return serverURI;
	}

	public String getBrokerList() {
		return brokerList;
	}

	public String[] getMqttTopicFilters() {
		return mqttTopicFilters.split(",");
	}

	public void parse(String[] args) throws CmdLineException {
		parser.parseArgument(args);
		if (showHelp) {
			printUsage(System.out);
			System.exit(0);
		}
	}

	public void printUsage(OutputStream out) {
		PrintStream stream = new PrintStream(out);
		stream.println("java " + Bridge.class.getName() + " [options...]");
		parser.printUsage(out);
	}
}
