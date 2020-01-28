public class StackRefBased<T> implements Stack<T> {

	int count;
	StackNode<T> head;
	StackNode<T> next;
	
    public StackRefBased() 
	{
		count = 0;
		head = null;
    }

    public int size()
	{
        return count;
    }


    public boolean isEmpty() 
	{
		if (count == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
    }


    public void push(T data) 
	{
		StackNode<T> n = new StackNode<T>(data);
		if (head == null)
		{
			head = n;
		}
		else
		{
			n.next = head;
			head = n;
		}
		count++;
    }


    public T pop() throws StackEmptyException 
	{
		
		StackNode<T> p = head;
		if (p != null)
		{
			head = p.next;
			count--;
			return p.data;
		}
		else
		{
			throw new StackEmptyException();
		}
    }


    public T peek() throws StackEmptyException 
	{
		StackNode<T> p = head;
		if (p != null)
		{
			return p.data;
		}
		else
		{
			throw new StackEmptyException();
		}
    }


    public void makeEmpty() 
	{
		count = 0;
    }


    public String toString() 
	{
		String s = "";
		StackNode p = head;
		if (p == null)//empty
		{
			return s;
		}
		else//not empty
		{
			while (p.next != null)//while not the last item
			{
				s += p.data;
				p = p.next;
			}
			s += p.data;
		}
		return s;
      
    }
}

