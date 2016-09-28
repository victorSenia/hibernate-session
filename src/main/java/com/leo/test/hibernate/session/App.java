package com.leo.test.hibernate.session;

import com.leo.test.hibernate.session.controller.HomeController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
@ApplicationPath("hibernate-session")
public class App extends Application {
    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();
        set.add(new HomeController());
        return set;
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        ResourceConfig config = new ResourceConfig();
        config.packages("com.leo.test.hibernate.session.controller");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            System.out.println("Server started on http://localhost:" + port + "/browser");
            server.join();
        } finally {
            server.destroy();
        }
    }
}
