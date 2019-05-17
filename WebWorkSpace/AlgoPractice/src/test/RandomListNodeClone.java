package test;
/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * 注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class RandomListNodeClone {
	//method one:
	public RandomListNode Clone(RandomListNode pHead) 
	{
		if(pHead==null)	return null;
		RandomListNode newHead = null;
		//连接  A-A'-B-B'-C-C'-D-D'-E-E'-F-F'-G-G'
		for(RandomListNode node = pHead; node!=null;) {
			newHead = cloneNode(node);
			node.next = newHead;
			node = newHead.next;
		}
		//更改A' B'...的random
		for(RandomListNode node = pHead; node!=null;node=node.next.next) 
			if(node.random!=null)
				node.next.random = node.random.next;
		
		newHead = pHead.next;
		for(RandomListNode node = pHead,newNode=newHead; 
				node!=null;node=node.next,newNode=newNode.next) {
			node.next = node.next.next;
			newNode.next = node.next.next;
		}
		return newHead;
	}
	private RandomListNode cloneNode(RandomListNode node) {
		RandomListNode newNode = new RandomListNode(node.label);
		newNode.next = node.next;
		return newNode;
	}
	//method two:
	public RandomListNode Clone2(RandomListNode pHead)
    {
		RandomListNode clone = cloneNodeExceptRandom(pHead);
		setRandom(clone, pHead);
        return clone;
    }
	private RandomListNode cloneNodeExceptRandom(RandomListNode node) {
		if(node == null)	return null;
		RandomListNode clone = new RandomListNode(node.label);
		clone.next = cloneNodeExceptRandom(node.next);
		return clone;
	}
	private void setRandom(RandomListNode newList,RandomListNode oldList) {
		for(RandomListNode newNode = newList,oldNode = oldList;
				newNode!=null;newNode = newNode.next,oldNode = oldNode.next)
		{
			for(RandomListNode old = oldList,neww = newList;
					old!=null;old = old.next,neww = neww.next) 
			{
				if(old == oldNode.random)
				{
					newNode.random = neww;
					break;
				}
			}
		}
	}
}
class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
