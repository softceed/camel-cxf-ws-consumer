package de.softceed.camel.example.toexternal.cxf;

import java.io.IOException;
import java.io.InputStream;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.phase.Phase;

/**
 * Logging of incoming SOAP messages
 */
public class SoapInboundLoggingInterceptor extends AbstractSoapInterceptor {

  /**
   * Constructor, placing interceptor in PRE_STREAM phase of CXF handler chain
   */
  public SoapInboundLoggingInterceptor() {
    super(Phase.PRE_STREAM);
  }

  /**
   * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
   */
  public void handleMessage(SoapMessage soapMessage) throws Fault {
    // Get the SOAP request (xml)
    try {
      InputStream inputStream = soapMessage.getContent(InputStream.class);
      CachedOutputStream outputStream = new CachedOutputStream();
      IOUtils.copy(inputStream, outputStream);
      soapMessage.setContent(InputStream.class, outputStream.getInputStream());

      outputStream.flush();
      inputStream.close();
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
