package parallelism;

public class NThread extends Thread {

	protected int n;
	
	public NThread(int n) {
		// TODO Auto-generated constructor stub
		
		this.n = n;
		
	}

	public NThread(Runnable arg0, int n) {
		super(arg0);
		this.n = n;
		// TODO Auto-generated constructor stub
	}

	public NThread(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NThread(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NThread(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NThread(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NThread(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public NThread(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
