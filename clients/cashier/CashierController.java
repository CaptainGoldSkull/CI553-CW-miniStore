package clients.cashier;

import clients.customer.SearchName;

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
			  Integer.parseInt(pn);
			} catch(NumberFormatException e){
				SearchName nameSearch = new SearchName();
				pn = nameSearch.getNumFromName(nameSearch,pn);
			}
		  if (pn != null) {
			  model.doCheck(pn, Quant);  
		  }
	  }

	   
 }

   /**
   * Buy interaction from view
   */
  public void doBuy(String quant)
  {
	  //try {
	//	  model.doBuy(Integer.parseInt(quant));
	//	} catch(NumberFormatException e){
	//		model.doBuy(1);
	//	}
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
