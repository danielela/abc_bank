package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    
    /**
     * 
     * @param account
     * @return
     * 
     * A customer can Open an account. If a customer tries to open an account type which he already has
     * Then it will not be allowed
     * 
     */
    public Customer openAccount(Account account) {
    	
    	System.out.println("The number of accounts="+this.getNumberOfAccounts()+" This name="+this.getName());
    	/* Check if this Customer already has the same account type
    	 * The overriden equals method in the Account class will check this based on the accountType
    	 */
    	if(!this.accounts.contains(account)){
    		this.accounts.add(account);
    	}else{
    		//Action to be taken when the account is duplicate
    		System.out.println("the user already has this account"+account.getAccountType());
    	}
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        
        System.out.println("The total balance of "+this.getName());
        return total;
    }
    
    public void transferFundWithinAcc(int fromAccType, int toAccType, double amount){
    	if(getNumberOfAccounts()>=2){
    		Account fromAcc=accounts.get(fromAccType);
    		fromAcc.withdraw(amount);
    		
    		Account toAcc=accounts.get(toAccType);
    		toAcc.deposit(amount);
    	}
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions(true);
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
