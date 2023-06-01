import java.util.Scanner;

//3-Student ile ilgili metodlar
//4-DB e bağlanma,DB işlemleri için yeni bir repo class oluşturma
public class StudentService {
Scanner inp=new Scanner(System.in);
    //repository classındaki metotları kullanabilmek için obje oluşturalım
    private StudentRepository repo=new StudentRepository();

    //9-student için tablo oluşturma
    public void createStudentTable(){
        repo.createTable();
    }
    //11-Öğrenciyi kaydetme
    public void saveStudent(){
        System.out.println("AD:  ");//lenghth("  asd  ")=7 trim("    ")->""
        String name=inp.nextLine().trim();//asd
        System.out.println("SOYAD:  ");
        String lastname=inp.nextLine().trim();
        System.out.println("ŞEHİR:  ");
        String city=inp.nextLine().trim();
        System.out.println("YAŞ:  ");
        int age=inp.nextInt();
        inp.nextLine();
        Student newStudent=new Student(name,lastname,city,age);
        repo.save(newStudent);
    }

//13-ADIM:Tüm öğrencileri Listeleme
    public void getAllStudent() {
        repo.findAll();
    }

    //15--id si verilen student silme
    public void deleteStudent(int id) {
        repo.delete(id);
    }
    //17-id si verilen öğrenciyi bulma
    public Student getStudentById(int id){
      Student student=repo.findById(id);
      return student;
    }
    //19-id si verilen öğrenciyi güncelleme
    public void updateStudent(int id){
        //id ile kayıtlı öğrenci mevcut mu?
        Student foundStudent=getStudentById(id);
        if(foundStudent==null){
            System.out.println("Student does not exist by id: "+id);
        }else{
            System.out.println("AD:  ");//lenghth("  asd  ")=7 trim("    ")->""
            String name=inp.nextLine().trim();//asd
            System.out.println("SOYAD:  ");
            String lastname=inp.nextLine().trim();
            System.out.println("ŞEHİR:  ");
            String city=inp.nextLine().trim();
            System.out.println("YAŞ:  ");
            int age=inp.nextInt();
            inp.nextLine();

            //bulunan öğrenci özelliklerini güncelle
            foundStudent.setName(name);
            foundStudent.setLastname(lastname);
            foundStudent.setCity(city);
            foundStudent.setAge(age);
            //id si aynı kalacak
            repo.update(foundStudent);

        }
    }
//21 id si verilen öğrenciyi görüntüleme
    public void displayStudent(int id) {
        Student student=getStudentById(id);
        if(student==null){
            System.out.println("Student does not exist by id:"+id);
        }else{
            System.out.println(student);
        }
    }
}
