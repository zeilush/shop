import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

/**
 * Created by AAA on 23.03.2016.
 */
@RunWith(Arquillian.class)
public class PersistenceTest {

    @ArquillianResource
    URL context;

    @Deployment
    public static Archive<?> createDeploymentPackage() {
        return ShrinkWrap.create(WebArchive.class, "UserPersistenceTest.war");
//                .addPackage(User.class.getPackage())
//                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @Test
    public void testContext() {
        System.out.println("asdsad " + context);
    }
}
