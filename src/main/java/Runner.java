
/*
Proje:Student Management System
     -1-Herhangi bir eğitim kurumu için öğrenci yönetim uygulaması geliştiriniz.
     -2-Kullanıcı
               -öğrenci kayıt
               -öğrenci veya öğrencileri görüntüleme
               -id ile öğrenci güncelleme
               -id ile öğrenci silme
       işlemlerini yapabilmeli.
     -3-öğrenci:id,name,lastname,city,age özelliklerine sahiptir.
 *//*
Proje:Student Management System
     -1-Herhangi bir eğitim kurumu için öğrenci yönetim uygulaması geliştiriniz.
     -2-Kullanıcı
               -öğrenci kayıt
               -öğrenci veya öğrencileri görüntüleme
               -id ile öğrenci güncelleme
               -id ile öğrenci silme
       işlemlerini yapabilmeli.
     -3-öğrenci:id,name,lastname,city,age özelliklerine sahiptir.
 */
/*     ÖDEVV:öğrenci silme işleminden sonra silinen öğrencinin bilgilerini gösterelim.
     ÖDEVV:öğrenci ekleme işleminden sonra eklenen öğrencinin bilgilerini gösterelim.
     ÖDEVV: -R: ad-soyad ile öğrenci filtreleme işlemlerini yapabilmeli.
        */

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        start();

    }
    //1-ADIM:Kullanıcıya menü göstersin
    //2-ADIM:student classını oluşturma
    public static void start(){

        Scanner inp=new Scanner(System.in);
        //10-Tablo Oluşturma
        StudentService service=new StudentService();
        service.createStudentTable();
        int select;

        int id;
        do{
            System.out.println("==============================================");
            System.out.println("Öğrenci Yönetim Paneli");
            System.out.println("1 Öğrenci Kayıt");
            System.out.println("2 Tüm Öğrencileri Listele");
            System.out.println("3 Öğrenci Güncelle");
            System.out.println("4 Öğrenci Sil");
            System.out.println("5 Tek Bir Öğrenciyi Gösterme");
            System.out.println("0 ÇIKIŞ");
            System.out.println("İşlem seçiniz:");
    select= inp.nextInt();
            inp.nextLine();
            switch (select){
                case 1:
                    //11-Öğrenci Kayıt
                    service.saveStudent();
                    service.getAllStudent();
                    break;
                case 2:
                    //Tüm öğrencileri Listele
                    service.getAllStudent();
                    break;
                case 3:
                    //öğrenci güncelleme
                    id=getId(inp);
               //19-Öğrenci güncelleme
                    service.updateStudent(id);
                    service.getAllStudent();

                    break;
                case 4:
                    //öğrenci silme
                    id=getId(inp);
                    service.deleteStudent(id);
                    service.getAllStudent();
                    break;
                case 5:
                    //21 Öğrenci bilgilerni yazdırma
                    id=getId(inp);
                    service.displayStudent(id);
                    break;
                case 0:
                    System.out.println("İyi günler...");
                    break;
                default:
                    System.out.println("Hatalı giriş !!!");

            }

        }while(select != 0);

    }
private static int getId(Scanner inp){
    System.out.println("Öğrenci id giriniz: ");
    int id=inp.nextInt();
    inp.nextLine();
        return id;
}





}
