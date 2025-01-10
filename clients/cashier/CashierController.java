package clients.cashier;

import clients.SearchName;

/**
 * The Cashier Controller
 */

public class CashierController
{
  private CashierModel model = null;
  private CashierView  view  = null;

  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public CashierController( CashierModel model, CashierView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   * Check interaction from view
   * @param pn The product number to be checked
   */
  public void doCheck(String pn , String Quant)
  {
	  if (pn.length() > 1) {
		  try {
			  Integer.parseInt(pn); // try and parse the pn input as a number, throws an exception if it cant
			} catch(NumberFormatException e){ // if the above line doesnt work it is a string so try and run the searchName method on it
				SearchName nameSearch = new SearchName();
				pn = nameSearch.getNumFromName(nameSearch,pn);
			}
		  try { 
			  Integer.parseInt(Quant); // Check if the quantity string is already a number e.g "1"
		  } catch(NumberFormatException e) { // if an exception above is thrown then the input isnt an integer so just add 1
			  Quant = "1";
		  }
		  if (pn != null) { // if it did find a product number
			  model.doCheck(pn, Quant);  
		  }
	  }

	   
 }

   /**
   * Buy interaction from view
   */
  public void doBuy(String quant)
  {
	  model.doBuy();

    
  }
  
   /**
   * Bought interaction from view
   */
  public void doBought()
  {
    model.doBought();
  }
}
