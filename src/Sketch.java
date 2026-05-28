import processing.core.PApplet;

/**
 * REPLACE ME LATER
 * @author Theodore Lee and Jordan Lam
 */
public class Sketch extends PApplet {

    /* DECLARE GLOBAL VARIABLES */
    int rarity = 4; /* (1 = common, 2 = uncommon, 3 = rare, 4 = super rare,
    5 = epic, 6 = mythic, 7 = legendary, 8 = ultra) */
    int circleX = 600;
    int circleY = 400;
    int circleRadius = 250;
    String[] rarityText = {"Common", "Uncommon", "Rare", "Super Rare", "Epic", "Mythic", "Legendary", "Ultra"};

    public static void main(String[] args) {
        PApplet.main("Sketch");
    }

    /* CANVAS */
    @Override
    public void settings() {
        size(1200, 800); 
    }

    /* SETUP */ 
    @Override
    public void setup() {
        background(220, 252, 249);  // Light Cyan
    }

    /* ANIMATION */ 
    @Override
    public void draw() {
        decideRarity(rarity);  // Draw sticker depending on rarity
        drawSticker();
    }

    public void decideRarity(int rarityIndicator) {
        if (rarityIndicator == 1) {
            common();
        } else if (rarityIndicator == 2) {
            uncommon();
        } else if (rarityIndicator == 3) {
            rare();
        } else if (rarityIndicator == 4) {
            superRare();
        } else if (rarityIndicator == 5) {
            epic();
        } else if (rarityIndicator == 6) {
            mythic();
        } else if (rarityIndicator == 7) {
            legendary();
        } else if (rarityIndicator == 8) {
            ultra();
        }
    }

    public void displayRarity(String rarityDisplayText) {
        fill(0);                            // Black fill
        textSize(100);                      // Text size
        textAlign(CENTER);                  // Make text appear in center
        text(rarityDisplayText, (width / 2), 110);       // Display instructions for ball control
    }

    public void common() {
        background(220, 252, 249);  // Light blue
        displayRarity(rarityText[0]);      
    }

    public void uncommon() {
        background(225, 255, 221);  // Light green
        displayRarity(rarityText[1]);   
    }

    public void rare() {
        background(100, 255, 79);  // Green
        displayRarity(rarityText[2]);   
    }

    public void superRare() {
        background(0, 150, 232);  // Blue
        displayRarity(rarityText[3]);   
    }

    public void epic() {
        background(163, 0, 232);  // Dark purple
        displayRarity(rarityText[4]);   
    }

    public void mythic() {
        background(230, 0, 0);  // Red
        displayRarity(rarityText[5]);   
    }

    public void legendary() {
        background(252, 255, 0);  // Yellow
        displayRarity(rarityText[6]);   
    }

    public void ultra() {
        background(255, 0, 189);  // Light purple
        displayRarity(rarityText[7]);   
    }

    public void drawSticker(){
        fill(255, 208, 67);         // Yellow fill
        strokeWeight(5);            // Outline thickness
        circle(circleX, circleY, circleRadius * 2); 
    }

}
