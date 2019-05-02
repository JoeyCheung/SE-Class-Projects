package edu.qc.seclass;

public class BuggyClass {
	
	public static int buggyMethod1(int x, int y) {
		
		int res = 0;
		
		if (!(x % 2 == 1)) {
			x = 0;
		}
		
		res = y / x;
		
		if(!(res % 2 == 1)){
            res = y/res;
        }

        else {
            res = y/res;
        }

        return res;
	}
	
	public static int buggyMethod2(int x, int y) {
		
		int res = 0;

		if (!(x != 0)) {
            res = y/res;
        }
		
        if (x % 2 != 1) {
            res = 0;
        }

        else {
            res = y/res;
        }

        return res;
	}
	
	public static int buggyMethod3(int y) {
		int res = 0;
		
		if(! (y > -1)) {
			res = 2000;
		}

		return res/y;
	}
	
	public static void buggyMethod4(int x, int y) {
		/*	
		 	This is impossible to do because if every test that achieves 
		 	100% statement coverage reveals the fault, it must be an unavoidable
		 	division by 0
		*/
	}
	
	public static void buggyMethod5(int x, int y) {
		/*
        	This is also impossible to do as we saw in method 4, it's not possible to reach 100% statement 
        	coverage because it would have to be an unavoidable division by 0.
		*/
	}
}