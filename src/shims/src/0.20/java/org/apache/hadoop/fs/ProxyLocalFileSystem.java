/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2010 - 2026 by Pentaho Canada Inc. : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2030-06-15
 ******************************************************************************/


package org.apache.hadoop.fs;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.util.Progressable;

/****************************************************************
 * A Proxy for LocalFileSystem
 *
 * Serves uri's corresponding to 'pfile:///' namespace with using
 * a LocalFileSystem 
 *****************************************************************/

public class ProxyLocalFileSystem extends FilterFileSystem {

  protected LocalFileSystem localFs;

  public ProxyLocalFileSystem() {
    localFs = new LocalFileSystem();
  }

  public ProxyLocalFileSystem(FileSystem fs) {
    throw new RuntimeException ("Unsupported Constructor");
  }

  @Override
  public void initialize(URI name, Configuration conf) throws IOException {
    // create a proxy for the local filesystem
    // the scheme/authority serving as the proxy is derived
    // from the supplied URI

    String scheme = name.getScheme();
    String authority = name.getAuthority() != null ? name.getAuthority() : "";
    String proxyUriString = name + "://" + authority + "/";
    fs = new ProxyFileSystem(localFs, URI.create(proxyUriString));

    fs.initialize(name, conf);
  }
}
