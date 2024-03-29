
package com.mycompany.mavenproject2;
import com.github.javafaker.Faker;



public class Kullanıcı {
    
    private String kullaniciAdi;
    private String adSoyad;
    private int takipciSayisi;
    private int takipEdilenSayisi;
    private String dil;
    private String bolge;
    private String ilgiAlan;
    private MyLinkedList<String> tweetler;
    private MyLinkedList<Kullanıcı> takipEdilenler;
    private MyLinkedList<Kullanıcı> takipciler;
    
    
    public Kullanıcı(){
        
    }

    public Kullanıcı(String kullaniciAdi, String adSoyad, int takipciSayisi, int takipEdilenSayisi, String dil, String bolge) {
        this.kullaniciAdi = kullaniciAdi;
        this.adSoyad = adSoyad;
        this.takipciSayisi = takipciSayisi;
        this.takipEdilenSayisi = takipEdilenSayisi;
        this.dil = dil;
        this.bolge = bolge;
        this.takipciler = new MyLinkedList<>();
        this.takipEdilenler=new MyLinkedList<>();
        
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public int getTakipciSayisi() {
        return takipciSayisi;
    }

    public int getTakipEdilenSayisi() {
        return takipEdilenSayisi;
    }

    public String getDil() {
        return dil;
    }

    public String getBolge() {
        return bolge;
    }

    public MyLinkedList<Kullanıcı> getTakipciler() {
        return takipciler;
    }   
    public MyLinkedList<Kullanıcı> getTakipEdilenler() {
        return takipEdilenler;
    }

    public String getIlgiAlan() {
        return ilgiAlan;
    }

    public MyLinkedList<String> getTweetler() {
        return tweetler;
    }
    
    
    
    public void TakipçiAyarla(Faker faker, int takipciSayisi){
        for (int i = 0; i < takipciSayisi; i++) {
            Kullanıcı takipci = new Kullanıcı(
                    faker.name().username(),
                    faker.name().fullName(),
                    faker.number().numberBetween(2, 6),
                    faker.number().numberBetween(2, 6),
                    "Turkce",
                    faker.address().state()
            );
            takipciler.ekle(takipci);
        }
        
        
    }
    public void TakipEdilenAyarla(Faker faker, int takipEdilenSayisi) {
        for (int i = 0; i < takipEdilenSayisi; i++) {
            Kullanıcı takipEdilen = new Kullanıcı(
                    faker.name().username(),
                    faker.name().fullName(),
                    faker.number().numberBetween(3, 6),
                    faker.number().numberBetween(3, 6),
                    "Turkce",
                    faker.address().state()
            );
            takipEdilenler.ekle(takipEdilen);
        }
    }
     public void TakipçiVeTakipEdilenTweetleriAyarla(Faker faker) {
        // Takipçilerin tweetlerini ayarla
        for (Kullanıcı takipci : takipciler) {
            takipci.TweetAyarla(faker);
        }

        // Takip edilenlerin tweetlerini ayarla
        for (Kullanıcı takipEdilen : takipEdilenler) {
            takipEdilen.TweetAyarla(faker);
        }
    }
    
    public void TweetAyarla(Faker faker){
        
        tweetler = new MyLinkedList<>();
        
        int secim=faker.random().nextInt(5);
        String ilgiAlani = "";               
        String kelimeSecim="";
        String kelimeSecim2="";
        switch(secim){
            case 0:
                ilgiAlani="spor";
                 break;
            case 1:
                ilgiAlani="müzik";
                 break;
            case 2:
                ilgiAlani="dans";
                 break;
            case 3:
                ilgiAlani="film";
                 break;
            case 4:
                ilgiAlani="oyun";
                 break;
        }
        int secim2=faker.random().nextInt(3);
        switch(secim2){
            case 0:
                kelimeSecim="kus";
                 break;
            case 1:
                kelimeSecim="kopek";
                 break;
            case 2:
                kelimeSecim="kedi";
                 break;
 
        }
         int secim3=faker.random().nextInt(3);
        switch(secim3){
            case 0:
                kelimeSecim2="fenerbahce";
                 break;
            case 1:
                kelimeSecim2="galatasaray";
                 break;
            case 2:
                kelimeSecim2="besiktas";
                 break;
 
        }
        int tweetSayisi = faker.random().nextInt(2,4);
        
            if(tweetSayisi>=1){
            String tweet = "Bugün " + ilgiAlani + " ile ilgili bir şeyler paylaştım! #"+ilgiAlani;
            tweetler.ekle(tweet);
            }
             if(tweetSayisi>=2){
             String tweet="Evde "+ kelimeSecim + " evcil hayvanım var. #Hayvan";
             tweetler.ekle(tweet);
             }
            if(tweetSayisi>=3){
             String tweet="Yasa "+ kelimeSecim2 + ". #"+kelimeSecim2;
             tweetler.ekle(tweet);
            
            }
        if(tweetSayisi>=4){
             String tweet="En büyük "+ kelimeSecim2 + ". #Derbi";
             tweetler.ekle(tweet);
            
            }
    
        IlgiAlanAyarla();
        
    }
    public void IlgiAlanAyarla(){
    
    
       String tweet = tweetler.getir(0); 

    if (tweet != null) {
        String[] kelimeler = tweet.split("\\s+");
        for (String kelime : kelimeler) {
            if (kelime.startsWith("#")) {
                // "#" işaretinden sonraki tüm harfleri al
                ilgiAlan = kelime.substring(1);
                break;
            }
        }
    }
    
   }
    
    
    
    
}
