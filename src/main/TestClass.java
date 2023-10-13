package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestClass {
    private student ahmet, veli, elif, selin, burak;
    @SuppressWarnings("unused")
    private admin husam;

    @Before
    public void setUp() {
        ArrayList<student> students = new ArrayList<student>();
        ahmet = new student("Ahmet Yalnız", "ahmo123", "pa$$");
        veli = new student("Veli Demir", "aliveli4950", "velipass");
        elif = new student("Elif Yılmaz", "bumblebee", "butterfly");
        selin = new student("Selin Kütük", "CatsAndRoses", "benSelin");
        burak = new student("Burak Ayık", "günaydın", "afiyetolsun");
        students.add(ahmet);
        students.add(elif);
        students.add(selin);
        students.add(veli);
        students.add(burak);
        husam = new admin("Hüsamettin IŞIKTAŞ", "husam", students);
    }

    @Test
    public void testStudent() {
        int score = ahmet.getTotalScore();
        assertEquals(0, score);
    }

    @Test
    public void adminLoginTest() {
        admin admin = new login().loginAdm("husam", "1234", "students.dat");
        String name = admin.getName();
        assertEquals("Hüsamettin", name);
    }

    @Test
    public void signUpTest() {
        boolean sit = new signUp().signStudent("Tuna", "tantuni", "1234", "students.dat");
        assertEquals(true, sit);
    }

    @Test
    public void questTest() {
        ArrayList<question> quests = new game(1, 5, 3).getQuests();
        int size = quests.size();
        assertEquals(3, size);
    }
}
