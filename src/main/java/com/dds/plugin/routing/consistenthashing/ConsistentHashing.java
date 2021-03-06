package com.dds.plugin.routing.consistenthashing;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import com.dds.interfaces.HashingInterface;
import com.dds.interfaces.RoutingInterface;
import com.dds.utils.Helper;
import com.dds.cluster.Node;

public class ConsistentHashing implements RoutingInterface {

	private final HashingInterface hashFunction;
	private final int numberOfReplicas;
	private final TreeMap<Integer, Node> circle = new TreeMap<Integer, Node>();
	private Collection<Node> nodes;

	public ConsistentHashing(
			HashingInterface hashFunction, 
			int numberOfReplicas, 
			Collection<Node> nodes) {
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;
		this.nodes = nodes;
	}
	
	public void setupRoutingCluster()
	{

		for (Node node : nodes) {
			addNode(node);
		}
	}
	
	public void addNode(Node node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.put(hashFunction.hash(node.toString().getBytes()), node); // Need to discuss
		}
	}

	public void removeNode(Node node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString().getBytes()));
		}
	}

	public Node getNode(Object key) {
		if (circle.isEmpty()) {
			return null;
		}
		int hash = hashFunction.hash(Helper.getBytes(key));
		if (!circle.containsKey(hash)) {
			SortedMap<Integer, Node> tailMap = circle.tailMap(hash);
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		return circle.get(hash);
	}
}