package mylab.book.control;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class StatisticsAnalyzer {
	
	public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
		Map<String, Integer> typeCount = new HashMap<>();
		Map<String, Long> typeTotalPrice = new HashMap<>();
		Map<String, Double> averagePrice = new HashMap<>();
		
		for (Publication publication : publications) {
			String typeName = getPublicationType(publication);
			typeCount.put(typeName, typeCount.getOrDefault(typeName, 0) + 1);
			typeTotalPrice.put(typeName, typeTotalPrice.getOrDefault(typeName, 0L) + publication.getPrice());
		}
        
        for (String type : typeTotalPrice.keySet()) {
        	int count = typeCount.get(type);
        	long totalPrice = typeTotalPrice.get(type);
            double avg = totalPrice / count;
            averagePrice.put(type, avg);
        }
        
        return averagePrice;
	}
	
	public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
		Map<String, Integer> typeCount = new HashMap<>();
		Map<String, Double> distribution = new HashMap<>();
		
		double totalLength = publications.length;
		
		for (Publication publication : publications) {
			String typeName = getPublicationType(publication);
			typeCount.put(typeName, typeCount.getOrDefault(typeName, 0) + 1);
		}
		
        for (String type : typeCount.keySet()) {
            int count = typeCount.get(type);
            double percentage = (count / totalLength) * 100;
            distribution.put(type, percentage);
        }
		
		return distribution;
	}
	
	public double calculatePublicationRatioByYear(Publication[] publications, String year) {
		int count = 0;
		double totalLength = publications.length;
		
		if (totalLength == 0) {
            return 0.0;
        }
		
		for (Publication publication : publications) {
			String[] publicationYear = publication.getPublishDate().split("-");
			
            if (publicationYear[0].equals(year)) {
                count++;
            }
		}
		
		return (count / totalLength) * 100;
	}
	
	private String getPublicationType(Publication pub) {
		String type = "기타";
		if (pub instanceof Magazine) {
			type = "잡지";
		} else if (pub instanceof Novel) {
			type = "소설";
		} else if (pub instanceof ReferenceBook) {
			type = "참고서";
		}
		return type;
	}
	
	public void printStatistics(Publication[] publications) {
		DecimalFormat priceFormat = new DecimalFormat("#,###원");
        DecimalFormat percentFormat = new DecimalFormat("##.##'%'");
        
		
		System.out.println("===== 출판물 통계 분석 =====");
		System.out.println("1. 타입별 평균 가격:");
		
		Map<String, Double> averagePrice = calculateAveragePriceByType(publications);
	    for (String type : averagePrice.keySet()) {
	        System.out.println("   - " + type + ": " + priceFormat.format(averagePrice.get(type)));
	    }
	    
	    System.out.println("");
	    System.out.println("2. 출판물 유형 분포:");
	    
	    Map<String, Double> distribution = calculatePublicationDistribution(publications);
	    for (String type : distribution.keySet()) {
	        System.out.println("   - " + type + ": " + percentFormat.format(distribution.get(type)));
	    }
	    
	    System.out.println("");
	    double ratio2007 = calculatePublicationRatioByYear(publications, "2007");
	    System.out.println("3. 2007년에 출판된 출판물 비율:" + percentFormat.format(ratio2007));
	}

}