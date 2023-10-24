package quizapp;

import java.util.List;

/**
 * Die Quiz Klasse repräsentiert ein Quiz, das aus mehreren QuizQuestions besteht.
 */
public class Quiz {
    private List<QuizQuestion> questions;  // Die Liste der Fragen im Quiz

    /**
     * Konstruktor für die Quiz Klasse.
     * @param questions Die Fragen für das Quiz.
     */
    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
    }

    /**
     * Gibt die Fragen des Quizzes zurück.
     * @return Die Fragen des Quizzes.
     */
    public List<QuizQuestion> getQuestions() {
        return questions;
    }
}
