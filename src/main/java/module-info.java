module UniLab.client {
	requires io.github.cdimascio.dotenv.java;
	requires java.logging;
	requires org.apache.logging.log4j;
	requires org.apache.logging.log4j.core;
	requires java.rmi;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires UniLab.server;

	exports org.a3b.clientCM;
}