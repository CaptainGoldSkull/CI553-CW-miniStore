package clients.backDoor;

import clients.customer.SearchName;

/**
 * The BackDoor Controller
 */

public class BackDoorController
{
  private BackDoorModel model = null;
  private BackDoorView  view  = null;
  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public BackDoorController( BackDoorModel model, BackDoorView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   * Query interaction from view
   * @param pn The product number to be checked
   */
  public void doQuery( String pn )
  {
	  if (pn.length() > 1) {
		  try {
			  Integer.parseInt(pn);
		  } catch(NumberFormatException e){
			  SearchName nameSearch = new SearchName();
			  pn = nameSearch.getNumFromName(nameSearch,pn);
		  }
		  if (pn != null) {
			  model.doQuery(pn);
		  }  
	  }
  }
 
  /**
   * RStock interaction from view
   * @param pn       The product number to be re-stocked
   * @param quantity The quantity to be re-stocked
   */
  public void doRStock( String pn, String quantity )
  {
	  if (pn != " ") {
		  try {
			  Integer.parseInt(pn);
		  } catch(NumberFormatException e){
			  SearchName nameSearch = new SearchName();
			  pn = nameSearch.getNumFromName(nameSearch,pn);
		  }
		  if (pn != null) {
			  //model.doCheck(pn);
			  model.doRStock(pn, quantity);
		  }
	  }
    
  }

  /**
   * Clear interaction from view
   */
  public void doClear()
  {
    model.doClear();
  }

  
}

