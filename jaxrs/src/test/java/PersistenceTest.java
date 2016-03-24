import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.testng.annotations.Test;
import zeilush.shop.order.boundary.OrderManager;
import zeilush.shop.order.entity.OrderItem;

import javax.inject.Inject;
import java.io.File;
import java.net.URL;

/**
 * Created by AAA on 23.03.2016.
 */

public class PersistenceTest extends Arquillian {

    @ArquillianResource
    URL context;

    @Inject
    private OrderManager orderManager;

    @Deployment
    public static Archive<?> createDeploymentPackage() {
//        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").resolve("org.apache.deltaspike.core:deltaspike-core-impl").withoutTransitivity().as(File.class);

        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class, "UserPersistenceTest.war")
                // Enable CDI
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml")).addAsLibraries(libs)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addPackages(true, "zeilush.shop.config", "zeilush.shop.exception", "zeilush.shop.jaxrs");
//                .addAsLibraries(JarLocation.jarLocation(ConfigSourceProvider.class))
//                .addAsLibraries(JarLocation.jarLocation(DefaultConfigSourceProvider.class));
//                .addPackage(User.class.getPackage())
//                .addAsManifestResource(new ClassLoaderAsset("META-INF/test-persistence.xml"), "test-persistence.xml");
    }

    @Test
    public void createOrder() {
        OrderItem item = new OrderItem();
        item.setName("hello");

        item = orderManager.createItem(item);

        Assert.assertTrue(item.getId() > 0);
        Assert.assertEquals("hello", item.getName());
    }
}
