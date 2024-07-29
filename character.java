public abstract class character {
    //variables of characters
    public String name;
    public int maxHp, hp, xp;
    
    //constructors for character
    public character(String name, int maxHp, int xp){
        this.name = name;
        this.maxHp = maxHp;
        this.xp = xp;
        this.hp = maxHp;
    }
    //methods every characteer has
    public abstract int attack();
    public abstract int defend();

}
