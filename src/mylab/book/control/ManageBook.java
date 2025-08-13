package mylab.book.control;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class ManageBook {

	public static void main(String[] args) {
		Publication[] publications = {
	            new Magazine("����ũ�μ���Ʈ", "2007-10-01", 328, 9900, "�ſ�"),
	            new Magazine("�濵����ǻ��", "2007-10-03", 316, 9000, "�ſ�"),
	            new Novel("���߿�", "2007-07-01", 396, 9800, "����������������", "����Ҽ�"),
	            new Novel("���ѻ꼺", "2007-04-14", 383, 11000, "����", "���ϼҼ�"),
	            new ReferenceBook("�ǿ��������α׷���", "2007-01-14", 496, 25000, "����Ʈ�������"),
	            new Novel("�ҳ��̿´�", "2014-05-01", 216, 15000, "�Ѱ�", "����Ҽ�"),
	            new Novel("�ۺ������ʴ´�", "2021-09-09", 332, 15120, "�Ѱ�", "����Ҽ�")
	        };
		
		System.out.println("==== ���� ���� ��� ====");
		int count = 1;
		for(Publication publication : publications) {
			System.out.println(count + ". " + publication.toString());
			count++;
		}
		System.out.println("");
		System.out.println("==== ���� ���� ====");
		int currentPrice = publications[2].getPrice();
		modifyPrice(publications[2]);
		System.out.println(String.format("%s ���� �� ����: %d��", publications[2].getTitle(), currentPrice));
		System.out.println(String.format("%s ���� �� ����: %d��", publications[2].getTitle(), publications[2].getPrice()));
		System.out.println(String.format("����: %d��", currentPrice-publications[2].getPrice()));
		System.out.println("");
		
		StatisticsAnalyzer statistics = new StatisticsAnalyzer();
		statistics.printStatistics(publications);
	}
	
	public static void modifyPrice(Publication publication) {
		int currentPrice = publication.getPrice(); 

		if (publication instanceof Magazine) {
			publication.setPrice((int)(currentPrice * 0.6)); // 40% ���� 
		} else if (publication instanceof Novel) {
			publication.setPrice((int)(currentPrice * 0.8)); // 20% ���� 
		} else if (publication instanceof ReferenceBook) {
			publication.setPrice((int)(currentPrice * 0.9)); // 10% ����
		} 
	}
}
