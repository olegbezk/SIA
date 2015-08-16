package com.sia.tutorial.data;

import java.util.List;

import com.sia.tutorial.Node;

public interface NodeRepository {
	
	List<Node> findRecentNodes();

	List<Node> findNodes(long max, int count);

	Node findOne(long nodeId);
	
	void save(Node node);

}
