/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author longnh
 */
public class DB {
    private String m_hostname = "localhost";
    private String m_username = "root";
    private String m_password = "1111";
    private String m_port = "3306";
    private String m_dbname = "qlsv";
    private String m_connection_url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=yes&characterEncoding=UTF-8", m_hostname, m_port, m_dbname);
    private Connection conn;
    private static DB instance;
    private JsonFactory json_factory;
    //
    private final String TBL_STUDENT = "student";
    private final String COL_STUDENT_ID = "id";
    private final String COL_STUDENT_CODE = "code";
    private final String COL_STUDENT_FIRST_NAME = "first_name";
    private final String COL_STUDENT_LAST_NAME = "last_name";
    private final String COL_STUDENT_D_O_B = "date_of_birth";
    private final String COL_STUDENT_P_O_B = "place_of_birth";
    private final String COL_STUDENT_PHONENUMBER = "phonenumber";
    private final String COL_STUDENT_EMAIL = "email";
    // singleton constructor
    private DB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(m_connection_url, m_username, m_password);
            this.conn.createStatement().execute("SET NAMES utf8mb4");
            json_factory = new JsonFactory();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static class BillPughSingleton{
        private static final DB INSTANCE = new DB();
    }
    public static DB get_instance(){
        return BillPughSingleton.INSTANCE;
    }
    // end of constructor
    
    public boolean is_exist(String s_code){
        try {
            System.out.println("is_exist() performed! arg -> " + s_code);
            String query = String.format("SELECT id FROM %s WHERE %s = '%s';", TBL_STUDENT, COL_STUDENT_CODE, s_code);
            System.out.println(String.format("select query -> [%s]", query));
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) return true;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int get_student_id_by_student_code(String s_code){
        System.out.println("get_student_id_by_student_code() performed! given s_code = " + s_code);
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s';", COL_STUDENT_ID, TBL_STUDENT, COL_STUDENT_CODE, s_code);
        System.out.println(String.format("select query -> [%s]", query));
        ResultSet rs;
        int s_id = -1;
        try {
            rs = this.conn.createStatement().executeQuery(query);
            if(rs.next()){
            s_id = rs.getInt(1);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s_id;
    }
    
    public JSONObject create_student(Student s){
        System.out.println(String.format("create_student() performed! arg -> [%s]", s.toString()));
        JSONObject j = null;
        try {
            if(!is_exist(s.getM_code())){
                System.out.println(String.format("Inserting... student_code = [%s]", s.getM_code()));
                String query = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');", TBL_STUDENT, COL_STUDENT_CODE, COL_STUDENT_FIRST_NAME, COL_STUDENT_LAST_NAME, COL_STUDENT_D_O_B, COL_STUDENT_P_O_B, COL_STUDENT_PHONENUMBER, COL_STUDENT_EMAIL, s.getM_code(), s.getM_first_name(), s.getM_last_name(), s.getM_date_of_birth(), s.getM_place_of_birth(), s.getM_phonenumber(), s.getM_email());
                System.out.println(String.format("insert query -> [%s]", query));
                Statement stmt = this.conn.createStatement();
                int row_cnt = stmt.executeUpdate(query);
                System.out.println(String.format("[%d] row affected!", row_cnt));
            } else {
                System.out.println(String.format("Student with student_code = [%s] existed in database! INSERT DENIED!", s.getM_code()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            j = json_factory.create_json(Type.ERROR);
            return j;
        }
        j =  json_factory.create_json(Type.OK);
        j.put("student_id", get_student_id_by_student_code(s.getM_code()));
        return j;
    }
    
    public JSONObject update_student(Student s){
        System.out.println(String.format("update_student() performed! arg -> [%s]", s.toString()));
        JSONObject j = null;
        try {
            if(is_exist(s.getM_code())){
                System.out.println(String.format("Student with student_code [%s] existed in database!", s.getM_code()));
                String query = String.format("UPDATE %s SET %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = '%s';", TBL_STUDENT, COL_STUDENT_CODE, s.getM_code(), COL_STUDENT_FIRST_NAME, s.getM_first_name(), COL_STUDENT_LAST_NAME, s.getM_last_name(), COL_STUDENT_D_O_B, s.getM_date_of_birth(), COL_STUDENT_P_O_B, s.getM_place_of_birth(), COL_STUDENT_PHONENUMBER, s.getM_phonenumber(), COL_STUDENT_EMAIL, s.getM_email(), COL_STUDENT_CODE, s.getM_code());
                System.out.println(String.format("update query -> [%s]", query));
                int row_cnt = this.conn.createStatement().executeUpdate(query);
                System.out.println(String.format("[%d] row affected!", row_cnt));
            } else {
                System.out.println(String.format("Student with student_code [%s] not existed in database! ABORT", s.getM_code()));
            }
        } catch (Exception ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            j = json_factory.create_json(Type.ERROR);
            return j;
        }
        j =  json_factory.create_json(Type.OK);
        j.put("student_id", get_student_id_by_student_code(s.getM_code()));
        return j;
    }
    
    public JSONObject delete_student_by_student_code(String s_code){
        System.out.println("delete_student_by_code() performed! s_code = " + s_code);
        JSONObject j = null;
        if(is_exist(s_code)){
            String query = String.format("DELETE FROM %s WHERE %s = '%s';", TBL_STUDENT, COL_STUDENT_CODE, s_code);
            System.out.println(String.format("delete query -> [%s]", query));
            int row_cnt = 0;
            try {
                row_cnt = this.conn.createStatement().executeUpdate(query);
                j = json_factory.create_json(Type.OK);
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                j = json_factory.create_json(Type.ERROR);
            }
            System.out.println(String.format("[%d] row affected!", row_cnt));
        } else {
            System.out.println(String.format("Student with student_code [%s] not existed in database! ABORT", s_code));
        }
        return j;
    }
    
    public List<Student> get_all_students(){
        List<Student> rtn = new ArrayList<>();
        try {
            System.out.println("get_all_students() performed!");
            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s FROM %s", COL_STUDENT_ID, COL_STUDENT_CODE, COL_STUDENT_FIRST_NAME, COL_STUDENT_LAST_NAME, COL_STUDENT_D_O_B, COL_STUDENT_P_O_B, COL_STUDENT_PHONENUMBER, COL_STUDENT_EMAIL, TBL_STUDENT);
            System.out.println(String.format("select query -> [%s]", query));
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int row_cnt = 0;
            while(rs.next()){
                row_cnt++;
                Student s = new Student(rs.getInt(COL_STUDENT_ID), rs.getString(COL_STUDENT_CODE), rs.getString(COL_STUDENT_FIRST_NAME), rs.getString(COL_STUDENT_LAST_NAME), rs.getString(COL_STUDENT_D_O_B), rs.getString(COL_STUDENT_P_O_B), rs.getString(COL_STUDENT_PHONENUMBER), rs.getString(COL_STUDENT_EMAIL));
                System.out.println(s.toString());
                rtn.add(s);
            }
            System.out.println(String.format("[%d] record found!", row_cnt));
        } catch (Exception ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtn;
    }
    
}
