package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {

    public List<Test> filter(String schoolCd, int entYear, String classNum, String subjectCd, int no) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select t.* from test t " +
                "join student s on t.student_no = s.no " +
                "where t.school_cd = ? and s.ent_year = ? and t.class_num = ? " +
                "and t.subject_cd = ? and t.no = ? order by t.student_no"
            );
            statement.setString(1, schoolCd);
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, subjectCd);
            statement.setInt(5, no);
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Test test = new Test();
                test.setStudentNo(rSet.getString("student_no"));
                test.setSubjectCd(rSet.getString("subject_cd"));
                test.setSchoolCd(rSet.getString("school_cd"));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getObject("point") != null ? rSet.getInt("point") : null);
                test.setClassNum(rSet.getString("class_num"));
                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
        return list;
    }

    public boolean save(Test test) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "merge into test (student_no, subject_cd, school_cd, no, point, class_num) " +
                "key(student_no, subject_cd, school_cd, no) values (?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, test.getStudentNo());
            statement.setString(2, test.getSubjectCd());
            statement.setString(3, test.getSchoolCd());
            statement.setInt(4, test.getNo());
            statement.setObject(5, test.getPoint());
            statement.setString(6, test.getClassNum());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
    }
    
    // 参照で追加
    public List<Test> filterBySubject(String schoolCd, Integer entYear, String classNum, String subjectCd) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            StringBuilder sql = new StringBuilder(
                "select t.*, s.name as student_name, sub.name as subject_name " +
                "from test t " +
                "join student s on TRIM(t.student_no) = TRIM(s.no) " +
                "join subject sub on t.subject_cd = sub.cd " +
                "where t.school_cd = ? and t.point is not null"
            );

            if (entYear != null) {
                sql.append(" and s.ent_year = ?");
            }
            if (classNum != null && !classNum.isEmpty()) {
                sql.append(" and t.class_num = ?");
            }
            if (subjectCd != null && !subjectCd.isEmpty()) {
                sql.append(" and t.subject_cd = ?");
            }

            sql.append(" order by t.student_no, t.no");

            statement = connection.prepareStatement(sql.toString());

            int index = 1;
            statement.setString(index++, schoolCd);

            if (entYear != null) {
                statement.setInt(index++, entYear);
            }
            if (classNum != null && !classNum.isEmpty()) {
                statement.setString(index++, classNum);
            }
            if (subjectCd != null && !subjectCd.isEmpty()) {
                statement.setString(index++, subjectCd);
            }

            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                Test test = new Test();
                test.setStudentNo(rSet.getString("student_no"));
                test.setSubjectCd(rSet.getString("subject_cd"));
                test.setSchoolCd(rSet.getString("school_cd"));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getObject("point") != null ? rSet.getInt("point") : null);
                test.setClassNum(rSet.getString("class_num"));
                test.setStudentName(rSet.getString("student_name"));
                test.setSubjectName(rSet.getString("subject_name"));
                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
        return list;
    }
    public List<Test> filterByStudent(String studentNo) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select t.*, s.name as student_name, sub.name as subject_name " +
                "from test t " +
                "join student s on TRIM(t.student_no) = TRIM(s.no) " +
                "join subject sub on t.subject_cd = sub.cd " +
                "where t.student_no = ? and t.point is not null " +
                "order by t.subject_cd, t.no"
            );
            statement.setString(1, studentNo);

            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                Test test = new Test();
                test.setStudentNo(rSet.getString("student_no"));
                test.setSubjectCd(rSet.getString("subject_cd"));
                test.setSchoolCd(rSet.getString("school_cd"));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getObject("point") != null ? rSet.getInt("point") : null);
                test.setClassNum(rSet.getString("class_num"));
                test.setStudentName(rSet.getString("student_name"));
                test.setSubjectName(rSet.getString("subject_name"));
                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
        return list;
    }
}
