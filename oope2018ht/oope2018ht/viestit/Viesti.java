package oope2018ht.viestit;

import oope2018ht.apulaiset.*;
import oope2018ht.tiedostot.*;
import oope2018ht.omalista.*;

public class Viesti implements Komennettava<Viesti>, Comparable<Viesti> {
   
   // Attribuutit
   private int tunniste;
   private String sisalto;
   private Viesti aikaisempi;
   private Tiedosto liite;
   private OmaLista vastaukset;
   
   // Rakentajat
   public Viesti (int t, String s, Viesti a, Tiedosto f){
         tunniste(t);
         sisalto(s);
         aikaisempi(a);
         liite(f);
         vastaukset = new OmaLista();
   }
   
   // Aksessorit
   @Setteri
   public void tunniste(int t) throws IllegalArgumentException {
      if (t > 0) {
         tunniste = t;
      } else {
         throw new IllegalArgumentException("Error!");
      }
   }
   
   @Setteri
   public void sisalto(String s) throws IllegalArgumentException {
      if (s.length() >= 1){
         sisalto = s;
      } else {
         throw new IllegalArgumentException("Error!");
      }
   }
      
   @Setteri
   public void aikaisempi(Viesti a) throws IllegalArgumentException {
      aikaisempi = a;
   }
   
   @Setteri
   public void liite(Tiedosto f) throws IllegalArgumentException {
      liite = f;
   }
   
   @Setteri
   public void vastaukset(OmaLista o){
      if (o != null){
         vastaukset = o;
      } else {
          throw new IllegalArgumentException("Error!");
      }
   }
   
   @Getteri
   public int tunniste(){
      return tunniste;
   }
   
   @Getteri
   public String sisalto(){
      return sisalto;
   }
   
   @Getteri
   public Viesti aikaisempi(){
      return aikaisempi;
   }
   
   @Getteri
   public Tiedosto liite(){
      return liite;
   }
   
   @Getteri
   public OmaLista vastaukset(){
      return vastaukset;
   }
   
   //Comparable-rajapinnan metodit
   @Override
   public int compareTo(Viesti kohde){
      if(tunniste > kohde.tunniste()){
         return 1;
      } else if(tunniste < kohde.tunniste()) {
         return -1;
      } else {
         return 0;
      }
   }
   
   //Object-luokan metodit
   @Override
   public String toString(){
      String paluuarvo = "#" + tunniste + " " + sisalto;
      if(liite != null){
         paluuarvo += " (" + liite.toString() + ")";
      }
      return paluuarvo;
   }
   
   @Override
   public boolean equals(Object o){
      Viesti v = (Viesti)o;
      return tunniste == v.tunniste();
   }
   
   
   // Komennettava-rajapinnan metodit
   @Override
   public Viesti hae (Viesti haettava) throws IllegalArgumentException {
      if (haettava != null){
         Viesti v = (Viesti)vastaukset.hae(haettava);
         return v;
      }
      else {
         throw new IllegalArgumentException("Error!");
      }
   }
   
   @Override
   public void lisaaVastaus (Viesti lisattava) throws IllegalArgumentException {
      if(lisattava!= null && vastaukset.hae(lisattava) == null){
         vastaukset.lisaaLoppuun(lisattava);
         
      } else {
         throw new IllegalArgumentException("Error!");
      }
   }
   
   @Override
   public void tyhjenna() {
      sisalto = POISTETTUTEKSTI;
      liite = null;
   }
}