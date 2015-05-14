package hr.fer.oop.lab1.topic1.prob2;
/**
 * 
 * @author josipGatjal
 *
 */
public class TreeProgram {
	
	static class TreeNode {
		TreeNode left;
		TreeNode right;
		String data;
	}
	//bla
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	  TreeNode node = null;
		
		node = insert(node, "Jasna");
		node = insert(node, "Ana");
		node = insert(node, "Ivana");
		node = insert(node, "Anamarija");
		node = insert(node, "Vesna");
		node = insert(node, "Kristina");
		
		System.out.println("Writing tree inorder:");
		writeTree(node);
		
		node = reverseTreeOrder(node);
		
		System.out.println("Writing reversed tree inorder:");
		writeTree(node);
		
		int size = sizeOfTree(node);
		System.out.println("Number of nodes in tree is "+size+".");
		
		boolean found =containsData(node, "Kristina");
		System.out.println("Searched element is found: " + found);
		
	}
	
	/**
	 * 
	 * @param treeRoot (root of the tree)
	 * @param data (the data that the node contains)
	 * @return boolean
	 */
	static boolean containsData(TreeNode treeRoot, String data) {
		
		while (!((treeRoot.data).equals(data))) {
			
			boolean provjera = (data.compareTo(treeRoot.data) < 0);
			
			if (provjera == true) {
				treeRoot = treeRoot.right;
	
			}
			
			else {
				
				treeRoot = treeRoot.left;
			}
			
			if (treeRoot == null){
				
				return false;
			}
			
		}
		
		return true;
		
	}
	/**
	 * 
	 * @param treeRoot
	 * @return int (number of nodes)
	 */
	static int sizeOfTree(TreeNode treeRoot) {
		
		if (treeRoot != null){
			
			return 1 + sizeOfTree(treeRoot.left) + sizeOfTree(treeRoot.right);
			
			
		}
		
		return 0;
		
		
	}
	
	/**
	 * 
	 * @param treeRoot
	 * @param data
	 * @return treeRoot
	 */
	static TreeNode insert(TreeNode treeRoot, String data) {
		//stvaranje novog korijena
		TreeNode novi = new TreeNode();
		novi.data = data;
		
		
		//ako nema korijena,stvori ga
		if (treeRoot == null){
			treeRoot = novi;
			treeRoot.data = novi.data;
		}
		
		
		
		else {
			
			TreeNode parent;
			TreeNode trenutniNode = treeRoot;
			
			while (true){
				
				parent = trenutniNode;
				
				boolean provjera = (data.compareTo(trenutniNode.data) < 0);
				
				//ako je manji tj. treba ici lijevo
				if (provjera == true) {
					
					trenutniNode = trenutniNode.left;
					
					if (trenutniNode == null){
						
						parent.left = novi;
						
						return treeRoot;
						
					}
				}
				
				else {
					//inace idi desno
					trenutniNode = trenutniNode.right;
					
					if (trenutniNode == null){
						
						parent.right = novi;
						
						return treeRoot;
					}
				}
			}
		}
		return treeRoot;
	}
	
	/**
	 * 
	 * @param treeRoot
	 */
	static void writeTree(TreeNode treeRoot) {
		
		if (treeRoot != null){
			
			writeTree(treeRoot.left);
			
			System.out.println(treeRoot.data);
			
			writeTree(treeRoot.right);
		}
	}
	
	/**
	 * 
	 * @param treeRoot
	 * @return treeRoot
	 */
	static TreeNode reverseTreeOrder(TreeNode treeRoot) {
		if (treeRoot != null) {
		
			TreeNode tmp = treeRoot.left;
			treeRoot.left = treeRoot.right;
			treeRoot.right = tmp;
			
			reverseTreeOrder(treeRoot.left);
			reverseTreeOrder(treeRoot.right);
		
		}
		
		return treeRoot;
	}
		
	
}
