package curso.jboss.jms.cliente;

import java.util.Date;
import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviarAColaJMS11 {

	@SuppressWarnings("unchecked")
	private static InitialContext getInitialContext() throws NamingException {
		// Creamos un hashtable con los datos necesarios para obtener el contexto inicial
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,	
				"org.jboss.naming.remote.client.InitialContextFactory");
		//env.put("java.naming.factory.url.pkgs",	"org.jboss.naming:org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "adminAdmin1");
		return new InitialContext(env);
	}

	public static String sendMessage(String name, String value) {
		String result = "";
		try {
			// Conseguimos de la JNDI los objetos administrados por JBoss. Son la Factoria de conexiones y la Cola
			InitialContext contextoInicial = getInitialContext();
			ConnectionFactory factory = (ConnectionFactory)
				contextoInicial.lookup("jms/RemoteConnectionFactory");
			Destination cola = (Destination) contextoInicial.lookup("queue/miCola");
			// Creamos la conexion y la sesion
			Connection conexion = factory.createConnection("admin","adminAdmin1");
			Session sesion = conexion.createSession(false,Session.AUTO_ACKNOWLEDGE);
			// Creamos una sesion de envio
			MessageProducer emisor = sesion.createProducer(cola);
			// Creamos un mensaje JMS de tipo texto
			TextMessage mensaje = sesion.createTextMessage();
			mensaje.setText(new Date() + " "+ name + " " + value);
			// Lo enviamos
			emisor.send(mensaje);
			result =  "Mensaje enviado: " + mensaje.getText();
			// Cerramos la conexion
			conexion.close();
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		} 
		return result;
	}
}



