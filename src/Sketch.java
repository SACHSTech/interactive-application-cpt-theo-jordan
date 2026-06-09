import processing.core.PApplet;
import processing.core.PFont;

/**
 * In this immersive game, the player clicks the circle sticker in order to increase the number of clicks. When the player
 * reaches a certain number of clicks they can upgrade the sticker (change background + increase multiplier + change rarity).
 * The sticker can be upgraded all the way until ultra, which will let the player rebirth and reset all their clicks, but 
 * in return give a slight multiplier boost. 
 * @author Theodore Lee and Jordan Lam 
 */

public class Sketch extends PApplet {
    /* CUSTOM FONT VARIABLE */ 
    PFont brawlFont;  

    /* RARITY VARIABLES */
    int rarity = 0;  // (1 = common, 2 = uncommon..., 8 = ultra)
    String[] rarityText = {"Common", "Uncommon", "Rare", "Super Rare", "Epic", "Mythic", "Legendary", "Ultra"};
    // Background colours for rarities
    int[][] bgColours = {
        {220, 252, 249},
        {225, 255, 221},
        {100, 255, 79},
        {0, 150, 232},
        {163, 0, 232},
        {230, 0, 0},
        {252, 255, 0},
        {255, 0, 189}
    };

    /* STICKER VARIABLES */ 
    int circleX = 600;
    int circleY = 400;
    int circleRadius = 250;
    int[] mouthShape = {1, 1, 2, 3, 3, 3, 1, 1};  // (1 = arc, 2 = rectangle, 3 = circle)
    // Parameters for mouth shapes
    float[][] mouthParameters = {
        {circleX, circleY + 125, 300, 250, PI, TWO_PI},
        {circleX, circleY + 100, 250, 125, PI, TWO_PI},
        {circleX - 150, circleY + 50, 300, 25},
        {circleX, circleY + 100, 80},
        {circleX, circleY + 60, 180},
        {circleX, circleY + 80, 250},
        {circleX, circleY + 50, 250, 125, 0, PI},
        {circleX, circleY + 10, 380, 350, 0, PI}
    };  

    /* CLICK VARIABLES */ 
    int score = 0;
    int[] clicks = {0, 10, 40, 150, 400, 1000, 2500, 10000, 25000, Integer.MAX_VALUE};  // Clicks required to upgrade rarity (Index match up to the rarity #)
    String lastClick = "";  // Blank string to be hidden
    float lastClickX = 0;
    float lastClickY = 0;

    /* SHOP VARIABLES */
    int shopX = 800;
    int shopY = 600;
    int multiplier = 1; 
    int rebirthMultiplier = 1; 

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
        background(220, 252, 249);  // Light cyan 

