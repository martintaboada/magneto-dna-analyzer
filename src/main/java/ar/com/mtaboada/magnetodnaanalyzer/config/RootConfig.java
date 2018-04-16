package ar.com.mtaboada.magnetodnaanalyzer.config;

import static java.lang.Integer.parseInt;

import java.util.LinkedList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

import ar.com.mtaboada.magnetodnaanalyzer.config.utils.MongoDescriptorUtils;
import ar.com.mtaboada.magnetodnaanalyzer.config.utils.MongoHostDescriptor;
import ar.com.mtaboada.magnetodnaanalyzer.store.model.StoredModelMarker;

/**
 * Configuración de la aplicación. Actualmente cuenta con las bases como son la
 * base de datos y el acceso a servicios de AWS
 *
 * @author mtaboada
 */
@Configuration
@PropertySource({ "classpath:application.properties" })
public class RootConfig {

	@Value("${mongo.hosts}")
	private String mongoHosts;

	@Value("${mongo.database}")
	private String mongodatabase;

	@Bean
	public MongoClient getMongoClient() {

		List<ServerAddress> seeds = getMongos();
		return new MongoClient(seeds, configureMongoOptions());
	}

	@Bean
	public MongoDatabase getMongoDataBase(final MongoClient mongoClient) {
		MongoDatabase database = mongoClient.getDatabase(mongodatabase);
		return database;
	}

	@Bean
	public Datastore getMorphiaDataStore(final MongoClient mongoClient) {
		final Morphia morphia = new Morphia();

		morphia.mapPackageFromClass(StoredModelMarker.class);

		final Datastore datastore = morphia.createDatastore(mongoClient, mongodatabase);
		datastore.ensureIndexes();

		return datastore;
	}

	private List<ServerAddress> getMongos() {
		List<ServerAddress> seeds = new LinkedList<>();
		List<MongoHostDescriptor> hostsDescriptor = MongoDescriptorUtils.parseMongoHost(mongoHosts);
		hostsDescriptor.stream().forEach(h -> seeds.add(new ServerAddress(h.getIp(), parseInt(h.getPort()))));
		return seeds;
	}

	private MongoClientOptions configureMongoOptions() {
		MongoClientOptions options = MongoClientOptions.builder()//
				.writeConcern(WriteConcern.UNACKNOWLEDGED)//
				.readConcern(ReadConcern.LOCAL)//
				.readPreference(ReadPreference.secondaryPreferred())//
				.build();
		return options;
	}

}
