package org.yooreeka.util.internet.crawling.transport.file;

import org.yooreeka.util.internet.crawling.transport.common.TransportException;

public class FileTransportException extends TransportException {

    /**
	 * Unique identifier for serialization
	 */
	private static final long serialVersionUID = -6380601992826152509L;

	public FileTransportException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public FileTransportException(String msg) {
        super(msg);
    }
}
