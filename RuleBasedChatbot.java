import java.util.Scanner;

public class RuleBasedChatbot {

    public static String getResponse(String userInput) {
        String input = userInput.toLowerCase();
        if (input.contains("hello") || input.contains("hi")) {
            return "Hello! How can I help you today?";
        } else if (input.contains("how are you")) {
            return "I'm just a bot, but I'm doing great! How about you?";
        } else if (input.contains("your name")) {
            return "I'm a simple chatbot created with rule-based logic.";
        } else if (input.contains("bye")) {
            return "Goodbye! Have a great day!";
        } else {
            return "I'm not sure how to respond to that. Can you try rephrasing?";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chatbot: Hi! Type 'bye' to exit.");
        while (true) {
            System.out.print("You: ");
            String userText = scanner.nextLine();
            String response = getResponse(userText);
            System.out.println("Chatbot: " + response);
            if (userText.toLowerCase().contains("bye")) {
                break;
            }
        }
        scanner.close();
    }
}
