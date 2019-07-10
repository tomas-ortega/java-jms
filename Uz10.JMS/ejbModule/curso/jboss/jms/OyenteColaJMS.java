package curso.jboss.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
		activationConfig = { 
			@ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue") ,
			@ActivationConfigProperty(propertyName="destination",	propertyValue="queue/miCola") ,
			@ActivationConfigProperty(propertyName = "user", propertyValue = "admin"),
			@ActivationConfigProperty(propertyName = "password", propertyValue = "adminAdmin1")
			})
public class OyenteColaJMS implements MessageListener {
  
	public OyenteColaJMS() {}
	
    public void onMessage(Message message) {
    	   try {
         	
           	//Pasamos el mensaje obtenido a uno de tipo texto para poder leer el contenido
           	TextMessage mensaje= (TextMessage)message;
   			System.out.println("Id del mensaje: "+mensaje.getJMSMessageID());
   			System.out.println("Destino del mensaje: "+mensaje.getJMSDestination());
   			System.out.println("Texto del mensaje: "+mensaje.getText());
   		} catch (JMSException e) {
   			
   			e.printStackTrace();
   		}
        
    }

}
