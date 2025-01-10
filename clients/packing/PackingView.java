package clients.packing;

import catalogue.Basket;
import java.util.Collections; // Import for sorting
import middle.MiddleFactory;
import middle.OrderException;
import middle.OrderProcessing;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Implements the Packing view.

 */

public class PackingView implements Observer
{
  private static final String PACKED = "Packed";

  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private final JLabel      pageTitle  = new JLabel();
  private final JLabel      theAction  = new JLabel();
  private final JTextArea   theOutput  = new JTextArea();
  private final JScrollPane theSP      = new JScrollPane();
  private final JButton     theBtPack= new JButton( PACKED );
  private final JScrollPane ordersScroll      = new JScrollPane();
  private final JPanel   	ordersContainer  = new 	JPanel();
  
  
 
  private OrderProcessing theOrder     = null;
  
  private PackingController cont= null;

  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen 
   * @param y     y-cordinate of position of window on screen  
   */
  public PackingView(  RootPaneContainer rpc, MiddleFactory mf, int x, int y )
  {
    try                                           // 
    {      
      theOrder = mf.makeOrderProcessing();        // Process order
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    Container cp         = rpc.getContentPane();    // Content Pane
    Container rootWindow = (Container) rpc;         // Root Window
    cp.setLayout(null);                             // No layout manager
    rootWindow.setSize( W, H );                     // Size of Window
    rootWindow.setLocation( x, y );
    
    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is
    
    pageTitle.setBounds( 110, 0 , 270, 20 );       
    pageTitle.setText( "Packing Bought Order" );                        
    cp.add( pageTitle );

    theBtPack.setBounds( 16, 25+60*0, 80, 40 );   // Check Button
    theBtPack.addActionListener(                   // Call back code
      e -> cont.doPacked() );
    cp.add( theBtPack );                          //  Add to canvas

    theAction.setBounds( 110, 25 , 270, 20 );       // Message area
    theAction.setText( "" );                        // Blank
    cp.add( theAction );                            //  Add to canvas

    theSP.setBounds( 140, 80, 230, 180 );           // Scrolling pane
    theOutput.setText( "" );                        //  Blank
    theOutput.setFont( f );                         //  Uses font  
    cp.add( theSP );                                //  Add to canvas
    theSP.getViewport().add( theOutput );           //  In TextArea
    rootWindow.setVisible( true );                  // Make visible
    
    ordersScroll.setBounds( 16, 80, 100, 180 );           // Scrolling pane
    cp.add( ordersScroll );                                //  Add to canvas
    ordersScroll.getViewport().add( ordersContainer );           //  In TextArea
    rootWindow.setVisible( true );                  // Make visible
    ordersContainer.setLayout(new BoxLayout(ordersContainer, BoxLayout.Y_AXIS));
    
  }
  
  public void setController( PackingController c )
  {
    cont = c;
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
  @Override
  public void update( Observable modelC, Object arg )
  {
	PackingModel model  = (PackingModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );
    
    Basket basket =  model.getBasket();
    ordersContainer.removeAll();
	try {
		List<Integer> waitingOrders = model.getWaiting();
	    Collections.sort(waitingOrders);
		for (Integer entry : waitingOrders){

			if (entry != null)
				System.out.println(entry);
				JButton orderButton = new JButton();
				orderButton.setText(entry.toString());
				orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				orderButton.addActionListener(e -> {
					try {
						model.setSpecificOrder(Integer.parseInt(orderButton.getText()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
				}});
				ordersContainer.add(orderButton);
				ordersContainer.revalidate();
			    ordersContainer.repaint();
			}
		} catch (OrderException e) {
			e.printStackTrace();
		}
		

    
    if ( basket != null )
    {
      theOutput.setText( basket.getDetails() );
    } else {
      theOutput.setText("");
    }
  }

}

