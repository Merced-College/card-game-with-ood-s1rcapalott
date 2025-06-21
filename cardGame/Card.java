package cardGame;

public class Card {
    
    private String cardsuit;
    private String cardName;
    private int cardValue;
    private String cardPicture;


    public Card(String cardsuit, String cardName, int cardValue, String cardPicture) {
        this.cardsuit = cardsuit;
        this.cardName = cardName;
        this.cardValue = cardValue;
        this.cardPicture = cardPicture;
    }

    public String getCardsuit() {
        return cardsuit;
    }
    public void setCardsuit(String cardsuit) {
        this.cardsuit = cardsuit;
    }
    public String getCardName() {
        return cardName;
    }
    public String getCardPicture() {
        return cardPicture;
    }
    public int getCardValue() {
        return cardValue;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return cardValue == card.cardValue &&
               cardsuit.equals(card.cardsuit) &&
               cardName.equals(card.cardName) &&
               cardPicture.equals(card.cardPicture);
    }
    @Override
    public String toString() {
        return "Card{" +
                "cardsuit='" + cardsuit + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardValue=" + cardValue +
                ", cardPicture='" + cardPicture + '\'' +
                '}';
    }

    
}
