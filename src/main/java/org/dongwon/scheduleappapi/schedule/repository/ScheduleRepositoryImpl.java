package org.dongwon.scheduleappapi.schedule.repository;

import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.entity.Schedule;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository{
    private final DataSource dataSource;
    @Override
    public Long save(Schedule schedule) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "INSERT INTO schedules (content, password, created_at, updated_at, author_id) VALUES (?, ?, ?, ?, ?)";


        try {
            // 리소스 초기화
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // 값 세팅
            int index = 1;
            ps.setString(index++, schedule.getContent());
            ps.setString(index++, schedule.getPassword());
            ps.setTimestamp(index++, Timestamp.valueOf(schedule.getCreatedAt()));
            ps.setTimestamp(index++, Timestamp.valueOf(schedule.getUpdatedAt()));
            ps.setLong(index++, schedule.getAuthorId());

            // 쿼리 실행
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
    public Optional<Schedule> findById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT schedule_id, password, content, created_at, updated_at, author_id FROM schedules WHERE schedule_id = ?";

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(Schedule.createSchedule(
                        rs.getLong("schedule_id"),
                        rs.getString("content"),
                        rs.getString("password"),
                        rs.getLong("author_id"),
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
    public void update(Schedule schedule) {

    }

    @Override
    public void delete(Schedule schedule) {

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
