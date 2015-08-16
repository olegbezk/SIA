package com.sia.tutorial.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sia.tutorial.Node;

@Repository
public class JdbcNodeRepository implements NodeRepository {

	private JdbcOperations jdbc;

	@Autowired
	public JdbcNodeRepository(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<Node> findRecentNodes() {
		return jdbc.query(
				"select id, message, created_at, latitude, longitude" + " from Node"
				+ " order by created_at desc limit 20", new NodeRowMapper());
	}

	@Override
	public List<Node> findNodes(long max, int count) {
		return jdbc.query(
		"select id, message, created_at, latitude, longitude"
		+ " from Node"
		+ " where id < ?"
		+ " order by created_at desc limit 20", new NodeRowMapper(), max);
	}

	@Override
	public Node findOne(long nodeId) {
		return jdbc.queryForObject(
		"select id, message, created_at, latitude, longitude" + " from Node"
		+ " where id = ?",
				new NodeRowMapper(), nodeId);
	}

	@Override
	public void save(Node node) {
		jdbc.update(
	    "insert into Node (message, created_at, latitude, longitude)"
		+ " values (?, ?, ?, ?)",
				node.getMessage(),
				node.getTime(),
				node.getLatitude(), 
				node.getLongitude());

	}

	private static class NodeRowMapper implements RowMapper<Node> {
		public Node mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Node(
					rs.getLong("id"),
					rs.getString("message"),
					rs.getDate("created_at"),
					rs.getDouble("longitude"),
					rs.getDouble("latitude"));
		}
	}

}
