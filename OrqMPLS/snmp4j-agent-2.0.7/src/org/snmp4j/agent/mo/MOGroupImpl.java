/*_############################################################################
  _## 
  _##  SNMP4J-Agent 2 - MOGroupImpl.java  
  _## 
  _##  Copyright (C) 2005-2012  Frank Fock (SNMP4J.org)
  _##  
  _##  Licensed under the Apache License, Version 2.0 (the "License");
  _##  you may not use this file except in compliance with the License.
  _##  You may obtain a copy of the License at
  _##  
  _##      http://www.apache.org/licenses/LICENSE-2.0
  _##  
  _##  Unless required by applicable law or agreed to in writing, software
  _##  distributed under the License is distributed on an "AS IS" BASIS,
  _##  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  _##  See the License for the specific language governing permissions and
  _##  limitations under the License.
  _##  
  _##########################################################################*/


package org.snmp4j.agent.mo;

import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.MOServer;
import org.snmp4j.smi.OctetString;
import java.util.LinkedList;
import org.snmp4j.agent.ManagedObject;
import java.util.Iterator;
import org.snmp4j.agent.DuplicateRegistrationException;
import java.util.List;

/**
 * The <code>MOGroupImpl</code> implements a simple object group.
 *
 * @author Frank Fock
 * @version 1.0
 */
public class MOGroupImpl implements MOGroup {

  private List<ManagedObject> objects = new LinkedList<ManagedObject>();

  public MOGroupImpl() {
  }

  public void registerMOs(MOServer server, OctetString context)
      throws DuplicateRegistrationException
  {
    for (ManagedObject mo : objects) {
      server.register(mo, context);
    }
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    for (ManagedObject mo : objects) {
      server.unregister(mo, context);
    }
  }

  public boolean addInstance(ManagedObject mo) {
    return objects.add(mo);
  }

  public boolean removeInstance(ManagedObject mo) {
    return objects.remove(mo);
  }

}
