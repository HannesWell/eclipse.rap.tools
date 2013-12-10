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
package org.eclipse.rap.tools.intro.internal;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.*;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;


public final class ErrorUtil {

  public static void show( String message, Throwable throwable ) {
    final IStatus status = createErrorStatus( message, throwable );
    IntroPlugin.getDefault().getLog().log( status );
    Display display = Display.getCurrent();
    if( display == null ) {
      display = Display.getDefault();
    }
    display.asyncExec( new Runnable() {
      public void run() {
        ErrorDialog.openError( null, null, null, status );
      }
    } );
  }

  public static void log( String message, Throwable throwable ) {
    IStatus status = createErrorStatus( message, throwable );
    IntroPlugin.getDefault().getLog().log( status );
  }

  public static IStatus createErrorStatus( String message, Throwable throwable ) {
    Throwable cause = throwable;
    if( cause instanceof InvocationTargetException ) {
      cause = ( ( InvocationTargetException )cause ).getTargetException();
    }
    IStatus result;
    if( cause instanceof CoreException ) {
      result = ( ( CoreException )cause ).getStatus();
    } else {
      String statusMessage = message;
      if( statusMessage == null && cause != null ) {
        if( cause.getMessage() != null ) {
          statusMessage = cause.getMessage();
        } else {
          statusMessage = cause.toString();
        }
      }
      if( statusMessage == null ) {
        statusMessage = ""; //$NON-NLS-1$
      }
      result = new Status( IStatus.ERROR, IntroPlugin.getPluginId(), 0, statusMessage, cause );
    }
    return result;
  }

  private ErrorUtil() {
    // prevent instantiation
  }

}
