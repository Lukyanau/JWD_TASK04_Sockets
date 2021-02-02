package by.epam_training.lukyanov.task4.server;

import by.epam_training.lukyanov.task4.exception.ProjectException;
import by.epam_training.lukyanov.task4.server.impl.ServiceImpl;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws ProjectException {
        System.out.println("Server is started!!!");
        try ( ServerSocket serverSocket = new ServerSocket(5000);
              Socket socket = serverSocket.accept();
              ObjectOutputStream osToClient = new ObjectOutputStream(socket.getOutputStream());
              ObjectInputStream isFromClient = new ObjectInputStream(socket.getInputStream())) {
            TextParser textParser = TextParser.getInstance();
            ServiceImpl service = ServiceImpl.getInstance();
            menu:
            while (true) {
                int option = isFromClient.readInt();
                System.out.println("Option received from client: " + option);
                switch (option) {
                    case 0:
                        break menu;
                    case 1:
                        osToClient.writeObject(service.findRepeatingWords(textParser.generateParsedTest("resources/programmingText.txt")));
                        osToClient.flush();
                        break;
                    case 2:
                        osToClient.writeObject(service.sortSentenceByNumberOfWords(textParser.generateParsedTest("resources/programmingText.txt")));
                        osToClient.flush();
                        break;
                    case 3:
                        osToClient.writeObject(service.findUniqueWordsInFirstSe(textParser.generateParsedTest("resources/programmingText.txt")));
                        osToClient.flush();
                        break;
                    default:
                        osToClient.writeObject("Your input is incorrect, try again");
                        osToClient.flush();
                        break;
                }
            }

        } catch (IOException exp) {
            throw new ProjectException(exp.getMessage());
        }
    }
}
