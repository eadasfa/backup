package test;

import java.util.Random;

public class LastKLinkList {

	/**
	 * 输入一个链表，反转链表后，输出链表的所有元素。
	 */
	public ListNode ReverseList(ListNode head) {
		if(head == null)
			return null;
		ListNode newHead=head;
		ListNode temp = head.next;
		newHead.next = null;
		
		while(temp!=null) {
			ListNode ln = temp.next;
			temp.next = newHead;
			newHead = temp;
			temp = ln;
		}

		return newHead;
    }
	/*
	 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
	 */
	public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1==null)	return list2;
        if(list2==null)	return list1;
        ListNode newHead = new ListNode(list1.val>list2.val?(list2.val-1):(list1.val-1), null);
       	ListNode temp = newHead;
       	while(true) {
       		if(list1 == null) {
       			temp.next = list2;
       			break;
       		}
       		if(list2 == null) {
       			temp.next = list1;
       			break;
       		}
       		if(list1.val>=list2.val) {
       			temp.next = list2;
       			list2 = list2.next;
       			temp = temp.next;
       		}
       		else {
       			temp.next = list1;
       			list1 = list1.next;
       			temp = temp.next;
       		}
       	}
        
        
        return newHead.next;
    }

	
	static int n = 10;
	public static void main(String[] args) {
		
		LastKLinkList ll = new LastKLinkList();

		ListNode list1 = ll.produceALink();
		ListNode list2 = ll.produceALink();
		ll.printH(list1);
		ll.printH(list2);
		ListNode h = ll.Merge(list1, list2);
		ll.printH(h);
		
	}
	public void printH(ListNode head) {
		for(ListNode l=head;l!=null;l = l.next) 
			System.out.print(l.val+" ");
		System.out.println();
	}
	public ListNode produceALink() {
		ListNode head = null;
		int t = (int)(Math.random()*10+0);
		head = new ListNode(t,head);
		ListNode last = head;
		for(int i=1;i<n;i++) {
			t = (int)(Math.random()*10+t);
			last.next = new ListNode(t,null);
			last = last.next;
		}
		return head;
	}
}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val,ListNode next) {
        this.val = val;
        this.next = next;
    }
}
