package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class QueryPreparer implements QueryRequest {

    private DBWorker dbw;

    public String getAll(String s) throws RemoteException {
        dbw = new DBWorker();
        System.out.println(s);
        String result = dbw.getAll(s);
        dbw.closer();
        return result;
    }

    @Override
    public String getById(String s, int id) throws RemoteException {
        dbw = new DBWorker();
        System.out.println(s);
        String result =  dbw.getById(s,id);
        dbw.closer();
        return result;
    }
}
