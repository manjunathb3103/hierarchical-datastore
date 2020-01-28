package com.HierarchialDataStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class HierarchialDataStore implements Handlers{
	
	static Node root = new Node("root", "");

	@Override
	public void createNode(String path, String data) {
		// TODO Auto-generated method stub
		Node newNode = new Node();
		String name;
		
		if (path.equals("root"))
		{
			root.data = data;
		}
		
		else {
			String[] pathArray = path.split("/");
			name = pathArray[pathArray.length - 1];
			newNode.setData(data);
			newNode.SetName(name);	
			Node tempNode = new Node();
			for (int i = 0; i < pathArray.length-1; i++) {
				if (i == 0)
					tempNode = root;
				else
					tempNode = tempNode.getChild(pathArray[i]);
			}
			tempNode.addChild(newNode);
		}
		
	}

	@Override
	public void updateNode(String path, String data) {
		// TODO Auto-generated method stub
		
		if (path.equals("root"))
		{
			if (root == null)
				System.out.println("Node root does not exist\n");
			else
				root.data = data;
		}
		
		else {
			String[] pathArray = path.split("/");
			Node tempNode = new Node();
			for (int i = 0; i < pathArray.length; i++) {
				if (i == 0)
					tempNode = root;
				else
					tempNode = tempNode.getChild(pathArray[i]);
			}
			if (tempNode != null)
				tempNode.setData(data);
			else
				System.out.println("Update operation failed\n");
		}
		
	}

	@Override
	public void deleteNode(String path) {
		// TODO Auto-generated method stub
		
		if (path.equals("root"))
		{
			if (root != null)
				root = null;
			else
				System.out.println("Node \"root\" does not exist\n");
		}
		
		else {
			String[] pathArray = path.split("/");
			Node tempNode = new Node();
			String name = pathArray[pathArray.length - 1];
			for (int i = 1; i < pathArray.length; i++) {
				if (i == 1)
					tempNode = root;
				else
					tempNode = tempNode.getChild(pathArray[i]);
			}
			if (tempNode != null)
				tempNode.children.remove(name);
			else
				System.out.println("Delete operation failed\n");
		}
		
	}

	@Override
	public String getData(String path) {
		// TODO Auto-generated method stub
		if (path.equals("root"))
		{
			if (root != null)
				return root.data;
			else
				System.out.println("Node root does not exist\n");
		}
		
		else {
			String[] pathArray = path.split("/");
			Node tempNode = null;
			for (int i = 0; i < pathArray.length; i++) {
				if (i == 0)
					tempNode = root;
				else
					tempNode = tempNode.getChild(pathArray[i]);
			}
			if (tempNode != null)
				return tempNode.data;
			else 
				System.out.println("Requested node does not exist\n");
		}
		return null;
	}

	@Override
	public Set<String> getDirectChildren(String path) {
		// TODO Auto-generated method stub
		Set<String> children = new TreeSet<String>();
		if (path.equals("root"))
		{
			if (root != null)
				children = root.children.keySet();
			else
				System.out.println("Node root does not exist\n");
		}
		
		else {
			String[] pathArray = path.split("/");
			Node tempNode = null;
			for (int i = 0; i < pathArray.length; i++) {
				if (i == 0)
					tempNode = root;
				else
					tempNode = tempNode.getChild(pathArray[i]);
			}
			if (tempNode != null)
				children = tempNode.children.keySet();
			else
				System.out.println("Requested node does not exist\n");
		}
		return children;
	}
	
	public static void main(String[] args) throws IOException {
		while (true) {
			//Display Options
			System.out.println("Select the Operation\n" 
							+ "1. Create Node\n" 
							+ "2. Delete Node\n" 
							+ "3. Update Node\n"
							+ "4. List Children of a Node\n" 
							+ "5. Get Data of a Node\n" 
							+ "6. exit");
			
			BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
			
			int choice = Integer.parseInt(obj.readLine());
			HierarchialDataStore HDS = new HierarchialDataStore();

			// Declaring variables used in switch statement
			String path; // Customer ID
			String data;
			switch (choice) {
				case 1:
					System.out.println("Enter the path\n");
					path = obj.readLine();
					System.out.println("Enter the data\n");
					data = obj.readLine();
					HDS.createNode(path, data);
					break;
				case 2:
					System.out.println("Enter the path of the node to be deleted\n");
					path = obj.readLine();
					HDS.deleteNode(path);
					break;
				case 3:
					System.out.println("Enter the path\n");
					path = obj.readLine();
					System.out.println("Enter the data\n");
					data = obj.readLine();
					HDS.updateNode(path, data);
					break;
				case 4:
					Set<String> children = null;
					System.out.println("Enter the path\n");
					path = obj.readLine();
					children = HDS.getDirectChildren(path);
					for (String child : children) {
						System.out.print(child + "\n");
					}
					break;
				case 5:
					System.out.println("Enter the path\n");
					path = obj.readLine();
					data = HDS.getData(path);
					System.out.print(data + "\n");
					break;
				case 6:
					return;
				default:
					System.out.println("Invalid Option\n");
					break;
				}
			}
		}
	}
