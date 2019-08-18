/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author longnh
 */
public class Student {
    private int m_id;
    private String m_code;
    private String m_first_name;
    private String m_last_name;
    private String m_date_of_birth;
    private String m_place_of_birth;
    private String m_phonenumber;
    private String m_email;

    public Student(int m_id, String m_code, String m_first_name, String m_last_name, String m_date_of_birth, String m_place_of_birth, String m_phonenumber, String m_email) {
        this.m_id = m_id;
        this.m_code = m_code;
        this.m_first_name = m_first_name;
        this.m_last_name = m_last_name;
        this.m_date_of_birth = m_date_of_birth;
        this.m_place_of_birth = m_place_of_birth;
        this.m_phonenumber = m_phonenumber;
        this.m_email = m_email;
    }

    public int getM_id() {
        return m_id;
    }

    public String getM_code() {
        return m_code;
    }

    public String getM_first_name() {
        return m_first_name;
    }

    public String getM_last_name() {
        return m_last_name;
    }

    public String getM_date_of_birth() {
        return m_date_of_birth;
    }

    public String getM_place_of_birth() {
        return m_place_of_birth;
    }

    public String getM_phonenumber() {
        return m_phonenumber;
    }

    public String getM_email() {
        return m_email;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public void setM_code(String m_code) {
        this.m_code = m_code;
    }

    public void setM_first_name(String m_first_name) {
        this.m_first_name = m_first_name;
    }

    public void setM_last_name(String m_last_name) {
        this.m_last_name = m_last_name;
    }

    public void setM_date_of_birth(String m_date_of_birth) {
        this.m_date_of_birth = m_date_of_birth;
    }

    public void setM_place_of_birth(String m_place_of_birth) {
        this.m_place_of_birth = m_place_of_birth;
    }

    public void setM_phonenumber(String m_phonenumber) {
        this.m_phonenumber = m_phonenumber;
    }

    public void setM_email(String m_email) {
        this.m_email = m_email;
    }

    @Override
    public String toString() {
        return "Student{" + "m_id=" + m_id + ", m_code=" + m_code + ", m_first_name=" + m_first_name + ", m_last_name=" + m_last_name + ", m_date_of_birth=" + m_date_of_birth + ", m_place_of_birth=" + m_place_of_birth + ", m_phonenumber=" + m_phonenumber + ", m_email=" + m_email + '}';
    }
    
    
    
}
