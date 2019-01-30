package oope2018ht.kayttoliittyma;

import oope2018ht.apulaiset.*;
import oope2018ht.viestit.Alue;
import oope2018ht.viestit.Viesti;

public class Kayttoliittyma {
    public static final String EXIT = "exit";
    public static final String ADD = "add ";
    public static final String CATALOG = "catalog";
    public static final String SELECT = "select ";
    public static final String NEW = "new ";
    public static final String REPLY = "reply ";
    public static final String TREE = "tree";
    public static final String LIST = "list";
    public static final String HEAD = "head";
    public static final String TAIL = "tail";
    public static final String EMPTY = "empty";
    public static final String FIND = "find";
            
    // harjoitustyön pääsilmukka
    public void aja(){
        System.out.println("Welcome to S.O.B.");
        Alue alue = new Alue();
        boolean aja = true;
        while(aja){
            System.out.print(">");
            String syote = In.readString();
            String[] temp;
            
            if(syote.startsWith(EXIT) && syote.length() == 4){
                    aja = false;
                    System.out.println("Bye! See you soon.");
            }else if(syote.startsWith(ADD) && syote.length() > 4) {
                temp = syote.split(" ", 2); // katkaistaan syote kahteen osaa, komentoon ja loput
                syote = temp[1];    // päivitetään syötteen olevan vain merkkijono-osuus
                if(check(syote)) {
                    alue.add(syote);
                } else {
                    valita();
                }

            }else if(syote.startsWith(CATALOG) && syote.length() == 7) {
                if(alue.ketjuLkm() > 0) {   // jos alueella on olemassa ketjuja
                    alue.catalog();
                } else {
                    valita();
                }
            }else if(syote.startsWith(SELECT) && syote.length() > 6) {
                temp = syote.split(" ", 2);
                if((temp[1].charAt(temp[1].length() -1) != ' ')
                        && (temp[1].charAt(0) != ' ')){
                    try{
                        int id = Integer.parseInt(temp[1]);
                        alue.select(id);
                    } catch (Exception e) {
                        valita();
                    }
                } else {
                    valita();
                }
            }else if(syote.startsWith(NEW) && syote.length() > 4) {
                temp = syote.split(" ", 2); // jaetaan syöte kahteen osaan
                syote = temp[1];
                if(check(syote)) {
                    try{
                        if(checkL(syote)){
                            temp = syote.split(" &");
                            alue.liite(temp[0], temp[1], null);
                        }else {
                            alue.newMessage(syote, null);
                        }
                    } catch (Exception e) {
                        valita();
                    }
                } else {
                    valita();
                }

            }else if(syote.startsWith(REPLY) && syote.length() > 6) {
                try{
                    temp = syote.split(" ", 3); // jaetaan syöte kolmeen osaan
                    int id = Integer.parseInt(temp[1]);
                    syote = temp[2];
                    // jos vastattava vieti löytyy ja syötteen merkkijono on kunnossa
                    Viesti test  = alue.etsi(id);
                    if(test.tunniste() != Integer.MAX_VALUE && check(syote)){
                        if(checkL(syote)){
                            temp = syote.split(" &");
                            alue.liite(temp[0], temp[1], test);
                        } else { // tapaukset joissa ei liitettä
                            alue.reply(syote, null, test);
                        }
                    } else {
                        valita();
                    }
                } catch (Exception e) {
                    valita();
                }
            }else if(syote.startsWith(TREE) && syote.length() == 4) {
                try{
                    alue.tree();
                } catch (Exception e) {
                    valita();
                }
            }else if(syote.startsWith(LIST) && syote.length() == 4){
                try{
                    alue.list();
                } catch (Exception e) {
                    valita();
                }
            }else if(syote.startsWith(HEAD) && syote.length() > 5){
                temp = syote.split(" ", 2);
                try{
                    alue.head(Integer.parseInt(temp[1]));
                } catch (Exception e) {
                    valita();
                }
            }else if(syote.startsWith(TAIL) && syote.length() > 5){
                temp = syote.split(" ", 2);
                try{
                    alue.tail(Integer.parseInt(temp[1]));
                } catch (Exception e) {
                    valita();
                }
            }else if(syote.startsWith(EMPTY) && syote.length() > 6){
                temp = syote.split(" ",2 );
                int id = Integer.parseInt(temp[1]);
                Viesti test = alue.etsi(id);
                if(test.tunniste() != Integer.MAX_VALUE){
                    try{
                        alue.empty(test);
                    } catch (Exception e) {
                        valita();
                    }
                } else {
                    valita();
                }
            }else if(syote.startsWith(FIND) && syote.length() > 5){
                temp = syote.split(" ", 2);
                if(check(temp[1])){
                    try{
                        alue.find(temp[1]);
                    } catch (Exception e) {
                        valita();
                    }
                }else{
                    valita();
                }
            } else {
                valita();
            }
        }
    }
    
    // valitustulostukseen käytettävä operaatio, nopeuttaa valituksen muokkaamista
    public void valita(){
        System.out.println("Error!");
    }
    
    // tarkistetaan syotteen oikeellisuus
    public boolean check(String merkkijono){
        boolean paluuarvo = true;
        if(merkkijono.length() == 0){
            return false;
        }
        if(merkkijono.charAt(0) == ' '){
            return false;
        }
        if(merkkijono.charAt(merkkijono.length() - 1) == ' '){
            return false;
        }
        return paluuarvo;
    }
    
    public boolean checkL(String merkkijono){
        boolean liite = false;
            for (int i = 0; i + 1 < merkkijono.length(); i++) {
                if(merkkijono.charAt(i) == ' ' && merkkijono.charAt(i+1) == '&'){
                    return true;
            }
        }
        return liite;
    }
}