        brawlFont = createFont("LilitaOne-Regular.ttf", 32);  // Calls on custom font
        textFont(brawlFont);  // Makes all text custom font
    }

    /* ANIMATION */ 
    @Override
    public void draw() {
        if (rarity == 0) {  
            displayInstructions();
        }

        if (rarity != 0) {
            rarityLogic(rarity);  // Draws sticker, background, text, mouth shape for each rarity
            displayScore(score); 
            displayLastClick(lastClick);
            drawShop();
            displayMultiplier(1 * multiplier * rebirthMultiplier);
        }
    }

    // Creates the Home Screen when Program Starts
    public void displayInstructions() {
        fill(0);  // Black
        textSize(60); 
        textAlign(CENTER);
        text("Click the Sticker to Increase Score. \n To Start, Click the Screen...", width / 2, height / 2);  // Instructions
    }

    /**
     * Displays text depending on rarity
     * @param rarityDisplayText text to display
    */
    public void displayRarity(String rarityDisplayText) {
        fill(0);                                    // Black
        textSize(100); 
        textAlign(CENTER);
        text(rarityDisplayText, (width / 2), 110);  // Rarity
    }

    /**  
     * Draws sticker, background, and mouth depending on current rarity
     * @param rarityNumber the current rarity number
    */
    public void rarityLogic(int rarityNumber) {
        background(bgColours[rarityNumber - 1][0], bgColours[rarityNumber - 1][1], bgColours[rarityNumber - 1][2]);
        displayRarity(rarityText[rarityNumber - 1]);  // Display rarity text
        drawSticker();

        // Mouth
        fill(215, 107, 120);  // Light red

        // Mouth shape (1 = arc, 2 = rectangle, 3 = circle)
        switch (mouthShape[rarityNumber - 1]) {
            case 1:
                arc(mouthParameters[rarityNumber - 1][0], mouthParameters[rarityNumber - 1][1], 
                    mouthParameters[rarityNumber - 1][2], mouthParameters[rarityNumber - 1][3], 
                    mouthParameters[rarityNumber - 1][4], mouthParameters[rarityNumber - 1][5]);
                break;

            case 2:
                rect(mouthParameters[rarityNumber - 1][0], mouthParameters[rarityNumber - 1][1], 
                    mouthParameters[rarityNumber - 1][2], mouthParameters[rarityNumber - 1][3]);
                break;

            case 3:
                circle(mouthParameters[rarityNumber - 1][0], mouthParameters[rarityNumber - 1][1], 
                    mouthParameters[rarityNumber - 1][2]);
                break;
        }
    }

    // Change Sticker to Next Rarity
    public void upgrade() {
        score -= clicks[rarity];  // Deduct price of rarity from current score
        rarity++;                 // Changes rarity
        multiplier *= 2;          // Multiplies multiplier by 2
    }

    /**
     * Displays amount of clicks counted last
     * @param lastClickText current # of clicks
     */
    public void displayLastClick(String lastClickText) {
        fill(0);            // Black 
        textSize(70);   
        textAlign(CENTER); 
        text(lastClickText, lastClickX, lastClickY);  // Display # of clicks on sticker
    }

    /**
     * Displays current score
     * @param clickScore current # of clicks
     */
    public void displayScore(int clickScore) {
        fill(0);  // Black 
        textSize(70);   
        textAlign(LEFT); 
        text("Clicks: " + clickScore, width - 1150, height - 50);  // Display total # of clicks
    }

    /**
     * Displays current multiplier
     * @param clickMultiplier current multiplier
     */
    public void displayMultiplier(int clickMultiplier) {
        fill(0);  // Black 
        textSize(35);   
        textAlign(LEFT); 
        text("Multiplier: x" + clickMultiplier, width - 310, height - 500);  // Multiplier text
    }

    /** 
     * Checks if sticker is clicked, adds to score, changes rarity when certain # of clicks reached
     */
    public void mousePressed() {
        float distanceMiddleCircle = dist(mouseX, mouseY, circleX, circleY);  // Distance between circle and mouse

        if (distanceMiddleCircle < circleRadius && rarity != 0) {  // Checks if mouse is clicked inside sticker radius
            addClick();
        } 

        if (shopX <= mouseX && mouseX <= (shopX + 350) && shopY <= mouseY && mouseY <= (shopY + 160)) {  // Checks if mouse is clicked inside shop button
            if (score >= clicks[rarity] && rarity < 8 && rarity > 0) {  // Check if score is large enough to upgrade to next rarity if rarity is 1 to 7
                upgrade();
            } else if (rarity == 8 && score >= clicks[rarity]) {  // Check if score is large enough to rebirth if rarity is 8
                rebirth();
            }
        }

        // Remove Instructions
        if (rarity == 0) {
            rarity++;  // Set rarity to common once game starts
        }
    }

    // Adds clicks to score based on multiplier and displays the previous click
    public void addClick() {
        score += (1 * multiplier * rebirthMultiplier);                // Add 1 score multiplied by current multiplier
        lastClick = "+ " + (1 * multiplier * rebirthMultiplier);      // Display previous amount of clicks
        lastClickX = random(circleX - 100, circleX + 100);  // Random x location
        lastClickY = random(circleY - 100, circleY + 100);  // Random y location
    }

    // Rebirth
    public void rebirth() {
        score = 0;  // Resets game
        rarity = 1;
        multiplier = 1;
        rebirthMultiplier += 1;
        clicks[8] += 5000;  // Adds 5000 to rebirth requirement each rebirth
        lastClick = "";     // Hide last click text 
    }

    // Draw Shop Button
    public void drawShop() {
        // Button
        fill(25);         // Gray
        strokeWeight(5);  // Outline thickness
        rect(shopX, shopY, 350, 160); 

        // Required Clicks Text
        fill(0);  // Black 
        textSize(30);   
        textAlign(CENTER); 
        text("Clicks Required: " + clicks[rarity], (width - 225), (height - 220));  // Display clicks required text

        // Upgrade/Rebirth Text
        fill(255);  // Black 
        textSize(70);   
        textAlign(CENTER);

        if (rarity >= 1 && rarity < 8) {
            text("UPGRADE", (width - 225), (height - 100)); 
        } else {
            text("REBIRTH", (width - 225), (height - 100));
        } 
    }

    // Base of Sticker
    public void drawSticker() {
        for (int i = 15; i >= 0; i--) {  // Loop 15 times
            if (i == 0) {                // Final loop
                fill(255, 208, 67);      // Yellow
                stroke(0);
                strokeWeight(5);
            } else {
                fill(30);  // Gray shadow 
                noStroke();
            }
            circle(circleX + i, circleY + i, circleRadius * 2);  // Draw circle translated bottom right
        }
        drawEyes();
    }

    // Eyes of Sticker
    public void drawEyes() {
        fill(255);  // White
        noStroke();
        circle(circleX - 100, circleY - 100, 75);
        circle(circleX + 100, circleY - 100, 75);

        fill(0);   // Black
        circle(circleX - 100, circleY - 90, 30); 
        circle(circleX + 100, circleY - 90, 30); 
    }
}