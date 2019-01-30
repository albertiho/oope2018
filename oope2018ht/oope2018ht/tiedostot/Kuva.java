package oope2018ht.tiedostot;

import oope2018ht.apulaiset.*;

public class Kuva extends Tiedosto {
   // Attribuutit
   private int leveys;
   private int korkeus;
   
   // Rakentajat
   public Kuva(String n, int k, int l, int h){
      super(n, k);
      leveys(l);
      korkeus(h);
   }
   
   // Aksessorit
   @Setteri
   public void leveys(int l) throws IllegalArgumentException {
      if(l > 0){
         leveys = l;
      } else {
         throw new IllegalArgumentException("Error!");
      }
   }
   
   @Setteri
   public void korkeus(int h) throws  IllegalArgumentException {
      if(h > 0) {
         korkeus = h;
      } else {
         throw new IllegalArgumentException ("Error!");
      }
   }
   
   @Getteri
   public int leveys(){
      return leveys;
   }
   
   @Getteri
   public int korkeus(){
      return korkeus;
   }
   
   //toString metodin korvaaminen
   @Override
   public String toString(){
      return super.toString() + leveys + "x" + korkeus;
   }
}