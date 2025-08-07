package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {
	private String name;
	private List<Book> books;
	
	public Library(String name) {
		this.name = name;
		this.books = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void addBook(Book book) {
		books.add(book);
		System.out.println("도서가 추가되었습니다: " + book.getTitle());
	}
	
	public Book findBookByTitle(String title) {
		for(Book book:books) {
			if(book.getTitle().equals(title)) {
				return book;
			}
		}
		return null;
	}
	
	public List<Book> findBooksByAuthor(String author) {
		List<Book> bookAuthor = new ArrayList<>();
		for(Book book:books) {
			if(book.getAuthor().equals(author)) {
				bookAuthor.add(book);
			}
		}
		return bookAuthor;
	}

	public Book findBookByISBN(String isbn) {
		for(Book book:books) {
			if(book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}
	
	public boolean checkOutBook(String isbn) {
		boolean result;
	    Book book = findBookByISBN(isbn);
	    if (book == null) {
	    	return false;
	    }
    	result = book.checkOut();
        return result;
	}
	
	public boolean returnBook(String isbn) {
	    Book book = findBookByISBN(isbn);
	    // 책 반납
	    if (book != null) {
	    	book.returnBook();
	        return true;
	    }
	    // 책 반납 오류
	    return false;
	}
	
	public List<Book> getAvailableBooks() {
		List<Book> availableBooks = new ArrayList<>();
		for(Book book:books) {
			if(book.isAvailable()) {
				availableBooks.add(book);
			}
		}
		return availableBooks;
	}
	
	public List<Book> getAllBooks() {
		return books;
	}
	
	public int getTotalBooks() {
		int num = books.size();
		return num;
	}
	
	public int getAvailableBooksCount() {
		int numA = 0;
		for(Book book:books) {
			if(book.isAvailable()) {
				numA++;
			}	
		}
		return numA;
	}
	
	public int getBorrowedBooksCount() {
		int numB = 0;
		for(Book book:books) {
			if(book.isAvailable() == false) {
				numB++;
			}	
		}
		return numB;
	}

}
