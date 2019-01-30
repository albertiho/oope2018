lisää kommentit ennenkun laitat wetoon, weton tietokantaan jää jokanen versio.
näyttää pahalt jos palautat version mis ei lue omaa nimee kommenteis.

kommentteihin voi myös kirjottaa et harkkatyö ei osaa hakee jos haettavan viestin
tunniste == Integer.MAX_VALUE, mutta voidaan olettaa niin laajan keskustelupalstan
päivittävän koodin toimimaan jollain muulla arvolla kun int

jos se valittaa että Alue.aktiivinenKetju ei oo oikeenlaista setteriä niin
lisää siihen @Setterin kohalle 
public void aktiivinenKetju(Ketju k) throws IllegalArgumentException{
 if(k != null){
  aktiivinenKetju = k;
 } else {
  throw new IllegalArgumentException("Error!");
}

ja siirrä se nykynen asettava alaspäin, sitä käytetään aktiivisen ketjun valintaan.


mitään kysyttävää/ohjeita >wa/snapchat