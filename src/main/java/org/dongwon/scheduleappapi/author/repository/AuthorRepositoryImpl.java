package org.dongwon.scheduleappapi.author.repository;

import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.entity.Author;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

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

    @Override
    public Optional<Author> findById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT author_id, author_name, email, created_at, updated_at FROM authors WHERE author_id = ?";

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(Author.createAuthor(
                        rs.getLong("author_id"),
                        rs.getString("author_name"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, ps, rs);
        }
    }
    @Override
    public void update(Author author) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE authors SET author_name = ?, updated_at = ? WHERE author_id = ?";

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            int index = 1;
            ps.setString(index++, author.getAuthorName());
            ps.setTimestamp(index++, Timestamp.valueOf(author.getUpdatedAt()));
            ps.setLong(index++, author.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, ps, null);
        }


    }

    public void deleteById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM authors WHERE author_id = ?";
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            int index = 1;
            ps.setLong(index++, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, ps, null);
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
