package file;

import java.io.File;

public class DeleteFile {

	public static void main(String[] args) {
		final String dirStr = "E:\\카메라_메모리\\70D\\2017\\09\\2017_09_02";
		
		File dir = new File(dirStr);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			int inx = 0;
			for (File file : files) {
				System.out.println((inx++) + " : " + file.getName().length() + "  ==> " + file.getName());
				
				if (file.getName().length() == 14) {
					file.delete();
				}
				if (file.getName().endsWith("CR2")) {
					file.delete();
				}
			}
		}
	}
}
