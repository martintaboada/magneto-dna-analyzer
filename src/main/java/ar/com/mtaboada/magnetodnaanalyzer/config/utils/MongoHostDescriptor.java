package ar.com.mtaboada.magnetodnaanalyzer.config.utils;

/**
 * @author mtaboada
 */
public class MongoHostDescriptor {
	private final String ip;
	private final String port;

	public MongoHostDescriptor(final String ip, final String port) {
		this.ip = ip;
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public String getPort() {
		return port;
	}

}
