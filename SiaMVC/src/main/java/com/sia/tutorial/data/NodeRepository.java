package com.sia.tutorial.data;

import com.sia.tutorial.Node;

import java.util.List;

public interface NodeRepository {
	
	List<Node> findRecentNodes();

	List<Node> findNodes(long max, int count);

	Node findOne(long nodeId);
	
	void save(Node node);

}
