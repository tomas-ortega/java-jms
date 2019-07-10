package curso.jboss.jms.cliente;

import java.util.*;
import javax.jms.*;
import javax.jms.Queue;
import javax.naming.*;

public class EnviarAColaJMS10 {

	@SuppressWarnings("unchecked")
	private static InitialContext getInitialContext() throws NamingException {
		// Creamos un hashtable con los datos necesarios para obtener el contexto inicial
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,	
				"org.jboss.naming.remote.client.InitialContextFactory");
		//env.put("java.naming.factory.url.pkgs",	"org.jboss.naming:org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "testuser");
		env.put(Context.SECURITY_CREDENTIALS, "password");
		return new InitialContext(env);
	}

	public static void main(String args[]) {
		try {
			// Conseguimos de la JNDI los objetos administrados por JBoss. Son la Factoria de conexiones y la Cola
			InitialContext contextoInicial = getInitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory)
				contextoInicial.lookup("jms/RemoteConnectionFactory");
			Queue cola = (Queue) contextoInicial.lookup("queue/miCola");
			// Creamos la conexion y la sesion
			QueueConnection conexion = factory.createQueueConnection("testuser","password");
			QueueSession sesion = conexion.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			// Creamos una sesion de envio
			QueueSender emisor = sesion.createSender(cola);
			// Creamos un mensaje JMS de tipo texto
			TextMessage mensaje = sesion.createTextMessage();
			mensaje.setText(new Date() + " Esto es un mensaje de texto");
			// Lo enviamos
			emisor.send(mensaje);
			System.out.println("Mensaje enviado: " + mensaje.getText());
			// Cerramos la conexion
			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}



