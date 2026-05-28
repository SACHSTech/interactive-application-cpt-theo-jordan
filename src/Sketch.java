import processing.core.PApplet;

/**
 * REPLACE ME LATER
 * @author Theodore Lee and Jordan Lam
 */
public class Sketch extends PApplet {

    /* DECLARE GLOBAL VARIABLES */
    int rarity = 3; /* (1 = common, 2 = uncommon, 3 = rare, 4 = super rare,
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
    }

    public void decideRarity(int rarityIndicator) {
        if (rarityIndicator == 1) {
            common();
        } else if (rarityIndicator == 2) {
            uncommon();
        } else if (rarityIndicator == 3) {
            rare();
        }
    }

    public void common() {
        background(220, 252, 249);  // Re-draw background to erase previous frame
        fill(255, 208, 67);         // Yellow fill
        strokeWeight(5);            // Outline thickness
        circle(circleX, circleY, circleRadius * 2);      
    }

    public void uncommon() {
        background(225, 255, 221);  // Re-draw background to erase previous frame
        fill(255, 208, 67);         // Yellow fill
        strokeWeight(5);            // Outline thickness
        circle(circleX, circleY, circleRadius * 2);      
    }

    public void rare() {
        background(100, 255, 79);  // Re-draw background to erase previous frame
        fill(255, 208, 67);         // Yellow fill
        strokeWeight(5);            // Outline thickness
        circle(circleX, circleY, circleRadius * 2);      
    }

}
