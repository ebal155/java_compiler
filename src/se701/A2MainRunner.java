package se701;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import japa.parser.ParseException;

public class A2MainRunner {

	public static void main(String[] args) {
		
	
		/* These tests will be testing correctness of your Semantic Analysis visitors. The marker will be using their own files here. */ 
		 
		for (int i = 1; i <= 21; i++) { 
			String file = "tests"+System.getProperty("file.separator")+"Test"+i+".javax";
			
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String flag = null;
			
			try {
				flag = br.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				A2Compiler.compile(new File(file));
				if (flag.equals("//FAIL")) {
					System.out.println(file + "...failure");
				}else{
					System.out.println(file + "...OK");
				}
				
				
			} catch (ParseException e) {
				System.err.println(file+" Parser exception... "+e.getMessage());
				e.printStackTrace();
			} catch (A2SemanticsException e) {
				if (flag.equals("//FAIL")) {
					System.out.println(file + "...OK");
					e.printStackTrace();
				}else{
					System.out.println(file + "...failure");
				}
			}  catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	
		/*
		 * This is to compile YOUR supplied sample, make sure it compiles (i.e. should not throw a ParseException or anything). It should compile, and the marker
		 * should be able to run it (so it needs to have a main() file inside it!)
		 */
//		try {
//			A2Compiler.compile(new File("src"+System.getProperty("file.separator")+"se701"+System.getProperty("file.separator")+"StudentSample.javax"));
//			System.out.println("src/se701.StudentSample compiled correctly");
//		} catch (ParseException e) {
//			System.err.println("Student file should not have any errors! ");
//			e.printStackTrace();
//		} catch (A2SemanticsException e) {
//			System.err.println("Student file should not have any errors! ");
//			e.printStackTrace();
//		}  catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}
}
