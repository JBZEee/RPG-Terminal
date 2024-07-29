public class player extends character{

    //additional player stats
    int gold, restsLeft, pots;
    //integers to store number of upgrades/skills in each path
    public int numAtkUpgrades, numDefUpgrades;
    //Arrays to store skill names
    public String[] atkUpgrades = {"Strength", "Power", "Might", "Godlike Strength"};
    public String[] defUpgrades = {"Heavy Bones", "Stone Skin", "Scale Armor", "Holy Aura"};
    //Player specific constructor
    public player(String name) {
        //calling constructor of superclass
        super(name, 100, 0);
        
        //setting number of upgrades to 0
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;
        //set additional stats
        this.gold = 5;
        this.restsLeft = 1;
        this.pots = 0;
        //let the player choose a trait when creating a new character
        chooseTrait();
        }

    //Player specific methods
    @Override
    public int attack() {
        
        return (int)(Math.random()*(xp/4 + numAtkUpgrades*3 + 3) + xp/10 + numAtkUpgrades*2 + numDefUpgrades + 1 );
    }

    @Override
    public int defend() {
        // TODO Auto-generated method stub
        return (int)(Math.random()*(xp/4 + numDefUpgrades*3 + 3) + xp/10 + numDefUpgrades*2 + numAtkUpgrades + 1 );
    }

    // let the player choose a trait of either skill path
    public void chooseTrait(){
        gameLogic.clearConsole();
        gameLogic.printHeading("Choose a trait");
        System.out.println("(1)" + atkUpgrades[numAtkUpgrades]);
        System.out.println("(2)" + defUpgrades[numDefUpgrades]);
        //get player choice
        int input = gameLogic.readInt("->", 2);
        gameLogic.clearConsole();
        //deal with both choices
        if(input ==1){
            gameLogic.printHeading("You have choosen: " + atkUpgrades[numAtkUpgrades] + "!");
            numAtkUpgrades++;
        }else{
            gameLogic.printHeading("You have chosen: " + defUpgrades[numDefUpgrades] + "!");
            numDefUpgrades++;
        }
        gameLogic.anythingToContinue();
    }

}

