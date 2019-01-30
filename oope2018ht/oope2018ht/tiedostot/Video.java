package oope2018ht.tiedostot;

import oope2018ht.apulaiset.*;


public class Video extends Tiedosto {
   // Attribuutit
   private double pituus;
   
   //Rakentajat
   public Video(String n, int k, double p){
      super(n, k);
      pituus(p);
   }
   
   //Aksessorit
   @Setteri
   public void pituus(double p) throws IllegalArgumentException {
      if (p > 0){
         pituus = p;
      } else {
         throw new IllegalArgumentException("Error!");
      }
   }
   
   @Getteri
   public double pituus(){
      return pituus;
   }
   
   //toString metodin korvaaminen
   @Override
   public String toString(){
      return super.toString() + pituus + " s";
   }
}