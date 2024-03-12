import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Witaj w menu aplikacji! Jeśli masz ochotę na Quiz wpisz Quiz\nZ kolei jeśli chcesz usłyszeć Chińskie porzekadło na dziś wpisz Porzekadło ;) : ");

        Scanner scanner = new Scanner(System.in);
        ChineseProverb proverb = new ChineseProverb();
        String approval = scanner.nextLine().toUpperCase();
        if (approval.equalsIgnoreCase("Quiz")) {
            int howManyQuestions;

            do {
                System.out.println("Na ile pytań chcesz odpowiedzieć?\nPodaj liczbę od 1 do 10: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Podaj liczbę od 1 do 10!");
                    scanner.next(); // Konsumuje błędną wartość
                }

                howManyQuestions = scanner.nextInt();

                if (howManyQuestions < 1 || howManyQuestions > 10) {
                    System.out.println("Podaj liczbę od 1 do 10!!!");
                }

            } while (howManyQuestions < 1 || howManyQuestions > 10);

            quizLoop(howManyQuestions);
        }
        if (approval.equalsIgnoreCase("Porzekadło")) {
            ChineseProverb.proverb();
        } else {
            System.out.println("Do widzenia!");
        }

        scanner.close();
    }


    public static void quizLoop(int howManyQuestions) {
        HashMap<String, String> quizQuestion = new HashMap<>();
        quizQuestion.put("Kto wydał Jezusa", "Judasz");
        quizQuestion.put("Kto napisał list do Koryntian", "Paweł");
        quizQuestion.put("Gdzie chociaż raz w życiu musi wybrać się Muzułmanin", "do Medyny");
        quizQuestion.put("Gdzie urodził się jezus", "Betlejem");
        quizQuestion.put("Jak miała na imie matka jezusa", "Maryja");
        quizQuestion.put("Jak miał na imie ojciec jezusa", "Józef");
        quizQuestion.put("W co Jezus zamienił wode", "w wino");
        quizQuestion.put("Co miało stać się po tym jak kur zapieje 2 razy", "Piotr wyprze się jezusa");
        quizQuestion.put("Na jakiej górze nastąpiła przemiana Jezusa", "Tabor");
        quizQuestion.put("Kogo uwolniono podczas procesu Jezusa", "Barabasza");
        quizQuestion.put("W którym roku urodził się Jezus", "0");

        HashSet<String> usedKeys = new HashSet<>();
        double correctAnswersCount = 0;
        for (int i = 1; i <= howManyQuestions; i++) {
            String randomKey = getRandomKey(quizQuestion, usedKeys);
            if (randomKey == null) {
                System.out.println("Nie wystarczająca liczba unikalnych pytań. Koniec quizu.");
                break;
            }

            String correctAnswer = quizQuestion.get(randomKey);
            System.out.println("Pytanie " + i + ": " + randomKey);
            System.out.print("Twoja odpowiedź: ");

            Scanner scanner = new Scanner(System.in);
            String userAnswer = scanner.nextLine();

            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                System.out.println("Odpowiedź poprawna!");
                correctAnswersCount++;

            } else {
                System.out.println("Odpowiedź niepoprawna. Prawidłowa odpowiedź to: " + correctAnswer);
                System.out.println("Do widzenia!");
                scanner.close();
                System.exit(0);
            }
        }
        System.out.println("Gratulacje! Zakończyłeś quiz.");
        System.out.println("Twój wynik to: " + (int) correctAnswersCount + "/" + howManyQuestions + ", co stanowi: " + ((double) correctAnswersCount / howManyQuestions) * 100 + "%");
    }

    public static <K, V> K getRandomKey(HashMap<K, V> map, HashSet<K> usedKeys) {
        Set<K> keySet = map.keySet();
        keySet.removeAll(usedKeys);

        if (keySet.isEmpty()) {
            // Jeśli wszystkie klucze zostały już użyte, możesz zwrócić null lub obsłużyć to w inny sposób
            return null;
        }

        K[] keys = keySet.toArray((K[]) new Object[keySet.size()]);
        Random random = new Random();
        int randomIndex = random.nextInt(keys.length);
        K randomKey = keys[randomIndex];

        usedKeys.add(randomKey);
        return randomKey;
    }
}

