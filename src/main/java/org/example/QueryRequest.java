package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QueryRequest extends Remote {

    String getAll(String s) throws RemoteException;

    String getById(String s, int id) throws RemoteException;
}
