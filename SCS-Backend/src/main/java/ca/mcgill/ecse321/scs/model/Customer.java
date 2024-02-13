package ca.mcgill.ecse321.scs.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

// line 24 "model.ump"
// line 121 "model.ump"
@Entity
public class Customer extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Associations
  @OneToOne(optional = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private PaymentMethod paymentMethod;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(int aAccountId, Date aCreationDate, String aName, String aEmail, String aPassword)
  {
    super(aAccountId, aCreationDate, aName, aEmail, aPassword);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public PaymentMethod getPaymentMethod()
  {
    return paymentMethod;
  }

  public boolean hasPaymentMethod()
  {
    boolean has = paymentMethod != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setPaymentMethod(PaymentMethod aNewPaymentMethod)
  {
    boolean wasSet = false;
    paymentMethod = aNewPaymentMethod;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    paymentMethod = null;
    super.delete();
  }

}