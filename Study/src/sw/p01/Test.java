package sw.p01;

import java.io.File;
import java.io.FileOutputStream;

public class Test {

	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream(new File("D:\\Dev\\sample_input.txt"), false);
		
		// test case
		int t = 1;
		fos.write(String.valueOf(t).getBytes());
		fos.write("\n".getBytes());

		// number
		int a = 100000;
		fos.write(String.valueOf(a).getBytes());
		fos.write("\n".getBytes());

		// building
		for (int inx = 0; inx < a; inx++) {
			if (inx > 0)
				fos.write(" ".getBytes());
			fos.write(String.valueOf((int)(Math.random()*10000000)%100000).getBytes());
		}
		fos.write("\n".getBytes());

		// power
		for (int inx = 0; inx < a; inx++) {
			if (inx > 0)
				fos.write(" ".getBytes());
			fos.write(String.valueOf((int)(Math.random()*10000000)%100000).getBytes());
		}
		fos.write("\n".getBytes());
		
		fos.flush();
		fos.close();
	}
}
