package de.softceed.camel.example.toexternal.cxf;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.phase.Phase;

/**
 * Logging of outgoing SOAP messages
 */
public class SoapOutboundLoggingInterceptor extends AbstractSoapInterceptor {

  /**
   * Constructor, placing interceptor in PRE_STREAM phase of CXF handler chain
   */
  public SoapOutboundLoggingInterceptor() {
    super(Phase.PRE_STREAM);
  }

  /**
   * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
   */
  public void handleMessage(SoapMessage soapMessage) throws Fault {
    // Get SOAP request (as xml):
    OutputStream out = soapMessage.getContent(OutputStream.class);
    CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(out);

    try {
      String content = IOUtils.toString(newOut.getInputStream());
      System.out.println("OutboundMessage to external: " + content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
