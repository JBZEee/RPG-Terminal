import java.util.Scanner;
public class gameLogic {
    static Scanner sc = new Scanner(System.in);
    
    static player Player;
    
    public static boolean isRunning;
    //encounters
    public static String[] encounters = {"Battle", "Battle", "Battle", "Rest", "Rest"};

    //enemy names
    public static String[] enemyNames = {"Goblin", "Ogre", "Skeletons", "Ghostmaster", "Dullahan"};
    //story elements
    public static int place= 0 , act=1 ;
    public static String[] places = {"Blizzard Mountains", "Haunted Lands", "The Final Showdown", "THE REAL EMPEROR"};
    
    //method to get user input from console
    public static int readInt(String prompt, int userChoices){
        int input;
        do{
            System.out.println(prompt);
            try{
                input = Integer.parseInt(sc.next());
            }catch(Exception e){
                input = -1;
                System.out.println("please enter a number");
            }
        }while(input<1 || input>userChoices);
        return input;
    }

    //method to simulate clearing out the console
    public static void clearConsole(){
        for(int i = 0; i<100;i++){
            System.out.println();
        }
    }

    //method to print a seperator with length n
    public static void printSeperator(int n){
        for(int i=0;i<n;i++){
            System.out.print("-");
        }
        System.out.println();
    }

    //method to print a heading
    public static void printHeading(String title){
        printSeperator(30);
        System.out.println(title);
        printSeperator(30);
    }

    //method to stop the game until the user enters anything
    public static void anythingToContinue(){
        System.out.println("\n Enter anything to continue....");
        sc.next();
    }
    //methpd to start the game
    public static void startGame(){
        boolean nameSet = false;
        String name;
        //print title screen
        clearConsole();
        printSeperator(40);
        printSeperator(30);
        System.out.println("AGE OF THE EVIL EMPEROR");
        System.out.println("Text RPG by Jatin Bansal for learning java");
        printSeperator(30);
        printSeperator(40);
        anythingToContinue();

        //getting the player name
        do {
            clearConsole();
            printHeading("What's Your name?");
            name = sc.next();
            //asking the player if he wants to correct something
            clearConsole();
            printHeading("Your name is: " + name + ".\n Is that correct");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int input = readInt("->", 2);
            if(input == 1)
            nameSet = true;
        } while (!nameSet);

        //print story intro
        story.printIntro();
        
        //create a new player object with the name
        Player = new player(name);
        
        //Print act 1
        story.printFirstActIntro();
        
        
        //setting the isRunning loop to true, so the game loop can continue
        isRunning = true;
        
        
        //start the main game loop
        gameLoop();
    }
    
    //method that changes the game's value based on player xp
    public static void checkAct(){
        //change act based on player xp
        if(Player.xp >=5 && act ==1){
            //increment act and place
            act=2;
            place=1;
            //story
            story.printFirstActOutro();
            //let the player level up
            Player.chooseTrait();
            //story
            story.printSecondActIntro();
            //assign new values to enemy
            enemyNames[0] = "Evil Mercenary";
            enemyNames[1] = "Goblins";
            enemyNames[3] = "Evil Henchmen";
            enemyNames[4] = "Ogre";
            //assign new values to encounters
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";
            //fully heal the player
            Player.hp = Player.maxHp;
        }else if(Player.xp >=10 && act==2){
            //increment act and place
            act=3;
            place=2;
            //story
            story.printSecondActOutro();
            //let the player level up
            Player.chooseTrait();
            //story
            story.printThirdActIntro();
            //assign new values to enemy
            enemyNames[0] = "Evil Mercenary";
            enemyNames[1] = "Skeletons";
            enemyNames[3] = "Evil Henchmen";
            enemyNames[4] = "Dullahan";
            //assign new values to encounters
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";
            //fully heal the player
            Player.hp = Player.maxHp;
        }else if(Player.xp >=15 && act ==3){
            //increment act and place
            act=4;
            place=3;
            //story
            story.printThirdActOutro();
            //let the player level up
            Player.chooseTrait();
            //story
            story.printFourthActIntro();
            //assign new values to enemy
            enemyNames[0] = "Evil Mercenary";
            enemyNames[1] = "Evil Henchmen";
            enemyNames[3] = "Evil General";
            enemyNames[4] = "Evil Hunter ";
            //assign new values to encounters
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";
            //fully heal the player
            Player.hp = Player.maxHp;
            //Calling Final Battle
            finalBattle();
        }
    }

