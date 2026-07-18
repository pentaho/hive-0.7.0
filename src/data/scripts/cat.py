# ******************************************************************************
#
# Pentaho
#
# Copyright (C) 2026 by Pentaho Canada Inc. : http://www.pentaho.com
#
# Use of this software is governed by the Business Source License included
# in the LICENSE.TXT file.
#
# Change Date: 2030-06-15
# ******************************************************************************
import sys, re
import datetime
import os

table_name=None
if os.environ.has_key('hive_streaming_tablename'):
  table_name=os.environ['hive_streaming_tablename']

for line in sys.stdin:
  print line
  print >> sys.stderr, "dummy"
