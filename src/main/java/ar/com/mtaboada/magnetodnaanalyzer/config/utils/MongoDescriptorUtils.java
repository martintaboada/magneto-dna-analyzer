package ar.com.mtaboada.magnetodnaanalyzer.config.utils;

import java.util.LinkedList;
import java.util.List;

public class MongoDescriptorUtils {

	private static final int PORT_INDEX = 1;
	private static final int HOST_INDEX = 0;
	private static final String PORT_SEPARATOR = ":";
	private static final String HOST_SEPARATOR = ";";

	public static List<MongoHostDescriptor> parseMongoHost(final String mongoHostsAsString) {
		List<MongoHostDescriptor> hosts = new LinkedList<>();
		String[] mongoHosts = mongoHostsAsString.split(HOST_SEPARATOR);
		for (String hostAndPort : mongoHosts) {
			hosts.add(new MongoHostDescriptor(hostAndPort.split(PORT_SEPARATOR)[HOST_INDEX],
					hostAndPort.split(PORT_SEPARATOR)[PORT_INDEX]));
		}
		return hosts;
	}

}
