package org.pentaho.ui.xul.samples;

import javax.swing.JOptionPane;

import org.pentaho.ui.xul.impl.AbstractXulEventHandler;
import org.pentaho.ui.xul.impl.XulWindowContainer;

public class SampleMenuHandler extends AbstractXulEventHandler{

  public void open(){
    this.getXulDomContainer().createMessageBox("testing...").open();
  }
  
  @Override
  public Object getData() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setData(Object data) {
    // TODO Auto-generated method stub
    
  }
}

  