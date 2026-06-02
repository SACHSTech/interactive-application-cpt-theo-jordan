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
    int[] clicks = {0, 10, 40, 150, 400, 1000, 2500, 10000, Integer.MAX_VALUE};  // Clicks required to upgrade rarity (Index match up to the rarity #)
    String lastClick = "thedore";  // Blank string to be hidden
    float lastClickX = 0;
    float lastClickY = 0;

    /* SHOP VARIABLES */
    int shopX = 800;
    int shopY = 600;
    int multiplier = 1; 
    // int rebirth = 1; --> if lunch is found :)

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
            displayScore(score);
            displayLastClick(lastClick);
            drawShop();
            displayMultiplier(multiplier);
        }
    }

    public void displayInstructions() {
        fill(0);            // Black
        textSize(60); 
        textAlign(CENTER);  // Center text
        text("Click the Sticker to Increase Score. \n To Start, Click the Screen...", width / 2, height / 2);  // Display intructions
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
     * Displays current multiplier
     * @param clickMultiplier current multiplier
     */
    public void displayMultiplier(int clickMultiplier) {
        fill(0);          // Black 
        textSize(40);   
        textAlign(LEFT);  // Align text to left
        text("Multipler: x" + clickMultiplier, width - 310, height - 500);  // Display multiplier
    }

    /**
     * Displays amount of clicks counted last
     * @param lastClickText current # of clicks
     */
    public void displayLastClick(String lastClickText) {
        fill(0);          // Black 
        textSize(70);   
        textAlign(CENTER);  // Align text to center
        text(lastClickText, lastClickX, lastClickY);  // Display # of clicks 
    }

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
            score += (1 * multiplier);  // Add 1 score multiplied by current multiplier
            lastClick = "+ " + (1 * multiplier);  // Display previous amount of clicks
            lastClickX = random(circleX - 100, circleX + 100);  // Random x location
            lastClickY = random(circleY - 100, circleY + 100);  // Random y location
        } 

        if (shopX <= mouseX && mouseX <= (shopX + 350) && shopY <= mouseY && mouseY <= (shopY + 160)){
            if (score >= clicks[rarity]) {  // Check if current score is large enough to upgrade to next rarity
                score -= clicks[rarity];  // Deduct price of rarity from current score
                rarity++;  // Changes Rarity
                multiplier *= 2;  // Multiplies multiplier by 2
            }
        }

        // Remove Instructions
        if (rarity == 0) {
            rarity++;  // Set rarity to one once game starts
        }
    }

    // Draw Shop Button
    public void drawShop() {
        if (rarity >= 1 && rarity < 8){
            // BUTTON
            fill(25);  // Gray fill
            strokeWeight(5);     // Outline thickness
            rect(shopX, shopY, 350, 160); 
        
            // UPGRADE TEXT
            fill(255);          // Black 
            textSize(70);   
            textAlign(CENTER);  // Align text to center
            text("UPGRADE", (width - 225), (height - 100)); 

            // REQUIRED CLICKS TEXT
            fill(0);          // Black 
            textSize(30);   
            textAlign(CENTER);  // Align text to center
            text("Clicks Required: " + clicks[rarity], (width - 225), (height - 220));  // Display clicks required to upgrade
        }
    }

    // Base of Sticker
    public void drawSticker() {
        fill(255, 208, 67);  // Yellow fill
        stroke(5);
        strokeWeight(5);     // Outline thickness
        circle(circleX, circleY, circleRadius * 2); 
        drawEyes();
    }

    // Eyes of Sticker
    public void drawEyes() {
        fill(255);  // White
        noStroke();
        circle(circleX - 100, circleY - 100, 75);
        circle(circleX + 100, circleY - 100, 75);

        fill(0);  // Black
        circle(circleX - 100, circleY - 90, 30); 
        circle(circleX + 100, circleY - 90, 30); 

    }

    // Display Common Rarity
    public void common() {
        background(220, 252, 249);  // Light blue
        displayRarity(rarityText[0]);
        drawSticker();

        // Mouth
        fill(215, 107, 120);  // Light Red
        arc(circleX, circleY + 125, 300, 250, PI, TWO_PI);

    }

    // Display Uncommon Rarity
    public void uncommon() {
        background(225, 255, 221);  // Light green
        displayRarity(rarityText[1]);   
        drawSticker();

        // Mouth
        fill(215, 107, 120);  // Light Red
        arc(circleX, circleY + 100, 250, 125, PI, TWO_PI);
    }

    // Display Rare Rarity
    public void rare() {
        background(100, 255, 79);  // Green
        displayRarity(rarityText[2]);  
        drawSticker(); 

        // Mouth
        fill(215, 107, 120);  // Light Red
        rect(circleX - 150, circleY + 50, 300, 25);
    }

    // Display Super Rare Rarity
    public void superRare() {
        background(0, 150, 232);  // Blue
        displayRarity(rarityText[3]);   
        drawSticker();

        // Mouth
        fill(215, 107, 120);  // Light Red
        circle(circleX, circleY + 100, 80);
    }

    // Display Epic Rarity
    public void epic() {
        background(163, 0, 232);  // Dark purple
        displayRarity(rarityText[4]);
        drawSticker();

        // Mouth
        fill(215, 107, 120);  // Light Red
        circle(circleX, circleY + 60, 180);
    }

    // Display Mythic Rarity
    public void mythic() {
        background(230, 0, 0);  // Red
        displayRarity(rarityText[5]);  
        drawSticker();

        // Mouth
        fill(215, 107, 120);  // Light Red
        circle(circleX, circleY + 80, 250);
    }

    // Display Legendary Rarity
    public void legendary() {
        background(252, 255, 0);  // Yellow
        displayRarity(rarityText[6]);   
        drawSticker();

        // Mouth
        fill(215, 107, 120);  // Light Red
        arc(circleX, circleY + 50, 250, 125, 0, PI);
    }

    // Display Ultra Rarity
    public void ultra() {
        background(255, 0, 189);  // Light purple
        displayRarity(rarityText[7]);   
        drawSticker();

        // Mouth
        fill(215, 107, 120);  // Light Red
        arc(circleX, circleY + 10, 380, 350, 0, PI);
    }

}
