package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {

    public Teacher login(String id, String password) throws Exception {

        Teacher teacher = null;

        String sql = "SELECT * FROM TEACHER WHERE ID = ? AND PASSWORD = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getString("ID"));
                teacher.setName(rs.getString("NAME"));

                School school = new School();
                school.setCd(rs.getString("SCHOOL_CD"));
                teacher.setSchool(school);
            }
        }

        return teacher;
    }
}