package matlabjava09;

import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import com.mathworks.engine.MatlabExecutionException;
import com.mathworks.engine.MatlabSyntaxException;

public class MatlabJava09_lib {
	private double mu[], sigma[][];
	private int number;
	Future<MatlabEngine> eng;
	MatlabEngine ml;
	
	public MatlabJava09_lib(double[] mu, double[][] sigma, int number) {
		this.mu = mu;
		this.sigma = sigma;
		this.number = number;
		eng = MatlabEngine.startMatlabAsync();
		try {
			//返された Future オブジェクトの get メソッドを使用して、MatlabEngine オブジェクトが返されるのを待ちます。
			ml = eng.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//https://jp.mathworks.com/help/stats/mvnrnd.html
	public double[][] getMvnrnd() {
		double r[][] = new double[number][mu.length];
		try {
			ml.putVariableAsync("mu", mu);
			ml.putVariableAsync("sigma", sigma);
			ml.putVariableAsync("number", number);
			ml.eval("r = mvnrnd(mu,sigma,number)");
			
			Future<double[][]> futureEval_r = ml.getVariableAsync("r");
			r = futureEval_r.get();
		} catch (MatlabExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatlabSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CancellationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
}
