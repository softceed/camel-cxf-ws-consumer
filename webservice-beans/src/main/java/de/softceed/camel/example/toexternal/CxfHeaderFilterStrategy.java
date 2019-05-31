package de.softceed.camel.example.toexternal;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultHeaderFilterStrategy;

/**
 * Configuration of CXF header filtering
 */
public class CxfHeaderFilterStrategy extends DefaultHeaderFilterStrategy {

  @Override
  public boolean applyFilterToCamelHeaders(String headerName, Object headerValue, Exchange exchange) {
    return true;
  }
}
