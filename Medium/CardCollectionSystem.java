import java.util.*;

class CardCollection {
    private final Map<String, List<String>> cardMap; // HashMap to store cards by symbol

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    // Add a card to the collection
    public void addCard(String symbol, String cardName) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(cardName);
    }

    // Get all cards of a specific symbol
    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    // Display all cards in the collection
    public void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards in the collection.");
            return;
        }

        System.out.println("\nCard Collection:");
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

// Main Class
public class CardCollectionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection cardCollection = new CardCollection();

        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Card Symbol (e.g., Hearts, Spades): ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter Card Name (e.g., Ace, King): ");
                    String cardName = scanner.nextLine();
                    cardCollection.addCard(symbol, cardName);
                    System.out.println("Card added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Symbol to search (e.g., Diamonds, Clubs): ");
                    String searchSymbol = scanner.nextLine();
                    List<String> cards = cardCollection.getCardsBySymbol(searchSymbol);
                    if (cards.isEmpty()) {
                        System.out.println("No cards found for " + searchSymbol);
                    } else {
                        System.out.println("Cards in " + searchSymbol + ": " + cards);
                    }
                    break;

                case 3:
                    cardCollection.displayAllCards();
                    break;

                case 4:
                    System.out.println("Exiting..."); 
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
    }
}

