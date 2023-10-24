package quizapp;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Die PlayerHandler Klasse verwaltet die Kommunikation mit einem Spieler und führt das Quiz durch.
 */
public class PlayerHandler implements Runnable {
    private Socket clientSocket;  // Der Socket für die Kommunikation mit dem Spieler
    private Quiz quiz;  // Das Quiz, das durchgeführt wird
    private static AtomicInteger score = new AtomicInteger(0);  // Der Punktestand des Spielers

    /**
     * Konstruktor für die PlayerHandler Klasse.
     * @param clientSocket Der Socket für die Kommunikation mit dem Spieler.
     * @param quiz Das Quiz, das durchgeführt wird.
     */
    public PlayerHandler(Socket clientSocket, Quiz quiz) {
        this.clientSocket = clientSocket;
        this.quiz = quiz;
    }

    /**
     * Führt das Quiz durch und verwaltet die Kommunikation mit dem Spieler.
     */
    @Override
    public void run() {
        // Versucht, eine Verbindung zum Client zu erstellen und das Quiz durchzuführen.
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            // Durchläuft alle Fragen im Quiz.
            for (QuizQuestion question : quiz.getQuestions()) {
                // Sendet die Frage und die Antwortoptionen an den Spieler.
                out.println(question.getQuestion());
                for (String option : question.getOptions()) {
                    out.println(option);
                }
                // Empfängt die Antwort des Spielers.
                String playerAnswer = in.readLine();
                // Überprüft, ob die Antwort des Spielers korrekt ist.
                if (playerAnswer.toUpperCase().charAt(0) == question.getCorrectAnswer()) {
                    // Wenn die Antwort korrekt ist, erhöht er den Punktestand und sendet eine Erfolgsmeldung an den Spieler.
                    score.incrementAndGet();
                    out.println("Richtige Antwort! Aktueller Punktestand: " + score);
                } else {
                    // Wenn die Antwort falsch ist, sendet er eine Fehlernachricht an den Spieler.
                    out.println("Falsche Antwort. Aktueller Punktestand: " + score);
                }
            }
            // Sendet eine abschliessende Nachricht an den Spieler, nachdem alle Fragen beantwortet wurden.
            out.println("Quiz beendet. Ihr Punktestand: " + score);
        } catch (IOException e) {
            // Bei einem Fehler in der Kommunikation wird eine Fehlermeldung ausgegeben.
            System.err.println("Fehler bei der Kommunikation mit dem Spieler: " + e.getMessage());
        } finally {
            try {
                // Versucht, das Client-Socket zu schliessen.
                clientSocket.close();
            } catch (IOException e) {
                // Bei einem Fehler beim Schliessen des Sockets wird eine Fehlermeldung ausgegeben.
                System.err.println("Fehler beim Schliessen des Sockets: " + e.getMessage());
            }
        }
    }
}
