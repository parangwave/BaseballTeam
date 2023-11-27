package main;

import java.util.Scanner;

import dao.BaseballDao;
import dao.BaseballDaoImpl;

public class MainClass {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// menu
		BaseballDao dao = new BaseballDaoImpl();
		
		while(true) {
		System.out.println("========================= Baseball Team Menu");
		System.out.println("1.선수정보 추가");
		System.out.println("2.선수정보 삭제");
		System.out.println("3.선수정보 검색");	// 난이도 上
		System.out.println("4.선수정보 수정");
		System.out.println("5.선수정보 모두출력");
		
		System.out.println("6.타율 순위");
		System.out.println("7.방어율 순위");
		System.out.println("8.데이터 저장하기");
		System.out.println("9.데이터 불러오기");
		System.out.println("============================================");

		System.out.print("메뉴의 번호 >> ");
		
		int menuNumber = sc.nextInt();

		switch(menuNumber) {
			case 1:
				dao.insert();
				break;
			case 2:
				dao.delete();
				break;
			case 3:
				dao.select();
				break;
			case 4:
				dao.update();
				break;
			case 5:
				dao.allPrint();
				break;
			case 6:
				dao.batSort();
				break;
			case 7:
				dao.pitchSort();
				break;
			case 8:
				dao.save();
				break;
			case 9:
				dao.load();
				break;
			}
		}
	}

}
