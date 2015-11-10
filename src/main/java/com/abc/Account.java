package com.abc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountType;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account) obj;
		if (accountType != other.accountType)
			return false;
		return true;
	}

	public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
    }
}


public void transfer(int fromAccType, int toAccType, double amount){
	
}

    public double interestEarned() {
        double amount = sumTransactions(true);
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return calculateInterest(amount , 0.001);
                else
                    return calculateInterest(1 + (amount-1000) , 0.002);

            case MAXI_SAVINGS:
            	/*
            	 * Change Maxi-Savings accounts to have an interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
            	 */
            	if(!checkWithdrawalDays())
            		return calculateInterest(amount , 0.05);
            	else
            		calculateInterest(amount , 0.001);
            	/*
                if (amount <= 1000)
                    return calculateInterest(amount , 0.02);
                if (amount <= 2000)
                    return calculateInterest(20 + (amount-1000) , 0.05);
                return calculateInterest(70 + (amount-2000) , 0.1);
                */
            default:
                return calculateInterest(amount , 0.001);
        }
    }

    private double calculateInterest(double amount, double interest){
    	return amount*interest;
    }
    
    private boolean checkWithdrawalDays(){
    	
    	/*
    	read the transaction list and get the transaction type 
    	withdraw and the last date of withdraw transaction
    	
    	Since no transaction type is captured in the Transaction bean
    	assuming the last transaction date as withdraw date
    	*/
    	
    	Transaction trans=transactions.get(transactions.size());
    	//get the transaction date
    	Date transdate=trans.transactionDate;
    	//get the curent date
    	Date today=DateProvider.getInstance().now();
    	
    	//find the difference of days
    	int days =daysBetween(transdate.getTime(), today.getTime());
    	System.out.println("the number of Dayes="+days);
    	
    	if(days<=10)
    		return true;
    	else
    		return false;
    	
    }
    
  
    /**
     * 
     * @param t1 from Date in miliSeconds
     * @param t2 to date in mili seconds
     * @return difference of days
     */
    private int daysBetween(long t1, long t2) {
        return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
    } 
    
    
    
   /* public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }
*/
    public double sumTransactions(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
