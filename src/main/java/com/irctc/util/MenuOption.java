package com.irctc.util;

public enum MenuOption {
    SIGN_UP(1, "Sign Up"),
    LOGIN(2, "Login"),
    FETCH_BOOKINGS(3, "Fetch Bookings"),
    SEARCH_TRAINS(4, "Search Trains"),
    EXIT(5, "Exit"),
    BOOK_TICKET(6, "Book Ticket");

    private final int optionId;
    private final String label;

    MenuOption(int optionId, String label) {
        this.optionId = optionId;
        this.label = label;
    }

    public int getOptionId() {
        return optionId;
    }

    public String getLabel() {
        return label;
    }

    public static MenuOption fromId(int id) {
        for (MenuOption option : values()) {
            if (option.getOptionId() == id) {
                return option;
            }
        }
        return null; // Handle invalid option gracefully
    }
}
