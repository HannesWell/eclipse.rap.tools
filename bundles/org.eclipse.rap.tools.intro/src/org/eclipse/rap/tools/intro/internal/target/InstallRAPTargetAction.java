/*******************************************************************************
 * Copyright (c) 2007, 2013 Innoopract Informationssysteme GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Innoopract Informationssysteme GmbH - initial API and implementation
 *    EclipseSource - ongoing development
 ******************************************************************************/
package org.eclipse.rap.tools.intro.internal.target;

import java.text.MessageFormat;

import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.Action;
import org.eclipse.rap.tools.intro.internal.ErrorUtil;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.statushandlers.StatusManager;

public final class InstallRAPTargetAction extends Action {

  private static final String INSTALL_COMMAND = "org.eclipse.rap.tools.intro.installTarget"; //$NON-NLS-1$

  public void run() {
    IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    IHandlerService handlerService
      = ( IHandlerService )workbenchWindow.getService( IHandlerService.class );
    try {
      handlerService.executeCommand( INSTALL_COMMAND, null );
    } catch( CommandException e ) {
      handleException( e );
    }
  }

  private static void handleException( CommandException e ) {
    String text = IntroMessages.InstallRAPTargetAction_FailedExecuteCommand;
    Object[] args = new Object[] { INSTALL_COMMAND };
    String msg = MessageFormat.format( text, args );
    IStatus status  = ErrorUtil.createErrorStatus( msg, e );
    StatusManager statusManager = StatusManager.getManager();
    statusManager.handle( status, StatusManager.LOG | StatusManager.SHOW );
  }

}
