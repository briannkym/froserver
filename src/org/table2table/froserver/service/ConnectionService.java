package org.table2table.froserver.service;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.table2table.froserver.model.IFRODatabase;

/**
 * 
 * @author Brian Nakayama
 *
 */
public class ConnectionService implements Runnable, Closeable {
	private int portNumber;
	private volatile boolean running = true;
	private IFRODatabase database;
	public ConnectionService(int portNumber, IFRODatabase database) {
		this.portNumber = portNumber;
		this.database = database;
	}

	@Override
	public void run() {
		running = true;
		try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
			while (running) {
				Socket clientSocket = serverSocket.accept();
				CommunicationService cS = new CommunicationService(clientSocket, database);
				new Thread(cS).run();
			}
		} catch (IOException e) {
			System.out
					.println("I/O Exception caught when trying to listen on port "
							+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out
			.println("Other exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
	System.out.println(e.getMessage());
		}
	}

	@Override
	public void close() {
		running = false;
	}
}
