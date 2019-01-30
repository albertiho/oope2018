package oope2018ht.omalista;

import oope2018ht.apulaiset.*;
import fi.uta.csjola.oope.lista.*;

public class OmaLista extends LinkitettyLista implements Ooperoiva<OmaLista> {
   
   // Hae-operaatio ooperoiva-rajapinnasta
   @Override
   public Object hae(Object haettava) {
      for ( int i = 0; i < koko(); i++ ) {
         if (alkio(i).equals(haettava))
            return alkio(i);
      }
      return null;
   }
   
   // Lisää-operaatio ooperoiva-rajapinnasta
   @SuppressWarnings("unchecked")
   @Override
   public boolean lisaa(Object lisattava) {
      // jos
      if (lisattava != null && hae(lisattava) == null) {
         try {
            Comparable temp = (Comparable)lisattava;
            // jos lista on tyhjä
            if (koko < 1) {
               lisaaAlkuun(lisattava);
               return true;
            } else { // sijoitetaan lisättävä
               for ( int i = 0; i <= koko; i++ ) {
                  if (i == koko) {
                     lisaaLoppuun(lisattava);
                     return true;
                  }else if (temp.compareTo((Comparable)alkio(i)) < 0) {
                     lisaa(i, lisattava);
                     return true;
                  }
               }
            }
         }
         catch (Exception e) {
            return false;
         }
      }
      return false;
   }

   // annaAlku-operaatio ooperoiva-rajapinnasta
   @Override
   public OmaLista annaAlku(int n){
      if (koko > 0){
         if(n >= 1 && n <= koko){ 
            OmaLista paluuarvo = new OmaLista();
            for (int i =0; i < n; i++){
               paluuarvo.lisaaLoppuun(alkio(i));
            }
            return paluuarvo;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }
   
   // annaLoppu-operaatio ooperoiva-rajapinnasta
   @Override
   public OmaLista annaLoppu(int n){
      if (koko > 0){
         if(n>= 1 && n <= koko) {
            OmaLista paluuarvo = new OmaLista();
            for (int i = koko-1; i >= koko - n; i--) {
               paluuarvo.lisaaAlkuun(alkio(i));
            }
            return paluuarvo;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }
}