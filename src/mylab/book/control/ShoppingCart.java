package mylab.book.control;

import java.util.ArrayList;
import java.util.List;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class ShoppingCart {
	private List<Publication> items;
	
	public ShoppingCart() {
		super();
		items = new ArrayList<Publication>();
	}

	public static void main(String[] args) {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		shoppingCart.addItem(new Magazine("마이크로소프트", "2007-10-01", 328, 9900, "매월"));
		shoppingCart.addItem(new Magazine("경영과컴퓨터", "2007-10-03", 316, 9000, "매월"));
		shoppingCart.addItem(new Novel("빠삐용", "2007-07-01", 396, 9800, "베르나르베르베르", "현대소설"));
		shoppingCart.addItem(new Novel("남한산성", "2007-04-14", 383, 11000, "김훈", "대하소설"));
		shoppingCart.addItem(new ReferenceBook("실용주의프로그래머", "2007-01-14", 496, 25000, "소프트웨어공학"));
//		shoppingCart.addItem(new Novel("소년이온다", "2014-05-01", 216, 15000, "한강", "장편소설"));
//		shoppingCart.addItem(new Novel("작별하지않는다", "2021-09-09", 332, 15120, "한강", "장편소설"));
		
		shoppingCart.displayCart();
		
		shoppingCart.printStatistics();
		
		shoppingCart.removeItem("빠삐용");
		
		shoppingCart.displayCart();
	}
	
	public void addItem(Publication item) { 
		items.add(item);
		System.out.println(item.getTitle() + "이(가) 장바구니에 추가되었습니다."); 
    }

	public boolean removeItem(String title) { 
		for (int i = 0; i < items.size(); i++) { 
			if (items.get(i).getTitle().equals(title)) { 
				Publication removed = items.remove(i); 
				System.out.println(removed.getTitle() + "이(가) 장바구니에서 제거되었습니다.");
				return true; 
            }
		}
		System.out.println("해당 제목의 출판물을 찾을 수 없습니다."); 
		return false;
	}
	
	public void displayCart() {
		int count = 1;
		System.out.println("====== 장바구니 내용 ======");
		for (Publication item : items) {
			System.out.println(String.format("%d. %s - %,d원", count, item.getTitle(), item.getPrice()));
			count++;
		}
		int totalPrice = calculateTotalPrice();
		int discountedPrice = calculateDiscountedPrice();
		System.out.println(String.format("총 가격: %,d원", totalPrice));
		System.out.println(String.format("할인 적용 가격: %,d원", discountedPrice));
	}
	
	public int calculateTotalPrice() {
		int total = 0;
		for (Publication item : items) {
	            total += item.getPrice();
		}
		return total;
	}
	
	public int calculateDiscountedPrice() { 
		int total = 0;
		for (Publication item : items) {  
			if (item instanceof Magazine) { 
				total += item.getPrice() * 0.9;
	        } else if (item instanceof Novel) { 
	            total += item.getPrice() * 0.85;
	        } else if (item instanceof ReferenceBook) { 
	            total += item.getPrice() * 0.8;
	        } else { 
	            total += item.getPrice();
	        } 
		}
		return total; 
	}
	
	public void printStatistics() {
		int magazineCount = 0;
	    int novelCount = 0; 
	    int referenceBookCount = 0;
	    for (Publication item : items) {
	    	if (item instanceof Magazine) {
	    		magazineCount++; 
	    	} else if (item instanceof Novel) {
	    		novelCount++; 
	    	} else if (item instanceof ReferenceBook) {
	    		referenceBookCount++; 
	    	}
	    }
	    System.out.println("====== 장바구니 통계 ======");
	    System.out.println("잡지: " + magazineCount + "권");
	    System.out.println("소설: " + novelCount + "권");
	    System.out.println("참고서: " + referenceBookCount + "권");
	    System.out.println("총 출판물: " + items.size() + "권");
	}
	
}