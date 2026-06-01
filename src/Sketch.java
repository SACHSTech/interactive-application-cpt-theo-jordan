import processing.core.PApplet;

/**
 * In this game, the player clicks the circle sticker in order to upgrade it to a new rarity (new background + text). 
 * Depending on the number of clicks (score), the sticker will change to the next rarity. However, if the 
 * player does not click the sticker and clicks the background instead, it will not count as a click. 
 * @author Theodore Lee and Jordan Lam 
 */
public class Sketch extends PApplet {

    /* RARITY VARIABLES */
    int rarity = 0;  // (1 = common, 2 = uncommon..., 8 = ultra)
    String[] rarityText = {"Common", "Uncommon", "Rare", "Super Rare", "Epic", "Mythic", "Legendary", "Ultra"};

    /* STICKER VARIABLES */ 
    int circleX = 600;
    int circleY = 400;
    int circleRadius = 250;

    /* CLICK VARIABLES */ 
    int score = 0;
    int[] clicks = {0, 10, 30, 60, 100, 150, 200, 300, Integer.MAX_VALUE};  // Clicks required to upgrade rarity (Index match up to the rarity #)
    String lastClick = "";  // Blank string to be hidden
    

    /* RUNNING SKETCH */ 
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
        background(220, 252, 249);  // Light cyan (common rarity)
    }

    /* ANIMATION */ 
    @Override
    public void draw() {

        if (rarity == 0) {  
            displayInstructions();
        }

        if (rarity != 0) {
            decideRarity(rarity);  // Background and text of current rarity
            drawSticker();
            displayScore(score);
        }
    }

    public void displayInstructions() {
        fill(0);            // Black
        textSize(60); 
        textAlign(CENTER);  // Center text
        text("Click the Sticker to Increase Score. \n To Start, Click the Screen...", 600, 400);  // Display intructions
    }

    /**
     * Displays text depending on rarity
     * @param rarityDisplayText text to display
    */
    public void displayRarity(String rarityDisplayText) {
        fill(0);                                    // Black
        textSize(100); 
        textAlign(CENTER);                          // Center text
        text(rarityDisplayText, (width / 2), 110);  // Display rarity
    }

    /**
     * Displays current score
     * @param clickScore current # of clicks
     */
    public void displayScore(int clickScore) {
        fill(0);          // Black 
        textSize(70);   
        textAlign(LEFT);  // Align text to left
        text("Clicks: " + clickScore, width - 1150, height - 50);  // Display # of clicks
    }

    /**
     * Displays amount of clicks counted last
     * @param clickScore current # of clicks
     */
    // public void displayLastClick(String ) {
    //     fill(0);          // Black 
    //     textSize(70);   
    //     textAlign(LEFT);  // Align text to left
    //     text("Clicks: " + clickScore, width - 1150, height - 50);  // Display # of clicks WHAT IS THIS 
    // }

    /**  
     * Determines which rarity method to run depending on the current rarity
     * @param rarityIndicator the current rarity number
    */
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

    /** 
     * Checks if sticker is clicked, adds to score, changes rarity when certain # of clicks reached
     */
    public void mousePressed() {
       float distanceMiddleCircle = dist(mouseX, mouseY, circleX, circleY);  // Distance between the circle and mouse

       if (distanceMiddleCircle < circleRadius && rarity != 0) {  // Checks if mouse is clicked inside sticker radius
            score++;
            if (score >= clicks[rarity]) {  // Check if current score is large enough to upgrade to next rarity
                rarity++;  // Changes Rarity
            }
       } 

       if (rarity == 0) {
            rarity++;  // Set rarity to one once game starts
       }
    }

    // Base of Sticker
    public void drawSticker() {
        fill(255, 208, 67);  // Yellow fill
        strokeWeight(5);     // Outline thickness
        circle(circleX, circleY, circleRadius * 2); 
    }

    // Display Common Rarity
    public void common() {
        background(220, 252, 249);  // Light blue
        displayRarity(rarityText[0]);      
    }

    // Display Uncommon Rarity
    public void uncommon() {
        background(225, 255, 221);  // Light green
        displayRarity(rarityText[1]);   
    }

    // Display Rare Rarity
    public void rare() {
        background(100, 255, 79);  // Green
        displayRarity(rarityText[2]);   
    }

    // Display Super Rare Rarity
    public void superRare() {
        background(0, 150, 232);  // Blue
        displayRarity(rarityText[3]);   
    }

    // Display Epic Rarity
    public void epic() {
        background(163, 0, 232);  // Dark purple
        displayRarity(rarityText[4]);   
    }

    // Display Mythic Rarity
    public void mythic() {
        background(230, 0, 0);  // Red
        displayRarity(rarityText[5]);   
    }

    // Display Legendary Rarity
    public void legendary() {
        background(252, 255, 0);  // Yellow
        displayRarity(rarityText[6]);   
    }

    // Display Ultra Rarity
    public void ultra() {
        background(255, 0, 189);  // Light purple
        displayRarity(rarityText[7]);   
    }

}
