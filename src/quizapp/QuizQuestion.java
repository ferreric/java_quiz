package quizapp;

/**
 * Die QuizQuestion Klasse repräsentiert eine einzelne Quiz-Frage, ihre Optionen und die richtige Antwort.
 */
public class QuizQuestion {
    private String question;  // Die Quiz-Frage
    private String[] options;  // Die Optionen für die Quiz-Frage
    private char correctAnswer;  // Die richtige Antwort auf die Quiz-Frage

    /**
     * Konstruktor für die QuizQuestion Klasse.
     * @param question Die Quiz-Frage.
     * @param options Die Optionen für die Quiz-Frage.
     * @param correctAnswer Die richtige Antwort auf die Quiz-Frage.
     */
    public QuizQuestion(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Gibt die Quiz-Frage zurück.
     * @return Die Quiz-Frage.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gibt die Optionen für die Quiz-Frage zurück.
     * @return Die Optionen für die Quiz-Frage.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Gibt die richtige Antwort auf die Quiz-Frage zurück.
     * @return Die richtige Antwort auf die Quiz-Frage.
     */
    public char getCorrectAnswer() {
        return correctAnswer;
    }
}