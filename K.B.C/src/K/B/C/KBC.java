package K.B.C;

import java.util.*;

class Question {
    String question;
    String[] options;
    String correctAnswer;

    public Question(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class KBC {
    static int score = 0;
    static int level = 1;
    static List<Question> questionPool = new ArrayList<>();
    static Set<Integer> askedQuestions = new HashSet<>();
    static Scanner scanner = new Scanner(System.in);
    static boolean fiftyFiftyUsed = false;
    static boolean phoneAFriendUsed = false;
    static int correctAnswers = 0;  // Track correct answers to ensure at least 10 questions are answered

    public static void main(String[] args) {
        // Initialize the question pool with 20 questions
        initializeQuestions();
        System.out.println("Welcome to Kaun Banega Crorepati!");
        System.out.println("You are playing for the top prize of 1 Crore!");

        // Start the game
        while (correctAnswers < 10) {  // Ensure at least 10 correct answers
            if (askQuestion()) {
                score += 10000; // Increase score with each correct answer
                level++;
                correctAnswers++;
                if (correctAnswers == 10) {
                    System.out.println("Congratulations! You've answered 10 questions correctly!");
                    break;
                }
            } else {
                System.out.println("Incorrect Answer! You are out of the game.");
                break;
            }
        }

        if (correctAnswers >= 10) {
            System.out.println("You've won 1 Crore!");
        }

        System.out.println("Your total score is: " + score);
    }

    // Method to initialize the question pool with 20 questions
    public static void initializeQuestions() {
        questionPool.add(new Question("What is the capital of India?", 
            new String[] {"Mumbai", "Delhi", "Kolkata", "Bangalore"}, "Delhi"));
        questionPool.add(new Question("Who is the CEO of Tesla?", 
            new String[] {"Elon Musk", "Jeff Bezos", "Sundar Pichai", "Mark Zuckerberg"}, "Elon Musk"));
        questionPool.add(new Question("What is the largest planet in our solar system?", 
            new String[] {"Earth", "Mars", "Jupiter", "Saturn"}, "Jupiter"));
        questionPool.add(new Question("Who wrote 'Harry Potter'?", 
            new String[] {"J.K. Rowling", "J.R.R. Tolkien", "George R.R. Martin", "Stephen King"}, "J.K. Rowling"));
        questionPool.add(new Question("What is the smallest country in the world?", 
            new String[] {"Vatican City", "Monaco", "Nauru", "San Marino"}, "Vatican City"));
        questionPool.add(new Question("Which is the tallest mountain in the world?", 
            new String[] {"Mount Everest", "K2", "Kangchenjunga", "Makalu"}, "Mount Everest"));
        questionPool.add(new Question("Which country invented tea?", 
            new String[] {"India", "China", "Japan", "Sri Lanka"}, "China"));
        questionPool.add(new Question("What is the largest mammal?", 
            new String[] {"African Elephant", "Blue Whale", "Giraffe", "Shark"}, "Blue Whale"));
        questionPool.add(new Question("What is the boiling point of water?", 
            new String[] {"100°C", "90°C", "110°C", "120°C"}, "100°C"));
        questionPool.add(new Question("Who painted the Mona Lisa?", 
            new String[] {"Pablo Picasso", "Vincent van Gogh", "Leonardo da Vinci", "Claude Monet"}, "Leonardo da Vinci"));
        questionPool.add(new Question("Who invented the light bulb?", 
            new String[] {"Nikola Tesla", "Thomas Edison", "Alexander Graham Bell", "Michael Faraday"}, "Thomas Edison"));
        questionPool.add(new Question("What is the currency of Japan?", 
            new String[] {"Won", "Yuan", "Yen", "Ringgit"}, "Yen"));
        questionPool.add(new Question("Which continent is the Sahara Desert located in?", 
            new String[] {"Africa", "Asia", "Australia", "South America"}, "Africa"));
        questionPool.add(new Question("What is the fastest animal on land?", 
            new String[] {"Lion", "Cheetah", "Horse", "Elephant"}, "Cheetah"));
        questionPool.add(new Question("What is the longest river in the world?", 
            new String[] {"Amazon", "Nile", "Yangtze", "Mississippi"}, "Nile"));
        questionPool.add(new Question("Which is the largest ocean on Earth?", 
            new String[] {"Atlantic", "Indian", "Arctic", "Pacific"}, "Pacific"));
        questionPool.add(new Question("What is the national flower of India?", 
            new String[] {"Lotus", "Rose", "Tulip", "Sunflower"}, "Lotus"));
        questionPool.add(new Question("Who is known as the Father of the Nation in India?", 
            new String[] {"Jawaharlal Nehru", "Mahatma Gandhi", "Subhas Chandra Bose", "Sardar Patel"}, "Mahatma Gandhi"));
        questionPool.add(new Question("Which bird is the national bird of India?", 
            new String[] {"Peacock", "Eagle", "Sparrow", "Crow"}, "Peacock"));
        questionPool.add(new Question("What is the chemical symbol for water?", 
            new String[] {"O2", "H2O", "CO2", "NaCl"}, "H2O"));
    }

    // Method to ask a random question from the pool
    public static boolean askQuestion() {
        // Randomly select a question
        Random rand = new Random();
        int questionIndex;
        do {
            questionIndex = rand.nextInt(questionPool.size());
        } while (askedQuestions.contains(questionIndex));

        // Mark this question as asked
        askedQuestions.add(questionIndex);

        // Get the question and options
        Question q = questionPool.get(questionIndex);

        System.out.println("\nQuestion " + (correctAnswers + 1) + ": " + q.question);
        for (int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ". " + q.options[i]);
        }

        // Ask the user if they want to use a lifeline
        System.out.println("Do you want to use a lifeline? (1: 50:50, 2: Phone a Friend, 3: No)");
        int lifelineChoice = scanner.nextInt();

        switch (lifelineChoice) {
            case 1:
                if (!fiftyFiftyUsed) {
                    fiftyFifty(q);
                    fiftyFiftyUsed = true;
                } else {
                    System.out.println("You have already used the 50:50 lifeline.");
                }
                break;
            case 2:
                if (!phoneAFriendUsed) {
                    phoneAFriend(q);
                    phoneAFriendUsed = true;
                } else {
                    System.out.println("You have already used the Phone a Friend lifeline.");
                }
                break;
            case 3:
                break;
        }

        // Get the user's answer
        System.out.print("Your answer (1/2/3/4): ");
        int userAnswer = scanner.nextInt();

        // Check if the answer is correct
        if (q.options[userAnswer - 1].equalsIgnoreCase(q.correctAnswer)) {
            System.out.println("Correct! You've won " + (level * 10000) + "!");
            return true;
        } else {
            return false;
        }
    }

    // Method for 50:50 lifeline
    public static void fiftyFifty(Question q) {
        Random rand = new Random();
        int correctIndex = Arrays.asList(q.options).indexOf(q.correctAnswer);
        List<Integer> wrongIndexes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i != correctIndex) wrongIndexes.add(i);
        }
        
