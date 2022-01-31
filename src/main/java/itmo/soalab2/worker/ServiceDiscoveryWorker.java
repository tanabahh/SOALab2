package itmo.soalab2.worker;

import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.ServiceHealth;
import org.springframework.stereotype.Service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class ServiceDiscoveryWorker {
    public static Consul client = null;

    static  {
        try {
            client = Consul.builder().build();
        } catch (Exception e) {
            System.err.println("Consul is unavailable");
        }
    }

    public static String getUriFromConsul() {
        try {
            if (client != null) {
                HealthClient healthClient = client.healthClient();
                List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances("vehicle-app").getResponse();
                if (nodes.size() > 0) {
                    ServiceHealth service = nodes.get(0);
                    System.out.println("Got service's uri from consul");
                    //String address = service.getNode().getAddress();
                    String address = "localhost";
                    int port = service.getService().getPort();
                    String app = service.getService().getMeta().get("app");
                    return String.format("https://%s:%d/%s", address, port, app);
                }
            }
            System.err.println("Service 2 not available from consul - using fallback jndi resource");
            InitialContext cont = new InitialContext();
            return (String) cont.lookup("java:/service2_uri");
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }
}
