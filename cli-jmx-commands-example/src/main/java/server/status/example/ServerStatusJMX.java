package server.status.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class ServerStatusJMX {

	private String host = "127.0.0.1";
	private int port = 9999;
	private String user = "username";
	private String password = "password";

	// mapping credentials
	HashMap<String, String[]> propEnv = new HashMap<String, String[]>();
	String[] credentials = { user, password };

	final String urlString = "service:jmx:remoting-jmx://" + host + ":" + port;
	JMXConnector jmxConnector = null;
	JMXServiceURL serviceURL;

	public void runJmx() throws IOException {

		propEnv.put(JMXConnector.CREDENTIALS, credentials);
		
		try {
			
			serviceURL = new JMXServiceURL(urlString);
			jmxConnector = JMXConnectorFactory.connect(serviceURL, propEnv);
			MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("jboss.as:management-root=server");
			String serverState = (String) connection.getAttribute(objectName,"serverState");
			System.out.println("server Status is:= " + serverState);
			
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (MalformedObjectNameException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (AttributeNotFoundException e) {

			e.printStackTrace();
		} catch (InstanceNotFoundException e) {

			e.printStackTrace();
		} catch (MBeanException e) {

			e.printStackTrace();
		} catch (ReflectionException e) {

			e.printStackTrace();
		} finally {
			jmxConnector.close();
		}
	}
}
