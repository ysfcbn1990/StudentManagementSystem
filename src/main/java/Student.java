import java.util.Scanner;

//2-student ın özelliklerini içeren POJO class
public class Student {

    private int id;
    private String name;
    private String lastname;
    private String city;
    private int age;

    //parametreli/parametreli  const. oluşturma


    public Student() {}

    public Student(String name, String lastname, String city, int age) {
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                " name=" + name +
                " lastname=" + lastname +
                " city=" + city +
                " age=" + age ;

    }
}
