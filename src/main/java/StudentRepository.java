import java.sql.*;

//4-DB ye bağlanacak olan sınıf:Connection,Statement,PreparedStatement
public class StudentRepository {

    private Connection conn;
    private Statement st;
    private PreparedStatement prst;


    //5-connection oluşturma için metod
    private void setConnection() {
        try {
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //6-statement oluşturma için method
    private void setStatement() {
        try {
            this.st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //7-preparedstatement oluşturma için method
    private void setpreparedstatement(String sql) {
        try {
            this.prst = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //8-tablo oluşturma
    public void createTable() {
        setConnection();
        setStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS t_student(" +
                    "id SERIAL UNIQUE," +
                    "name VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0)," +//empty ""
                    "lastname VARCHAR(50) NOT NULL CHECK(LENGTH(lastname)>0)," +
                    "city VARCHAR(50) NOT NULL CHECK(LENGTH(city)>0)," +
                    "age INT NOT NULL CHECK(age>0))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    //12-Tabloya kayıt ekleme
    public void save(Student student) {
        setConnection();
        String sql = "INSERT INTO t_student(name,lastname,city,age) VALUES(?,?,?,?)";
        setpreparedstatement(sql);
        try {
            prst.setString(1, student.getName());
            prst.setString(2, student.getLastname());
            prst.setString(3, student.getCity());
            prst.setInt(4, student.getAge());
            prst.executeUpdate();
            System.out.println("Saved successfully...");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void findAll() {
        setConnection();
        setStatement();
        String query = "SELECT * FROM t_student";
        System.out.println("========== ALL STUDENT ============");
        try {
            ResultSet resulset = st.executeQuery(query);
            while (resulset.next()) {
                System.out.print("id: " + resulset.getInt("id"));
                System.out.print("   -ad: " + resulset.getString("name"));
                System.out.print("   -soyad: " + resulset.getString("lastname"));
                System.out.print("   -şehir: " + resulset.getString("city"));
                System.out.print("   -yaş: " + resulset.getInt("age"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //16-Tablodan id si verilen kaydı silme
    public void delete(int id) {
        setConnection();
        String query = "DELETE FROM t_student where id=?";
        setpreparedstatement(query);
        try {
            prst.setInt(1, id);
            int deleted = prst.executeUpdate();
            //Kayıt bulunursa deleted=1 olur
            if (deleted > 0) {
                System.out.println("Student is deleted successfully by id: " + id);
            } else {//Böyle bir kayıt yoksa deleted=0 olur
                System.out.println("Student could not found by id: " + id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //18-id si verilen kaydı tablodan getirme
    public Student findById(int id) {
        Student student = null;
        setConnection();
        String sql = "SELECT * FROM t_student WHERE id=?";
        setpreparedstatement(sql);
        try {
            prst.setInt(1, id);
            ResultSet resultSet = prst.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setLastname(resultSet.getString("lastname"));
                student.setCity(resultSet.getString("city"));
                student.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return student;
    }
//20- tablodaki id si verilen kaydı güncelleme
    public void update(Student foundStudent) {
        setConnection();
        String query="UPDATE t_student set name=?,lastname=?,city=?,age=? where id=?";
        setpreparedstatement(query);
        try {
            prst.setString(1, foundStudent.getName());
            prst.setString(2, foundStudent.getLastname());
            prst.setString(3, foundStudent.getCity());
            prst.setInt(4, foundStudent.getAge());
            prst.setInt(5, foundStudent.getId());
            prst.executeUpdate();
            if(prst.executeUpdate()>0){
                System.out.println("updated successfully..");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}