package by.epam_training.lukyanov.task4.client;

import by.epam_training.lukyanov.task4.entity.ParsedText;
import by.epam_training.lukyanov.task4.exception.ProjectException;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws ProjectException {
        try (Socket socket = new Socket("127.0.0.1", 5000);
             ObjectOutputStream osToServer = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream isFromServer = new ObjectInputStream(socket.getInputStream());
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {

            menu:
            while (true) {

                // Read the radius from the keyboard
                System.out.println("Please select an option:");
                System.out.println("0 - Exit:");
                System.out.println("1 - Show sentences with the same words:");
                System.out.println("2 - Show sentences by number of words:");
                System.out.println("3 - Show first sentence unique words:");


                int option = Integer.parseInt(input.readLine());

                // Send the option selected to the server
                osToServer.writeInt(option);
                osToServer.flush();

                // Get answer from the server

                switch (option) {
                    case 0:
                        break menu;
                    case 1, 2, 3:
                        ParsedText answer = (ParsedText) isFromServer.readObject();
                        System.out.println("Answer to the selected option is " + answer);
                        break;
                    default:
                        System.out.println("Something wrong with your input, try again(");
                        break;
                }


            }//end while


        } catch (IOException | ClassNotFoundException exp) {
            throw new ProjectException(exp.getMessage());
        }
    }
}