    //method to calculate random encounter
    public static void randomEncounter(){
        //random number between 0 and length of encounters array
        int encounter = (int)(Math.random()*encounters.length);
        if(encounters[encounter].equals("Battle")){
            randomBattle();
        }else if (encounters[encounter].equals("Rest")){
            takeRest();
        }else if(encounters[encounter].equals("Shop")){
            shop();
        }
    }

    // method to continue the journey
    public static void continueJourney(){
        //check if act must be increasing
        checkAct();
        //check if game isn't in last act
        if(act !=4){
            randomEncounter();
        }
    }

    //printing all the important information about the player character
    public static void charaterInfo(){
        clearConsole();
        printHeading("CHARACTER INFO");
        System.out.println(Player.name + "\tHP" + Player.hp + "/" + Player.maxHp );
        printSeperator(20);
        //player xp and gold
        System.out.println("XP: " + Player.xp + "\tGold: " + Player.gold);
        printSeperator(20);
        //Number of pots
        System.out.println("Number of Potions: " + Player.pots);
        printSeperator(20);

        //print the chosen traits
        if(Player.numAtkUpgrades > 0){
            System.out.println("Offesnsive trait : " + Player.atkUpgrades[Player.numAtkUpgrades - 1]);
            printSeperator(20);
        }
        if(Player.numDefUpgrades > 0){
            System.out.println("Defensive trait :" + Player.defUpgrades[Player.numDefUpgrades - 1]);
        }
        anythingToContinue();
    }

    //shopping/encountering a travelling trader
    public static void shop(){
        clearConsole();
        printHeading("You met a mysterious stranger.\nHe offers you something");
        int price = (int)(Math.random()*(10 + Player.pots*3) + 10 + Player.pots);
        System.out.println("Magic Potion: " + price + " gold.");
        printSeperator(20);
        //ask the player to buy one
        System.out.println("Do you want to but one?\n(1)Yes!\n(2)No thanks.");
        int input = readInt("->", 2);
        //check if the player wants to buy
        if(input==1){
            //Player wants to buy
            clearConsole();
            //check if player has enough gold.
            if(Player.gold>=price){
                printHeading("You bought the magic potion for " + price + "gold.");
                Player.pots++;
                Player.gold -=price;
            }else{
                printHeading("You don't have enough gold to buy it....");
                anythingToContinue();
            }
        }
    }
    //taking a rest
    public static void takeRest(){
        clearConsole();
        if(Player.restsLeft>0){
            printHeading("Do you want to take a rest? You have " +Player.restsLeft +"rest(s) left.\n(1)Yes!\n(2)No, not now");
            int input = readInt("->", 2);
            if(input==1){
                //Player wants to rest
                clearConsole();
                if (Player.hp<Player.maxHp) {
                    int hpRestored = (int)(Math.random()*(Player.xp/4 + 1) + 10);
                    Player.hp +=hpRestored;
                    if(Player.hp>Player.maxHp){
                        Player.hp = Player.maxHp;
                        System.out.println("You took rest and restored " + hpRestored + "health.");
                        System.out.println("You now have " + Player.hp + "/" + Player.maxHp + "health.");
                        Player.restsLeft--;
                    }
                }else{
                    System.out.println("You are at full health. You don't need to rest now!");
                }
                anythingToContinue();
            }
        }
    }
    //creating a random battle
    public static void randomBattle(){
        clearConsole();
        printHeading("You encountered a evil minion. You'll need to fight and defeat it or you can try to run away to proceed!!");
        anythingToContinue();
        //creating random new enemy
        battle(new enemy(enemyNames[(int)(Math.random()*enemyNames.length)],Player.xp));
    }
    
