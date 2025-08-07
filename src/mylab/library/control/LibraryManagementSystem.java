package mylab.library.control;

import java.util.ArrayList;
import java.util.List;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

public class LibraryManagementSystem {

	public static void main(String[] args) {
		// 도서관 객체를 생성
		Library library = new Library("중앙 도서관");
		// 샘플 도서를 추가
		addSampleBooks(library);
		// 도서관 정보를 출력
		System.out.println("====" + library.getName() + "====");
		System.out.println("전체 도서 수: " + library.getTotalBooks());
		System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
		System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
		
		testFindBook(library);
		testCheckOut(library);
		testReturn(library);
		displayAvailableBooks(library);
	}
	
	private static void addSampleBooks(Library library) {
        library.addBook(new Book("자바 프로그래밍", "김자바", "978-89-01-12345-6", 2022));
        library.addBook(new Book("객체지향의 사실과 오해", "조영호", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("자바의 정석", "남궁성", "978-89-01-14077-4", 2019));
        }
	public static void testFindBook(Library library) {
		System.out.println("===== 도서 검색 테스트 =====");
		Book searchBook = new Book();
		String isloan;
		
		System.out.println("제목으로 검색 결과: ");
		searchBook = library.findBookByTitle("자바의 정석");
		if(searchBook != null) {
			if(searchBook.isAvailable()) {
				isloan = "가능";
			} else {
				isloan = "대출 중";
			}
			System.out.println("책 제목: " + searchBook.getTitle() + "\t저자: " + searchBook.getAuthor() + "\tISBN: " + searchBook.getIsbn() + "\t출판년도: " + searchBook.getPublishYear() + "\t대출 가능 여부: " + isloan);
		} else { System.out.println("찾는 도서가 없습니다."); }
		List<Book> searchBook2 = new ArrayList<>();
		System.out.println("저자로 검색 결과: ");
		searchBook2 = library.findBooksByAuthor("Robert C. Martin");
		if(!searchBook2.isEmpty()) {
			for(Book search: searchBook2) {
				if(search.isAvailable()) {
					isloan = "가능";
				} else {
					isloan = "대출 중";
				}
				System.out.println("책 제목: " + search.getTitle() + "\t저자: " + search.getAuthor() + "\tISBN: " + search.getIsbn() + "\t출판년도: " + search.getPublishYear() + "\t대출 가능 여부: " + isloan);
			}
		} else { System.out.println("찾는 도서가 없습니다."); }
	}
	public static void testCheckOut(Library library) {
		String isbnBook = "978-89-01-14077-4";
		System.out.println("===== 도서 대출 테스트 =====");
		if(library.checkOutBook(isbnBook)) {
			System.out.println("도서 대출 성공!");
			System.out.println("대출된 도서 정보: ");
			
			String isloan;
			Book searchBook = new Book();
			searchBook = library.findBookByISBN(isbnBook);
			if(searchBook != null) {
				if(searchBook.isAvailable()) {
					isloan = "가능";
				} else {
					isloan = "대출 중";
				}
				System.out.println("책 제목: " + searchBook.getTitle() + "\t저자: " + searchBook.getAuthor() + "\tISBN: " + searchBook.getIsbn() + "\t출판년도: " + searchBook.getPublishYear() + "\t대출 가능 여부: " + isloan);
			}
		} else { System.out.println("해당 도서는 없는 도서이거나 이미 대출 중입니다!"); }
		System.out.println("\n도서관 현재 상태:");
		System.out.println("전체 도서 수: " + library.getTotalBooks());
		System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
		System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
	}
	public static void testReturn(Library library) {
		String isbnBook = "978-89-01-14077-4";
		System.out.println("===== 도서 반납 테스트 =====");
		if(library.returnBook(isbnBook)) {
			System.out.println("도서 반납 성공!");
			System.out.println("반납된 도서 정보: ");
			
			String isloan;
			Book searchBook = new Book();
			searchBook = library.findBookByISBN(isbnBook);
			if(searchBook != null) {
				if(searchBook.isAvailable()) {
					isloan = "가능";
				} else {
					isloan = "대출 중";
				}
				System.out.println("책 제목: " + searchBook.getTitle() + "\t저자: " + searchBook.getAuthor() + "\tISBN: " + searchBook.getIsbn() + "\t출판년도: " + searchBook.getPublishYear() + "\t대출 가능 여부: " + isloan);
			}
		} else { System.out.println("찾는 도서가 없습니다. 다시 시도해 주세요!"); }
		System.out.println("\n도서관 현재 상태:");
		System.out.println("전체 도서 수: " + library.getTotalBooks());
		System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
		System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
	}
	public static void displayAvailableBooks(Library library) {
		System.out.println("===== 대출 가능한 도서 목록 =====");
		List<Book> books = new ArrayList<>();
		
		String isloan;
		books = library.getAvailableBooks(); // library.getAllBooks(); 전체 목록 
		for(Book book:books) {
			if(book != null) {
				if(book.isAvailable()) {
					isloan = "가능";
				} else {
					isloan = "대출 중";
				}
				System.out.println("책 제목: " + book.getTitle() + "\t저자: " + book.getAuthor() + "\tISBN: " + book.getIsbn() + "\t출판년도: " + book.getPublishYear() + "\t대출 가능 여부: " + isloan);
				System.out.println("------------------------");
			}
		}
	}
}
