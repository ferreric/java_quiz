package quizapp;

import org.junit.*;
import java.util.*;

/**
 * Die QuizTest Klasse enthält Unit-Tests für die Quiz Klasse.
 */
public class QuizTest {
    private Quiz quiz;
    private List<QuizQuestion> questions;

    /**
     * Initialisierung vor jedem Test.
     */
    @Before
    public void setup() {
        // Initialisieren Sie die Fragenliste und das Quiz
        questions = new ArrayList<>();
        questions.add(new QuizQuestion("Question1", new String[]{"A. Option1", "B. Option2", "C. Option3"}, 'A'));
        quiz = new Quiz(questions);
    }
    
    /**
     * Testet, ob die getQuestions Methode korrekt funktioniert.
     */
    @Test
    public void testGetQuestions() {
        Assert.assertEquals(questions, quiz.getQuestions());
    }

    /**
     * Testet, ob der Konstruktor korrekt funktioniert.
     */
    @Test
    public void testQuizConstructor() {
        Quiz newQuiz = new Quiz(questions);
        Assert.assertNotNull(newQuiz);
        Assert.assertEquals(questions, newQuiz.getQuestions());
    }
}
