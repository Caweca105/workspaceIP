package Aula01;
import java.util.Scanner;

public class Date {
	private int month;
	private int day;
	private int year;
	
	public Date(int month, int day, int year) {
		if(!isValid(month, day, year)) {
			throw new IllegalArgumentException("Data inválida!");
		}
		
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
//	Check if the function is valid
	public static boolean isValid(int month, int day, int year) {
		if (month < 1 || month > 12) {
			return false;
		}
		if (year < 0) {
			return false;
		}
		if (day < 0 || day > 31) {
			return false;
		}
		int maxDayInMonth = dayInMonth(month, year);

	    if (day < 1 || day > maxDayInMonth) {
	        return false;
	    }

		return true;
	}
	
//	Check how many days there are in each month
	public static int dayInMonth(int month, int year) {
		if (month == 4 || month == 6 || month == 9 || month == 11) return 30;
		if (month == 2) {
			if (leapYear(year)) {
				return 29;
			}
			return 28;
		}
		return 31;
	}
	
//	check if it is leap year or not
	public static boolean leapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
			return true;
		}
		return false;
	}
	
	public int month() {
		return month;
	}
	
	public int day() {
		return day;
	}
	
	public int year() {
		return year;
	}
	
//	outputs the format of the string
	public String toString() {
		return month + "/" + day + "/" + year;
	}

//	Gives me how many days there are since the beginning of the year
	public int daysSinceBeginYear()    {
        int sum = 0;
                
		// Count the days in the months before the current month
		for (int m = 1; m < month; m++) {
			sum += dayInMonth(m, year);
		}
	
		// Add the days in the current month
		sum += day;

        return sum;
    }
    
//	Gives me how many days there are until end of year
    public int daysUntilEndYear() {
		int sum = 0;
	
		// Count the days in the months after the current month
		for (int m = month + 1; m <= 12; m++) {
			sum += dayInMonth(m, year);
		}
	
		// Add the remaining days in the current month
		sum += dayInMonth(month, year) - day;
	
		return sum;
	}

//    Gives me the number of days between two dates
//	Método errado
	public int daysBetween(Date other) {
		if (this.year == other.year) {
			// Both dates are in the same year
			return Math.abs(this.daysSinceBeginYear() - other.daysSinceBeginYear());
		} else {
			// Dates are in different years
			int daysBetween = 0;
	
			// Days remaining in the first year
			daysBetween += this.daysUntilEndYear();
	
			// Days since the beginning of the second year
			daysBetween += other.daysSinceBeginYear();
	
			// Add days of the years in between
			for (int y = this.year + 1; y < other.year; y++) {
				daysBetween += leapYear(y) ? 366 : 365;
			}
			return daysBetween;
		}
	}

//	Checks if one date is before the other
	public boolean before(Date other) {
		return this.year < other.year ||
			(this.year == other.year && (this.month < other.month ||
			(this.month == other.month && this.day < other.day)));
	}

//	Checks if one date is after the other
	public boolean after(Date other) {
		return this.year > other.year ||
			(this.year == other.year && (this.month > other.month ||
			(this.month == other.month && this.day > other.day)));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// First input date
		System.out.println("Introduza o mês: ");
		int month1 = sc.nextInt();
		
		System.out.println("Introduza o dia: ");
		int day1 = sc.nextInt();
		
		System.out.println("Introduza o ano: ");
		int year1 = sc.nextInt();
		Date d = new Date(month1, day1, year1);

		// Second input date
		System.out.println("Introduza o segundo mês: ");
		int month2 = sc.nextInt();

		System.out.println("Introduza o segundo o dia: ");
		int day2 = sc.nextInt();

		System.out.println("Introduza o segundo o ano: ");
		int year2 = sc.nextInt();
		Date d2 = new Date(month2, day2, year2);

		sc.close();
		
		System.out.println("\nA data é: " + d.toString());
		System.out.println("\nA segunda data é: " + d2.toString());
		System.out.println("\nO número de dias desde o início do ano " + year1 + " é: " + d.daysSinceBeginYear());
		System.out.println("\nO número de dias até ao final do ano " + year1 + " é: " + d.daysUntilEndYear());
		System.out.println("\nO número de dias desde o início do ano " + year2 + " é: " + d2.daysSinceBeginYear());
		System.out.println("\nO número de dias até ao final do ano " + year2 + " é: " + d2.daysUntilEndYear());
		System.out.println("\nO número de dias entre a segunda data introduzida e o final do ano " + year2 + " é: " + d2.daysUntilEndYear());
		System.out.println("\nOs dias entre ambas as datas é: " + d.daysBetween(d2));
    	System.out.println("\nA data é depois da segunda data introduzida? " + d.before(d2));
    	System.out.println("\nA data é após a segunda introduzida? " + d.after(d2));
	}
}
