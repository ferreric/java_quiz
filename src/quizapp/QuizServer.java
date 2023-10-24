package quizapp;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Die QuizServer Klasse stellt die Server-Seite der Quiz-Anwendung dar.
 * Sie verwaltet die Verbindungen zu den Spielern und startet für jeden Spieler einen PlayerHandler.
 */
public class QuizServer {
    private static final int PORT = 12348;  // Der Port, auf dem der Server läuft
    private ServerSocket serverSocket;  // Der ServerSocket für eingehende Verbindungen
    private Quiz quiz;  // Das Quiz, das durchgeführt wird

    /**
     * Konstruktor für die QuizServer Klasse.
     * @param quiz Das Quiz, das durchgeführt wird.
     * @throws IOException Wenn ein I/O-Fehler auftritt.
     */
    public QuizServer(Quiz quiz) throws IOException {
        this.quiz = quiz;
        serverSocket = new ServerSocket(PORT);
    }

    /**
     * Startet den Server und wartet auf eingehende Verbindungen von Spielern.
     * @throws IOException Wenn ein I/O-Fehler auftritt.
     */
    public void start() throws IOException {
    	// Druckt eine Nachricht, um anzuzeigen, dass der Server gestartet wurde.
        System.out.println("Server gestartet. Warte auf Spieler...");

        // Wartet kontinuierlich auf neue Clientverbindungen.
        while (true) {
            // Akzeptiert eine neue Clientverbindung.
            Socket clientSocket = serverSocket.accept();
            // Erstellt einen neuen PlayerHandler, um die Kommunikation mit dem Client zu verwalten.
            PlayerHandler playerHandler = new PlayerHandler(clientSocket, quiz);
            // Startet den PlayerHandler in einem neuen Thread.
            new Thread(playerHandler).start();
    }
    }

    /**
     * Der Einstiegspunkt für die QuizServer Anwendung.
     * @param args Die Kommandozeilenargumente.
     * @throws IOException Wenn ein I/O-Fehler auftritt.
     */
    public static void main(String[] args) throws IOException {
        // Lädt die Fragen aus der Datei und erstellt ein Quiz.
        List<QuizQuestion> questions = loadQuestionsFromFile("quizfragen.txt");
        Quiz quiz = new Quiz(questions);
        // Erstellt einen neuen QuizServer und startet ihn.
        QuizServer server = new QuizServer(quiz);
        server.start();
    }

    /**
     * Lädt die Quiz-Fragen aus einer Datei.
     * @param fileName Der Name der Datei, aus der die Quiz-Fragen geladen werden.
     * @return Eine Liste der geladenen Quiz-Fragen.
     */
    private static List<QuizQuestion> loadQuestionsFromFile(String fileName) {
        // Die Liste der Fragen.
        List<QuizQuestion> questions = new ArrayList<>();

        // Versucht, die Fragen aus der Datei zu lesen.
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Liest jede Frage und ihre Optionen und die richtige Antwort aus der Datei.
            while ((line = reader.readLine()) != null) {
                String question = line;
                String[] options = new String[3];
                options[0] = reader.readLine();
                options[1] = reader.readLine();
                options[2] = reader.readLine();
                char correctAnswer = reader.readLine().charAt(0);
                // Fügt die Frage zur Fragenliste hinzu.
                questions.add(new QuizQuestion(question, options, correctAnswer));
            }
        } catch (IOException e) {
            // Bei einem Fehler beim Lesen der Datei wird eine Fehlermeldung ausgegeben.
            System.err.println("Fehler beim Einlesen der Quizfragen: " + e.getMessage());
        }

        // Gibt die Liste der Fragen zurück.
        return questions;
    }

}

