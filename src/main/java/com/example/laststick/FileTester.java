package com.example.laststick;
import org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileTester {

    @Test
    public void test1() throws FileNotFoundException {
        File f1 =new File("SaveProgress.txt");
        Scanner sc = new Scanner(f1);


        assertTrue(sc.hasNext());
    }

}