    //main Battle method
    public static void battle(enemy enemy){
        //main battle loop
        while (true) {
            clearConsole();
            printHeading(enemy.name + "\n HP: " + enemy.hp + "/" + enemy.maxHp);
            printHeading(Player.name + "\nHP: " + Player.hp + "/" + Player.maxHp);
            System.out.println("Choose an action: ");
            printSeperator(20);
            System.out.println("(1) Fight\n(2) Use an potion\n(3) Run away");
            int input = readInt("->", 3);
            //react accordingly to the user input
            if(input==1){
                //Fight
                //calculate damage and damageTook (damage enemy deals to Player)
                int dmg = Player.attack() - enemy.defend();
                int dmgTook = enemy.attack() - Player.defend();
                //check that damage and damageTook is not negative
                if(dmgTook<0){
                    //add some dmg if player defends very well
                    dmg -= dmgTook/2;
                    dmgTook = 0;
                }
                if(dmg<0){
                    dmg=0;
                }
                Player.hp -= dmgTook;
                enemy.hp -= dmg;
                //print the info of this battle
                clearConsole();
                printHeading("BATTLE");
                System.out.println("You dealt " + dmg + "damage to the " + enemy.name + ".");
                printSeperator(10);
                System.out.println("The " + enemy.name + "dealt" + dmgTook + "damage to you.");
                anythingToContinue();
                //check if the player is alive
                if (Player.hp < 0) {
                    playerDied();
                    break;
                }else if(enemy.hp <= 0){
                    //tell the player he/she won
                    clearConsole();
                    printHeading("You defeated the " + enemy.name + "!");
                    //increase player xp
                    Player.xp += enemy.xp;
                    System.out.println("You gained "+ enemy.xp + "XP!");
                    //random drops
                    boolean addRest = (Math.random()*5 + 1 <= 2.25);
                    int goldEarned = (int)(Math.random()*enemy.xp + 1) ;
                    if(addRest){
                        Player.restsLeft++;
                        System.out.println("You earned the chance to get an additional rest");
                    }
                    if(goldEarned>0){
                        Player.gold +=goldEarned;
                        System.out.println("You collect " + goldEarned + "gold from the " + enemy.name + "'s corpse!!");
                    }
                    anythingToContinue();
                    break;
                }
            }else if (input==2) {
                //use potion
                clearConsole();
                if (Player.pots > 0 && Player.hp < Player.maxHp) {
                    //Player can take a potion
                    //make sure the player wants to use a potion
                    System.out.println("Do you want to drink a potion? ( " + Player.pots + " left.)");
                    System.out.println("(1) Yes\n(2) No, maybe later");
                    input = readInt("->", 2);
                    if (input==1) {
                        //player actually took it
                        Player.hp = Player.maxHp;
                        clearConsole();
                        printHeading("You drank a magic potion. It restored your health to " + Player.maxHp);
                        anythingToContinue();
                    }
                }else{
                    //Player cannot take a potion
                    printHeading("You don't have any potion or you are at full health.");
                    anythingToContinue();
                }
            }else if (input ==3) {
                //Run Away
                clearConsole();
                //check if player isn't in act 4
                if(act != 4){
                    //chance of escape 35%
                if(Math.random()*10 + 1 <=3.5){
                    printHeading("You ran away from " + enemy.name +"!");
                    anythingToContinue();
                    break;
                } else{
                    printHeading("You didn't manage to run away");
                    //punish the player
                    int dmgTook = enemy.attack();
                    System.out.println("In your hurry you took " + dmgTook + " damage! ");
                    Player.hp -= dmgTook;
                    anythingToContinue();
                    //check if player is alive
                    if(Player.hp < 0){
                        playerDied();
                    }
                }
                }else{
                    printHeading("YOU CANNOT ESCAPE THE EVIL EMPEROR!!!");
                    anythingToContinue();

                }
            }
        }
    }
    //printing the menu
    public static void printMenu(){
        clearConsole();
        printHeading(places[place]);
        System.out.println("Choose an action: ");
        printSeperator(20);
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Character Info");
        System.out.println("(3) Exit Game");
    }
    //the final (last) battle
    public static void finalBattle(){
        battle(new enemy("THE EVIL EMPEROR", 200));
        //printing the proper end of the game
        if(Player.hp>0){
        story.printFourthActOutro();
        isRunning =false;}
    }
    //method when the player is dead
    public static void playerDied(){
        clearConsole();
        printHeading("YOU DIED!!!");
        printHeading("You earned " + Player.xp + "XP on your journey.Try to earn more next time.");
        System.out.println("Thank you for playing my game. I hope you enjoyed it.");
        isRunning=false;
    }
    
    public static void gameLoop(){
        while(isRunning){
            printMenu();
            int input = readInt("->", 3);
            if(input==1){
                continueJourney();
            }
            else if(input ==2 ){
                charaterInfo();
            }else{
                isRunning = false;
            }
        }
    }
}
