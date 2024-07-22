package prj3;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.Test;

public class RangeSearchTest {

    public Input readFile(String inputName, String outputName) {
	int n = -1;
	int q = -1;
	int count = 0;
	int[][] nodes = new int[1][1];
	int[][] queries = new int[1][1];
	int[] output = new int[1];
	try {
	    File myObj = new File(inputName);
	    Scanner myReader = new Scanner(myObj);
	    while (myReader.hasNextLine()) {
		String data = myReader.nextLine();
		String[] splited = data.split(" ", 0);
		if (n == -1) {
		    n = Integer.valueOf(splited[0]);
		    q = Integer.valueOf(splited[1]);
		    nodes = new int[n][3];
		    queries = new int[q][6];
		    output = new int[q];
		} else if (count < n) {
		    nodes[count][0] = Integer.valueOf(splited[0]);
		    nodes[count][1] = Integer.valueOf(splited[1]);
		    nodes[count][2] = Integer.valueOf(splited[2]);
		    count++;
		} else {
		    queries[count - n][0] = Integer.valueOf(splited[0]);
		    queries[count - n][1] = Integer.valueOf(splited[1]);
		    queries[count - n][2] = Integer.valueOf(splited[2]);
		    queries[count - n][3] = Integer.valueOf(splited[3]);
		    queries[count - n][4] = Integer.valueOf(splited[4]);
		    queries[count - n][5] = Integer.valueOf(splited[5]);
		    count++;
		}
	    }
	    myReader.close();
	    count = 0;
	    myObj = new File(outputName);
	    myReader = new Scanner(myObj);
	    while (myReader.hasNextLine()) {
		String data = myReader.nextLine();
		String[] splited = data.split(" ", 0);
		output[count++] = Integer.valueOf(splited[0]);
	    }
	    myReader.close();
	} catch (FileNotFoundException e) {
	    System.out.println("An error occurred.");
	    e.printStackTrace();
	}
	return new Input(nodes, queries, output);
    }

    @Test
    public void test1() {
	Input input = readFile("tests/test1.in", "tests/test1.out");
	RangeSearch rs = new RangeSearch(input.nodes);
	for (int i = 0; i < input.queries.length; i++) {
	    assertEquals(input.output[i], rs.query(input.queries[i][0], input.queries[i][1], input.queries[i][2],
		    input.queries[i][3], input.queries[i][4], input.queries[i][5]));
	}
    }

    @Test
    public void test2() {
	Input input = readFile("tests/test2.in", "tests/test2.out");
	RangeSearch rs = new RangeSearch(input.nodes);
	for (int i = 0; i < input.queries.length; i++) {
	    assertEquals(input.output[i], rs.query(input.queries[i][0], input.queries[i][1], input.queries[i][2],
		    input.queries[i][3], input.queries[i][4], input.queries[i][5]));
	}
    }

    @Test
    public void test3() {
	Input input = readFile("tests/test3.in", "tests/test3.out");
	RangeSearch rs = new RangeSearch(input.nodes);
	for (int i = 0; i < input.queries.length; i++) {
	    assertEquals(input.output[i], rs.query(input.queries[i][0], input.queries[i][1], input.queries[i][2],
		    input.queries[i][3], input.queries[i][4], input.queries[i][5]));
	}
    }

    @Test
    public void test4() {
	Input input = readFile("tests/test4.in", "tests/test4.out");
	RangeSearch rs = new RangeSearch(input.nodes);
	for (int i = 0; i < input.queries.length; i++) {
	    assertEquals(input.output[i], rs.query(input.queries[i][0], input.queries[i][1], input.queries[i][2],
		    input.queries[i][3], input.queries[i][4], input.queries[i][5]));
	}
    }

    @Test
    public void test5() {
	Input input = readFile("tests/test5.in", "tests/test5.out");
	RangeSearch rs = new RangeSearch(input.nodes);
	for (int i = 0; i < input.queries.length; i++) {
	    assertEquals(input.output[i], rs.query(input.queries[i][0], input.queries[i][1], input.queries[i][2],
		    input.queries[i][3], input.queries[i][4], input.queries[i][5]));
	}
    }

    @Test
    public void test6() {
	Input input = readFile("tests/test6.in", "tests/test6.out");
	RangeSearch rs = new RangeSearch(input.nodes);
	for (int i = 0; i < input.queries.length; i++) {
	    assertEquals(input.output[i], rs.query(input.queries[i][0], input.queries[i][1], input.queries[i][2],
		    input.queries[i][3], input.queries[i][4], input.queries[i][5]));
	}
    }

    class Input {
	public int[][] nodes;
	public int[][] queries;
	public int[] output;

	public Input(int[][] nodes, int[][] queries, int[] output) {
	    this.nodes = nodes;
	    this.queries = queries;
	    this.output = output;
	}
    }

}
