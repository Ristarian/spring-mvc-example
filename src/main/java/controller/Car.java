package controller;

public class Car {
   private int id;
   private String mark;
   private int power;
   public Car() {
   }

   public Car(int id, String mark, int power) {
       this.id = id;
       this.mark = mark;
       this.power = power;
   }

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public String getMark() {
       return mark;
   }

   public void setMark(String mark) {
       this.mark = mark;
   }

   public int getPower() {
       return power;
   }

   public void setPower(int power) {
       this.power = power;
   }
}
