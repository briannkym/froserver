package org.table2table.froserver.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ConnectionService implements Runnable {
	private int portNumber;
	private volatile boolean running = true;

	public ConnectionService(int portNumber) {
		this.portNumber = portNumber;
	}

	@Override
	public void run() {
		running = true;
		try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
			while (running) {
				Socket clientSocket = serverSocket.accept();
				CommunicationService cS = new CommunicationService(clientSocket);
				new Thread(cS).run();
				
			}
		} catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}

	public void stop() {
		running = false;
	}
}
