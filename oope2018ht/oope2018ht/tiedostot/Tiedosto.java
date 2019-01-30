package oope2018ht.tiedostot;

import oope2018ht.apulaiset.*;

public abstract class Tiedosto {
   // Attribuutit
   private String nimi;
   private int koko;
   
   
   // Rakentajat
   public Tiedosto(String n, int k){
         nimi(n);
         koko(k);
   }
   
   //Aksessorit
   @Setteri
   public void nimi(String n) throws IllegalArgumentException {
      if(n.length() > 1){
         nimi = n;
      } else {
         throw new IllegalArgumentException("Error!");
      }
   }
   
   @Setteri
   public void koko(int k) throws IllegalArgumentException {
      if(k >= 1){
         koko = k;
      } else {
         throw new IllegalArgumentException("Error!");
      }
   }
   
   @Getteri
   public String nimi(){
      return nimi;
   }
   
   @Getteri
   public int koko(){
      return koko;
   }
   
   //toString metodin korvaaminen
   @Override
   public String toString(){
      return nimi + " " + koko + " " + "B ";
   }
}