lis�� kommentit ennenkun laitat wetoon, weton tietokantaan j�� jokanen versio.
n�ytt�� pahalt jos palautat version mis ei lue omaa nimee kommenteis.

kommentteihin voi my�s kirjottaa et harkkaty� ei osaa hakee jos haettavan viestin
tunniste == Integer.MAX_VALUE, mutta voidaan olettaa niin laajan keskustelupalstan
p�ivitt�v�n koodin toimimaan jollain muulla arvolla kun int

jos se valittaa ett� Alue.aktiivinenKetju ei oo oikeenlaista setteri� niin
lis�� siihen @Setterin kohalle 
public void aktiivinenKetju(Ketju k) throws IllegalArgumentException{
 if(k != null){
  aktiivinenKetju = k;
 } else {
  throw new IllegalArgumentException("Error!");
}

ja siirr� se nykynen asettava alasp�in, sit� k�ytet��n aktiivisen ketjun valintaan.


mit��n kysytt�v��/ohjeita >wa/snapchat