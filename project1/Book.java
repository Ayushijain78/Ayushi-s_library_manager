package project1;
class Book
{
	int isbn,price,qty;
	String author,name,status;
	Book(){}
	Book(int isbn,String name,String author,String status,int price,int qty)
	{
		this.isbn=isbn;
		this.author=author;
		this.price=price;
		this.name=name;
		this.status=status;//a
		this.qty=qty;
	}
	boolean isAvailable()
	{
		if(this.status.equals("available"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	void setqty(int qty)
	{
		this.qty=qty;
	}
	int getqty()
	{
		return qty;
	}
	void setstatus(String status)
	{
		this.status=status;
	}
	String getstatus()
	{
		return status;
	}
	void updateStatus(String Status)
	{
		 this.status=status;
	}
	
	void setauthor(String author)
	{
		this.author=author;
	}
	String getauthor()
	{
		return author;
		
	}
	void setname(String name)
	{
		this.name=name;
	}
	String getname()
	{
		return name;
	}
	void setprice(int price)
	{
		this.price=price;
	}
	int getprice()
	{
		return price;
	}
	void showBook()
	{
		System.out.println("isbn number:-"+""+isbn);
		System.out.println("title:-"+""+name);
		System.out.println("author:-"+""+author);
		System.out.println("status:---"+""+status+"    "+isAvailable());
		
	}
	int getisbn() 
	{
		// TODO Auto-generated method stub
		return isbn;
	}
	void setisbn(int isbn)
	{
		this.isbn=isbn;
	}




}