package day03;

//public class Monster extends Object {
public class Monster implements Damagable {

   private String name;
   private int hitPoints;

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public int getHitPoints() { return hitPoints; }
   public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }

   public void damage(int damage) {
      this.hitPoints -= damage;
   }
   public int getDamage() { return this.hitPoints; }

   @Override
   public String toString() {
      return "Monster{name=%s, hitPoints=%d}".formatted(name, hitPoints);
   }

}
