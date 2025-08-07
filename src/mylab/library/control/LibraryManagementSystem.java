package mylab.library.control;

import java.util.ArrayList;
import java.util.List;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

public class LibraryManagementSystem {

	public static void main(String[] args) {
		// ������ ��ü�� ����
		Library library = new Library("�߾� ������");
		// ���� ������ �߰�
		addSampleBooks(library);
		// ������ ������ ���
		System.out.println("====" + library.getName() + "====");
		System.out.println("��ü ���� ��: " + library.getTotalBooks());
		System.out.println("���� ���� ���� ��: " + library.getAvailableBooksCount());
		System.out.println("���� ���� ���� ��: " + library.getBorrowedBooksCount());
		
		testFindBook(library);
		testCheckOut(library);
		testReturn(library);
		displayAvailableBooks(library);
	}
	
	private static void addSampleBooks(Library library) {
        library.addBook(new Book("�ڹ� ���α׷���", "���ڹ�", "978-89-01-12345-6", 2022));
        library.addBook(new Book("��ü������ ��ǰ� ����", "����ȣ", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("�ڹ��� ����", "���ü�", "978-89-01-14077-4", 2019));
        }
	public static void testFindBook(Library library) {
		System.out.println("===== ���� �˻� �׽�Ʈ =====");
		Book searchBook = new Book();
		String isloan;
		
		System.out.println("�������� �˻� ���: ");
		searchBook = library.findBookByTitle("�ڹ��� ����");
		if(searchBook != null) {
			if(searchBook.isAvailable()) {
				isloan = "����";
			} else {
				isloan = "���� ��";
			}
			System.out.println("å ����: " + searchBook.getTitle() + "\t����: " + searchBook.getAuthor() + "\tISBN: " + searchBook.getIsbn() + "\t���ǳ⵵: " + searchBook.getPublishYear() + "\t���� ���� ����: " + isloan);
		} else { System.out.println("ã�� ������ �����ϴ�."); }
		List<Book> searchBook2 = new ArrayList<>();
		System.out.println("���ڷ� �˻� ���: ");
		searchBook2 = library.findBooksByAuthor("Robert C. Martin");
		if(!searchBook2.isEmpty()) {
			for(Book search: searchBook2) {
				if(search.isAvailable()) {
					isloan = "����";
				} else {
					isloan = "���� ��";
				}
				System.out.println("å ����: " + search.getTitle() + "\t����: " + search.getAuthor() + "\tISBN: " + search.getIsbn() + "\t���ǳ⵵: " + search.getPublishYear() + "\t���� ���� ����: " + isloan);
			}
		} else { System.out.println("ã�� ������ �����ϴ�."); }
	}
	public static void testCheckOut(Library library) {
		String isbnBook = "978-89-01-14077-4";
		System.out.println("===== ���� ���� �׽�Ʈ =====");
		if(library.checkOutBook(isbnBook)) {
			System.out.println("���� ���� ����!");
			System.out.println("����� ���� ����: ");
			
			String isloan;
			Book searchBook = new Book();
			searchBook = library.findBookByISBN(isbnBook);
			if(searchBook != null) {
				if(searchBook.isAvailable()) {
					isloan = "����";
				} else {
					isloan = "���� ��";
				}
				System.out.println("å ����: " + searchBook.getTitle() + "\t����: " + searchBook.getAuthor() + "\tISBN: " + searchBook.getIsbn() + "\t���ǳ⵵: " + searchBook.getPublishYear() + "\t���� ���� ����: " + isloan);
			}
		} else { System.out.println("�ش� ������ ���� �����̰ų� �̹� ���� ���Դϴ�!"); }
		System.out.println("\n������ ���� ����:");
		System.out.println("��ü ���� ��: " + library.getTotalBooks());
		System.out.println("���� ���� ���� ��: " + library.getAvailableBooksCount());
		System.out.println("���� ���� ���� ��: " + library.getBorrowedBooksCount());
	}
	public static void testReturn(Library library) {
		String isbnBook = "978-89-01-14077-4";
		System.out.println("===== ���� �ݳ� �׽�Ʈ =====");
		if(library.returnBook(isbnBook)) {
			System.out.println("���� �ݳ� ����!");
			System.out.println("�ݳ��� ���� ����: ");
			
			String isloan;
			Book searchBook = new Book();
			searchBook = library.findBookByISBN(isbnBook);
			if(searchBook != null) {
				if(searchBook.isAvailable()) {
					isloan = "����";
				} else {
					isloan = "���� ��";
				}
				System.out.println("å ����: " + searchBook.getTitle() + "\t����: " + searchBook.getAuthor() + "\tISBN: " + searchBook.getIsbn() + "\t���ǳ⵵: " + searchBook.getPublishYear() + "\t���� ���� ����: " + isloan);
			}
		} else { System.out.println("ã�� ������ �����ϴ�. �ٽ� �õ��� �ּ���!"); }
		System.out.println("\n������ ���� ����:");
		System.out.println("��ü ���� ��: " + library.getTotalBooks());
		System.out.println("���� ���� ���� ��: " + library.getAvailableBooksCount());
		System.out.println("���� ���� ���� ��: " + library.getBorrowedBooksCount());
	}
	public static void displayAvailableBooks(Library library) {
		System.out.println("===== ���� ������ ���� ��� =====");
		List<Book> books = new ArrayList<>();
		
		String isloan;
		books = library.getAvailableBooks(); // library.getAllBooks(); ��ü ��� 
		for(Book book:books) {
			if(book != null) {
				if(book.isAvailable()) {
					isloan = "����";
				} else {
					isloan = "���� ��";
				}
				System.out.println("å ����: " + book.getTitle() + "\t����: " + book.getAuthor() + "\tISBN: " + book.getIsbn() + "\t���ǳ⵵: " + book.getPublishYear() + "\t���� ���� ����: " + isloan);
				System.out.println("------------------------");
			}
		}
	}
}
