package org.dongwon.scheduleappapi.author.repository;

import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.entity.Author;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final DataSource dataSource;

    @Override
    public Long save(Author author) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO authors (author_name, email, created_at, updated_at) VALUES (?, ?, ?, ?)";

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            ps.setString(index++, author.getAuthorName());
            ps.setString(index++, author.getEmail());
            ps.setTimestamp(index++, Timestamp.valueOf(author.getCreatedAt()));
            ps.setTimestamp(index++, Timestamp.valueOf(author.getUpdatedAt()));

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return -1L;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, ps, rs);
        }
    }

    private void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) conn.close();
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
