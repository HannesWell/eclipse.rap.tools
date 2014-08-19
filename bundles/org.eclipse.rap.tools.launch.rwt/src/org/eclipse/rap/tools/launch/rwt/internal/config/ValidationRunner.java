/*******************************************************************************
 * Copyright (c) 2011. 2014 Rüdiger Herrmann and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rüdiger Herrmann - initial API and implementation
 *    EclipseSource - ongoing development
 ******************************************************************************/
package org.eclipse.rap.tools.launch.rwt.internal.config;

import org.eclipse.core.runtime.IStatus;


public class ValidationRunner {

  private final RWTLaunchConfig config;
  private ValidationResult validationResult;

  public ValidationRunner( RWTLaunchConfig config ) {
    this.config = config;
    this.validationResult = new ValidationResult();
  }

  public void validate() {
    validationResult = new ValidationResult();
    Validator[] validators = createValidators();
    for( int i = 0; i < validators.length; i++ ) {
      validators[ i ].validate();
    }
  }

  public IStatus[] getErrors() {
    return validationResult.getErrors();
  }

  public IStatus[] getWarnings() {
    return validationResult.getWarnings();
  }

  private Validator[] createValidators() {
    return new Validator[] {
      new ProjectValidator( config, validationResult ),
      new ApplicationClassValidator( config, validationResult ),
      new WebXmlLocationValidator( config, validationResult ),
      new ServletPathValidator( config, validationResult ),
      new ContextPathValidator( config, validationResult )
    };
  }
}
