
import Controllers.*;
import Models.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author longnh
 */
public class Main {
    public static void main(String[] args) {
        DB db = DB.get_instance();
        Student s = new Student(0, "B16DCCN276", "Phạm Thị", "Phươngg", "??/??/1998", "Hà Tây", "0922222222", "minhphuong@gmail.com");
        System.out.println(db.create_student(s));
        db.get_all_students();
//        db.delete_student_by_student_code("B15DCCN528");
//        db.get_all_students();
//        db.update_student(s);
//        db.get_all_students();
    }
}
