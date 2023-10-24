package quizapp;

import java.io.*;
import java.net.*;

/**
 * Die QuizClient Klasse stellt die Client-Seite der Quiz-Anwendung dar.
 * Sie verbindet sich mit dem QuizServer und ermöglicht es dem Spieler, am Quiz teilzunehmen.
 */
public class QuizClient {
    private static final String HOST = "localhost";  // Der Hostname des QuizServers
    private static final int PORT = 12348;  // Der Port des QuizServers

    /**
     * Der Einstiegspunkt für die QuizClient Anwendung.
     * @param args Die Kommandozeilenargumente.
     * @throws IOException Wenn ein I/O-Fehler auftritt.
     */
    public static void main(String[] args) throws IOException {
        // Versucht, eine Verbindung zum Server zu erstellen und das Quiz zu spielen.
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            // Die von Server empfangenen Daten.
            String received;
            // Eine temporäre Variable, um die Frage und die Optionen zu speichern.
            StringBuilder questionAndOptions = new StringBuilder();
            // Liest die Daten vom Server bis keine Daten mehr verfügbar sind.
            while ((received = in.readLine()) != null) {
                // Fügt die empfangenen Daten zu den aktuellen Fragen und Optionen hinzu.
                questionAndOptions.append(received).append("\n");
                // Überprüft, ob die Option C erhalten wurde, was bedeutet, dass alle Optionen empfangen wurden.
                if (received.startsWith("C.")) {
                    // Druckt die Frage und die Optionen aus und erwartet die Eingabe des Benutzers.
                    System.out.println(questionAndOptions.toString());
                    System.out.print("Ihre Antwort (A, B oder C): ");
                    // Sendet die Antwort des Benutzers an den Server.
                    String answer = userInput.readLine();
                    out.println(answer);
                    // Löscht die aktuelle Frage und Optionen.
                    questionAndOptions.setLength(0);
                } else if (received.startsWith("Quiz beendet.")) {
                    // Beendet das Quiz, wenn die abschliessende Nachricht vom Server erhalten wird.
                    break;
                }
            }
        }
    }
}
