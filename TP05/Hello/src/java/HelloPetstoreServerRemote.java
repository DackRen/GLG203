
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface HelloPetstoreServerRemote extends Remote {

    public String hello() throws RemoteException;
}
