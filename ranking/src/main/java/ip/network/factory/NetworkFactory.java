package ip.network.factory;

import ip.network.AbstractNetwork;
import ip.network.exceptions.CannotCreateNetworkException;

/**
 *
 * @author Wojciech Szałapski
 */
public interface NetworkFactory {

    public AbstractNetwork createNetwork() throws CannotCreateNetworkException;
}
