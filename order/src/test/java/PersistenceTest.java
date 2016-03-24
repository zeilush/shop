import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.testng.annotations.Test;
import zeilush.shop.order.boundary.OrderManager;
import zeilush.shop.order.entity.OrderItem;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        JavaArchive[] libs = Maven.configureResolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().as(JavaArchive.class);

        JavaArchive testArchive = ShrinkWrap.create(JavaArchive.class, "order.jar")
                // Enable CDI
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addPackages(true, "zeilush.shop.order");

//        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

        for (JavaArchive lib : libs) {
            testArchive = testArchive.merge(lib);
        }

        return testArchive;
//                .addPackages(true, "zeilush.shop.order");
//                .addAsLibraries(JarLocation.jarLocation(ConfigSourceProvider.class))
//                .addAsLibraries(JarLocation.jarLocation(DefaultConfigSourceProvider.class));
//                .addPackage(User.class.getPackage())
//                .addAsManifestResource(new ClassLoaderAsset("META-INF/test-persistence.xml"), "test-persistence.xml");
    }

    @Deployment
    public static Archive<?> createDeploymentPackage() {
        JavaArchive[] libs = Maven.configureResolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().as(JavaArchive.class);

        JavaArchive testArchive = ShrinkWrap.create(JavaArchive.class, "order.jar")
                // Enable CDI
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addPackages(true, "zeilush.shop.order");

//        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

        for (JavaArchive lib : libs) {
            testArchive = testArchive.merge(lib);
        }

        return testArchive;
//                .addPackages(true, "zeilush.shop.order");
//                .addAsLibraries(JarLocation.jarLocation(ConfigSourceProvider.class))
//                .addAsLibraries(JarLocation.jarLocation(DefaultConfigSourceProvider.class));
//                .addPackage(User.class.getPackage())
//                .addAsManifestResource(new ClassLoaderAsset("META-INF/test-persistence.xml"), "test-persistence.xml");
    }

    @PersistenceContext
    @Produces
    EntityManager em;

    @Test
    public void createOrder() {
        OrderItem item = new OrderItem();
        item.setName("hello");

//        em.persist(item);
        item = orderManager.createItem(item);

        Assert.assertTrue(item.getId() > 0);
        Assert.assertEquals("hello", item.getName());
    }
}
