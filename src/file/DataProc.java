package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataProc {
	
	File file = null;
	
	public DataProc(String fileName) {
		file = new File("C:\\tmp\\" + fileName + ".txt");
	}
	
	public void createFile() {
		try {
			if (file.createNewFile()) {
				System.out.println("파일 생성 성공!");
			} else {
				System.out.println("파일 생성 실패");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 파일 저장, 불러오기
	public void dataSave(String arr[]) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (String s: arr) {
				pw.println(s);
			}
			pw.close();
			System.out.println("성공적으로 저장하였습니다.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] dataLoad() {

		String datas[] = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			// 데이터의 갯수		
			int count = 0;
			String str = "";
			while((str = br.readLine()) != null) {
				count++;
			}
			
			// 배열 할당
			datas = new String[count];	
			
			// 파일의 처음 위치로 
			br = new BufferedReader(new FileReader(file));
			
			// 데이터 저장
			int c = 0;
			while((str = br.readLine()) != null) {
				datas[c] = str;
				c++;
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return datas;
	}
}
