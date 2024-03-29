
package com.mycompany.mavenproject2;

import com.github.javafaker.Faker;
import static com.mycompany.mavenproject2.MyGraf.printGraph;
import java.util.Locale;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Mavenproject2 {
    
   public static MyArrayList<String> DFSAnalizTweetler(MyGraf<Kullanıcı> graf, Kullanıcı kullanici, String anahtarKelime, String hashtag, MyArrayList<String> ziyaretEdilenKullanicilar, MyArrayList<String> eslesenTweetler) {
    if (!ziyaretEdilenKullanicilar.contains(kullanici.getKullaniciAdi())) {

        if (kullanici.getTweetler() != null) {
            for (String tweet : kullanici.getTweetler()) {
                if (tweet.contains(anahtarKelime) && tweet.contains(hashtag)) {
                    String eslesenTweet = "Kullanıcı: " + kullanici.getKullaniciAdi() + ", Tweet: " + tweet;
                    eslesenTweetler.ekle(eslesenTweet);
                }
            }
        }

        ziyaretEdilenKullanicilar.ekle(kullanici.getKullaniciAdi());

        MyLinkedList<Kullanıcı> komsular = graf.getNeighbors(kullanici);
        if (komsular != null) {
            for (Kullanıcı komsu : komsular) {
                DFSAnalizTweetler(graf, komsu, anahtarKelime, hashtag, ziyaretEdilenKullanicilar, eslesenTweetler);
            }
        }
    }

    return eslesenTweetler;
}
    public static boolean ortakIlgiAlaniVarmi(MyGraf<Kullanıcı> graph, Kullanıcı startUser, Kullanıcı targetUser) {
        MyQueue<Kullanıcı> queue = new MyQueue<>();
        MyLinkedList<String> visitedUsers = new MyLinkedList<>();

        queue.enqueue(startUser);
        visitedUsers.ekle(startUser.getKullaniciAdi());
        
          

        while (!queue.isEmpty()) {
            Kullanıcı currentUser = queue.dequeue();

             MyLinkedList<Kullanıcı> neighbors = graph.getNeighbors(targetUser);
        if (neighbors != null) {
            for (Kullanıcı neighbor : neighbors) {
                if (neighbor.getIlgiAlan().equals(currentUser.getIlgiAlan())) {
                    return true;  
                }
            }
        }

            MyLinkedList<Kullanıcı> neighbors2 = graph.getNeighbors(currentUser);
            if (neighbors2 != null) {
                for (Kullanıcı neighbor : neighbors2) {
                    if (!visitedUsers.contains(neighbor.getKullaniciAdi())) {
                        visitedUsers.ekle(neighbor.getKullaniciAdi());
                        queue.enqueue(neighbor);
                    }
                }
            }
        }

        return false;
    }

public static void writeGraphToFile(MyGraf<Kullanıcı> graph, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Kullanıcı node : graph) {
                writer.write(node.getKullaniciAdi() + " -> (");

                MyLinkedList<Kullanıcı> neighbors = graph.getNeighbors(node);
                if (neighbors != null) {
                    int count = 0;
                    for (Kullanıcı neighbor : neighbors) {
                        if (count > 0) {
                            writer.write(", ");
                        }
                        writer.write(neighbor.getKullaniciAdi());
                        count++;
                    }
                }

                writer.write(")\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
private static <T> void ortakIlgiAlanFile(MyGraf<T> graph, String fileName, Kullanıcı kullanici1, Kullanıcı kullanici2) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        MyLinkedList<T> yazilanKullanicilar = new MyLinkedList<>();

        // Write the header
        writer.write("- " + kullanici1.getKullaniciAdi() + " ve " + kullanici2.getKullaniciAdi() + " kullanıcıların takipçiler arasında ortak ilgi alanı: " + "\n");

        for (T node : graph) {
           

           

            writer.write(node + " -> (");

            MyLinkedList<T> neighbors = graph.getNeighbors(node);
            if (neighbors != null) {
                int count = 0;
                for (T neighbor : neighbors) {
                    if(!yazilanKullanicilar.contains(neighbor)){
                    if (count > 0) {
                        writer.write(", ");
                    }
                    writer.write(String.valueOf(neighbor));
                    yazilanKullanicilar.ekle(neighbor);
                    count++;
                }
            }
            }
            writer.write(")\n");
           
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static MyGraf<String> ortakIlgiAlanlariGrafiniOlustur(MyHashMap<String, MyLinkedList<Kullanıcı>> ortakIlgiAlaninaSahipKullanicilar) {
    MyGraf<String> ortakIlgiAlanlariGrafi = new MyGraf<>();

    for (MyHashMap.Entry<String, MyLinkedList<Kullanıcı>> entry : ortakIlgiAlaninaSahipKullanicilar) {
        String ilgiAlani = entry.getAnahtar();
        ortakIlgiAlanlariGrafi.addNode(ilgiAlani);

        MyLinkedList<Kullanıcı> kullaniciListesi = entry.getDeger();

        for (int i = 0; i < kullaniciListesi.size(); i++) {
            for (int j = i + 1; j < kullaniciListesi.size(); j++) {
                Kullanıcı kullanici1 = kullaniciListesi.getir(i);
                Kullanıcı kullanici2 = kullaniciListesi.getir(j);

                // Her iki kullanıcı da daha önce eklenmiş mi kontrol et
                if (!ortakIlgiAlanlariGrafi.containsNode(kullanici1.getKullaniciAdi())) {
                    ortakIlgiAlanlariGrafi.addEdge(ilgiAlani, kullanici1.getKullaniciAdi());
                }

                if (!ortakIlgiAlanlariGrafi.containsNode(kullanici2.getKullaniciAdi())) {
                    ortakIlgiAlanlariGrafi.addEdge(ilgiAlani, kullanici2.getKullaniciAdi());
                }
            }
        }
    }

    return ortakIlgiAlanlariGrafi;
}

public static MyHashMap<String, MyLinkedList<Kullanıcı>> getOrtakIlgiAlaninaSahipKullanicilar(MyGraf<Kullanıcı> graph, Kullanıcı kullanici1, Kullanıcı kullanici2) {
    MyHashMap<String, MyLinkedList<Kullanıcı>> ortakIlgiAlaninaSahipKullanicilar = new MyHashMap<>();

    MyLinkedList<Kullanıcı> takipciler1 = kullanici1.getTakipciler();
    MyLinkedList<Kullanıcı> takipciler2 = kullanici2.getTakipciler();

    if (takipciler1 != null && takipciler2 != null) {
        for (Kullanıcı takipci1 : takipciler1) {
            for (Kullanıcı takipci2 : takipciler2) {
                if (takipci1.getIlgiAlan().equals(takipci2.getIlgiAlan())) {
                    // İki kullanıcı daha önce eklenmiş mi kontrol et
                    if (!kullanicilarZatenEklenmisMi(ortakIlgiAlaninaSahipKullanicilar, takipci1, takipci2)) {
                        MyLinkedList<Kullanıcı> ortakIlgiAlanListesi = ortakIlgiAlaninaSahipKullanicilar.getir(takipci1.getIlgiAlan());

                        if (ortakIlgiAlanListesi == null) {
                            ortakIlgiAlanListesi = new MyLinkedList<>();
                            ortakIlgiAlaninaSahipKullanicilar.ekle(takipci1.getIlgiAlan(), ortakIlgiAlanListesi);
                        }

                        ortakIlgiAlanListesi.ekle(takipci1);
                        ortakIlgiAlanListesi.ekle(takipci2);
                    }
                }
            }
        }
    }

    return ortakIlgiAlaninaSahipKullanicilar;
}

// İki kullanıcının  eklenip eklenmediğini kontrolu
private static boolean kullanicilarZatenEklenmisMi(MyHashMap<String, MyLinkedList<Kullanıcı>> ortakIlgiAlaninaSahipKullanicilar, Kullanıcı kullanici1, Kullanıcı kullanici2) {
    for (MyHashMap.Entry<String, MyLinkedList<Kullanıcı>> entry : ortakIlgiAlaninaSahipKullanicilar) {
        MyLinkedList<Kullanıcı> kullaniciListesi = entry.getDeger();
        if (kullaniciListesi.contains(kullanici1) || kullaniciListesi.contains(kullanici2)) {
            return true;
        }
    }
    return false;
}
 
public static MyLinkedList<Kullanıcı> findUsersWithSameFollowers(MyGraf<Kullanıcı> graph, Kullanıcı baslangicKullanici, int hedefTakipciSayisi) {
    MyLinkedList<Kullanıcı> sonuclar = new MyLinkedList<>();
    MyArrayList<String> ziyaretEdilenKullanicilar = new MyArrayList<>();

    MyQueue<Kullanıcı> queue = new MyQueue<>();
    queue.enqueue(baslangicKullanici);
    

    while (!queue.isEmpty()) {
        Kullanıcı aktifKullanici = queue.dequeue();
        
        MyLinkedList<Kullanıcı> komsular = graph.getNeighbors(aktifKullanici);
        
        if (!ziyaretEdilenKullanicilar.contains(aktifKullanici.getKullaniciAdi())&& aktifKullanici.getTakipciSayisi() == hedefTakipciSayisi) {
                        sonuclar.ekle(aktifKullanici);
                        ziyaretEdilenKullanicilar.ekle(aktifKullanici.getKullaniciAdi());
            }
                


        if (komsular != null) {
            for (Kullanıcı komsu : komsular) {
                if (!ziyaretEdilenKullanicilar.contains(komsu.getKullaniciAdi())) {
                    ziyaretEdilenKullanicilar.ekle(komsu.getKullaniciAdi());
                    queue.enqueue(komsu);

                    if (komsu.getTakipciSayisi() == hedefTakipciSayisi) {
                        sonuclar.ekle(komsu);
                    }
                }
            }
        }
    }

    return sonuclar;
}

    public static void main(String[] args) {
    
         //Dil ayarlama kaldırılabilir/eklenebilir
         Faker faker = new Faker();
         MyHashMap<Integer, Kullanıcı> userMap = new MyHashMap<>();
         MyGraf<Kullanıcı> userGraph = new MyGraf<>();
         MyHashMap<String, MyLinkedList<Kullanıcı>> ilgiAlanlariHash = new MyHashMap<>();
         MyLinkedList<Kullanıcı>TakipçiSAynıolanlar=new MyLinkedList<>();
         
         //Kullanıcıları ayarla
        for (int i = 0; i < 100; i++) {
            Kullanıcı kullanici = new Kullanıcı(
                    faker.name().username(),
                    faker.name().fullName(),
                    faker.number().numberBetween(5, 8),
                    faker.number().numberBetween(5, 8),
                    "Turkce",
                    faker.address().state()
            );
            kullanici.TakipçiAyarla(faker, kullanici.getTakipciSayisi());
            kullanici.TakipEdilenAyarla(faker, kullanici.getTakipEdilenSayisi());
            kullanici.TweetAyarla(faker);
            kullanici.TakipçiVeTakipEdilenTweetleriAyarla(faker);
            userMap.ekle(i, kullanici);
            
        }

        // Oluşturulan kullanıcıları  yazdır
        for (MyHashMap.Entry<Integer, Kullanıcı> entry : userMap) {
            Kullanıcı kullanici = entry.getDeger();
            System.out.println("Kullanıcı Adı: " + kullanici.getKullaniciAdi());
            System.out.println("Ad-Soyad: " + kullanici.getAdSoyad());
            System.out.println("Takipçi Sayısı: " + kullanici.getTakipciSayisi());
            System.out.println("Takip Edilen Sayısı: " + kullanici.getTakipEdilenSayisi());
            System.out.println("Dil: " + kullanici.getDil());
            System.out.println("Bölge: " + kullanici.getBolge());
             System.out.println("İlgi alanı "+kullanici.getIlgiAlan());
           System.out.println("Takipçiler:");
               for (Kullanıcı takipci : kullanici.getTakipciler()) {
        System.out.println("- " + takipci.getKullaniciAdi());
    }
    
    System.out.println("Takip edilenler:");
    for (Kullanıcı takipEdilen : kullanici.getTakipEdilenler()) {
        System.out.println("- " + takipEdilen.getKullaniciAdi());
    }
            System.out.println("------------------------------------------------------------------");
        }
        
     //Kullanıcı bilgilerini dosyaya yazdır
     try (BufferedWriter writer = new BufferedWriter(new FileWriter("kullanici_bilgileri.txt"))) {
    for (MyHashMap.Entry<Integer, Kullanıcı> entry : userMap) {
        Kullanıcı kullanici = entry.getDeger();
        writer.write("Kullanıcı Adı: " + kullanici.getKullaniciAdi());
        writer.newLine();
        writer.write("Ad-Soyad: " + kullanici.getAdSoyad());
        writer.newLine();
        writer.write("Takipçi Sayısı: " + kullanici.getTakipciSayisi());
        writer.newLine();
        writer.write("Takip Edilen Sayısı: " + kullanici.getTakipEdilenSayisi());
        writer.newLine();
        writer.write("Dil: " + kullanici.getDil());
        writer.newLine();
        writer.write("Bölge: " + kullanici.getBolge());
        writer.newLine();
        writer.write("İlgi alanı " + kullanici.getIlgiAlan());
        writer.newLine();
        writer.write("Takipçiler:");
        writer.newLine();
        for (Kullanıcı takipci : kullanici.getTakipciler()) {
            writer.write("- " + takipci.getKullaniciAdi());
            writer.newLine();
        }

        writer.write("Takip edilenler:");
        writer.newLine();
        for (Kullanıcı takipEdilen : kullanici.getTakipEdilenler()) {
            writer.write("- " + takipEdilen.getKullaniciAdi());
            writer.newLine();
        }

        writer.write("------------------------------------------------------------------");
        writer.newLine();
    }
} catch (IOException e) {
    e.printStackTrace(); // Dosya yazma hatası oluştuğunda işlenecek durumu belirtin
}
     

  //oluşturulan kullanıcılardan graf oluştur      
for (MyHashMap.Entry<Integer, Kullanıcı> entry : userMap) {
    Kullanıcı kullanici = entry.getDeger();

    userGraph.addNode(kullanici);
    for (Kullanıcı takipci : kullanici.getTakipciler()) {
        userGraph.addEdge(kullanici, takipci);
    }

    for (Kullanıcı takipEdilen : kullanici.getTakipEdilenler()) {
        userGraph.addEdge(kullanici, takipEdilen);
    }
}

//grafı yazdır
System.out.println("Kullanıcıların Takip ettikleri ve Takipçileri:");
for (Kullanıcı node : userGraph) {
    System.out.print(node.getKullaniciAdi() + " -> (");

    MyLinkedList<Kullanıcı> neighbors = userGraph.getNeighbors(node);
    if (neighbors != null) {
        int count = 0;
        for (Kullanıcı neighbor : neighbors) {
            if (count > 0) {
                System.out.print(", ");
            }
            System.out.print(neighbor.getKullaniciAdi());
            count++;
        }
    }

    System.out.println(")");
}


//Grafı dosyaya yazdır
 writeGraphToFile(userGraph, "graph_output.txt");
 
 
//kullanıcıların ilgi alanlarına göre ilgi alanı oluştur ve ilgi alanlarına ekle
for (MyHashMap.Entry<Integer, Kullanıcı> entry : userMap) {
            Kullanıcı kullanici = entry.getDeger();

            if (kullanici.getIlgiAlan() != null && !kullanici.getIlgiAlan().isEmpty()) {
                MyLinkedList<Kullanıcı> ilgiAlaninaSahipKullanicilar = ilgiAlanlariHash.getir(kullanici.getIlgiAlan());

                if (ilgiAlaninaSahipKullanicilar == null) {
                    ilgiAlaninaSahipKullanicilar = new MyLinkedList<>();
                    ilgiAlanlariHash.ekle(kullanici.getIlgiAlan(), ilgiAlaninaSahipKullanicilar);
                }

                ilgiAlaninaSahipKullanicilar.ekle(kullanici);
            }
        }

//Kullanıcı ilgi alanlarını yazdır
for (MyHashMap.Entry<String, MyLinkedList<Kullanıcı>> ilgiAlanEntry : ilgiAlanlariHash) {
    String ilgiAlanAdi = ilgiAlanEntry.getAnahtar();
    MyLinkedList<Kullanıcı> ilgiAlaninaSahipKullanicilar = ilgiAlanEntry.getDeger();

    System.out.println("Ilgi Alani: " + ilgiAlanAdi);
         for (Kullanıcı ilgiAlaninaSahipKullanici : ilgiAlaninaSahipKullanicilar) {
                System.out.println("- " + ilgiAlaninaSahipKullanici.getKullaniciAdi());
            }
            System.out.println("--------------------");
        }

//Kullanıcı ilgi alanlarını dosyaya yaz
 for (MyHashMap.Entry<String, MyLinkedList<Kullanıcı>> ilgiAlanEntry : ilgiAlanlariHash) {
            String ilgiAlanAdi = ilgiAlanEntry.getAnahtar();
            MyLinkedList<Kullanıcı> ilgiAlaninaSahipKullanicilar = ilgiAlanEntry.getDeger();

            // Create a new text file for each interest area
            String fileName = ilgiAlanAdi + "_analiz_rapor.txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("Ilgi Alani: " + ilgiAlanAdi);
                writer.newLine();

                for (Kullanıcı ilgiAlaninaSahipKullanici : ilgiAlaninaSahipKullanicilar) {
                    writer.write("- " + ilgiAlaninaSahipKullanici.getKullaniciAdi());
                    writer.newLine();
                }

                writer.write("--------------------");
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception based on your application's needs
            }
        }

//Kullanılan Hashtagleri listele
MyArrayList<String> hashtags=new MyArrayList<>();
 for (MyHashMap.Entry<Integer, Kullanıcı> entry : userMap) {
     Kullanıcı kullanici = entry.getDeger();

     
     for (String tweet : kullanici.getTweetler()) {
       
         if (tweet != null) {
        String[] kelimeler = tweet.split("\\s+");
        for (String kelime : kelimeler) {
            if (kelime.startsWith("#")) {
               
                 if (kelime.startsWith("#") && !hashtags.contains(kelime)) {
                    hashtags.ekle(kelime);
                }
            }
        }
    }
         
    }          
 } 
 
 
System.out.println("Kullanılan Hashtagler:");
 int count = 0;
   for (String hashtag : hashtags) {
       
        if (count > 0) {
     System.out.print(" , ");
   }
      System.out.print(hashtag);
    count++;
}         
   count=0;
   try (BufferedWriter writer = new BufferedWriter(new FileWriter("hashtags.txt"))) {
            for (String hashtag : hashtags) {
                if (count > 0) {
                    writer.write(" , ");
                }
                writer.write(hashtag);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    

   
System.out.println("");
   
 
System.out.println("Belirli kelime ve hashtag içeren tweetler :");
MyArrayList<String> eslesenTweetler = new MyArrayList<>();

try (BufferedWriter writer = new BufferedWriter(new FileWriter("eslesen_tweetler.txt"))) {
    for (MyHashMap.Entry<Integer, Kullanıcı> entry : userMap) {
        Kullanıcı kullanici = entry.getDeger();
        MyArrayList<String> visitedUsers = new MyArrayList<>();

        eslesenTweetler = DFSAnalizTweetler(userGraph, kullanici, "fenerbahce", "#Derbi", visitedUsers, eslesenTweetler);
    }

    for (String eslesenTweet : eslesenTweetler) {
        System.out.println(eslesenTweet);
        
        writer.write(eslesenTweet);
        writer.newLine(); // Yeni satıra geç
    }
} catch (IOException e) {
    System.err.println("Dosya yazma hatası: " + e.getMessage());
}     
        
        
        
//Belirli 2 kullanıcının takipcileri        
        
Kullanıcı kullanici1 = userMap.getir(0); 
Kullanıcı kullanici2 = userMap.getir(1);      

boolean ortakIlgiAlaniVarMi = ortakIlgiAlaniVarmi(userGraph, kullanici1, kullanici2);

        if (ortakIlgiAlaniVarMi) {
            System.out.println("Kullanıcılar arasında ortak ilgi alanı bulundu.");
        } else {
            System.out.println("Kullanıcılar arasında ortak ilgi alanı bulunamadı.");
        }

MyHashMap<String, MyLinkedList<Kullanıcı>> ortakIlgiAlaninaSahipKullanicilar = getOrtakIlgiAlaninaSahipKullanicilar(userGraph, kullanici1, kullanici2);
  for (MyHashMap.Entry<String, MyLinkedList<Kullanıcı>> entry : ortakIlgiAlaninaSahipKullanicilar) {
    String ilgiAlani = entry.getAnahtar();
    MyLinkedList<Kullanıcı> kullaniciListesi = entry.getDeger();

    System.out.println("İlgi Alanı: " + ilgiAlani);
    for (Kullanıcı kullanici : kullaniciListesi) {
        System.out.println("- " + kullanici.getKullaniciAdi());
    }
    System.out.println("--------------------");
}
        
  try (BufferedWriter writer = new BufferedWriter(new FileWriter("belirli_kullanıcıların_ortakalanı.txt"))) {
      
      writer.write("- " + kullanici1.getKullaniciAdi() +" ve "+kullanici2.getKullaniciAdi()+" kullanıcıların takipçiler arasında ortak ilgi alanı: "+ "\n");
            for (MyHashMap.Entry<String, MyLinkedList<Kullanıcı>> entry : ortakIlgiAlaninaSahipKullanicilar) {
                String ilgiAlani = entry.getAnahtar();
                MyLinkedList<Kullanıcı> kullaniciListesi = entry.getDeger();

                writer.write("İlgi Alanı: " + ilgiAlani + "\n");
                for (Kullanıcı kullanici : kullaniciListesi) {
                    writer.write("- " + kullanici.getKullaniciAdi() + "\n");
                }
                writer.write("--------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  
  
  
MyGraf<String> ortakIlgiAlanlariGrafi = ortakIlgiAlanlariGrafiniOlustur(ortakIlgiAlaninaSahipKullanicilar);
  
  printGraph(ortakIlgiAlanlariGrafi);

  ortakIlgiAlanFile(ortakIlgiAlanlariGrafi, "Ortak_ilgi_alan.txt",kullanici1,kullanici2);
  
  
  MinimumSpanningTree<String> mstAlgorithm = new MinimumSpanningTree<>(ortakIlgiAlanlariGrafi);
  MyGraf<String> minimumSpanningTree = mstAlgorithm.findMinimumSpanningTree();
  

  
  
  
  
  
  //belirli kullanıcının takipçi sayılarına eşit olan kullanıcıları bulma
  //Bu Metodları txt ye yazdır
  
  Kullanıcı baslangicKullanici = userMap.getir(0); 
int hedefTakipciSayisi = baslangicKullanici.getTakipciSayisi();

System.out.println("Benzer Takipçi Sayısına Sahip Kullanıcılar (" + baslangicKullanici.getKullaniciAdi() + " için):");
for (Kullanıcı kullanici : userGraph) {
    
    
    MyLinkedList<Kullanıcı> benzerTakipciSayisinaSahipKullanicilar = findUsersWithSameFollowers(userGraph, kullanici, hedefTakipciSayisi);

    
    for (Kullanıcı k : benzerTakipciSayisinaSahipKullanicilar) {
        System.out.println("- " + k.getKullaniciAdi());
    }
    
}
  try (BufferedWriter writer = new BufferedWriter(new FileWriter("Takipçi_sayisi_eşit_kullanıcılar.txt"))) {
      writer.write("Benzer Takipçi Sayısına Sahip Kullanıcılar (" + baslangicKullanici.getKullaniciAdi() + " için):\n");
      
    for (Kullanıcı kullanici : userGraph) {
        MyLinkedList<Kullanıcı> benzerTakipciSayisinaSahipKullanicilar = findUsersWithSameFollowers(userGraph, kullanici, hedefTakipciSayisi);

        for (Kullanıcı k : benzerTakipciSayisinaSahipKullanicilar) {
            System.out.println("- " + k.getKullaniciAdi());
            writer.write("- " + k.getKullaniciAdi() + "\n");
        }
    }
} catch (IOException e) {
    e.printStackTrace();
}
  
  
}
}