package oope2018ht.viestit;

import java.io.File;
import java.util.Scanner;
import oope2018ht.apulaiset.*;
import oope2018ht.omalista.OmaLista;
import oope2018ht.tiedostot.*;

public class Alue {
    // Attribuutit
    private int ketjuLkm;
    private int viestiLkm;
    private Ketju aktiivinenKetju;
    private OmaLista ketjut;

    // Rakentajat
    public Alue(){
        ketjuLkm = 0;
        viestiLkm = 0;
        ketjut = new OmaLista();
    }
    
    // Akesssorit
    @Setteri
    public void ketjuLkm(int k) throws IllegalArgumentException {
        if(k > 0) {
            ketjuLkm = k;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }
    
    @Setteri
    public void viestiLkm(int v) throws IllegalArgumentException {
        if(v > 0) {
            ketjuLkm = v;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }
    
    @Setteri
    public void aktiivinenKetju(int ak) throws IllegalArgumentException {
        boolean found = false;
        for (int i = 0; i < ketjuLkm; i++) {
            Ketju temp = (Ketju)ketjut.alkio(i);
            if(temp.tunniste() == ak){
                aktiivinenKetju = temp;
                found = true;
            }
        }
        if(!found){
            throw new IllegalArgumentException("Error!");
        }
    }
    
    @Getteri
    public int ketjuLkm(){
        return ketjuLkm;
    }
    
    @Getteri
    public int viestiLkm(){
        return viestiLkm;
    }
    
    @Getteri
    public Ketju aktiivinenKetju(){
        return aktiivinenKetju;
    }
    
    @Getteri
    public OmaLista ketjut(){
        return ketjut;
    }
    
    // lisätään keskustelualueelle uusi viestiketju ja asetetaan se aktiiviseksi jos se on eka
    public void add(String merkkijono){;
        Ketju temp = new Ketju(ketjuLkm +1, merkkijono);
        if(ketjut.koko() == 0){
            aktiivinenKetju = temp;
        }
        ketjut.lisaaLoppuun(temp);
        ketjuLkm++;
    }
    
    // listataan keskustelualueen ketjut
    public void catalog(){
        for (int i = 0; i < ketjut.koko(); i++) {
            Ketju temp = (Ketju)ketjut.alkio(i);
            System.out.println("#" + temp.tunniste() + " " + temp.merkkijono() + " "
                    + "(" + temp.viestit() + " messages)");
        }
    }
    
    // vaihdetaan aktiivista ketjua
    public void select(int id) {
        aktiivinenKetju(id);
    }
    
    // luodaan ketjuun uusi viesti
    public void newMessage(String merkkijono, Tiedosto liite){
        Viesti temp = new Viesti(viestiLkm +1, merkkijono, null, null);
        if(liite != null){
            temp.liite(liite);
        }
        aktiivinenKetju.oksaViestit().lisaaLoppuun(temp);
        aktiivinenKetju.viestit(aktiivinenKetju.viestit() + 1);
        viestiLkm++;
    }
    
    // tarkistetaan liitteen olemassaolo ja välitetään se viestin luovaan operaatioon
    public void liite(String merkkijono, String liite, Viesti v){
        try{
            Tiedosto tiedosto = null;
            File file = new File(liite);
            Scanner sc = new Scanner(file);
            String tiedot = "";
            while(sc.hasNextLine()){
                tiedot = sc.nextLine();
            }
            String[] temp = tiedot.split(" ");
            int koko = Integer.parseInt(temp[1]);
            
            if("Kuva".equals(temp[0])) {
                int leveys = Integer.parseInt(temp[2]);
                int korkeus = Integer.parseInt(temp[3]);
                tiedosto = new Kuva(liite, koko, leveys, korkeus);
            } else if ("Video".equals(temp[0])) {
                double pituus = Double.parseDouble(temp[2]);
                tiedosto = new Video(liite, koko, pituus);
            }
            // tarkistetaan vastataanko vai luodaanko uusi oksa
            if (v != null){
                reply(merkkijono, tiedosto, v);
            } else {
                newMessage(merkkijono, tiedosto);
            }
            
        } catch (Exception e) {
            throw new IllegalArgumentException("Error!");
        }
    }
    
    public Viesti etsi(int id){
        Viesti paluuarvo = new Viesti(Integer.MAX_VALUE, "haha", null, null);
        for (int i = 0; i < aktiivinenKetju.oksaViestit().koko(); i++) {
            Viesti v = (Viesti)aktiivinenKetju.oksaViestit().alkio(i);
            if(v.tunniste() == id){
                return v;
            }
            if(v.vastaukset() != null) {
                paluuarvo = etsi(id, v);
                if(paluuarvo.tunniste() != Integer.MAX_VALUE){
                    return paluuarvo;
                }
            }
        }
        return paluuarvo;
    }
    
    public Viesti etsi(int id, Viesti v){
        Viesti paluuarvo = new Viesti(Integer.MAX_VALUE, "haha", null, null);
        for (int i = 0; i < v.vastaukset().koko(); i++) {
            Viesti t = (Viesti)v.vastaukset().alkio(i);
            if(t.tunniste() == id){
                return t;
            }
            if (t.vastaukset()!= null){
                paluuarvo = etsi(id, t);
                if(paluuarvo.tunniste() != Integer.MAX_VALUE){
                    return paluuarvo;
                }
            }
        }
        return paluuarvo;
    }
    
    public void reply(String s, Tiedosto t, Viesti v){
        Viesti temp = new Viesti(viestiLkm +1, s, v, null);
        if(t != null){
            temp.liite(t);
        }
        v.lisaaVastaus(temp);
        aktiivinenKetju.viestit(aktiivinenKetju.viestit() + 1);
        viestiLkm++;
    }
    
    public void tree(){
        System.out.println("=");
        System.out.println("== #" + aktiivinenKetju.tunniste() + " " + aktiivinenKetju.merkkijono() + " "
                + "(" + aktiivinenKetju.viestit() + " messages)");
        System.out.println("===");
        int i = 0;
        while(i < aktiivinenKetju.oksaViestit().koko()){
            Viesti temp = (Viesti)aktiivinenKetju.oksaViestit().alkio(i);
            tree(temp, 0);
            i++;
        }
    }
    
    public void tree(Viesti v, int s){
        int i = 0;
        String tuloste = "";
        String sisennys = "   ";
        while(i < s){
            tuloste += sisennys;
            i++;
        }
        tuloste += v.toString();
        System.out.println(tuloste);
        int j = 0;
        if(v.vastaukset() != null) {
            while(j < v.vastaukset().koko()){
                Viesti temp = (Viesti)v.vastaukset().alkio(j);
                tree(temp, s + 1);
                j++;
            }
        }
    }
    
    // ketjun kaikki viestit tulostava operaatio
    public void list(){
        OmaLista tulostettavat = aktiivinenKetju.kaikkiViestit();
        // tulosta ketjun aihe koristeltuna
        System.out.println("=");
        System.out.println("== #" + aktiivinenKetju.tunniste() + " " + aktiivinenKetju.merkkijono() + " "
                + "(" + aktiivinenKetju.viestit() + " messages)");
        System.out.println("===");
        // tulosta ketjun viestit järjestyksessä
        tulosta(tulostettavat);
    }
    
    // ketjun vanhimmat viestit tulostava operaatio
    public void head(int range){
        OmaLista tulostettavat = aktiivinenKetju.kaikkiViestit();
        tulostettavat = tulostettavat.annaAlku(range);
        tulosta(tulostettavat);
    }
    
    // ketjun uusimmat viestit tulostava operaatio
    public void tail(int range){
        OmaLista tulostettavat = aktiivinenKetju.kaikkiViestit();
        tulostettavat = tulostettavat.annaLoppu(range);
        tulosta(tulostettavat);
    }
    
    // ketjun viestien listan tulostamisessa auttava operaatio
    public void tulosta(OmaLista lista){
        for (int i = 0; i < lista.koko(); i++) {
            Viesti temp = (Viesti)lista.alkio(i);
            System.out.println(temp.toString());
        }
    }
    
    // poistaa viestin sisällön ja mahdollisen liitteen
    public void empty(Viesti v){
        v.tyhjenna();
    }
    
    public void find(String merkkijono){
        OmaLista kaikki = aktiivinenKetju.kaikkiViestit();
        OmaLista tulostettavat = new OmaLista();
        for (int i = 0; i < kaikki.koko(); i++) {
            Viesti temp = (Viesti)kaikki.alkio(i);
            String full = temp.toString();
            if(tarkista(merkkijono, full)){
                tulostettavat.lisaa(temp);
            }
        }
        tulosta(tulostettavat);
    }
    
    // etsitään merkkijonoa viestin ja liitteen sisällöstä
    public boolean tarkista(String merkkijono, String viesti){
        for (int i = 0; i < viesti.length(); i++) {
            boolean erimerkki = false;
            if(viesti.charAt(i) == merkkijono.charAt(0)){
                for (int j = 0; j < merkkijono.length(); j++) {
                    if(i+j >= viesti.length()){
                        return false;
                    }
                    if(viesti.charAt(i + j) != merkkijono.charAt(j)){
                        erimerkki = true;
                    }
                }
                if(!erimerkki){
                    return true;
                }
            }
        }
        return false;
    }
}