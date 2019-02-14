package edu.qc.seclass;

public class MyCustomString implements MyCustomStringInterface {
	private String s;

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public void setString(String string) {
		// TODO Auto-generated method stub
		this.s = string;
		
	}

	@Override
	public int countNumbers() {
		// TODO Auto-generated method stub
		String temp = this.s;
		int count = 0;
		boolean toggle = false;
		
		if (temp == "") throw new NullPointerException("String is empty");
		
		for (int i = 0; i < temp.length(); i++) {
			if (Character.isDigit(temp.charAt(i))) {
				if (!toggle) {
					count++;
					toggle = true;
	            }
	        } else {toggle = false;}
	    }
	    return count;
	}

	@Override
	public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {
		// TODO Auto-generated method stub
		String newString = "";
		if (this.s.length() < n || this.s == "") {return "";}
		if (n <= 0) throw new IllegalArgumentException("Empty");
		if (this.s == null && n > 0 ) throw new NullPointerException("Not valid!");
		if (!startFromEnd) {
			for (int i = n - 1; i < this.s.length(); i += n) {
				newString = (newString + this.s.charAt(i));
			}
		}
		if(startFromEnd) {
			String temp = "";
			for(int i = this.s.length() - 1; i >= 0; i--) {
	            newString = newString + this.s.charAt(i);
	        }
			for (int i = n - 1; i < newString.length(); i += n) {
				temp = (temp + newString.charAt(i));
			}
			newString = "";
			for(int i = temp.length() - 1; i >= 0; i--) {
	            newString = newString + temp.charAt(i);
	        }
		}
		return newString;
	}

	@Override
	public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {
		// TODO Auto-generated method stub
		String middle = "";
		if (startPosition > endPosition) throw new IllegalArgumentException("Too big");
		if(startPosition < 1 || endPosition > this.s.length()) throw new MyIndexOutOfBoundsException("Index out of bounds");
		if (this.s == null) throw new NullPointerException("String is null");
		String firstHalf = this.s.substring(0, startPosition - 1);
		String temp = this.s.substring(startPosition - 1, endPosition);
		String secondHalf = this.s.substring(endPosition, this.s.length());
		// Fix this for loop and you're done
		int index = 0;
		String numbers = "";
		String everythingElse = "";
		System.out.println(temp);
		while (index < temp.length()) {
			if (!(Character.isDigit(temp.charAt(index)))) {
				everythingElse = everythingElse + temp.charAt(index);
				middle = (middle + everythingElse);
			}
			if (Character.isDigit(temp.charAt(index))) {
				if (temp.charAt(index) == '0') numbers = "Zero";
				if (temp.charAt(index) == '1') numbers = "One";
				if (temp.charAt(index) == '2') numbers = "Two";
				if (temp.charAt(index) == '3') numbers = "Three";
				if (temp.charAt(index) == '4') numbers = "Four";
				if (temp.charAt(index) == '5') numbers = "Five";
				if (temp.charAt(index) == '6') numbers = "Six";
				if (temp.charAt(index) == '7') numbers = "Seven";
				if (temp.charAt(index) == '8') numbers = "Eight";
				if (temp.charAt(index) == '9') numbers = "Nine";
				middle = (middle + numbers);
			}
			everythingElse = "";
			numbers = "";
			index++;
		}
		this.s = (firstHalf + middle + secondHalf);
	}
}