package ca.mcgill.ecse321.scs.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;

import jakarta.persistence.Entity;

// line 16 "model.ump"
// line 110 "model.ump"
@Entity
public class Owner extends Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Owner() {
  }

  public Owner(int aAccountId, Date aCreationDate, String aName, String aEmail, String aPassword, byte[] image)
  {
    super(aAccountId, aCreationDate, aName, aEmail, aPassword, image);
  }

  //------------------------
  // INTERFACE
  //------------------------



public void delete()
  {
    super.delete();
  }

}