package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public abstract class dataBase {
	
	
	
	public static void fileWrite(String filename, ArrayList<student> users) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public static ArrayList<student> fileRead(String filename) {
		ArrayList<student> users = new ArrayList<>();
		try (FileInputStream fileIn = new FileInputStream(filename);
		    ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
		    users = (ArrayList<student>) objectIn.readObject();
		} catch (IOException | ClassNotFoundException e) {
		    fileWrite(filename, users);
		}
		return users;
    }
	
	public static void writeGames(ArrayList<game> games, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(games);
        } catch (IOException e) {
        	e.getCause();
        }
    }

    @SuppressWarnings("unchecked")
	public static ArrayList<game> readGames(String fileName) {
        ArrayList<game> games = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            games = (ArrayList<game>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            writeGames(games, fileName);
            return games;
        }
        return games;
    }
	
	
	public static void updateStudent(student s, String filename) {
		ArrayList<student> students = fileRead(filename);
		for(student st : students) {
			if(st.getName().equals(s.getName())) {
				st.setTotalScore(s.getTotalScore());
				st.setGames(s.getGames());
				fileWrite(filename, students);
				return;
			}
		}
	}
	
	private static StringBuilder addData(StringBuilder excelContent, Object[][] data) {
		
		for (Object[] rowData : data) {
            for (Object field : rowData) {
                excelContent.append(field).append("\t");
            }
            excelContent.append("\n");
        }
		
		return excelContent;
	}
	
	private static StringBuilder addDataSingle(StringBuilder excelContent, Object[] data) {
		
        for (Object field : data) {
            excelContent.append(field).append("\t");
        }
        excelContent.append("\n");
		
		return excelContent;
	}
	
	public static boolean ExcelExporter (student s, game g, String filename, int mode) {
        String filePath = filename;
        
        try (FileOutputStream fileOut = new FileOutputStream(filePath); 
        	OutputStreamWriter writer = new OutputStreamWriter(fileOut, StandardCharsets.UTF_8)) {
        	StringBuilder excelContent = new StringBuilder();
        	
        	
        	
        	Object[] title  = {"a*b",  "Ogrenci Cevap", "Dogru Cevap", "Dogru/Yanlis", "Baslangic Zamani", "Bitis Zamani"};
        	Object[] gameTitle  = {"Oyun Adi", "Oyun Suresi", "", "", "Baslangic", "Bitis"};
        	Object[] altSatir = {""};
        	
	        if(mode == 1) {
	        	// öğrencinin bütün oyun verilerini dışa aktar.
	        	Object data[][] = {
	                    {"Ad", "nickname", "Total Skor", "Toplam Oyun Sayisi"},
	                    {s.getName(), s.getNickname(), s.getTotalScore(), s.getGames().size()}
	            };
	        	excelContent = addData(excelContent, data);
	        	for(game game : s.getGames()) {
	        		excelContent = addDataSingle(excelContent, altSatir);
	        		Object[] gamename = {game.getName(), game.getTime(), "", "", game.getStart(), game.getEnd()};
	        		excelContent = addDataSingle(excelContent, gameTitle);
	        		excelContent = addDataSingle(excelContent, gamename);
	        		excelContent = addDataSingle(excelContent, title);
	        		for(question q : game.getQuests()) {
	        			Object[] row = {q.getA() + "*" + q.getB(), q.getStudentAns(), q.getCorrect(), q.getSituation(), q.getStartDate(), q.getEndDate()};
	        			excelContent = addDataSingle(excelContent, row);
	        		}
	        		
	        	}
	        }
	        else if(mode == 2) {
	        	// Öğrencinin tek oyun verilerini dışa aktar.
	        	Object data[][] = {
	                    {"Ad", "nickname", "Total Skor", "Toplam Oyun Sayisi"},
	                    {s.getName(), s.getNickname(), s.getTotalScore(), s.getGames().size()}
	            };
	        	excelContent = addData(excelContent, data);
        		Object[] gamename = {g.getName(), g.getTime(), "", "", g.getStart(), g.getEnd()};
        		excelContent = addDataSingle(excelContent, gameTitle);
        		excelContent = addDataSingle(excelContent, gamename);
        		excelContent = addDataSingle(excelContent, title);
        		for(game game : s.getGames()) {
        			if(game.getID() == g.getID()) {
        				for(question q : game.getQuests()) {
                			Object[] row = {q.getA() + "*" + q.getB(), q.getStudentAns(), q.getCorrect(), q.getSituation(), q.getStartDate(), q.getEndDate()};
                			excelContent = addDataSingle(excelContent, row);
                		}
        			}
        		}
        		
	        }
	        else {
	        	// Oyunu ve oyunu çözen öğrencileri kaydet.
	        	ArrayList<student> students = fileRead("students.dat");
	        	Object[] gameTitleSingle = {"Oyun Adi"};
	        	Object[] gamename = {g.getName(), g.getTime()};
        		excelContent = addDataSingle(excelContent, gameTitleSingle);
        		excelContent = addDataSingle(excelContent, gamename);
        		excelContent = addDataSingle(excelContent, altSatir);
        		
	        	for(student stu : students) {
	        		Object[][] stuData = {
	                        {"Ad", "nickname", "Total Skor", "Toplam Oyun Sayisi"},
	                        {stu.getName(), stu.getNickname(), stu.getTotalScore(), stu.getGames().size()}
	                };
	        		for(game stuGame : stu.getGames()) {
	        			if(stuGame.getID() == g.getID()) {
	        				excelContent = addData(excelContent, stuData);
	                		excelContent = addDataSingle(excelContent, title);
	                		for(question q : stuGame.getQuests()) {
	                			Object[] row = {q.getA() + "*" + q.getB(), q.getStudentAns(), q.getCorrect(), q.getSituation(), q.getStartDate(), q.getEndDate()};
	                			excelContent = addDataSingle(excelContent, row);
	                		}
	                		break;
	        			}
	        		}
	        		excelContent = addDataSingle(excelContent, altSatir);	 
	        	}
	        	
	        	
	        }
        
            fileOut.write(excelContent.toString().getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
