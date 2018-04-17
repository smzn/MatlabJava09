package matlabjava09;

import java.util.Arrays;

public class MatlabJava09_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double mu[] = {2,3};//平均
		double sigma[][] = { { 1, 1.5 }, { 1.5, 3 }};//分散共分散行列
		int number = 100;//乱数の個数
		MatlabJava09_lib mlib = new MatlabJava09_lib(mu, sigma, number);
		double r[][] = mlib.getMvnrnd();
		System.out.println("r = " +Arrays.deepToString(r));
		
		//乱数の確認(平均値)
		double mean[] = new double[mu.length];
		for(int i = 0; i < mu.length; i++) {
			for(int j = 0; j < r.length; j++) {
				mean[i] += r[j][i];
			}
		}
		for(int i = 0; i < mu.length; i ++) mean[i] /= r.length;
		System.out.println("乱数平均値 = " +Arrays.toString(mean));
	}

}
