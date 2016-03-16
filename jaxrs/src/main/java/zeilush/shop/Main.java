package zeilush.shop;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.container.JARArchive;
import org.wildfly.swarm.datasources.DatasourceArchive;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;
import org.wildfly.swarm.logging.LoggingFraction;
import zeilush.shop.jaxrs.OrderResource;

public class Main {

    public static void main(String... args) throws Exception {
        Container container = new Container();

        container.start();

        container.deploy(Swarm.artifact("org.postgresql:postgresql", "org.postgresql"));

        // Create a JDBC driver deployment using maven groupId:artifactId
        // The version is resolved from your pom.xml's <dependency>
//        container.deploy(Swarm.artifact("com.h2database:h2", "h2"));

        // Create a DS deployment
        JARArchive dsArchive = ShrinkWrap.create(JARArchive.class);
        dsArchive.as(DatasourceArchive.class).dataSource("MyDS", (ds) -> {
            ds.connectionUrl("jdbc:postgresql://localhost:5432/postgres");
            ds.driverName("org.postgresql_org.postgresql.Driver_9_3");
            ds.userName("postgres");
            ds.password("a");
        });


        // Create a DS deployment
//        JARArchive dsArchive = ShrinkWrap.create(JARArchive.class);
//        dsArchive.as(DatasourceArchive.class).dataSource("ExampleDS", (ds) -> {
//            ds.connectionUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//            ds.driverName("h2");
//            ds.userName("sa");
//            ds.password("sa");
//        });
        // Deploy the datasource
        container.deploy(dsArchive);

        // Prevent JPA Fraction from installing it's default datasource fraction
        container.fraction(new JPAFraction()
                .inhibitDefaultDatasource()
                .defaultDatasource("jboss/datasources/MyDS")
        );

        container
                .fraction(LoggingFraction.createDefaultLoggingFraction())
        ;
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addPackage("zeilush.shop.config");

        deployment.addResource(OrderResource.class);
//        deployment.addAllDependencies();
        deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");
        deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/beans.xml", Main.class.getClassLoader()), "classes/META-INF/beans.xml");

        deployment.addAllDependencies();

        container.deploy(deployment);
    }
}
