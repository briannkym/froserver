package org.table2table.froserver.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.table2table.froserver.model.ServerCommand;

public class CommunicationService implements Runnable {

	private Socket s;

	CommunicationService(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try (
				BufferedInputStream bIS = new BufferedInputStream(s.getInputStream());
				ObjectInputStream oIS = new ObjectInputStream(bIS);
				BufferedOutputStream bOS = new BufferedOutputStream(s.getOutputStream());
				ObjectOutputStream oOS = new ObjectOutputStream(bOS);
				) {
			ServerCommand sC = (ServerCommand) oIS.readObject();
			System.out.println(sC.toString());
			// TODO code for doing things here.
		} catch (IOException e) {
			System.out.println("Error on port " + s.getPort());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				System.out.println("Unable to close socket on port "
						+ s.getPort());
				e.printStackTrace();
			}
		}
	}

}
