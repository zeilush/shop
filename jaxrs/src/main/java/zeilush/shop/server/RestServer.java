package zeilush.shop.server;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;


public class RestServer {

    public static void main(String... args) throws Exception {
        Container container = new Container();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addPackages(true, "zeilush.shop.jaxrs");
        deployment.addAllDependencies();
        container.start().deploy(deployment);
    }
}
