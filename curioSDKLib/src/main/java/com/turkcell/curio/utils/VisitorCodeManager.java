/*
 * Copyright (C) 2014 Turkcell
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.turkcell.curio.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

import android.content.Context;

/**
 * !!Legacy class from 8digits API. May be Changed.!!
 * 
 * @Changed Can Ciloglu
 *
 */
public class VisitorCodeManager {
  private static String sID = null;
  private static final String INSTALLATION = "INSTALLATION";
private static final String TAG = VisitorCodeManager.class.getSimpleName();

  public synchronized static String id(String trackingCode, Context context) {
      if (sID == null) {  
          File installation = new File(context.getFilesDir(), INSTALLATION + "-" + trackingCode);
          try {
              if (!installation.exists())
                  writeInstallationFile(installation);
              sID = readInstallationFile(installation);
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      } else {
    	  CurioLogger.d(TAG, "Curio installation file exists.");
      }
      return sID;
  }

  private static String readInstallationFile(File installation) throws IOException {
      RandomAccessFile f = new RandomAccessFile(installation, "r");
      byte[] bytes = new byte[(int) f.length()];
      f.readFully(bytes);
      f.close();
      return new String(bytes);
  }

  private static void writeInstallationFile(File installation) throws IOException {
      FileOutputStream out = new FileOutputStream(installation);
      String id = UUID.randomUUID().toString();
      out.write(id.getBytes());
      out.close();
  }
}
