import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class MainMenu {
  public static final String ADD_BOOK_TEXT =  "Add a book";
  public static final String CHECKOUT_TEXT = "Checkout a book";
  public static final String RETURN_BOOK_TEXT = "Return a book";
  public static final String QUIT_TEXT = "Quit";


  public enum MenuOption {
    a(ADD_BOOK_TEXT),
    c(CHECKOUT_TEXT),
    r(RETURN_BOOK_TEXT),
    q(QUIT_TEXT);

    private String optionText;

    MenuOption(String optionText) {

      this.optionText = optionText;
    }

    public String toString() {

      return this.name() + ")" + this.optionText+ "\n";
    }
  }


  public String toString() {
      String output = "Main Menu\n\n";
      for(MenuOption option : MenuOption.values()) {
        output += option.toString();
    }
    return output;
  }

  public static List<Book> getInitialBooksFromLibrary() {
    List<Book> books = new ArrayList<Book>();
    books.add(new Book("Ham on Rye"));
    books.add(new Book("Grapes of Wrath"));
    return books;
  }

  public static void promptUntilQuit() {
    //welcome user
    System.out.println("Welcome to the Library! Please make a selection.");

    //list out options
    MainMenu menu = new MainMenu();

    List<Book> booksInLibrary = MainMenu.getInitialBooksFromLibrary();


    //prompt user to make selection
    MenuOption selectedOption = null;
    do {
      System.out.println(menu.toString());
      Scanner scanner = new Scanner(System.in);
      try {
        selectedOption = MenuOption.valueOf(scanner.next());

      } catch(IllegalArgumentException exception) {
        System.out.println("That isn't a valid option");
      }

      if(selectedOption == MenuOption.c) {
        CheckoutMenu checkoutMenu = new CheckoutMenu(booksInLibrary);
        checkoutMenu.promptUntilDone();
      }

    } while(selectedOption != MenuOption.q);
    System.out.println("Thanks for coming to the Library!");

    //check for bad input and re-prompt
    //loop until they quit

  }
}
