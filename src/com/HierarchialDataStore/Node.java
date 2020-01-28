package com.HierarchialDataStore;

import java.util.HashMap;
import java.util.Map;

public class Node {
	String name;
	String data;
	HashMap<String, Node> children;
	
	public Node() {
		children = new HashMap<String, Node>();
	};
	
	public Node(String name, String data)
	{
		this.name = name;
		this.data = data;
		children = new HashMap<String, Node>();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void SetName(String name)
	{
		this.name = name;
	}
	
	public String getData()
	{
		return this.data;
	}
	
	public void setData(String data)
	{
		this.data = data;
	}
	
	public Node getChild(String name)
	{
		return this.children.get(name);
	}

	public void addChild(Node newNode) {
		// TODO Auto-generated method stub
		this.children.put(newNode.name, newNode);
	}
}
