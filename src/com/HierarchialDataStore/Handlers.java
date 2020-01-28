package com.HierarchialDataStore;

import java.util.Set;

public interface Handlers {
	
	void createNode(String path, String data);
	
	void updateNode(String path, String data);
	
	void deleteNode(String path);
	
	String getData(String path);
	
	Set<String> getDirectChildren(String path);

}
