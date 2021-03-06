package com.dds.interfaces;

import com.dds.cluster.Node;

public interface RoutingInterface {
	public void addNode(Node node);
	public void removeNode(Node node);
	public Node getNode(Object key);
	public void setupRoutingCluster();
}
