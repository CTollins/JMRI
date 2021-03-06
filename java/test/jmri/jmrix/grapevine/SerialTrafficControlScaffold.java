package jmri.jmrix.grapevine;

import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stands in for the SerialTrafficController class.
 *
 * @author	Bob Jacobsen Copyright 2004, 2007
 */
public class SerialTrafficControlScaffold extends SerialTrafficController {

    public SerialTrafficControlScaffold(GrapevineSystemConnectionMemo memo) {
        super(memo);
    }

    // override some SerialTrafficController methods for test purposes
    @Override
    public boolean status() {
        return true;
    }

    /**
     * Record messages sent, provide access for making sure they are OK.
     */
    public Vector<SerialMessage> outbound = new Vector<SerialMessage>(); // public OK here, so long as this is a test class

    @Override
    public void sendSerialMessage(SerialMessage m, SerialListener reply) {
        if (log.isDebugEnabled()) {
            log.debug("sendSerialMessage [" + m + "]");
        }
        // save a copy
        //log.debug("outbound.sendSerialMessage [" + m + "] added, size = " + outbound.size());
        outbound.addElement(m);
        // we don't return an echo so that the processing before the echo can be
        // separately tested
    }

    // test control member functions

    /**
     * Forward a message to the listeners, e.g. test receipt.
     */
    protected void sendTestMessage(SerialMessage m, SerialListener l) {
        // forward a test message to GrapevineListeners
        if (log.isDebugEnabled()) {
            log.debug("sendTestMessage    [" + m + "]");
        }
        notifyMessage(m, l);
        return;
    }

    /*
     * Check number of listeners, used for testing dispose().
     */
    public int numListeners() {
        return cmdListeners.size();
    }

    private final static Logger log = LoggerFactory.getLogger(SerialTrafficControlScaffold.class);

}
