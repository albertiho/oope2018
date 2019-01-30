package oope2018ht.viestit;

import oope2018ht.apulaiset.*;
import oope2018ht.omalista.OmaLista;

public class Ketju {
    // Attribuutit
    private int tunniste;
    private String merkkijono;
    private OmaLista oksaViestit;
    private int viestit;
    
    // Rakentajat
    public Ketju(int t, String m){
        tunniste(t);
        merkkijono(m);
        oksaViestit = new OmaLista();
    }
    // Aksessorit
    @Setteri
    public void tunniste(int t) throws IllegalArgumentException {
        if(t > 0) {
            tunniste = t;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }
    
    @Setteri
    public void merkkijono(String m) throws IllegalArgumentException {
        if(m != null){
            merkkijono = m;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }
    
    @Setteri
    public void oksaViestit(OmaLista o) throws IllegalArgumentException{
        if(o != null){
            oksaViestit = o;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }
    
    @Setteri
    public void viestit(int v) throws IllegalArgumentException {
        if(v > 0) {
            viestit = v;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }
    
    @Getteri
    public int tunniste(){
        return tunniste;
    }
    
    @Getteri
    public String merkkijono(){
        return merkkijono;
    }
    
    @Getteri
    public OmaLista oksaViestit(){
        return oksaViestit;
    }
    
    @Getteri
    public int viestit(){
        return viestit;
    }
    
    public OmaLista kaikkiViestit(){
        OmaLista paluuarvo = new OmaLista();
        for (int i = 0; i < oksaViestit.koko(); i++) {
            Viesti temp = (Viesti)oksaViestit.alkio(i);
            paluuarvo.lisaa(temp);
            if(temp.vastaukset() != null){
                OmaLista h = vastauksia(temp);
                for (int j = 0; j < h.koko(); j++) {
                    Viesti apu = (Viesti)h.alkio(j);
                    paluuarvo.lisaa(apu);
                }
            }
        }
        return paluuarvo;
    }
    
    public OmaLista vastauksia(Viesti v){
        OmaLista rvalue = new OmaLista();
        for (int i = 0; i < v.vastaukset().koko(); i++) {
            Viesti temp = (Viesti)v.vastaukset().alkio(i);
            rvalue.lisaa(temp);
            if(temp.vastaukset() != null){
                OmaLista hh = vastauksia(temp);
                for (int j = 0; j < hh.koko(); j++) {
                    Viesti apu = (Viesti)hh.alkio(j);
                    rvalue.lisaa(apu);
                }
            }
        }
        return rvalue;
    }
}
