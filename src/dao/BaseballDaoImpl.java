package dao;

import java.util.Scanner;
import dto.BatterDto;
import dto.HumanDto;
import dto.PitcherDto;
import file.DataProc;
	
public class BaseballDaoImpl implements BaseballDao {

	Scanner sc = new Scanner(System.in);
	private HumanDto human[] = null;
	
//	private HumanDto human[] = {
//			new PitcherDto(1001, "홍길동", 24, 172.1, "투수", 10, 3, 0.234),
//			new BatterDto(1002, "성춘향", 16, 157.8, "타자", 15, 5, 0.312),
//			new BatterDto(1003, "일지매", 22, 186.9, "타자", 9, 4, 0.276),
//			new BatterDto(1004, "홍길동", 24, 190.1, "타자", 20, 6, 0.253),
//			new PitcherDto(1005, "홍두께", 25, 175.9, "투수", 9, 3, 0.356),
//			new PitcherDto(1006, "임꺽정", 27, 188.4, "투수", 12, 4, 0.493),
//	};
	
	private int counter;
	private DataProc dp;
	
	public BaseballDaoImpl() {
		human = new HumanDto[10];
		counter = 0;
		
		dp = new DataProc("baseball");
		dp.createFile();
	}
	
	@Override
	public void insert() {
		System.out.println("선수 등록입니다");
		
		System.out.println("등록할 포지션을 입력해주세요");
		System.out.print("투수(1)/타자(2) >> ");
		int position = sc.nextInt();
		
		System.out.print("번호 = ");
		int number = sc.nextInt();
		
		System.out.print("이름 = ");
		String name = sc.next();
		
		System.out.print("나이 = ");
		int age = sc.nextInt();
		
		System.out.print("신장 = ");
		double height = sc.nextDouble();
		
		HumanDto humanDto = null;
		if (position == 1) {
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			humanDto = new PitcherDto(number, name, age, height, "투수", win, lose, defence);
		} else {
			System.out.print("타수 = ");
			int batCount = sc.nextInt();
			
			System.out.print("히트수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			
			humanDto = new BatterDto(number, name, age, height, "타자", batCount, hit, hitAvg);
		}
		
		human[counter] = humanDto;
		counter++;
		
		System.out.println("등록되었습니다");
	}

	@Override
	public void delete() {
		System.out.println("선수 삭제입니다");
		
		System.out.print("삭제할 선수의 이름 = ");
		String name = sc.next();
		
		int index = search(name);
		if (index == -1) {
			System.out.println("선수 명단에 없습니다");
			return;
		} 
		// human[index] = null;
		
		human[index].setNumber(0);
		human[index].setName("");
		human[index].setAge(0);
		human[index].setHeight(0.0);
		
		if (human[index] instanceof PitcherDto) {
			PitcherDto p = (PitcherDto)human[index];
			p.setPosition("");
			p.setWin(0);
			p.setLose(0);
			p.setDefence(0.0);
			
		} else {
			BatterDto b = (BatterDto)human[index];
			b.setPosition("");
			b.setBatCount(0);
			b.setHit(0);
			b.setHitAvg(0.0);
		}
	}

	@Override
	public void select() {
		System.out.println("선수 삭제입니다");
		
		System.out.print("삭제할 선수의 이름 = ");
		String name = sc.next();
		
		// 검색된 선수를 count
		int count = 0; // 동명이인 있는지 세기
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			if (h != null && !h.getName().equals("")) {
				if (name.equals(h.getName())) {
					count++;
				}
			}
		}
		
		if (count == 0) {
			System.out.println("선수 명단에 없습니다");
			return;
		}
		
		// 검색된 선수가 1명 이상인(동명이인) 경우, 배열을 확보
		HumanDto findHuman[] = new HumanDto[count];
		int c = 0;
		
		for (int i = 0; i < findHuman.length; i++) {
			HumanDto h = human[i];
			if (h != null && !h.getName().equals("")) {
				if (name.equals(h.getName())) {
					findHuman[c] = human[i];
					c++;
				}
			}
		}
		
		System.out.println("검색된 선수 명단입니다.");
		for (HumanDto h : findHuman) {
			System.out.println(h.info());
		}
		
	}
	
	public int search(String name) {
		int index = -1;
		
		for (int i = 0; i < human.length; i++) {
			HumanDto dto = human[i];
			if (name.equals(dto.getName())) {
				index = i;
				break;
			}
		}
		
		return index;
	}

