
import java.rmi.Naming;

public class Client {

    // Point d'entrée de la classe cliente
    public static void main(String[] args) {
        try {
            HelloPetstoreServerRemote server;
            // La classe obtient un stub
            server = (HelloPetstoreServerRemote) Naming.lookup("HelloPetstore");

            // Appelle la methode distante et affiche le résultat
            System.out.println(server.hello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
