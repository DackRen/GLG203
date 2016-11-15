
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class HelloPetstoreServer extends UnicastRemoteObject implements HelloPetstoreServerRemote {

    public HelloPetstoreServer() throws RemoteException {
    }

    // La methode ne lance pas une RemoteException
    public String hello() {
        return "Hello Petstore";
    }

    // Point d'entrée de la classe serveur
    public static void main(String[] args) {
        try {
            // Le serveur s'enregistre
            Naming.rebind("HelloPetstore", new HelloPetstoreServer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