	@Override
	public void update() {
		System.out.println("선수 삭제입니다");
		
		System.out.print("수정할 선수의 이름 = ");
		String name = sc.next();
		
		int index = search(name);
		if (index == -1) {
			System.out.println("선수 명단에 없습니다");
			return;
		} 
		
		if (human[index] instanceof PitcherDto) {
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			PitcherDto p = (PitcherDto)human[index];
			p.setWin(win);
			p.setLose(lose);
			p.setDefence(defence);
			
		} else {
			System.out.print("타수 = ");
			int batCount = sc.nextInt();
			
			System.out.print("안타수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			
			BatterDto b = (BatterDto)human[index];
			b.setBatCount(batCount);
			b.setHit(hit);
			b.setHitAvg(hitAvg);
		}
		
		System.out.println("수정되었습니다");
	}

	@Override
	public void allPrint() {
		for (HumanDto h : human) {
			if(h != null && !h.getName().equals("")) {
				System.out.println(h.info());
			}
		}		
	}

	@Override
	public void batSort() {
		
		HumanDto batters[] = new HumanDto[10];
		
		// 타자만으로 배열구성
		int count = 0;
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			// 뒤 조건이 앞으로 오면, NullPointerException 뜸
			if (h != null && !h.getName().equals("")) {
				if (h instanceof BatterDto) {
					batters[count] = h;
					count++;
				}
			}
		}
		
//		for (HumanDto h : batters) {
//			if (h != null) {
//				System.out.println(h.info());
//			}
//		}
		
		// 순위(내림차 정렬) 처리
		BatterDto temp;
		for (int i = 0; i < batters.length-1; i++) {
			for (int j = i+1; j < batters.length; j++) {
				if (batters[i] != null && !batters[i].getName().equals("") 
						&& batters[j] != null && !batters[j].getName().equals("")) {
					BatterDto b1 = (BatterDto)batters[i];
					BatterDto b2 = (BatterDto)batters[j];
					
					if (b1.getHitAvg() < b2.getHitAvg()) {	// 비교는 타율로 한다
						temp = b1;
						b1 = b2;
						b2 = temp;
					}
				}
			}
		}
		
		for (int i = 0; i < batters.length; i++) {
			if (batters[i] != null) {
				System.out.println((i+1) + " 이름: " + batters[i].getName() + 
									" 타율: " + ((BatterDto)batters[i]).getHitAvg());
			}
		}
	}

	@Override
	public void pitchSort() {
		
		HumanDto pitchers[] = new HumanDto[10];
		
		// 투수만으로 배열구성
		int count = 0;
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			// 뒤 조건이 앞으로 오면, NullPointerException 뜸
			if (h != null && !h.getName().equals("")) {
				if (h instanceof BatterDto) {
					pitchers[count] = h;
					count++;
				}
			}
		}
		
		// 순위(내림차 정렬) 처리
		PitcherDto temp;
		for (int i = 0; i < pitchers.length-1; i++) {
			for (int j = i+1; j < pitchers.length; j++) {
				if (pitchers[i] != null && !pitchers[i].getName().equals("") 
						&& pitchers[j] != null && !pitchers[j].getName().equals("")) {
					PitcherDto p1 = (PitcherDto)pitchers[i];
					PitcherDto p2 = (PitcherDto)pitchers[j];
					
					if (p1.getDefence() < p2.getDefence()) {	// 비교는 타율로 한다
						temp = p1;
						p1 = p2;
						p2 = temp;
					}
				}
			}
		}
				
		for (int i = 0; i < pitchers.length; i++) {
			if (pitchers[i] != null) {
				System.out.println((i+1) + " 이름: " + pitchers[i].getName() + 
									" 타율: " + ((PitcherDto)pitchers[i]).getDefence());
			}
		}
	}

	@Override
	public void save() {
		
		// 데이터가 몇 개인지 파악
		int count = 0;
		
		for (HumanDto h: human) {
			if (h != null && !h.getName().equals("")) {
				count++;
			}
		}
		
		if (count == 0) {
			System.out.println("데이터가 없습니다.");
			return;
		}
				
		// String 배열 설정
		String savedDatas[] = new String[count];
		int i = 0;
		for (HumanDto h: human) {
			if (h != null && !h.getName().equals("")) {
				savedDatas[i] = h.toString();
				i++;
			}
		}
		
		// 저장
		dp.dataSave(savedDatas);
		System.out.println("저장되었습니다");
	}

	@Override
	public void load() {
		
		String datas[] = dp.dataLoad();
		
		for (String s : datas) {
			System.out.println(s);
		}
		
		// 읽을 데이터가 없을 때
		if (datas == null || datas.length == 0) {
			counter = 0;
			return;
		}
		
		counter = datas.length;
		
		for (int i = 0; i < datas.length; i++) {
			// 문자열 자르기
			String split[] = datas[i].split("-");
			
			// 자른 문자열을 dto에 저장하기 위한 처리
			int number = Integer.parseInt(split[0]);
			String name = split[1];
			int age = Integer.parseInt(split[2]);
			double height = Double.parseDouble(split[3]);
			String position = split[4];
			
			if (position.equals("타자")) {
				int batCount = Integer.parseInt(split[5]);
				int hit = Integer.parseInt(split[6]);
				double hitAvg = Double.parseDouble(split[7]);
				
				human[i] = new BatterDto(number, name, age, height, position, batCount, hit, hitAvg);
			} 
			else if (position.equals("투수")) {
				int win = Integer.parseInt(split[5]);
				int lose = Integer.parseInt(split[6]);
				double defence = Double.parseDouble(split[7]);
				
				human[i] = new PitcherDto(number, name, age, height, position, win, lose, defence);
			}
		}
		System.out.println("데이터를 로드하였습니다");
	}
}
