/*******************************************************************************
 * Copyright (c) 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.ui.internal.launch.rwt.tab;

import junit.framework.TestCase;

import org.eclipse.swt.widgets.Shell;

public class RWTSettingsSection_Test extends TestCase {

  private Shell shell;
  private RWTSettingsSection rwtSettingsSection;

  public void testGetName() {
    String name = rwtSettingsSection.getName();

    assertNotNull( name );
    assertTrue( name.length() > 0 );
  }

  public void testCreateControlCallsSetControl() {
    rwtSettingsSection.createControl( shell );

    assertNotNull( rwtSettingsSection.getControl() );
  }

  protected void setUp() throws Exception {
    rwtSettingsSection = new RWTSettingsSection();
    shell = new Shell();
  }

  protected void tearDown() throws Exception {
    shell.dispose();
  }
}
