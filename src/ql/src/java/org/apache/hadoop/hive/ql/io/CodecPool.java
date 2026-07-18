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


package org.apache.hadoop.hive.ql.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.Decompressor;

/**
 * A global compressor/decompressor pool used to save and reuse (possibly
 * native) compression/decompression codecs.
 */
public final class CodecPool {
  private static final Log LOG = LogFactory.getLog(CodecPool.class);

  /**
   * A global compressor pool used to save the expensive
   * construction/destruction of (possibly native) decompression codecs.
   */
  private static final Map<Class<Compressor>, List<Compressor>> COMPRESSOR_POOL =
      new HashMap<Class<Compressor>, List<Compressor>>();

  /**
   * A global decompressor pool used to save the expensive
   * construction/destruction of (possibly native) decompression codecs.
   */
  private static final Map<Class<Decompressor>, List<Decompressor>> DECOMPRESSOR_POOL =
      new HashMap<Class<Decompressor>, List<Decompressor>>();

  private static <T> T borrow(Map<Class<T>, List<T>> pool,
      Class<? extends T> codecClass) {
    T codec = null;

    // Check if an appropriate codec is available
    synchronized (pool) {
      if (pool.containsKey(codecClass)) {
        List<T> codecList = pool.get(codecClass);

        if (codecList != null) {
          synchronized (codecList) {
            if (!codecList.isEmpty()) {
              codec = codecList.remove(codecList.size() - 1);
            }
          }
        }
      }
    }

    return codec;
  }

  private static <T> void payback(Map<Class<T>, List<T>> pool, T codec) {
    if (codec != null) {
      Class<T> codecClass = (Class<T>) codec.getClass();
      synchronized (pool) {
        if (!pool.containsKey(codecClass)) {
          pool.put(codecClass, new ArrayList<T>());
        }

        List<T> codecList = pool.get(codecClass);
        synchronized (codecList) {
          codecList.add(codec);
        }
      }
    }
  }

  /**
   * Get a {@link Compressor} for the given {@link CompressionCodec} from the
   * pool or a new one.
   * 
   * @param codec
   *          the <code>CompressionCodec</code> for which to get the
   *          <code>Compressor</code>
   * @return <code>Compressor</code> for the given <code>CompressionCodec</code>
   *         from the pool or a new one
   */
  public static Compressor getCompressor(CompressionCodec codec) {
    Compressor compressor = borrow(COMPRESSOR_POOL, codec.getCompressorType());
    if (compressor == null) {
      compressor = codec.createCompressor();
      LOG.info("Got brand-new compressor");
    } else {
      LOG.debug("Got recycled compressor");
    }
    return compressor;
  }

  /**
   * Get a {@link Decompressor} for the given {@link CompressionCodec} from the
   * pool or a new one.
   * 
   * @param codec
   *          the <code>CompressionCodec</code> for which to get the
   *          <code>Decompressor</code>
   * @return <code>Decompressor</code> for the given
   *         <code>CompressionCodec</code> the pool or a new one
   */
  public static Decompressor getDecompressor(CompressionCodec codec) {
    Decompressor decompressor = borrow(DECOMPRESSOR_POOL, codec
        .getDecompressorType());
    if (decompressor == null) {
      decompressor = codec.createDecompressor();
      LOG.info("Got brand-new decompressor");
    } else {
      LOG.debug("Got recycled decompressor");
    }
    return decompressor;
  }

  /**
   * Return the {@link Compressor} to the pool.
   * 
   * @param compressor
   *          the <code>Compressor</code> to be returned to the pool
   */
  public static void returnCompressor(Compressor compressor) {
    if (compressor == null) {
      return;
    }
    compressor.reset();
    payback(COMPRESSOR_POOL, compressor);
  }

  /**
   * Return the {@link Decompressor} to the pool.
   * 
   * @param decompressor
   *          the <code>Decompressor</code> to be returned to the pool
   */
  public static void returnDecompressor(Decompressor decompressor) {
    if (decompressor == null) {
      return;
    }
    decompressor.reset();
    payback(DECOMPRESSOR_POOL, decompressor);
  }

  private CodecPool() {
    // prevent instantiation
  }
}
