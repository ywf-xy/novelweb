package cn.xy.novelwebproject.utils;

public class PrintTest {
		public static void main(String[] args) throws InterruptedException {
				System.out.printf("下载进度：[");
				int j = 0;
				for (int i = 0; i < 100; i++) {
						j++;
						System.out.print("*");
						for (int k = 0; k < 100 - j; k++) {
								System.out.print("=");
						}
						System.out.print("]");
						String str = " " + j + "%" + 100;
						System.out.print(str);
						Thread.sleep(100);
						if (j != 100) {
								for (int k = 0; k < 100 - j + str.length() + 1; k++) {
										System.out.print("\b");
								}
						}

				}

		}
}
