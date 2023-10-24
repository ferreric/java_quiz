package quizapp;

import org.junit.*;

/**
 * Die QuizQuestionTest Klasse enthält Unit-Tests für die QuizQuestion Klasse.
 */
public class QuizQuestionTest {
    private QuizQuestion quizQuestion;
    private String[] options;

    /**
     * Initialisierung vor jedem Test.
     */
    @Before
    public void setup() {
        // Initialisieren Sie die Optionen und die QuizQuestion
        options = new String[]{"A. Option1", "B. Option2", "C. Option3"};
        quizQuestion = new QuizQuestion("Question1", options, 'A');
    }
    
    /**
     * Testet, ob die getQuestion Methode korrekt funktioniert.
     */
    @Test
    public void testGetQuestion() {
        Assert.assertEquals("Question1", quizQuestion.getQuestion());
    }

    /**
     * Testet, ob die getOptions Methode korrekt funktioniert.
     */
    @Test
    public void testGetOptions() {
        Assert.assertArrayEquals(options, quizQuestion.getOptions());
    }

    /**
     * Testet, ob die getCorrectAnswer Methode korrekt funktioniert.
     */
    @Test
    public void testGetCorrectAnswer() {
        Assert.assertEquals('A', quizQuestion.getCorrectAnswer());
    }
}
