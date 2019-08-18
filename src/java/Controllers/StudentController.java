/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import org.json.simple.*;

/**
 *
 * @author longnh
 */
@WebServlet(name = "StudentController", urlPatterns = {"/Controller/Student"})
public class StudentController extends HttpServlet {
    private DB db;
    private JsonFactory json_factory;
    public StudentController(){
        this.db = DB.get_instance();
        this.json_factory = new JsonFactory();
        System.out.println("hello ^^, db initialized!");
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res){
        PrintWriter out = null;
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        JSONObject ans = null;
        try {
            out = res.getWriter();
            String cmd = req.getParameter("command");
            switch(cmd){
                case Command.CREATE_STUDENT:
                    System.out.println(String.format("command [%s] received!", cmd));
                    ans = this.db.create_student(extract_student(req));
                    break;
                case Command.UPDATE_STUDENT:
                    System.out.println(String.format("command [%s] received!", cmd));
                    ans = this.db.update_student(extract_student(req));
                    break;
                case Command.DELETE_STUDENT:
                    System.out.println(String.format("command [%s] received!", cmd));
                    ans = this.db.delete_student_by_student_code(extract_student(req).getM_code());
                    break;
                case Command.GET_ALL_STUDENTS:
                    System.out.println(String.format("command [%s] received!", cmd));
                    ans = list_to_json(this.db.get_all_students());
                    break;
                default:
                    ans.clear();
                    ans.put("result", "ERROR! UNKNOWN COMMAND -> " + cmd);
            }
            out.write(ans.toJSONString());
        } catch (IOException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
    private Student extract_student(HttpServletRequest req){
        System.out.println(String.format("extracting Student object from Request -> [%s]", req.toString()));
        Student s = null;
        s = new Student(0, req.getParameter("ib_student_code"), req.getParameter("ib_student_first_name"), req.getParameter("ib_student_last_name"), req.getParameter("ib_student_date_of_birth"), req.getParameter("ib_student_place_of_birth"), req.getParameter("ib_student_phonenumber"), req.getParameter("ib_student_email"));
        return s;
    }
    
    private JSONObject list_to_json(List<Student> list){
        JSONObject j = this.json_factory.create_json(Type.OK);
        JSONArray j_arr = new JSONArray();
        for(Student s: list){
            JSONObject x = new JSONObject();
            x.put("id", s.getM_id());
            x.put("code", s.getM_code());
            x.put("first_name", s.getM_first_name());
            x.put("last_name", s.getM_last_name());
            x.put("date_of_birth", s.getM_date_of_birth());
            x.put("place_of_birth", s.getM_place_of_birth());
            x.put("phonenumber", s.getM_phonenumber());
            x.put("email", s.getM_email());
            j_arr.add(x);
        }
        j.put("data", j_arr);
        return j;
    }
}