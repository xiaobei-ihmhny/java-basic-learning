package cn.tedu.jdbc.pool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

interface Phone{
	public void call();
	public void message();
}

class Iphone implements Phone{
	public void call(){
		System.out.println("手机可以打电话了");
	}
	public void message(){
		System.out.println("手机可以发短信了");
	}
}

class RingIphone extends Iphone{
	@Override
	public void call() {
		System.out.println("打电话前可以听彩铃了");
		super.call();
	}

}

class RingIphoneDecorate implements Phone{
	private Phone iphone = null;
	public RingIphoneDecorate(Phone iphone){
		this.iphone  = iphone;
	}
	@Override
	public void call() {
		System.out.println("手机可以听彩铃了~!");
		iphone.call();
	}

	@Override
	public void message() {
		iphone.message();

	}

}


public class Demo1 {
	public static void main(String[] args) {
		Iphone iphone = new Iphone();
		iphone.call();
		iphone.message();
		System.out.println("-------------------");
		Iphone ringIphone = new RingIphone();
		ringIphone.call();

		System.out.println("------------------");
		Phone ringiPhoneDecorate = new RingIphoneDecorate(iphone);
		ringiPhoneDecorate.call();

//		InputStream is = System.in;
//		InputStreamReader isr = new InputStreamReader(is);
//		BufferedReader br = new BufferedReader(isr);

	}
}