        // Randomly remove two incorrect options
        Collections.shuffle(wrongIndexes);
        int wrongOption1 = wrongIndexes.get(0);
        int wrongOption2 = wrongIndexes.get(1);

        System.out.println("50:50 Lifeline Activated! Remaining options:");
        for (int i = 0; i < 4; i++) {
            if (i == correctIndex || i == wrongOption1) {
                System.out.println((i + 1) + ". " + q.options[i]);
            }
        }
    }

    // Method for Phone a Friend lifeline
    public static void phoneAFriend(Question q) {
        Random rand = new Random();
        int correctIndex = Arrays.asList(q.options).indexOf(q.correctAnswer);
        String[] responses = {"I think it's ", "I'm not sure, but I believe it's ", "My guess is ", "I have no idea, but it's probably "};
        String friendResponse = responses[rand.nextInt(responses.length)];

        if (rand.nextBoolean()) {
            System.out.println("Phone a Friend: " + friendResponse + q.correctAnswer);
        } else {
            int wrongOptionIndex = rand.nextInt(4);
            while (wrongOptionIndex == correctIndex) {
                wrongOptionIndex = rand.nextInt(4);
            }
            System.out.println("Phone a Friend: " + friendResponse + q.options[wrongOptionIndex]);
        }
    }
}
