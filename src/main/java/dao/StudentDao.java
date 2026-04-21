package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    private String baseSql = "select * from student where school_cd=? ";

    public Student get(String no) throws Exception {
        // 学生インスタンスを初期化
        Student student = new Student();
        // データベースへのコネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;

        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from student where no=?");
            // プリペアードステートメントに学生番号をバインド
            statement.setString(1, no);
            // プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();

            // 学校Daoを初期化
            SchoolDao schoolDao = new SchoolDao();
            School school = new School();
            school.setCd("tes");
            school.setName("テスト校");

            if (rSet.next()) {
                // リザルトセットが存在する場合
                // 学生インスタンスに検索結果をセット
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                // 学校フィールドには学校コードで検索した学校インスタンスをセット
                //student.setSchool(schoolDao.get(rSet.getString("school_cd")));
                student.setSchool(school);
            } else {
                // リザルトセットが存在しない場合
                // 学生インスタンスにnullをセット
                student = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return student;
    }

    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        // リストを初期化
        List<Student> list = new ArrayList<>();
        try {
            // リザルトセットを全権走査
            while (rSet.next()) {
                // 学生インスタンスを初期化
                Student student = new Student();
                // 学生インスタンスに検索結果をセット
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                student.setSchool(school);
                // リストに追加
                list.add(student);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = "and ent_year=? and class_num=?";
        // SQL文のソート
        String order = " order by no asc";

        // SQL文の在学フラグ条件
        String conditionIsAttend = "";
        // 在学フラグがtrueの場合
        if (isAttend) {
            conditionIsAttend = "and is_attend=true";
        }
        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            // プリペアードステートメントにクラス番号をバインド
            statement.setString(3, classNum);
            // プリペアードステートメントを実行
            rSet = statement.executeQuery();
            // リストへの格納処理を実行
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return list;
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = "and ent_year=? ";
        // SQL文のソート
        String order = " order by no asc";

        // SQL文の在学フラグ
        String conditionIsAttend = "";
        // 在学フラグがtrueだった場合
        if (isAttend) {
            conditionIsAttend = "and is_attend=true";
        }
        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            // プリペアードステートメントを実行
            rSet = statement.executeQuery();
            // リストへの格納処理を実行
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return list;
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文のソート
        String order = " order by no asc";

        // SQL文の在学フラグ
        String conditionIsAttend = "";
        // 在学フラグがtrueの場合
        if (isAttend) {
            conditionIsAttend = "and is_attend=true";
        }
        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントを実行
            rSet = statement.executeQuery();
            // リストへの格納処理を実行
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return list;
    }

    public boolean save(Student student) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int line = 0;
        
        try {
            // トランザクション開始
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(
                    "update student set name = ?, class_num = ?, is_attend = ? where no = ?");
            statement.setString(1, student.getName());
            statement.setString(2, student.getClassNum());
            statement.setBoolean(3, student.isAttend());
            statement.setString(4, student.getNo());
            
            line = statement.executeUpdate();

            if (line == 1) {
                connection.commit(); // 1件更新できたら確定
            } else {
                connection.rollback(); // それ以外（0件など）は取り消し
            }
            
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback(); // エラー時は必ずロールバック
            }
            throw e;
        } finally {
            // リソースのクローズ（この順番が鉄則）
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { /* 無視 */ }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // 元に戻す
                    connection.close();
                } catch (SQLException sqle) { /* 無視 */ }
            }
        }
        
        return line == 1; // 1件更新できればtrue、そうでなければfalse
    }
    
    public boolean insert(Student student) throws Exception {
        String checkSql = "select count(*) from student where no = ?";
        String insertSql = "insert into student (no, name, ent_year, class_num, is_attend, school_cd) values (?, ?, ?, ?, ?, ?)";
        
        // 接続を取得
        Connection connection = getConnection();
        
        try {
            connection.setAutoCommit(false); // 最初にオートコミットをオフにする

            // 1. 重複チェック
            try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                checkStmt.setString(1, student.getNo());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        return false; // 重複あり
                    }
                }
            }

            // 2. INSERT実行
            int line = 0;
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                insertStmt.setString(1, student.getNo());
                insertStmt.setString(2, student.getName());
                insertStmt.setInt(3, student.getEntYear());
                insertStmt.setString(4, student.getClassNum());
                insertStmt.setBoolean(5, student.isAttend());
                insertStmt.setString(6, student.getSchool().getCd()); // ※ここが親テーブルにあるか確認！

                line = insertStmt.executeUpdate();
            }

            if (line == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }

        } catch (Exception e) {
            if (connection != null) connection.rollback();
            throw e; // エラー内容をコンソールで確認してください
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }


}