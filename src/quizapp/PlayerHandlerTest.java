package quizapp;

import org.junit.*;

/**
 * Die PlayerHandlerTest Klasse enthält Unit-Tests für die PlayerHandler Klasse.
 */
public class PlayerHandlerTest {
    private PlayerHandler playerHandler;
    private Quiz quiz;
    private Socket clientSocket;

    /**
     * Initialisierung vor jedem Test.
     */
    @Before
    public void setup() {
        // Initialisieren Sie den Quiz und den PlayerHandler
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("Question1", new String[]{"A. Option1", "B. Option2", "C. Option3"}, 'A'));
        quiz = new Quiz(questions);
        // Bitte beachten Sie, dass wir hier nicht wirklich mit Sockets arbeiten können,
        // so dass wir einfach einen Null-Wert verwenden.
        clientSocket = null;
        playerHandler = new PlayerHandler(clientSocket, quiz);
    }
    
}
