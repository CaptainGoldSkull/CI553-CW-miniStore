package catalogue;


public class BetterBasket extends Basket
{
  private static final long serialVersionUID = 1L;
  @Override
  public boolean add( Product pr ){           
	  for (Product prInList:this){
		  if(prInList.getProductNum().equals(pr.getProductNum())) {
			  int newQuantity = pr.getQuantity() + prInList.getQuantity();
			  prInList.setQuantity(newQuantity);
			  return(true);
			  
		  }		  
	  }
	  return super.add( pr );     // Call add in ArrayList
  }
}
