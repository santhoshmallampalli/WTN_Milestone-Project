package com.wipro.ccbill.entity;
import java.util.Date;

import com.wipro.ccbill.exception.InputValidationException;

public class CreditCardBill {
	private String creditCardNo;
	private String customerId;
	private Date billDate;
	private Transaction monthTransactions[];
	public CreditCardBill ()
	{
		System.out.println("please take your credit card bill");
	}
	public CreditCardBill (String creditCardNo, String customerId, Date billDate, Transaction monthTransactions[])
	{
		this.creditCardNo=creditCardNo;
		this.customerId=customerId;
		this.billDate=billDate;
		this.monthTransactions=monthTransactions;

		
	}
	
	
	public double getTotalAmount(String transactionType)
	{
		 double sum=0;
		
			
			if(!transactionType.equals("DB")&& !transactionType.equals("CR"))
			{
				return 0.0;
			}
			else
			{
				for(Transaction i:monthTransactions)
				{
					if(i.getTransactionType().equals(transactionType))
					{
						sum=sum+i.getTransactionAmount();
					}
					
				}
				return sum;
			}
			
		
	}
	
	
	public String validateData() throws InputValidationException
	{
		if(!creditCardNo.equals(null) && creditCardNo.length()==16 && !customerId.equals(null) && customerId.length() >= 6  )
		{
			return "valid";
		}
		throw new InputValidationException();
		
		
	}
	
	public double calculateBillAmount() throws InputValidationException
	{
		
			if(validateData().equals("valid"))
			{
				if(monthTransactions.length>0 && !monthTransactions.equals(null))
				{
					double db = getTotalAmount("DB");
					double cr = getTotalAmount("CR");
					double outstanding = db-cr;
					double intrest = ((outstanding*(19.9/100))/12);
					
					return intrest+outstanding;
					
				}
				
			}
			throw new InputValidationException();
			
				
		
		
		
		
	}








	

}
