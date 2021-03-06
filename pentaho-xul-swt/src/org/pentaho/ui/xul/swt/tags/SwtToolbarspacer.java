/*!
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2002-2013 Pentaho Corporation..  All rights reserved.
 */

package org.pentaho.ui.xul.swt.tags;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.components.XulToolbarspacer;
import org.pentaho.ui.xul.dom.Element;
import org.pentaho.ui.xul.swt.AbstractSwtXulContainer;

public class SwtToolbarspacer extends AbstractSwtXulContainer implements XulToolbarspacer {

  private ToolItem spacer;
  private ToolBar toolbar;
  private final int MARGIN_VALUE = 3;

  public SwtToolbarspacer( Element self, XulComponent parent, XulDomContainer domContainer, String tagName ) {
    super( "toolbarspacer" );

    toolbar = (ToolBar) parent.getManagedObject();
    spacer = new ToolItem( toolbar, SWT.SEPARATOR );
    Composite box = new Composite( toolbar, SWT.NONE );
    box.setBackgroundMode( SWT.INHERIT_DEFAULT );
    spacer.setControl( box );
    setManagedObject( spacer );
  }

  public void setWidth( int width ) {
    super.setWidth( width );
    spacer.setWidth( width );
  }

  public void setFlex( int flex ) {
    super.setFlex( flex );

    if ( getFlex() > 0 ) {
      // only support one flexible spacer per toolbar for now.
      toolbar.addControlListener( new ControlAdapter() {

        @Override
        public void controlResized( ControlEvent arg0 ) {
          recalculateSize();
        }

      } );

    }
  }

  void recalculateSize() {
    int totalWidth = toolbar.getBounds().width;
    int childTotalWidth = 0;
    for ( ToolItem item : toolbar.getItems() ) {
      if ( item != spacer ) {
        childTotalWidth += item.getBounds().width + MARGIN_VALUE;
      }
    }
    spacer.setWidth( Math.max( 0, totalWidth - childTotalWidth ) );
  }

}
