module com.unsubble.dbConnector {
	exports com.unsubble.backend.connector;
	exports com.unsubble.backend.manager;
	exports com.unsubble.backend.model;

	requires java.persistence;
	requires org.eclipse.persistence.core;
}