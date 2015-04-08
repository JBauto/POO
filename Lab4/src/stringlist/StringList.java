package stringlist;

import java.util.*;

public class StringList {
		
	Node elem;
	Node head;
	Node current;
	Node previous;
	
	int length;
	
	public StringList (){
		elem = new Node("\0");
		head = elem; 		
		length = 0;
	}

	public void insert (String s){
		Node tmp =  new Node(s);
		elem.Nextstring = tmp;
		elem = tmp;
		length++;
	}
	public Node nextNode(Node x){
		return x.Nextstring;
	}
	public int List_length(){
		return length;
	}
	
	public int remove (String s){
		int i = 0;
		if (head == null)
			System.out.println("Vazio\n");
		else{
			current = head;
			while(current!=null && !current.s.equals(s)){
				previous = current;
				current = nextNode(current);
			}
			if(current == head && current.s.equals(s)){
				head = nextNode(head);
				i++;
			}else{
				if(current != null)
					previous.Nextstring = nextNode(current);
					i++;
			}
		}
	return i;
	}
	public static void main(String[] args) {
		int remove_cont;
		StringList root = new StringList();
		String s = "Ola";
		String s2 = "Adeus";
		String s3 = s + " " + s2;
		root.insert(s);
		root.insert(s2);
		root.insert(s3);
		System.out.println(root);
		System.out.println("List Length: "+root.List_length());
		System.out.println("Removed: "+ root.remove("Adeus") + "\n");
		System.out.println(root);

	}
	
	@Override
	public String toString() {
		return "StringList [head=" + head + ", length="
				+ length + "]";
	}

}
