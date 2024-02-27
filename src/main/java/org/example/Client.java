package org.example;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static final String UNIQUE_BINDING_NAME = "server.query";

    public static void main(String[] args) throws RemoteException, NotBoundException {

        new Client().run();
    }

    private void run() throws NotBoundException, RemoteException {

        int item;
        do {
            item = menu();
            switch (item) {
                case 1:
                    printAllReaders();
                    break;
                case 2:
                    printAllBooks();
                    break;
                case 3:
                    printBookById();
                    break;
            }
        } while (item != 0);
    }

    private void printBookById() throws RemoteException, NotBoundException {

        System.out.println("Введіть id книги");
        Scanner in = new Scanner(System.in);
        int bookId =  in.nextInt();

        long start = System.currentTimeMillis();

        final Registry registry = LocateRegistry.getRegistry(2732);
        QueryRequest calculator = (QueryRequest) registry.lookup(UNIQUE_BINDING_NAME);
        String result = calculator.getById("select * from books where book_id = ?",bookId);

        long finish = System.currentTimeMillis();
        System.out.println("Час виконання: " + (finish - start));

        System.out.println(result);
    }

    private void printAllBooks() throws RemoteException, NotBoundException {

        long start = System.currentTimeMillis();

        final Registry registry = LocateRegistry.getRegistry(2732);
        QueryRequest calculator = (QueryRequest) registry.lookup(UNIQUE_BINDING_NAME);
        String result = calculator.getAll("select * from books");

        long finish = System.currentTimeMillis();
        System.out.println("Час виконання: " + (finish - start));

        System.out.println(result);
    }

    private void printAllReaders() throws RemoteException, NotBoundException {

        long start = System.currentTimeMillis();

        final Registry registry = LocateRegistry.getRegistry(2732);
        QueryRequest calculator = (QueryRequest) registry.lookup(UNIQUE_BINDING_NAME);
        String result = calculator.getAll("select * from readers");

        long finish = System.currentTimeMillis();
        System.out.println("Час виконання: " + (finish - start));

        System.out.println(result);
    }

    private int menu() {

        System.out.println("1. Вивести всіх читачів");
        System.out.println("2. Вивести всі книги");
        System.out.println("3. Отримати книгу по ID");
        System.out.println("0. Вихід");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}