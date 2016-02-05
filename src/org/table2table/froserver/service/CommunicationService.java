package org.table2table.froserver.service;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.table2table.froserver.model.IFRODatabase;

/**
 * 
 * @author Brian Nakayama
 *
 */
public class CommunicationService implements Runnable, Closeable {

	private Socket s;
	private ObjectInputStream oIS;
	private ObjectOutputStream oOS;
	private boolean close = false;
	private IFRODatabase database;

	CommunicationService(Socket s, IFRODatabase database) {
		this.s = s;
		this.database = database;
	}

	@Override
	public void run() {
		System.out.println("Opening a connection with " + s.getInetAddress().getCanonicalHostName() + " at " + s.getInetAddress().getHostAddress());
		try {
			oIS = new ObjectInputStream(s.getInputStream());
			oOS = new ObjectOutputStream(s.getOutputStream());
			while (!close) {
				IServerCommand sC = (IServerCommand) oIS.readObject();
				try {
					System.out.println(sC.toString());
					sC.accept(database, this);
					oOS.writeObject(new ClientMessage("Command Completed.",
							true));
				} catch (IOException e) {
					oOS.writeObject(new ClientMessage("I/O Error.", false));
				}
			}
		} catch (IOException e) {
			System.out.println("Error on port " + s.getPort()+" ");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
				oIS.close();
				oOS.close();
			} catch (IOException e) {
				System.out.println("Unable to close socket on port "
						+ s.getPort());
				e.printStackTrace();
			}
		}
	}

	public ObjectInputStream getInputStream() {
		return oIS;
	}

	public ObjectOutputStream getOutputStream() {
		return oOS;
	}

	@Override
	public void close() {
		close = true;
	}
}
